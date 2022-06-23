public class Stats extends Poly{

   private String strength; 
   private String dexterity;
   private String constitution;  
   private String Stringelligence;
   private String wisdom;
   private String charisma;

//Constructors
   public Stats(){
      
      strength = "";
      dexterity = "";
      constitution = "";
      Stringelligence = "";
      wisdom = "";
      charisma = "";
      
      
}

//Overloaded constructor with all parameters
   public Stats(String str, String dex, String con, String Stringe, String wis, String cha){
   
      this.strength = str;
      this.dexterity = dex;
      this.constitution = con;
      this.Stringelligence = Stringe;
      this.wisdom = wis;
      this.charisma = cha;
}
//Mutators
   public void setStr(String str){
      this.strength = str;
}
   public void setDex(String dex){
      this.dexterity = dex;
}
   public void setCon(String con){
      this.constitution = con;
}
   public void setInte(String Stringe){
      this.Stringelligence = Stringe;
}
   public void setWis(String wis){
      this.wisdom = wis;
}
   public void setCha(String cha){
      this.charisma = cha;
}

//Accessors
   public String getStr(){
      return strength;
}
   public String getDex(){
      return dexterity;
}
   public String getCon(){
      return constitution;
}
   public String getInte(){
      return Stringelligence;
}
   public String getWis(){
      return wisdom;
}
   public String getCha(){
      return charisma;
}


   //All together
   public String toString() {
      
      String result = "";
      result = "Strength: "+getStr()+"\n";
      result += "Dexterity: "+getDex()+"\n";
      result += "Constitution: "+getCon()+"\n";
      result += "Intelligence: "+getInte()+"\n";
      result += "Wisdom: "+getWis()+"\n";
      result += "Charisma: "+getCha()+"\n";
      
      
      
      return result;
}

   


}