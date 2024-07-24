/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class TeacherloginController implements Initializable {
   @FXML
    private AnchorPane Log_in_format;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_btn;

    @FXML
    private Hyperlink Create_acc;

    @FXML
    private AnchorPane Sign_up_form;

    @FXML
    private Label m;

    @FXML
    private FontAwesomeIcon b2;

    @FXML
    private AnchorPane Sign_up_format;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button signup_btn;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private TextField su_username;

    @FXML
    private Label m1;

    @FXML
    private FontAwesomeIcon b1;

     //tool for database connection
     private Connection connect;
     private Statement statement;
     private PreparedStatement prepare;
     private ResultSet result;
    @FXML
            
    void Changeform(ActionEvent event) {
 if(event.getSource() == Create_acc){
                Sign_up_format.setVisible(true);
                Log_in_format.setVisible(false);
                 su_username.setText("");
                 su_password.setText("");
                 su_email.setText("");
        }
        else if(event.getSource() == login_acc){
           Sign_up_format.setVisible(false);
           Log_in_format.setVisible(true);
           username.setText("");
           password.setText("");
     }
    }

    @FXML
    void Exit(ActionEvent event) {
System.exit(0);
    }

    @FXML
  public void login(ActionEvent event) {
try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
            String sql=("SELECT * FROM schedule WHERE username= ? and password= ?");
         
             prepare=connect.prepareStatement(sql);
             prepare.setString(1,username.getText());
             prepare.setString(2,password.getText());
             
             result= prepare.executeQuery();
             if(result.next()){
             
              User.username=result.getString("username");
           Alert alert=new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("LOGIN");
           alert.setHeaderText(null);
           alert.setContentText("LOG IN SUCCESSFULLY....");
           alert.showAndWait();        
          login_btn.getScene().getWindow().hide();
                 
         Parent root=FXMLLoader.load(getClass().getResource("teacherDash.fxml"));
         Scene scene = new Scene(root);
         Stage stage=new Stage();
         stage.setScene(scene);
         stage.show();
          
             }
             else{
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("ERROR");
                 alert.setHeaderText(null);
                 alert.setContentText("wrong username/password!");
                 alert.showAndWait();
               // JOptionPane.showMessageDialog(null,"log in failed");
             }
         }
         catch(Exception e){
            e.printStackTrace(); 
         }
    }

    @FXML
 public void signup()throws SQLException{
         try{
             
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
             String sql=("INSERT INTO schedule(username,password,email)VALUES( ?,?,?)");
           
             
        if(su_username.getText().isEmpty()|su_password.getText().isEmpty()|su_email.getText().isEmpty()){
                 Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SCHOOL");
                 alert.setHeaderText(null);
                 alert.setContentText("please fill all the given list....");
                 alert.showAndWait();
             
         }
            else if(su_password.getText().length()<8){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SCHOOL");
                 alert.setHeaderText(null);
                 alert.setContentText("your password should be greater than 8 character");
                 alert.showAndWait();
                    }  
         else{ 
            
             if(validationemail()){
            prepare=connect.prepareStatement(sql);
            prepare.setString(1,su_username.getText());
            prepare.setString(2,su_password.getText());
            prepare.setString(3,su_email.getText());
            prepare.execute();
            su_username.setText("");
            su_password.setText("");
            su_email.setText("");
            
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("SCHOOL");
                 alert.setHeaderText(null);
                 alert.setContentText("your account is created successfully!....");
                 alert.showAndWait();
            }
            }
         }
         catch(Exception e){
            e.printStackTrace(); 
         }
     }
    
public boolean validationemail(){
       Pattern pattern=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9}+[.][a-zA-Z]+]+");
       Matcher match=pattern.matcher(su_email.getText());
       if(match.find()&&match.group().equals(su_email.getText())) {
           return true;
       }
       else{
                 Alert alert=new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("ERROR");
                 alert.setHeaderText(null);
                 alert.setContentText("invalid email!");
                 alert.showAndWait();
           return false;
       }
   }
   @FXML
    public void textfieldDesign(){
        if (username.isFocused()){
        username.setStyle("-fx-background-color:fff;"+"-fx-border-width:2px" );
        password.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
    }
        else if(password.isFocused()){
            
        username.setStyle("-fx-background-color:transparent;"+"-fx-border-width:2px" );
        password.setStyle("-fx-background-color:#fff;"+"-fx-border-width:1px" ); 
        }
        
    }
   public void textfieldDesign1(){
        if(su_username.isFocused()){
        su_username.setStyle("-fx-background-color:fff;"+"-fx-border-width:2px" );
        su_password.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
        su_email.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px");
        }
        else if(su_email.isFocused()){
            
        su_username.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
        su_password.setStyle("-fx-background-color:#fff;"+"-fx-border-width:1px" ); 
        su_email.setStyle("-fx-background-color:transparent;"+"-fx-border-width:2px");
        }
       else if(su_password.isFocused()){
            
        su_username.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
        su_password.setStyle("-fx-background-color:#fff;"+"-fx-border-width:2px" ); 
        su_email.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px"); 
        }
   }
   
     public void dropShadoweffect(){
        DropShadow orginal= new DropShadow(20,Color.valueOf("#ae44a5"));
        
        orginal.setRadius(30);
        m.setEffect(orginal);
        m1.setEffect(orginal);
        b1.setEffect(orginal);
        
        m.setOnMouseEntered((MouseEvent event)->{
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         m.setCursor(Cursor.HAND);
         m.setStyle("-fx-text-fill:ee5fe4");
          m.setEffect(shadow);
        });
        m.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            m.setStyle("-fx-text-fill:#000");
            m.setEffect(shadow);
        });
       
        m1.setOnMouseEntered((MouseEvent event)->{
            
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         m1.setCursor(Cursor.HAND);
         m1.setStyle("-fx-text-fill:ee5fe4");
          m1.setEffect(shadow);
        });
         m1.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            m1.setStyle("-fx-text-fill:#000");
            m1.setEffect(shadow);
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      dropShadoweffect();
    }    
    
}
