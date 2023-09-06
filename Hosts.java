public class Hosts extends Person
{
   private Numbers number;
   
   public Hosts(String name){
      super(name);
   }
   
   public Hosts(String fname, String lname){
      super(fname, lname);
   }
   
   public void setNumber(int num){
      number.setNumber(num);
   }
   
   public Numbers getNumber(){
      return number;
   }
   
   public void randomizeNum(){
      number = new Numbers();
      number.generateNumber();
   }
}