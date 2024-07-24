
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class StudentdashboardFXMLController implements Initializable {
  @FXML
    private AnchorPane navbar;

    @FXML
    private Label user;

    @FXML
    private Button student;

    @FXML
    private Button home;

    @FXML
    private FontAwesomeIcon cog_btn;

    @FXML
    private AnchorPane logout_card;

    @FXML
    private Button logout_button;

    @FXML
    private FontAwesomeIcon edit;

    @FXML
    private Label studentlable;

    @FXML
    private TextField Stud_id;

    @FXML
    private Button searech;

    @FXML
    private TextField stude_name;

    @FXML
    private TextField net;

    @FXML
    private TextField db;

    @FXML
    private TextField ip;

    @FXML
    private TextField auto;

    @FXML
    private TextField stude_gen;

    @FXML
    private TextField stude_age;

    @FXML
    private TextField stude_current;

    @FXML
    private TextField java;

     @FXML
    private TextField tot;

    @FXML
    private TextField grad;
    @FXML
    private TextField phyton;
     private Connection connect=null;
    private Statement statement=null;
    private PreparedStatement prepare;
    private ResultSet result=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
   public void openlogoutcard(){
        cog_btn.setOnMouseClicked((MouseEvent event)->{
              if(!logout_card.isVisible()){
                logout_card.setVisible(true);  
              }
                  else{
                  logout_card.setVisible(false); 
              }
          });
    }
   
    private double x=0;
    private double y=0;
    @FXML
      public void logoutAccount()throws Exception{
          logout_button.getScene().getWindow().hide();
          try{
           Parent root=FXMLLoader.load(getClass().getResource("studentloginFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            root.setOnMousePressed((MouseEvent event)->{
             x=event.getSceneX();
            y=event.getSceneY(); 
            
            });
           
             root.setOnMouseDragged((MouseEvent event)->{
            stage.setX(event.getScreenX()-x);
          stage.setY(event.getScreenY()-y);
        });
       stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
          }catch(Exception e){
              System.out.println(""+e);
          }
      }
       @FXML
    private void Exit(ActionEvent event) {
       System.exit(0); 
    }
     @FXML
    public void search(){
        try{
       Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select * from studentmark where studentid= ?";
         prepare=connect.prepareStatement(sql);
         prepare.setString(1,Stud_id.getText());
         result=prepare.executeQuery();
         if(result.next()){
             stude_name.setText(result.getString(2));
           
              java.setText(result.getString(3));
              phyton.setText(result.getString(8));
              auto.setText(result.getString(7));
              ip.setText(result.getString(6));
               db.setText(result.getString(4)); 
               net.setText(result.getString(3));
               tot.setText(result.getString(9));
               grad.setText(result.getString(10));
         }
         else{
             JOptionPane.showMessageDialog(null,"not found");
         }
        }catch(Exception e){
            System.out.println(""+e);
        }
}
       public void search2() throws ClassNotFoundException, SQLException{
        try{
       Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select * from studentmark where studentid= ?";
         prepare=connect.prepareStatement(sql);
         prepare.setString(1,Stud_id.getText());
         result=prepare.executeQuery();
         if(result.next()){
             stude_name.setText(result.getString(2));
              java.setText(result.getString(3));
              phyton.setText(result.getString(8));
              auto.setText(result.getString(7));
              ip.setText(result.getString(6));
               db.setText(result.getString(5));
             net.setText(result.getString(4));
            tot.setText(result.getString(9));
             grad.setText(result.getString(10));
         }
         else{
             JOptionPane.showMessageDialog(null,"welcom");
         }
    }
        catch(Exception e){
           System.out.println(""+e);
       }
       }
}
    

