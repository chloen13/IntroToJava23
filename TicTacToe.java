//TicTacToe.java                                          Author: Stephanie Yang//
import java.util.*;
public class TicTacToe {
      static String userTurn;//userTurn when they start their turn//
      static String[] gameBoard;//array for the board//
   public static void main(String[] args){//main method//
      userTurn = "O";//set the first turn to O//
      String win = null;//set the winner to null or nothing since no one has won yet//
      gameBoard = new String[9];//array to nine boxes because tic tac toe size//
      Scanner scan = new Scanner(System.in);//scanner//
      for(int i = 0; i < 9; i++){
         gameBoard[i] = String.valueOf(i + 1);
      }  
      System.out.println("TIC TAC TOE");//when program  is run message is printed out//
      System.out.println("O goes first please enter a number on the board to place your letter");
      printGame();//prints the board using the method//
      while( win == null ) {//while their is no winner//
         int userInput = scan.nextInt(); 
         
         if(userInput < 1 || userInput > 9) {//if your number is less than one and greater than nine ask for a number between the two//
            System.out.println("Please enter a number between one and nine");
            userInput = scan.nextInt();
         } 

          if(gameBoard[userInput - 1].equals(String.valueOf(userInput))){//switches the turns so the letters alternate when placing them on the board//
            gameBoard[userInput - 1] = userTurn;
            if(userTurn.equals("O")) {
               userTurn = "X";
            }
            else {
               userTurn = "O";
            }
            printGame();
            win = Game();//set the winner to the game method so if the user wins the programs detects it//
         }

         else {
            System.out.println("This box is already taken by the other player please enter a different box number");//if someone tries to place the letter in the same box as other player//
         }   
      }
      
      if(win.equalsIgnoreCase("DRAW :(")) {//draw//
         System.out.println("It's a draw :( try again! (rerun program)");
      }
      else {
         System.out.println("CONGRATS " + win);//else user has won//
         
      }
      scan.close();//closes scanner//
   }
   
   public static void  printGame(){//prints the game out//
      System.out.println("|---|---|---|");
      System.out.println("| " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " |");
      System.out.println("|---|---|---|");
      System.out.println("| " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " | ");
      System.out.println("|---|---|---|");
      System.out.println("| " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " |");
      System.out.println("|---|---|---|");
      
   }
   
   public static String Game(){//to see if the user has won the game//
      for(int i = 0; i < 8; i++) {
         String row = null;
         switch(i) {
            case 0:
               row = gameBoard[0] + gameBoard[1] + gameBoard[2];//if user has won horizontally//
               break;
            
            case 1:
               row = gameBoard[3] + gameBoard[4] + gameBoard[5];//if user has won horizontally//
               break;
            case 2: 
               row = gameBoard[6] + gameBoard[7] + gameBoard[8];//if user has won horizontally//
               break;
            case 3:
               row = gameBoard[0]  + gameBoard[3] + gameBoard[6];//if user has won vertically//
               break;
             
            case 4:
               row = gameBoard[1] + gameBoard[4] + gameBoard[7];//if user has won vertically//
               break;
            
            case 5:
               row = gameBoard[2] + gameBoard[5] + gameBoard[8];//if user has won vertically//
               break;
            
            case 6:
               row = gameBoard[2] + gameBoard[4] + gameBoard[6];//if user has won diagonally//
               break;
            
            case 7:
               row = gameBoard[0] + gameBoard[4] + gameBoard[8];//if user has won diagonally//
               break;
         }
         
         if (row.equals("OOO")){//if the row or case has a line of O's return O as the winner//
            return "O";
         }
         else {
            if(row.equals("XXX")){//else is the row has X return X as user//
               return "X";
            }
         } 
      }
      for( int i = 0; i < 9; i++){
         if(Arrays.asList(gameBoard).contains(String.valueOf(i + 1))){
            break;
         }
         else if ( i == 8){//if no line matches up return draw//
            return "DRAW! :(";
         }
      }
      
      System.out.println(userTurn + " Your turn now" + " enter where you want your letter " + userTurn + " in: " );//runs user turn and informs them//
      return null;
   
   }
}