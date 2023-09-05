public class Hosts extends Person
{
   private Numbers number;
   
   public Hosts(String name){
      super(name);
   }
   
   public Hosts(String fname, String lname){
      super(fname, lname);
   }
   
   public void randomizeNum(){
      number = new Numbers();
      number.generateNumber();
   }
}