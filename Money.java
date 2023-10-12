import javax.swing.*;

public class Money extends Award
{
   @Override
   public int displayWinnings(Players player, boolean win, JLabel messagesText){
      if (win){
         messagesText.setText(messagesText.getText() + "\nCongrats " + player.getFirstName() + ", you won $500");
         return 500;
      }
      else{
         messagesText.setText(messagesText.getText() + "\nSorry " + player.getFirstName() + ", you lost $200");
         return -200;
      }
   }
}