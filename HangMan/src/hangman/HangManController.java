/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

/**
 *
 * @author tati0
 */
public class HangManController implements Initializable 
{
    HangMan h= new HangMan();
    
      private String []words={"GAME","LOOP","HOUSE","SKY","JAVA","COMPUTER",
                         "PROGRAMMING","PROJECT","CURRENTLY","KEYBOARD","WHICH","CLICKING"};
          
      private  int randomNumber;
      private ArrayList<Character>guessedLetters= new ArrayList<>();
      private String randomWord;
      private int wordLength;
      private static final int maxAttempts=6;
      private static int nberrors = 0;
      private StringBuilder hiddenWord;
      private int score=0;
      private int correctAnswer=0;
      StringBuilder s;
        
  
   



    @FXML
    private Label lbl_img;
    @FXML
    private Label label;
    
   
    @FXML
    private Pane hangmanPane;

    @FXML
    private Pane guessPane;

    @FXML
    private Label labelPlayerName;

    @FXML
    private Label labelWord;
    
    @FXML
    private Label labelScore;

    @FXML
    private Pane lettersPane;

    @FXML
    private Button buttonA;

    @FXML
    private Button buttonB;

    @FXML
    private Button buttonC;

    @FXML
    private Button buttonD;

    @FXML
    private Button buttonE;

    @FXML
    private Button buttonF;

    @FXML
    private Button buttonG;

    @FXML
    private Button buttonH;

    @FXML
    private Button buttonI;

    @FXML
    private Button buttonJ;

    @FXML
    private Button buttonK;

    @FXML
    private Button buttonL;

    @FXML
    private Button buttonM;

    @FXML
    private Button buttonN;

    @FXML
    private Button buttonO;

    @FXML
    private Button buttonP;

    @FXML
    private Button buttonQ;

    @FXML
    private Button buttonR;

    @FXML
    private Button buttonS;

    @FXML
    private Button buttonT;

    @FXML
    private Button buttonU;

    @FXML
    private Button buttonW;

    @FXML
    private Button buttonX;

    @FXML
    private Button buttonY;

    @FXML
    private Button buttonV;

    @FXML
    private Button buttonZ;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonQuit;
    
   

    @FXML
    void buttonAction(ActionEvent event) {
       // buttonA.setDisable(true);
        ((Button)(event.getSource())).setDisable(true);
      String letter=  ((Button)(event.getSource())).getText();
        hiddenWord= letterMatch(randomWord,hiddenWord,letter.charAt(0),event);
       
    }

     @FXML
    void buttonQuitAction(ActionEvent event) {
        System.exit(0);
    }
     @FXML
    void buttonStartAction(ActionEvent event) {
        
        hiddenWord=getHiddenWord();
        ((Button)(event.getSource())).setDisable(true);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TextInputDialog a = new TextInputDialog("Please enter your name");
        a.setHeaderText("Enter your name");
        boolean success=false;
        while(!success) 
        {
           Optional<String> s=a.showAndWait();
           if(s.get().toString().isEmpty()|| s.get().toString().compareTo("Please enter your name")==0)
           {
               success=false;
           }
           else{
               success=true;
               labelPlayerName.setText(s.get().toString());
           }
        }
    }    
    
       
       
        
   public StringBuilder getHiddenWord()
   {
       
       randomNumber=(int) (Math.random() * words.length);
       randomWord= words[randomNumber];
       wordLength=randomWord.length();
       nberrors=0;
       score=0;
       labelScore.setText(String.valueOf(score));
               
       final ObservableList<Node> children = lettersPane.getChildren();
       for(Node n : children)
       {
           if(n instanceof Button)
           {
               if(((Button)n).getText().compareTo("Start")!=0)
               ((Button)n).setDisable(false);
           }
       }
       //buttonStart.setDisable(true);
        Image image = new Image(getClass().getResourceAsStream("Hangman-"+ nberrors +".png"));
        lbl_img.setGraphic(new ImageView(image));
       s= new StringBuilder();
       for (int i = 0; i < randomWord.length(); i++)
       {
           s.append("*");
        
       }  labelWord.setText(s.toString());
       //System.out.println(randomWord);
       return s;
           
   }
    public  StringBuilder letterMatch(String word,StringBuilder hWord,char guess, ActionEvent e)
   {
       boolean goodGuess=false;
       ((Button)e.getSource()).setDisable(true);
       
       Alert alert;
        for (int i = 0; i < word.length(); i++) 
        {
            
            if(word.charAt(i) == guess)
            {
                hWord.setCharAt(i, guess);
               score++;
               labelScore.setText(String.valueOf(score));
               goodGuess=true;
               if(wordLength == score){
               //you lose
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Congratulations");
                alert.setHeaderText("You Win! Your score is: "+labelScore.getText());
                alert.setContentText("The secret word was: "+randomWord);
                alert.showAndWait();
           
                
               
               }
            }

        }  
        
        if (!goodGuess) 
        {
            nberrors++;
            printHangMan();
        }
        
        if(nberrors == 6 || wordLength == score)
            {
               //you lose
                if(nberrors == 6)
                {
                 nberrors++;
                 printHangMan();
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Defeat!");
                alert.setHeaderText("You lose!The word was: " +randomWord);
                alert.showAndWait();
                }
                
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Continue to play");
                alert.setContentText("Do you want to play again? Press OK!");

                Optional<ButtonType> result = alert.showAndWait();
                
                if (result.get() == ButtonType.OK)
                {
                   
                   this.getHiddenWord();
                   hWord = new StringBuilder(s);
                   
                } else
                {
                   System.exit(0);
                }
            }
       
      labelWord.setText(hWord.toString());
      return hWord;
   }


     
     public  void printHangMan()
     {
      
        Image image = new Image(getClass().getResourceAsStream("Hangman-"+ nberrors +".png"));
        
        lbl_img.setGraphic(new ImageView(image));
       
        
     } 
     
     
     
}
    
      

//ANOTHER METHOD TO CHECK LETTER + DISABLE BUTTON TO AVOID TYPYING SAME LETTER
//     public StringBuilder letterMatch(String word,StringBuilder hWord,char guess)
//   {
//       
//    for (int i = 0; i < word.length(); i++) 
//    {
//        if(word.charAt(i) == guess)
//        {
//            hWord.setCharAt(i, guess);
//          
//        }
//        else
//        {
//            nberrors++;
//            printHangMan();
//        }
//        
//    }
//      labelWord.setText(hWord.toString());
//      return hWord;
//   }
        
       
    

