import java.util.Random;
import javax.swing.*;

public class Physical extends Award
{
   private String[] prizes = {"new car", "PS5", "flatscreen TV", "house", "painting"};
   
   public int getRandomPrize(){
      Random rand = new Random();
      return rand.nextInt(5);
   }
   
   @Override
   public int displayWinnings(Players player, boolean win, JLabel messagesText){
      if (win){
         messagesText.setText(messagesText.getText() + "\nCongrats " + player.getFirstName() + ", you just won a "
            + prizes[getRandomPrize()]);
      }
      else{
         messagesText.setText(messagesText.getText() + "\nSorry " + player.getFirstName() + ", you could have won a "
            + prizes[getRandomPrize()]);
      }
      return 0;
   }
}