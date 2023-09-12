import java.util.Random;

public class Physical extends Award
{
   private String[] prizes = {"new car", "PS5", "flatscreen TV", "house", "painting"};
   
   public int getRandomPrize(){
      Random rand = new Random();
      return rand.nextInt(5);
   }
   
   @Override
   public int displayWinnings(Players player, boolean win){
      if (win){
         System.out.println("Congrats " + player.getFirstName() + ", you just won a " + prizes[getRandomPrize()]);
      }
      else{
         System.out.println("Sorry " + player.getFirstName() + ", you could have won a " + prizes[getRandomPrize()]);
      }
      return 0;
   }
}