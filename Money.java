public class Money extends Award
{
   @Override
   public int displayWinnings(Players player, boolean win){
      if (win){
         System.out.println("Congrats " + player.getFirstName() + ", you won $500");
         return 500;
      }
      else{
         System.out.println("Sorry " + player.getFirstName() + ", you lost $200");
         return -200;
      }
   }
}