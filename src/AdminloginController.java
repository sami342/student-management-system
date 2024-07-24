

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class AdminloginController implements Initializable {
   
   
    @FXML
    private Label m;

    @FXML
    private FontAwesomeIcon b2;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_btn;

    @FXML
    private Button ex;

    @FXML
    private Button back;
    
    @FXML
    private AnchorPane Sign_up_format;
     //tool for database connection
     private Connection connect;
     private Statement statement;
     private PreparedStatement prepare;
     private ResultSet result;
    @FXML
    private void Exit(){
        System.exit(0);
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
        if(username.isFocused()){
        username.setStyle("-fx-background-color:fff;"+"-fx-border-width:2px" );
        password.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
       
        }
        else if(password.isFocused()){
            
        username.setStyle("-fx-background-color:transparent;"+"-fx-border-width:1px" );
        password.setStyle("-fx-background-color:#fff;"+"-fx-border-width:2px" ); 

        }
   }
   
    public void dropShadoweffect(){
        DropShadow orginal= new DropShadow(20,Color.valueOf("#ae44a5"));
        
        orginal.setRadius(30);
        m.setEffect(orginal);
       
        
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
       
       
    }
    
 
           
     public void login() throws SQLException{
          
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
            String sql=("SELECT * FROM admin WHERE username= ? and password= ?");
         
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
                 
         Parent root=FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
    
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       dropShadoweffect();
    }
 
}
