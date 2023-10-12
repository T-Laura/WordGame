import javax.swing.JOptionPane;

public class Hosts extends Person
{
   private Phrases phrase = null;
   
   public Hosts(String name){
      super(name);
   }
   
   public Hosts(String fname, String lname){
      super(fname, lname);
   }
   
   public void setPhrase(){
      boolean phraseSet = false;
      while (!phraseSet){
         try{
            phrase = new Phrases();
            phrase.setPhrase(JOptionPane.showInputDialog(null, "Enter the phrase for players to guess."));
            phraseSet = true;
         }
         catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      }
   }
   
   public Phrases getPhrase(){
      return phrase;
   }
}