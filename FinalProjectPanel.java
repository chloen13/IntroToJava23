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

   private JLabel info;
   private JTextField text;
   private int count = 0;
   private int arrind = 0;
   private String[] search;
   private int arrLength;
   private String link = "";
   //private String text = "";
   private boolean allFound = true;

   public FinalProjectPanel ()
   {
      text = new JTextField (10);
      text.addActionListener (new TextListener());
      
      info = new JLabel ("Enter Website link:");
      
      add (info);
      add (text);
      
   }
   
   
   private class TextListener implements ActionListener
   {

      public void actionPerformed (ActionEvent event)
      {

         if (count == 0) { // website link entered
            link = text.getText();
            count++;
            info.setText("Enter number of strings to search");
         }
         else if (count == 1) { // number of words to enter
            arrLength = Integer.parseInt (text.getText());
            search = new String[arrLength];
            count++;
         }
         
         else if (arrind > arrLength - 1) {
            info.setText("thanks!");
            
            try {
            //text = openWebpage(link);
            } catch (SecurityException e) {
               Thread.currentThread().interrupt();
            }
         
            for (int i = 0; i < arrind.length; i++) {
               if (text.indexOf(arrind[i] == -1)) {
                  allFound = false;
               }
            }
            
            if (allFound == false) {
               info.setText("Not all of those words were found in your website.");
            }
            else {
               info.setText("Wow, all of those words were found in your website!");
            }
         
         }
         
         else { // words being entered
            info.setText("Enter search string " + (arrind + 1));
            search[arrind] = text.getText();
            arrind++;
            
            try {
               Thread.sleep(1000);
            } catch (Exception e) {
               Thread.currentThread().interrupt();
            } // catch
            
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
      System.out.println(result);

      result = result.replaceAll("<[^>]*>", "");
      System.out.println("Contents of the web page: "+result);
      
      return result;
   }
} // class