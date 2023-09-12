public class Money extends Award
{
   @Override
   public int displayWinnings(Players player, boolean win){
      if (win){
         System.out.println("Congrats " + player.getFirstName() + ", you won $1000");
         return 1000;
      }
      else{
         System.out.println("Sorry " + player.getFirstName() + ", you lost $200");
         return -200;
      }
   }
}