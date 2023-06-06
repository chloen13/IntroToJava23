import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import java.security.*;
import java.security.cert.*;

import javax.net.ssl.*;

public class FinalProjectPanel extends JPanel
{

   private JLabel info; // to display output
   private JTextField text; // for user input
   private int count = 0, arrInd = 0, arrLength; // to remember (respectively) the stage of the program,
   // current index to store search string, number of search strings
   private String[] search; // array for strings to search for
   private String link = "", webStr = ""; // the link of the webpage, the HTML/CSS for webpage
   private boolean allFound = true; // whether or not all the search strings were found in script for webpage

   public FinalProjectPanel ()
   {
      text = new JTextField (10);
      text.addActionListener (new TextListener());
      
      info = new JLabel ("Enter Website link:"); // prompts user for webpage link
      
      add (info);
      add (text);
      
   }

   private class TextListener implements ActionListener // responds to input in text field
   {

      public void actionPerformed (ActionEvent event)
      {

         if (count == 0) { // website link entered
            link = text.getText();
            count++; // progress to next stage by asking for number of search strings
            info.setText("Enter number of strings to search");
         }
         else if (count == 1) { // number of words to enter
            arrLength = Integer.parseInt (text.getText());
            search = new String[arrLength]; // stores the number entered
            count++;
            info.setText("Enter search string " + (arrInd + 1)); // progress to next stage for asking for search string
         }
         
         else if (arrInd > arrLength - 1) {
            info.setText("loading...");

            // DEBUG
            for (int i = 0; i < arrLength; i++) {
               System.out.println(search[i]);
            }
            
            try {
               webStr = openWebpage(link);
            } catch (SecurityException e) {
               //Thread.currentThread().interrupt();
               System.exit(-1);
            } catch (Exception ex) {
               System.exit(-2);
            }
         
            for (int i = 0; i < arrLength; i++) {
               if (!webStr.contains(search[i])) {
                  allFound = false;
               }
            }
            
            if (!allFound) {
               info.setText("Not all of those words were found in your website.");
            }
            else {
               info.setText("Wow, all of those words were found in your website!");
            }
         
         }
         
         else { // search strings being entered
            search[arrInd] = text.getText(); // stores in the array
            arrInd++;

            try { // a pause after each entry so the user doesn't accidentally spam multiple of the same ones
               Thread.sleep(1000);
            } catch (Exception e) {
               System.exit(-3);
            } // catch

            if (arrInd < arrLength) info.setText("Enter search string " + (arrInd + 1));
            else { info.setText("thanks! hit enter to see your result"); }
            
         } // else
         
         
         
      } // actionPerformed
   } //textListener
   
   public String openWebpage(String link)  throws Exception {
      URL url = new URL(link);
      
      Scanner sc = new Scanner(url.openStream());

      StringBuffer sb = new StringBuffer();
      while(sc.hasNext()) {
         sb.append(sc.next());

      }

      String result = sb.toString();

      result = result.replaceAll("<[^>]*>", "");
      System.out.println("Contents of the web page: "+result);
      
      return result;
   }
} // class