import java.util.Scanner;

public class GamePlay
{
   private static Person player;
   private static Numbers num;
   
   public static void main(String[] args) {
      String firstName;
      String lastName;
      String guess;
      boolean numberGuessed = false;
      Scanner scnr = new Scanner(System.in);
      num = new Numbers();
      
      System.out.println("What is your first name?");
      firstName = scnr.nextLine();
      System.out.println("Would you like to enter a last name? Leave blank if not");
      lastName = scnr.nextLine();
      if (lastName == "") {
         player = new Person(firstName);
      }
      else {
         player = new Person(firstName, lastName);
      }
      num.generateNumber();
      while (numberGuessed == false) {
         System.out.print(player.getFirstName() + " " + player.getLastName() + ", ");
         System.out.print("guess what number I picked between 0 and 100.\n");
         guess = scnr.nextLine();
         try {
            numberGuessed = num.compareNumber(Integer.parseInt(guess));
         }
         catch (Exception e) {
            System.out.println("You must input a number between 0 and 100");
         }
      }
   }
}