import java.util.Scanner;

public class GamePlay
{
   private static Players player;
   private static Hosts host;
   private static Turn turn;
   
   public static void main(String[] args){
      String firstName;
      String lastName;
      boolean numberGuessed;
      Scanner scnr = new Scanner(System.in);
      host = new Hosts("Bob", "Barker");
      turn = new Turn();
      
      System.out.println("What is your first name?");
      firstName = scnr.nextLine();
      System.out.println("Would you like to enter a last name? Leave blank if not");
      lastName = scnr.nextLine();
      if (lastName == ""){
         player = new Players(firstName);
      }
      else{
         player = new Players(firstName, lastName);
      }
      do{
         host.randomizeNum();
         numberGuessed = false;
         while (!numberGuessed){
            numberGuessed = turn.takeTurn(player, host, scnr);
         }
         System.out.println("Play another game? (y or n)");
      } while (scnr.nextLine().equals("y"));
   }
}