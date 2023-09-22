import java.util.Random;

public class Phrases
{
   private static String gamePhrase;
   private static StringBuilder playingPhrase;
   
   public void setPhrase(String phrase) throws Exception{
      gamePhrase = phrase;
      playingPhrase = new StringBuilder(phrase);
      playingPhrase.setLength(phrase.length());
      for (int i = 0; i < gamePhrase.length(); i++) {
         if (!(Character.isLetter(gamePhrase.charAt(i)) || gamePhrase.charAt(i) == ' ')) {
            throw(new Exception("You can only enter letters and spaces"));
         }
         if (gamePhrase.charAt(i) == ' ') {
            playingPhrase.setCharAt(i, ' ');
         }
         else {
            playingPhrase.setCharAt(i, '_');
         }
      }
   }
   
   public int findLetters(String letterToCheck) throws MultipleLettersException, Exception{
      boolean altered = false;
      
      if (letterToCheck.length() != 1){
         throw(new MultipleLettersException());
      }
      if (!Character.isLetter(gamePhrase.charAt(0))){
         throw(new Exception("You need to enter a letter"));
      }
      char letter = letterToCheck.charAt(0);
      System.out.println("Well done, that letter is in the phrase");
      for (int i = 0; i < gamePhrase.length(); i++){
         if (Character.toLowerCase(gamePhrase.charAt(i)) == Character.toLowerCase(letter)
             && Character.toLowerCase(playingPhrase.charAt(i)) == '_'){
            playingPhrase.setCharAt(i, gamePhrase.charAt(i));
            altered = true;
         }
      }
      if (!altered){
         return 0;
      }
      else if (playingPhrase.toString().contains("_")){
         return 1;
      }
      else{
         System.out.println("Congrats, you solved the puzzle");
         return 2;
      }
   }
   
   public String getPhrase(){
      return gamePhrase;
   }
   
   public String getPlayingPhrase(){
      return playingPhrase.toString();
   }
}