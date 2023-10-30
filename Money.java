import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class Money extends Award
{
   @Override
   public int displayWinnings(Players player, boolean win, JLabel messagesText, JLabel prizeImage){
      prizeImage.setIcon(new ImageIcon());
      if (win){
         try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/Correct.wav"));
            clip.open(ais);
            clip.start();
         }
         catch (Exception e){
         }
         messagesText.setText(messagesText.getText() + "\nCongrats " + player.getFirstName() + ", you won $500");
         return 500;
      }
      else{
         try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/Incorrect.wav"));
            clip.open(ais);
            clip.start();
         }
         catch (Exception e){
         }
         messagesText.setText(messagesText.getText() + "\nSorry " + player.getFirstName() + ", you lost $200");
         return -200;
      }
   }
}