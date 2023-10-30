import java.util.Random;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.*;

public class Physical extends Award
{
   BufferedImage prizePicture = null;
   private String[] prizes = {"new car", "gaming console", "TV", "house", "painting"};
   
   public int getRandomPrize(){
      Random rand = new Random();
      return rand.nextInt(5);
   }
   
   @Override
   public int displayWinnings(Players player, boolean win, JLabel messagesText, JLabel prizeImage){
      int i = getRandomPrize();
      try{
         switch (i){
            case 0:
               prizePicture = ImageIO.read(new File("images/new_car.jpg"));
               break;
            case 1:
               prizePicture = ImageIO.read(new File("images/gaming_console.png"));
               break;
            case 2:
               prizePicture = ImageIO.read(new File("images/TV.jpg"));
               break;
            case 3:
               prizePicture = ImageIO.read(new File("images/house.jpg"));
               break;
            case 4:
               prizePicture = ImageIO.read(new File("images/painting.jpg"));
               break;
         }
      }
      catch (IOException e){
      }
      if (win){
         try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/Correct.wav"));
            clip.open(ais);
            clip.start();
         }
         catch (Exception e){
         }
         messagesText.setText(messagesText.getText() + "\nCongrats " + player.getFirstName() + ", you just won a "
            + prizes[i]);
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
         messagesText.setText(messagesText.getText() + "\nSorry " + player.getFirstName() + ", you could have won a "
            + prizes[i]);
      }
      prizeImage.setIcon(new ImageIcon(prizePicture));
      return 0;
   }
}