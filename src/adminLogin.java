

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

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

/**
 *
 * @author user
 */
  
public class adminLogin extends Application {
    private double x=0;
   private double y=0;
    @Override
    public void start(Stage primaryStage)throws Exception {
      Parent root=FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
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
