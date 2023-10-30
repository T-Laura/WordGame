import java.util.Random;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Turn
{
   public boolean takeTurn(Players player, Hosts host, JLabel messagesText, JLabel prizeImage){
      String guess;
      int letterGuessed;
      boolean correctGuess;
      Award prize;
      Random rand = new Random();
      
      guess = JOptionPane.showInputDialog(null, "The phrase to guess is: " + host.getPhrase().getPlayingPhrase() + "\"\n"
         + host.getFirstName() + " " + host.getLastName() + " says \""
         + player.getFirstName() + " " + player.getLastName() + ", "
         + "enter your guess for a letter in my phrase\"");
      try {
         letterGuessed = host.getPhrase().findLetters(guess, messagesText);
         if (letterGuessed == 1 || letterGuessed == 2){
            correctGuess = true;
         }
         else{
            correctGuess = false;
         }
         if (rand.nextInt(5) == 0){
            prize = new Physical();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess, messagesText, prizeImage));
         }
         else{
            prize = new Money();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess, messagesText, prizeImage));
         }
         if (letterGuessed == 1){
            correctGuess = false;
         }
         messagesText.setText(messagesText.getText() + "\n" + player);
         return correctGuess;
      }
      catch (MultipleLettersException e){
         messagesText.setText(messagesText.getText() + "\n" + e.getMessage());
         return false;
      }
      catch (Exception e){
         messagesText.setText(messagesText.getText() + "\n" + e.getMessage());
         return false;
      }
   }
}