import javax.swing.JOptionPane;

public class Money extends Award
{
   @Override
   public int displayWinnings(Players player, boolean win){
      if (win){
         JOptionPane.showMessageDialog(null, "Congrats " + player.getFirstName() + ", you won $500");
         return 500;
      }
      else{
         JOptionPane.showMessageDialog(null, "Sorry " + player.getFirstName() + ", you lost $200");
         return -200;
      }
   }
}