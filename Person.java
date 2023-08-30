public class Person
{
   private String firstName;
   private String lastName;
   
   public Person(String name) {
      firstName = name;
      lastName = "";
   }
   
   public Person(String fName, String lName) {
      firstName = fName;
      lastName = lName;
   }
   
   public setFirstName(String name) {
      firsName = name;
   }
   public setLastName(String name) {
      lastName = name;
   }
   
   public getFirstName() {
      return firstName;
   }
   public getLastName() {
      return lastName;
   }
}