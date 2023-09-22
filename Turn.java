import java.util.Random;
import java.util.Scanner;

public class Turn
{
   public boolean takeTurn(Players player, Hosts host, Scanner scnr){
      String guess;
      int letterGuessed;
      boolean correctGuess;
      Award prize;
      Random rand = new Random();
      
      System.out.print("The phrase to guess is: " + host.getPhrase().getPlayingPhrase() + "\"\n");
      System.out.print(host.getFirstName() + " " + host.getLastName() + " says \"");
      System.out.print(player.getFirstName() + " " + player.getLastName() + ", ");
      System.out.print("enter your guess for a letter in my phrase\"\n");
      guess = scnr.nextLine();
      try {
         letterGuessed = host.getPhrase().findLetters(guess);
         if (letterGuessed == 1 || letterGuessed == 2){
            correctGuess = true;
         }
         else{
            correctGuess = false;
         }
         if (rand.nextInt(5) == 0){
            prize = new Physical();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess));
         }
         else{
            prize = new Money();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess));
         }
         System.out.println(player);
         if (letterGuessed == 1){
            correctGuess = false;
         }
         return correctGuess;
      }
      catch (MultipleLettersException e){
         System.out.println(e.getMessage());
         return false;
      }
      catch (Exception e){
         System.out.println(e.getMessage());
         return false;
      }
   }
}