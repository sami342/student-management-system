



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class studentlogin extends Application {
    
    private double x=0;
    private double y=0;
    
    @Override
    public void start(Stage primaryStage)throws Exception {
       Parent root=FXMLLoader.load(getClass().getResource("studentloginFXML.fxml"));
        
        Scene scene = new Scene(root);
        root.setOnMousePressed((MouseEvent event)->{
            x=event.getSceneX();
            y=event.getSceneY();  
            
        });
        root.setOnMouseDragged((MouseEvent event)->{
            primaryStage.setX(event.getScreenX()-x);
            primaryStage.setY(event.getScreenY()-y);
            
        });
          
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
