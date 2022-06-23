public class Character extends Poly{

   private String level; 
   private String charName;
   private String classes;  
   private String race;
   private String subRace;
   private String age;
   private String weight;
   private String height;
   private String gender;

//Constructors
   public Character(){
      
      level = "";
      charName = "";
      classes = "";
      race = "";
      subRace = "";
      age = "";
      weight = "";
      height = "";
      gender = "";
      
}

//Overloaded constructor with all parameters
   public Character(String lvl, String cn, String cl, String ra, String sra, String ag, String wt, String ht, String gen){
   
      this.level = lvl;
      this.charName = cn;
      this.classes = cl;
      this.race = ra;
      this.subRace = sra;
      this.age = ag;
      this.weight = wt;
      this.height = ht;
      this.gender = gen;
}
//Mutators
   public void setLevel(String lvl){
      this.level = lvl;
}
   public void setCharName(String cn){
      this.charName = cn;
}
   public void setClasses(String cl){
      this.classes = cl;
}
   public void setRace(String ra){
      this.race = ra;
}
   public void setSubRace(String sra){
      this.subRace = sra;
}
   public void setAge(String ag){
      this.age = ag;
}
   public void setWeight(String wt){
      this.weight = wt;
}
   public void setHeight(String ht){
      this.height = ht;
}
   public void setGender(String gen){
      this.gender = gen;
}

//Accessors
   public String getLevel(){
      return level;
}
   public String getCharName(){
      return charName;
}
   public String getClasses(){
      return classes;
}
   public String getRace(){
      return race;
}
   public String getSubRace(){
      return subRace;
}
   public String getAge(){
      return age;
}
   public String getWeight(){
      return weight;
}
   public String getHeight(){
      return height;
}
   public String getGender(){
      return gender;
}

   //All together
   public String toString() {
      
      String result = "";
      result = "Level: "+getLevel()+"\n";
      result += "Character: "+getCharName()+"\n";
      result += "Class: "+getClasses()+"\n";
      result += "Race: "+getRace()+"\n";
      result += "Sub Race: "+getSubRace()+"\n";
      result += "Age: "+getAge()+"\n";
      result += "Weight: "+getWeight()+"\n";
      result += "Height: "+getHeight()+"\n";
      result += "Gender: "+getGender()+"\n";
      
      return result;
}

   


}

