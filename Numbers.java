import java.util.Random;

public class Numbers
{
   private int randomNum;
   
   public void setNumber(int num) {
      randomNum = num;
   }
   
   public int getNumber() {
      return randomNum;
   }
   
   public void generateNumber() {
      Random rand = new Random();
      randomNum = rand.nextInt(101);
   }
   
   public boolean compareNumber(int guess) {
      if (guess == randomNum) {
         System.out.println("Congratulations, you guessed the number!");
         return true;
      }
      else if (guess > randomNum) {
         System.out.println("I'm sorry.  That guess was too high.");
         return false;
      }
      else {
         System.out.println("I'm sorry, That guess was too low.");
         return false;
      }
   }
}