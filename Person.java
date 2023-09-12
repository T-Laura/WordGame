public class Person
{
   private String firstName;
   private String lastName;
   
   public Person(String name){
      this.setFirstName(name);
      this.setLastName("");
   }
   
   public Person(String fName, String lName){
      this.setFirstName(fName);
      this.setLastName(lName);
   }
   
   public void setFirstName(String name){
      firstName = name;
   }
   public void setLastName(String name){
      lastName = name;
   }
   
   public String getFirstName(){
      return firstName;
   }
   public String getLastName(){
      return lastName;
   }
}