import java.util.Random;
import javax.swing.JOptionPane;

public class Turn
{
   public boolean takeTurn(Players player, Hosts host){
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
         letterGuessed = host.getPhrase().findLetters(guess);
         if (letterGuessed == 1 || letterGuessed == 2){
            correctGuess = true;
         }
         else{
            correctGuess = false;
         }
         if (rand.nextInt(5) == 0){
            prize = new Physical();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess));
         }
         else{
            prize = new Money();
            player.setMoney(player.getMoney() + prize.displayWinnings(player, correctGuess));
         }
         if (letterGuessed == 1){
            correctGuess = false;
         }
         JOptionPane.showMessageDialog(null, player);
         return correctGuess;
      }
      catch (MultipleLettersException e){
         JOptionPane.showMessageDialog(null, e.getMessage());
         return false;
      }
      catch (Exception e){
         JOptionPane.showMessageDialog(null, e.getMessage());
         return false;
      }
   }
}