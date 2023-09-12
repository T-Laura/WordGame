import java.util.Random;
import java.util.Scanner;

public class Turn
{
   public boolean takeTurn(Players player, Hosts host, Scanner scnr){
      String guess;
      boolean numberGuessed;
      Award prize;
      Random rand = new Random();
      
      System.out.print(host.getFirstName() + " " + host.getLastName() + " says \"");
      System.out.print(player.getFirstName() + " " + player.getLastName() + ", ");
      System.out.print("enter your guess for the number I picked between 0 and 100.\"\n");
      guess = scnr.nextLine();
      try{
         numberGuessed = host.getNumber().compareNumber(Integer.parseInt(guess));
         if (rand.nextInt(5) == 0){
            prize = new Physical();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, numberGuessed));
         }
         else{
            prize = new Money();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, numberGuessed));
         }
         System.out.println(player);
         return numberGuessed;
      }
      catch (Exception e){
         System.out.println("You must input a number between 0 and 100");
         return false;
      }
   }
}