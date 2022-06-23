import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.*;
import java.io.*;
import java.awt.Desktop;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.awt.event.ActionEvent;
import javafx.event.EventHandler;
import java.awt.event.ActionListener;
import javafx.stage.Stage;

public class DnDChars extends Application{

   Stage window;//renaming stage
   Button save, prev, next, okay;
   BorderPane layout1;
   Scene scene1;
   Desktop desktop = Desktop.getDesktop();
   
   //to change between menus
   GridPane centerMenu, centerMenu2;
   
   //vbox for lucky nums
   VBox centerMenu3;
   //to change from previous and next slide
   int curMid = 0;
   
   //inputs for character
   TextField fNameInput, lNameInput;
   TextField lvlInput, cNameInput, cClassInput, cRaceInput,cSRaceInput, ageInput,
      weightInput, heightInput, genderInput;
   //inputs for stats
   TextField strInput, dexInput, conInput, inteInput, wisInput, chaInput;
   
   //input for recursive lucky num
   TextField lNumber;
   
   
   int num;//to decide the confirm box buttons
   
   
   public static void main(String[] args) throws IOException{
      launch(args); 
   }


   @Override //overide the start method in the Application class
   public void start(Stage primaryStage) throws Exception{
   
      window = primaryStage;//setting window to primatyStage
      window.setTitle("Dungeon and Dragons Characters");//title
      
      //file chooser
      FileChooser fileChooser = new FileChooser();
      
      
      
      //tsv  File
      FileWriter outputFiletwo = new FileWriter("History.tsv",true);
      
      PrintWriter pfiletwo = new PrintWriter(outputFiletwo);
      
      
      //character rext field
      fNameInput = new TextField();
      lNameInput = new TextField();
      lvlInput = new TextField();
      cNameInput = new TextField();
      cClassInput = new TextField();
      cRaceInput = new TextField();
      cSRaceInput = new TextField();
      ageInput = new TextField();
      weightInput = new TextField();
      heightInput = new TextField();
      genderInput = new TextField();
      
      //stats textfield
      strInput = new TextField();
      dexInput = new TextField();
      conInput = new TextField();
      inteInput = new TextField();
      wisInput = new TextField();
      chaInput = new TextField();
      
      
      
      //image
      Image img = new Image("dnd.jpg");
      ImageView img2 = new ImageView(img);
      img2.setFitHeight(100);
      img2.setFitWidth(100);
      
 
 
      
      //closing with x
      window.setOnCloseRequest(e -> {
         e.consume();//take the close request event and eats it
         
         if(checker()){
            pfiletwo.print(saving());
         }
         pfiletwo.close();
         closeProgram(1);
      });
      
      //edit menu
      //putting a underscore before it treats it as a short cut (alt + first letter)
      Menu editMenu = new Menu("_Edit");
      
      //edit items
      //create a character
      MenuItem newChar = new MenuItem("Create a New Character");
      newChar.setOnAction(e ->{ 
         curMid = 0;
         layout1.setCenter(centerMenu);
      });
      editMenu.getItems().add(newChar);

      
      

      //delete one
      MenuItem newDelete = new MenuItem("Delete History");
      newDelete.setOnAction(e -> {
         
         if(deleteProgram(1)){
         try{
         outputFiletwo.close();
         pfiletwo.close();
         }
         catch(IOException a){
            System.out.println("no bueno");
         }
         //file
         File file2 = new File("History.tsv");
      
         if(file2.delete()){
          System.out.println("woo");
         }else{
           System.out.println("booo");
         }
            window.close();
         }
      
     
         
      });
      editMenu.getItems().add(newDelete);
     
     
     //okay button for lucky numbers 
     
      okay = new Button("Okay");
      okay.setOnAction(e ->{
         int test = Integer.parseInt(lNumber.getText());
         ConfirmBox.display2("Lucky!!",(luckyNum(test)));
      
      });
      
      
      //save button
      save = new Button("save");
      save.setOnAction(e -> {
      
        if(checker()){
          pfiletwo.print(saving());
          clearFields();
        }
        layout1.setCenter(img2);
        savedProgram(2);
      });
      
      
      //previous button
      prev = new Button("Prev.");
      prev.setOnAction(e -> {
        if(curMid==1){
        curMid = 0;
        layout1.setCenter(centerMenu);
        }
      });
      
      //next button
      next = new Button("Next");
      next.setOnAction(e -> {
        if(curMid == 0){
         curMid = 1;
         layout1.setCenter(centerMenu2);
        }
      });
      
      
      //file menu
      Menu fileMenu = new Menu("_File");
      
      //open menu
      MenuItem newOpen = new MenuItem("Open");
      newOpen.setOnAction(e -> {
         File file = fileChooser.showOpenDialog(window);
         if (file != null) {
            openFile(file);
         }
      });
      fileMenu.getItems().add(newOpen);
      
      MenuItem newSave = new MenuItem("Old saves");
      newSave.setOnAction(e -> History());
      
      fileMenu.getItems().add(newSave);
      
      //for the recursive thing
      MenuItem newLuck = new MenuItem("Lucky Number");
      newLuck.setOnAction(e ->{ 

        layout1.setCenter(centerMenu3);
        
      });
      fileMenu.getItems().add(newLuck);
      
      //to Exit
      MenuItem newExit = new MenuItem("_Exit");
      newExit.setOnAction(e ->{
      
      if(checker()){
       pfiletwo.print(saving());
       }
       pfiletwo.close();
       closeProgram(1);
       
       });
      fileMenu.getItems().add(newExit);
      
      //Help menu
      Menu helpMenu = new Menu("_Help");
      //help items
      MenuItem newAbout = new MenuItem("about");
      newAbout.setOnAction(e -> {
         helpProgram(2);
      });
      helpMenu.getItems().add(newAbout);
      

      //Main menu bar
      MenuBar menuBar = new MenuBar();
      menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

      
      //create ccharacter scene
      centerMenu = new GridPane();
      centerMenu.setPadding(new Insets(20,20,20,20));
      centerMenu.setVgap(8);
      centerMenu.setHgap(10);
      
      //labels for character infor
      Label fname = new Label("First Name");
      GridPane.setConstraints(fname, 0,1);
      Label lname = new Label("Last Name");
      GridPane.setConstraints(lname, 0,2);
      
      Label c = new Label("Character");
      GridPane.setConstraints(c, 0,3);
      Label d = new Label("Class");
      GridPane.setConstraints(d, 0,4);
      Label e = new Label("Race");
      GridPane.setConstraints(e, 0,5);
      Label f = new Label("Age");
      GridPane.setConstraints(f, 0,6);
      Label g = new Label("Gender");
      GridPane.setConstraints(g, 0,7);
      
      Label h = new Label("Level");
      GridPane.setConstraints(h, 2,3);
      Label i = new Label("Sub-Race");
      GridPane.setConstraints(i, 2,4);
      Label j = new Label("Height");
      GridPane.setConstraints(j, 2,5);
      Label k = new Label("Weight");
      GridPane.setConstraints(k, 2,6);
            
      GridPane.setConstraints(save, 0, 8);
      GridPane.setConstraints(next, 2, 8);
      
      //setting up
      GridPane.setConstraints(fNameInput, 1, 1);
      GridPane.setConstraints(lNameInput, 1, 2);
      
      GridPane.setConstraints(cNameInput, 1, 3);
      GridPane.setConstraints(cClassInput, 1, 4);
      GridPane.setConstraints(cRaceInput, 1, 5);
      GridPane.setConstraints(ageInput, 1, 6);
      GridPane.setConstraints(genderInput, 1, 7);
      
      GridPane.setConstraints(lvlInput, 3, 3);
      GridPane.setConstraints(cSRaceInput, 3, 4);
      GridPane.setConstraints(heightInput, 3, 5);
      GridPane.setConstraints(weightInput, 3, 6);
      
      centerMenu.getChildren().addAll(fname,lname,fNameInput,lNameInput,save,next,
         c,d,e,f,g,h,i,j,k,lvlInput, cNameInput, cClassInput, cRaceInput,cSRaceInput, ageInput,
         weightInput, heightInput, genderInput);
         
      //luck num  
      Label lLable = new Label("Enter a number!");
      lNumber = new TextField();
     
      
      //create lucky num page
      centerMenu3 = new VBox();
      centerMenu3.setPadding(new Insets(20,20,20,20));
      centerMenu3.getChildren().addAll(lLable, lNumber, okay);
      centerMenu3.setAlignment(Pos.CENTER);

      //create Stats page thing
      centerMenu2 = new GridPane();
      centerMenu2.setPadding(new Insets(20,20,20,20));
      centerMenu2.setVgap(8);
      centerMenu2.setHgap(10);
      
      //labels for character infor
      Label str = new Label("Strength");
      GridPane.setConstraints(str, 0,1);
      Label dex = new Label("Dexterity");
      GridPane.setConstraints(dex, 0,2);
      
      Label con = new Label("Constitution");
      GridPane.setConstraints(con, 0,3);
      Label inte = new Label("Intelligence");
      GridPane.setConstraints(inte, 0,4);
      Label wis = new Label("Wisdom");
      GridPane.setConstraints(wis, 0,5);
      Label cha = new Label("Charisma");
      GridPane.setConstraints(cha, 0,6);
                  
      
      //setting up
      GridPane.setConstraints(strInput, 1, 1);
      GridPane.setConstraints(dexInput, 1, 2);
      GridPane.setConstraints(conInput, 1, 3);
      GridPane.setConstraints(inteInput, 1, 4);
      GridPane.setConstraints(wisInput, 1, 5);
      GridPane.setConstraints(chaInput, 1, 6);
      
      GridPane.setConstraints(prev, 1, 8);
            
      centerMenu2.getChildren().addAll(str,dex,con,inte,wis,cha,
         strInput, dexInput, conInput, inteInput, wisInput, chaInput, prev);
            
      //layout - original menu screen
      layout1 =  new BorderPane();
      layout1.setTop(menuBar);
      layout1.setCenter(img2);
      
      
      scene1 = new Scene(layout1, 600,500);
     
     
      window.setScene(scene1);
      window.show();
   }
   
   
   
   //removed Help for this instead
      //that way if i add more to about it displays correct buttons to what ever num is correlated to it
   public void helpProgram(int num){
      Boolean answer = ConfirmBox.display("About", Help.about(), num);
   }
   
   //to reset all text fields
   public void clearFields(){
      fNameInput.setText("");
      lNameInput.setText("");
      lvlInput.setText("");
      cNameInput.setText("");
      cClassInput.setText("");
      cRaceInput.setText("");
      cSRaceInput.setText("");
      ageInput.setText("");
      weightInput.setText("");
      heightInput.setText("");
      genderInput.setText("");
      
      strInput.setText("");
      dexInput.setText("");
      conInput.setText("");
      inteInput.setText("");
      wisInput.setText("");
      chaInput.setText("");
   }
   
   //pop-up saying you saved
   public void savedProgram(int num){
      ConfirmBox.display("Saving", "You have saved!", num);
   }
   
   
   
   //recursive thing
   public int luckyNum(int num){
      int lucky = 0;
            
      if (num == 0){
       
      }
      else{
      try{
      num--;
      lucky = num + luckyNum(num);
      }
      catch(NumberFormatException nfe){
      lucky = 0;
      }
    }
      return lucky;
   }
   
      
  //open files
   public void openFile(File file) {

            try {
                desktop.open(file);
            } catch (IOException ex) {
               Logger.getLogger(DnDChars.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
   
   //when trying to exit
   public void closeProgram(int num){
      Boolean answer = ConfirmBox.display("You Are Trying To Exit", "are you sure you want to exit?",num);
      if(answer){
         System.out.println("info was saved");
         window.close();
         
         }
   }
   //when trying to deleting
   public boolean deleteProgram(int num){
      Boolean answer = ConfirmBox.display("Deleting", "are you sure you want to delete your history?\n"+
         "*this will also close out of the application*",num);
      
      return answer;
   }
   
   public void History() {
      //prepping opening tsv file
      String file = "History.tsv";    
      String line = "";
      BufferedReader reader = null;
  try {
   reader = new BufferedReader(new FileReader(file));
   //detects things inside the file and saves it to an array
   while((line = reader.readLine()) != null) {
   
    String[] row = line.split("\t");

    for(String index : row) {
    //formatting it - left adjusted
     System.out.printf("%-21s", index);
    }
    //printing history out
    System.out.println();
    }
  } catch(Exception e) {
   e.printStackTrace();
  }
  
  
  finally {
   try {
    reader.close();//closing out of tsv file 
    
   } catch (IOException e) {
    //catch block
    e.printStackTrace();
   }
 }
   
  
  }
  
  public boolean checker(){
   boolean a = true;
   
   if (  fNameInput.getText().trim().isEmpty()
       
         &&lNameInput.getText().trim().isEmpty()
         
         &&lvlInput.getText().trim().isEmpty()
         &&cNameInput.getText().trim().isEmpty()
         &&cClassInput.getText().trim().isEmpty()
         &&cRaceInput.getText().trim().isEmpty()
         &&cSRaceInput.getText().trim().isEmpty()
         &&ageInput.getText().trim().isEmpty()
         &&weightInput.getText().trim().isEmpty()
         &&heightInput.getText().trim().isEmpty()
         &&genderInput.getText().trim().isEmpty()
         
         &&strInput.getText().trim().isEmpty()
         &&dexInput.getText().trim().isEmpty()
         &&conInput.getText().trim().isEmpty()
         &&inteInput.getText().trim().isEmpty()
         &&wisInput.getText().trim().isEmpty()
         &&chaInput.getText().trim().isEmpty()){
   
   
   a = false;
   }
   
   return a;
   
  }
  
  public String saving(){
         //before going to tsv file
         String s ="";
         
         
         Person person = new Person();
         Character character = new Character();
         Stats stats = new Stats();
         
         Poly[] poly = {person, character, stats};
         
         
         person.setFirstName(fNameInput.getText());
         person.setLastName(lNameInput.getText());
         
         character.setLevel(lvlInput.getText());
         character.setCharName(cNameInput.getText());
         character.setClasses(cClassInput.getText());
         character.setRace(cRaceInput.getText());
         character.setSubRace(cSRaceInput.getText());
         character.setAge(ageInput.getText());
         character.setWeight(weightInput.getText());
         character.setHeight(heightInput.getText());
         character.setGender(genderInput.getText());
         
         stats.setStr(strInput.getText());
         stats.setDex(dexInput.getText());
         stats.setCon(conInput.getText());
         stats.setInte(inteInput.getText());
         stats.setWis(wisInput.getText());
         stats.setCha(chaInput.getText());
         
         //polymorphism
         s = person.toString() +"\n"+ character.toString()+ "\n"+ stats.toString()+"\n-----------";

         
         return s;
     }
   
 }



