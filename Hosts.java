import java.util.Scanner;

public class Hosts extends Person
{
   private Phrases phrase;
   
   public Hosts(String name){
      super(name);
   }
   
   public Hosts(String fname, String lname){
      super(fname, lname);
   }
   
   public void setPhrase(Scanner scnr){
      boolean phraseSet = false;
      while (!phraseSet){
         try{
            System.out.println("Enter the phrase for players to guess.");
            phrase = new Phrases();
            phrase.setPhrase(scnr.nextLine());
            phraseSet = true;
         }
         catch (Exception e){
            System.out.println(e.getMessage());
         }
      }
   }
   
   public Phrases getPhrase(){
      return phrase;
   }
}