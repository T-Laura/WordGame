public class Players extends Person
{
   private int money;
   
   public Players(String name){
      super(name);
      this.setMoney(1000);
   }
   
   public Players(String fname, String lname){
      super(fname, lname);
      this.setMoney(1000);
   }
   
   public void setMoney(int amount){
      money = amount;
   }
   
   public int getMoney(){
      return money;
   }
   
   @Override
   public String toString(){
      return super.getFirstName() + " " + super.getLastName() + " has $" + money;
   }
}