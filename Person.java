public class Person extends Poly{

//Attributes
   private String firstName; //Person's first name
   private String lastName;  //Person's last name

//Constructors
   public Person(){
   
      firstName = "";
      lastName = "";
}

//Overloaded constructor with all parameters
   public Person(String fn, String ln){
   
      this.firstName = fn;
      this.lastName = ln;
}
//Mutators
   public void setFirstName(String fn){
      this.firstName = fn;
}
   public void setLastName(String ln){
      this.lastName = ln;
}

   //Accessors
   public String getFirstName(){
      return firstName;
   
}
   public String getLastName(){
      return lastName;
   
}

   //All together and polymorphism
   public String toString() {
      
      String result = "";
      result = "\nName: "+getFirstName()+" "+getLastName()+"\n";
      return result;
}



}
