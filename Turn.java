import java.util.Scanner;

public class Turn
{
   public boolean takeTurn(Players player, Hosts host, Scanner scnr){
      String guess;
      boolean numberGuessed;
      
      System.out.print(host.getFirstName() + " " + host.getLastName() + " says \"");
      System.out.print(player.getFirstName() + " " + player.getLastName() + ", ");
      System.out.print("enter your guess for the number I picked between 0 and 100.\"\n");
      guess = scnr.nextLine();
      try {
         numberGuessed = host.getNumber().compareNumber(Integer.parseInt(guess));
         if (numberGuessed){
            System.out.println("You win $1000");
            player.setMoney(player.getMoney() + 1000);
         }
         else{
            System.out.println("You lose $100");
            player.setMoney(player.getMoney() - 100);
         }
         System.out.println(player);
         return numberGuessed;
      }
      catch (Exception e) {
         System.out.println("You must input a number between 0 and 100");
         return false;
      }
   }
}