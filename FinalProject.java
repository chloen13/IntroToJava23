
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import java.security.*;
import java.security.cert.*;

import javax.net.ssl.*;

public class FinalProject
{
   public static void main (String[] args)
   {
      try { // so the program can be run anywhere
         UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
      } catch (Exception e) {
         e.printStackTrace();
      }
   
      JFrame frame = new JFrame ("TextDisplay"); // creates the frame
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add(new FinalProjectPanel());

      frame.pack();
      frame.setVisible(true);
   }
   
   
}