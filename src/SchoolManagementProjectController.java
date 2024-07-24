
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SchoolManagementProjectController implements Initializable {

     @FXML
    private Button Student;

    @FXML
    private Button Teacher;

    @FXML
    private Button Admin;
    
    public void Exit(){
       System.exit(0);
    }
     private double x=0;
    private double y=0;
    public void admin()throws Exception{
   
         Parent root = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
         Stage primaryStage=new Stage();
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();     
        });
        root.setOnMouseDragged((MouseEvent event)->{
            primaryStage.setX(event.getScreenX()-x);
            primaryStage.setY(event.getScreenY()-y);
        });
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public void student()throws Exception{
   
         Parent root = FXMLLoader.load(getClass().getResource("studentloginFXML.fxml"));
         Stage primaryStage=new Stage();
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();     
        });
        root.setOnMouseDragged((MouseEvent event)->{
            primaryStage.setX(event.getScreenX()-x);
            primaryStage.setY(event.getScreenY()-y);
        });
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      public void teacher()throws Exception{
   
         Parent root = FXMLLoader.load(getClass().getResource("teacherlogin.fxml"));
         Stage primaryStage=new Stage();
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();     
        });
        root.setOnMouseDragged((MouseEvent event)->{
            primaryStage.setX(event.getScreenX()-x);
            primaryStage.setY(event.getScreenY()-y);
        });
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
