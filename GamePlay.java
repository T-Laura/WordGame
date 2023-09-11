import java.util.Scanner;

public class GamePlay
{
   private static Players[] currentPlayers;
   private static Hosts host;
   private static Turn turn;
   
   public static void main(String[] args){
      String firstName;
      String lastName;
      int turnNumber = 0;
      boolean numberGuessed;
      Scanner scnr = new Scanner(System.in);
      currentPlayers = new Players[3];
      host = new Hosts("Bob", "Barker");
      turn = new Turn();
      
      for (int i = 0; i < 3; i++) {
         System.out.println("What is player " + (i + 1) + "'s first name?");
         firstName = scnr.nextLine();
         System.out.print("Would you like to enter a last name for player " + (i + 1));
         System.out.println("? Leave blank if not");
         lastName = scnr.nextLine();
         if (lastName == ""){
            currentPlayers[i] = new Players(firstName);
         }
         else{
            currentPlayers[i] = new Players(firstName, lastName);
         }
      }
      do{
         host.randomizeNum();
         numberGuessed = false;
         while (!numberGuessed){
            numberGuessed = turn.takeTurn(currentPlayers[turnNumber % 3], host, scnr);
            turnNumber++;
         }
         System.out.println("Play another game? (y or n)");
      } while (scnr.nextLine().equals("y"));
   }
}