import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


public class DashboardController implements Initializable {
  @FXML
    private Label user;

    @FXML
    private Button home;

    @FXML
    private Button student;

    @FXML
    private Button record;

    @FXML
    private Button data;

    @FXML
    private FontAwesomeIcon edit;
   
      @FXML
    private TextField id;

    @FXML
    private TextField surname;

    @FXML
    private TextField age;
    
    @FXML
    private ComboBox<String> gender;
    
    @FXML
    private ComboBox<String> adress;

    
   @FXML
    private TableView<Data> table_view;

    @FXML
    private TableColumn<Data, Integer> col_id;

    @FXML
    private TableColumn<Data, String> col_surname;

     @FXML
    private TableColumn<Data, String> col_age;


    @FXML
    private TableColumn<Data, String> col_gender;

    @FXML
    private TableColumn<Data, String> col_address;
    
     @FXML
    private TableColumn<Data, String> col_date;

       @FXML
    private TableColumn<Data, String> col_image;
    
    @FXML
    private Button Image_path;

    @FXML
    private Label file_path;
     @FXML
    private AnchorPane navbar;
     
    @FXML
    private ImageView image_view;
    
    @FXML
    private AnchorPane main;
     
    @FXML
    private AnchorPane student_page;
       
    @FXML
    private AnchorPane home_page;

    @FXML
    private AnchorPane recored_page;

    @FXML
    private AnchorPane Data_page;
    
   @FXML
    private DatePicker date;
     @FXML
    private Button updatebtn;
     
    @FXML
    private Label sr_id;

    @FXML
    private ImageView sr_view_image;
    
    @FXML
    private Label sr_surname;
    @FXML
    private Label total_student;
     @FXML
    private Label total_enrolled;

    @FXML
    private Label total_graduate;

    @FXML
    private Label total_inactive;
    @FXML
    private ComboBox<String> sr_current;
    @FXML
    private TableView<Data> sr_table_view;
    @FXML
    private Label boy;
    
    private Label female;
    @FXML
    private TableColumn<Data, Integer> sr_col_id;
    @FXML
    private TableColumn<Data, String> col_sr_surname;
    @FXML
    private TableColumn<Data, String> col_sr_given;
    
    @FXML
    private TableColumn<Data, String> col_sr_current;

    @FXML
    private TableColumn<Data, String> col_sr_data;
    
      @FXML
    private TableColumn<Data, String> col_sr_image;
   
    @FXML
    private TableColumn<Data, String> sr_col_gen;
      @FXML
    private AnchorPane total_male_card;
     @FXML
    private AnchorPane total_female_card;
     @FXML
    private AnchorPane total_ianactive_card;    
    @FXML
    private AnchorPane total_graduate_card; 
    @FXML
    private AnchorPane total_student_card;
     @FXML
    private AnchorPane total_enroll_card;
     @FXML
    private FontAwesomeIcon cog_btn;
    @FXML
    private Button sr_update;

    @FXML
    private Button sr_clear;
      @FXML
    private AnchorPane nav_chart;
    @FXML
    private AnchorPane logout_card;

    @FXML
    private Button logout_button;
     
    @FXML
    private Label studentlable;
    
    @FXML
    private Label recordelable;
    
    @FXML
    private Label datalable;

    @FXML
    private Button line_chart_button;

    @FXML
    private Button bar_chart_button;

    @FXML
    private Button area_chart_button;
     @FXML
    private AnchorPane line_chart_page;
    @FXML
    private LineChart<String,Integer> linechart;
    @FXML
    private BarChart<String,Integer> barchart;
    @FXML
    private AreaChart<String,Integer> areaChart;
    @FXML
    private Button show_chart_button;
    @FXML
    private AnchorPane bar_chart_page;
    @FXML
    private AnchorPane area_chart_page;
    
    @FXML
    private Label homelable;

    
    //tool for connect  datatbase
    private Connection connect=null;
    private Statement statement=null;
    private PreparedStatement prepare;
    private ResultSet result=null;
    private ObservableList<Data> listM;
    
    //action for closing the project......
    @FXML
    private void Exit(){
        System.exit(0);
    }
    //action to get user name from our database and display on our dashboard............
    public void account(){
        user.setText(User.username);
    }
    //action for focuse on one button when we clicked it ........
    public void navbutton(){
        home.setOnMouseClicked((MouseEvent event)->{
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        student.setOnMouseClicked((MouseEvent event)->{
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        data.setOnMouseClicked((MouseEvent event)->{
            data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        record.setOnMouseClicked((MouseEvent event)->{
            record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        
    }
    //action for focuse when we entered the button..........
    public void navbuttonhover(){
        home.setOnMouseEntered((MouseEvent event)->{
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        student.setOnMouseEntered((MouseEvent event)->{
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        data.setOnMouseEntered((MouseEvent event)->{
            data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        record.setOnMouseEntered((MouseEvent event)->{
            record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
    }
     public void navbuttonHover(){
        home.setOnMouseExited((MouseEvent event)->{
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        student.setOnMouseExited((MouseEvent event)->{
            student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                    "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        data.setOnMouseExited((MouseEvent event)->{
            data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
        record.setOnMouseExited((MouseEvent event)->{
            record.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.6),rgba(256,106,239,0.6));"+
                     "-fx-border-color:linear-gradient(to bottom right,#517ab5,#ae44a5);"+
                    "-fx-border-width:0px 0px 0px 5px");
            home.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
             data.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
              student.setStyle(" -fx-background-color:linear-gradient(to bottom right,"
                    + "rgba(121,172,255,0.2),rgba(256,106,239,0.2));");
        });
    }
  @FXML
  //action used to focuse on one field to when we get data from user ............ 
     public void textfieldRecord(){
         if(id.isFocused()){
             id.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             surname.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             age.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             adress.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             date.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
         }
         else if(surname.isFocused()){
             surname.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             age.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             adress.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             date.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
          }
         else if(age.isFocused()){
             age.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             surname.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             adress.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             date.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
          }
          else if(gender.isFocused()){
             gender.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             surname.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             age.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             adress.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             date.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
          }
          else if(adress.isFocused()){
             adress.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             surname.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             age.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             date.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
          }
           else if(date.isFocused()){
             date.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
             id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             surname.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             age.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             gender.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
             adress.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
          }
     }
     //home page style
       public void dropShadoweffect(){
        DropShadow orginal= new DropShadow(20,Color.valueOf("#ae44a5"));
        
        orginal.setRadius(30);
        homelable.setEffect(orginal);
       studentlable.setEffect(orginal);
      recordelable.setEffect(orginal);
      datalable.setEffect(orginal);
        
        homelable.setOnMouseEntered((MouseEvent event)->{
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         homelable.setCursor(Cursor.HAND);
         homelable.setStyle("-fx-text-fill:ee5fe4");
          homelable.setEffect(shadow);
        });
        homelable.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            homelable.setStyle("-fx-text-fill:#000");
            homelable.setEffect(shadow);
        });
         recordelable.setOnMouseEntered((MouseEvent event)->{
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         recordelable.setCursor(Cursor.HAND);
         recordelable.setStyle("-fx-text-fill:ee5fe4");
          recordelable.setEffect(shadow);
        });
        recordelable.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            recordelable.setStyle("-fx-text-fill:#000");
            recordelable.setEffect(shadow);
        });
         studentlable.setOnMouseEntered((MouseEvent event)->{
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         studentlable.setCursor(Cursor.HAND);
         studentlable.setStyle("-fx-text-fill:ee5fe4");
          studentlable.setEffect(shadow);
        });
        studentlable.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            studentlable.setStyle("-fx-text-fill:#000");
            studentlable.setEffect(shadow);
        });
         datalable.setOnMouseEntered((MouseEvent event)->{
         DropShadow shadow=new DropShadow(60,Color.valueOf("#ae44a5"));  
         
         datalable.setCursor(Cursor.HAND);
         datalable.setStyle("-fx-text-fill:ee5fe4");
          datalable.setEffect(shadow);
        });
        datalable.setOnMouseExited((MouseEvent)->{
            DropShadow shadow= new DropShadow(20,Color.valueOf("#ae44a5"));
            datalable.setStyle("-fx-text-fill:#000");
            datalable.setEffect(shadow);
        });
        
    }
     //action when we close our home page button
     public void hovertotalcard(){
       total_student_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_student_card.setEffect(shadow);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
           total_male_card.setEffect(null);
           total_female_card.setEffect(null);
       });
        total_student_card.setOnMouseExited((MouseEvent event)->{
            total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
          total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        });
        total_enroll_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_enroll_card.setEffect(shadow);
           total_student_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
       });
        total_enroll_card.setOnMouseExited((MouseEvent event)->{
            total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        });
         total_graduate_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_graduate_card.setEffect(shadow);
           total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
       });
        total_graduate_card.setOnMouseExited((MouseEvent event)->{
            total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        });
      total_ianactive_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_ianactive_card.setEffect(shadow);
           total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
       });
        total_ianactive_card.setOnMouseExited((MouseEvent event)->{
            total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        });  
         total_male_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_male_card.setEffect(shadow);
           total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
            total_ianactive_card.setEffect(null);
           total_female_card.setEffect(null);
       });
        total_male_card.setOnMouseExited((MouseEvent event)->{
            total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
            total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        }); 
          total_female_card.setOnMouseEntered((MouseEvent event)->{
           DropShadow shadow= new DropShadow(20,Color.valueOf("#aaa"));
           shadow.setOffsetX(3);
           shadow.setOffsetY(5);
           total_female_card.setEffect(shadow);
           total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
            total_ianactive_card.setEffect(null);
           total_male_card.setEffect(null);
       });
        total_female_card.setOnMouseExited((MouseEvent event)->{
           total_student_card.setEffect(null);
           total_enroll_card.setEffect(null);
           total_graduate_card.setEffect(null);
           total_ianactive_card.setEffect(null);
           total_male_card.setEffect(null);
           total_female_card.setEffect(null);
        }); 
     }
   //used to insert data from database and send it to constructor in Data java class..... 
    public ObservableList<Data> listData()throws SQLException, ClassNotFoundException{
         ObservableList<Data> dataList=FXCollections.observableArrayList();
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="SELECT * FROM `student`";
         try{
             statement=connect.createStatement();
             result= statement.executeQuery(sql); 
             Data data1;
             while(result.next()){
                 data1 =new Data(result.getInt("studentid"),
                           result.getString("surname"),
                           result.getString("Age"),
                           result.getString("gender"),
                           result.getString("address"),
                           result.getString("date"),
                            result.getString("image"),
                              result.getString("current"));
                 dataList.addAll(data1); 
                 
             }
         }catch(Exception e){
           e.printStackTrace(); 
         }
        return dataList;  
     }
     //used to accept image of our user 
  @FXML
     public void InsertImageData(){
         FileChooser open=new FileChooser();
         Stage stage=(Stage)navbar.getScene().getWindow();
         File file=open.showOpenDialog(stage);
         if(file !=null){
             System.out.println(""+file.getAbsolutePath());
             file_path.setText(file.getAbsolutePath());
             //image fill path ,width ,height,ratio,smooth
             Image image=new Image(file.toURI().toString(),110,110,false,true);
            image_view.setImage(image);
         }
         else{
             System.out.println("no data found");
         }
     }
     //used to show immedietly all data from database and show on table view....
    @FXML
    public void showData(){
        
      try {
          ObservableList<Data> show= listData();
       
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
          col_age.setCellValueFactory(new PropertyValueFactory<>("Age"));
          col_gender.setCellValueFactory(new PropertyValueFactory<>("gen"));
          col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
          col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        table_view.setItems(show);
      } catch (SQLException | ClassNotFoundException ex) {
        System.out.println(""+ex);
      }
 
    } 
    //give value for our combobox gender and address.......
 // @FXML
    public void comboBox(){
        ObservableList<String> gen=observableArrayList("Male","Female");
        gender.setItems(gen); 
    }
    @FXML
     public void comboBox2(){
       ObservableList<String> datalist=observableArrayList("Adiss Abeba", "DireDawa", "Jijiga","Gonder","Hareri","Mekelle");
        adress.setItems(datalist); 
     }
   //insert data that accept from user and save it on database.......
  @FXML
    public void insert() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql=("INSERT INTO student(studentid,surname,Age,gender,address,date,image,current) VALUES (?,?,?,?,?,?,?,?)");

         try{
           //check thaat all the field is empty or not ..........  
             if(surname.getText().isEmpty()
                     |age.getText().isEmpty()
                     |gender.getSelectionModel().isEmpty()|adress.getSelectionModel().isEmpty()|
                    image_view.getImage()==null){
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERror");
           alert.setHeaderText(null);
           alert.setContentText("Please fill all the above information......");
           alert.showAndWait();      
             }
             else{
             prepare=connect.prepareStatement(sql);
           
            prepare.setString(1,id.getText());
            prepare.setString(2,surname.getText());
            prepare.setString(3,age.getText());
            prepare.setString(4, gender.getSelectionModel().getSelectedItem().toString());
            prepare.setString(5,adress.getSelectionModel().getSelectedItem().toString());
             prepare.setString(6,date.getValue().toString());
             prepare.setString(7,file_path.getText());
               prepare.setString(8,"");
            
           prepare.executeUpdate();   
          
           showData();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("valid");
           alert.setHeaderText(null);
           alert.setContentText("your data successfully added thank you......");
           alert.showAndWait();
           clear();
                       }
         }catch(SQLException e){
            
          System.out.println("your id is taken....."+e);
         }
    }
    //used to select data from database and table view ........
    public void selectData(){
        
        int num=table_view.getSelectionModel().getSelectedIndex();
        Data data1=table_view.getSelectionModel().getSelectedItem();
         Date date=new Date();
         DateFormat format=new SimpleDateFormat("YYYY-MM-dd");
         String dateFormat=format.format(date);
        if(num-1 < -1)
           return;
       id.setText(String.valueOf(data1.getId()));
         surname.setText(data1.getSurname());
         age.setText(data1.getAge());
          gender.getSelectionModel().clearSelection();
           adress.getSelectionModel().clearSelection();
          file_path.setText(data1.getImage());
         Image image= new Image("file:"+data1.getImage(),110,110,true,false);
            image_view.setImage(image);
    }
    //used to clear all information insert on the field .........
    @FXML
  public void clear(){
      id.setText("");
      surname.setText("");
      age.setText("");
      gender.getSelectionModel().clearSelection();
      adress.getSelectionModel().clearSelection();
      date.setValue(null);
  }

//used to update the existing data if we want ........
   public void update() throws ClassNotFoundException, SQLException{
      
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect = null;
      try {
          connect = DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
      } catch (SQLException ex) {
          Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
      }
      String value1=id.getText();
       String value2=surname.getText();
       String value3=age.getText();
     // String value4=date.setValue(datel);
       String value5=gender.getSelectionModel().getSelectedItem();
        String value6=adress.getSelectionModel().getSelectedItem();
         String value7=file_path.getText();
         //syntax used to update value on database 
         String sql="update student set studentid='"+value1+"',surname='"+value2+"',Age= '"+value3+
                "',gender='"+value5+
                  "',address='"+value6+"',image='"+value7+
                 "'where studentid='"+value1+"'";        
         try{
             if(surname.getText().isEmpty()
                     |age.getText().isEmpty()
                     |gender.getSelectionModel().isEmpty()|adress.getSelectionModel().isEmpty()|
                    image_view.getImage()==null)
             {
             Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("Please select one.....");
           alert.showAndWait();
             }
              prepare=connect.prepareStatement(sql);
              prepare.execute();
           showData();
           Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Valid");
           alert.setHeaderText(null);
           alert.setContentText("your data updated ......");
           alert.showAndWait();
         }catch(Exception e){
            System.out.println(""+e);
         }
   }
   
   //used to delet data from both database and tableview........
   public void delet() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
       String sql="delete from student where studentid=?";
       try{
           if(id.getText().isEmpty()){
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText(null);
           alert.setContentText("please enter id ......");
           alert.showAndWait();   
           }
           else{
          
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("conformation Message");
           alert.setHeaderText(null);
           alert.setContentText("Are you sure? ......");
            Optional<ButtonType> option=alert.showAndWait();
            if(option.get() !=ButtonType.OK)
                return;
            else{
           prepare=connect.prepareStatement(sql);
           prepare.setString(1,id.getText());
           prepare.execute();
            showData();
            clear();
           }
           }
       }catch(Exception e){
           System.out.println(""+e);
       }
   }
   
    //student recored page start here .......
   //we have to give value for any regester data "Enrolle","Inactive","Graduate" then it become valid student ..........
   //we use currenr combobox that have the three choice.........
     public void SRfielddesign(){
         if(sr_current.isFocused()){
             sr_current.setStyle("-fx-background-color:#fff; -fx-border-width:2px");
              sr_id.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
         }
         else if(sr_id.isFocused()){
               sr_current.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
              sr_id.setStyle("-fx-background-color:#ff; -fx-border-width:2px");
         }
         else{
             sr_current.setStyle("-fx-background-color:transparent; -fx-border-width:1px");
              sr_id.setStyle("-fx-background-color:transparent; -fx-border-width:1px"); 
         }
     }
     //combox that haave the three value .......
     @FXML
    public void SRcomboBox(){
        ObservableList<String> sr=observableArrayList("Enrolled","Inactive","Graduate");
        sr_current.setItems(sr); 
    }
    //like the above used to show student ehich is valid from the database......
    public ObservableList<Data> StudentRecordlist() throws ClassNotFoundException, SQLException{
        
        ObservableList<Data> listdata=FXCollections.observableArrayList();
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
        String sql="select * from student";
        try{
            statement=connect.createStatement();
            result=statement.executeQuery(sql);
            Data data;
            while(result.next()){
                data=new Data(result.getInt("studentid"), result.getString("surname"),result.getString("date")
                        ,result.getString("gender"),result.getString("current"),result.getString("image"));
                               listdata.addAll(data);
                     
            }
        }catch(Exception e){
            
        }
        return listdata;
    }  
    public void StudentRecordShow() throws ClassNotFoundException,SQLException{
        try {
          ObservableList<Data> show= listData();
       
         sr_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_sr_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
           col_sr_current.setCellValueFactory(new PropertyValueFactory<>("current"));
          col_sr_image.setCellValueFactory(new PropertyValueFactory<>("image"));
          sr_col_gen.setCellValueFactory(new PropertyValueFactory<>("gen"));
        sr_table_view.setItems(show);
      } catch (SQLException | ClassNotFoundException ex) {
          Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
      }
 
    }
    //select from student recorde.....
    public void selectStudentRecored(){
        int num=sr_table_view.getSelectionModel().getSelectedIndex();
        Data connectiondata=sr_table_view.getSelectionModel().getSelectedItem();
        if(num-1 < -1)
           return;
        sr_id.setText(String.valueOf(connectiondata.getId()));
        sr_current.getSelectionModel().clearSelection();
         sr_surname.setText(connectiondata.getSurname());

    }
    //used to update student to become valid using current combobox........
    public void studentRecordUpdate() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
          String value1=sr_current.getSelectionModel().getSelectedItem();
             String sql="update student set current='"+value1+"'where studentid='"+sr_id.getText()+"'";
         try{
            if(sr_current.getSelectionModel().isEmpty()|sr_id.getText().isEmpty()){
                Alert alert=new Alert(AlertType.ERROR);
                alert.setTitle("error..");
                alert.setHeaderText(null);
                alert.setContentText("select student data");
                alert.showAndWait();
            }
            else{
             prepare=connect.prepareStatement(sql);
              prepare.execute();
              StudentRecordShow();
                Alert alert=new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Valid..");
                alert.setHeaderText(null);
                alert.setContentText("student become valid ......");
                alert.showAndWait();
            } 
         }catch(Exception e){
             System.out.println(""+e);
         }
    }
    //clear student information from student recorde page.........
    public void studentRecordclear(){
        sr_id.setText("");
        sr_surname.setText("");
        sr_current.getSelectionModel().clearSelection();
    }
    //used to disply total amount of studnet that become valid not become regester only on home page.....
    public void totalstudent() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where current!=''";
         try{
             prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
             while(result.next()){
                 String totalstudent=result.getString("count(surname)");
                 total_student.setText(totalstudent);
             }
         }
         catch(SQLException e){
            System.out.print(""+e); 
         }
    }
     //used to disply total amount of graduate students that become valid not become regester only on home page.....
    public void totalGraduate() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where current='Graduate'";
         try{
               prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
              while(result.next()){
                 String totalgraduate=result.getString("count(surname)");
                 total_graduate.setText(totalgraduate);
             }
         }catch(Exception e){
            System.out.print(""+e);  
         }
    }
     //used to disply total amount of Enroll student  that become valid not become regester only on home page.....
     public void totalEnrolle() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where current ='Enrolled'";
         try{
               prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
              while(result.next()){
                 String totalEnrolle=result.getString("count(surname)");
                 total_enrolled.setText(totalEnrolle);
             }
         }catch(Exception e){
            System.out.print(""+e);  
         }
    }
      //used to disply total amount of inactive student that become valid not become regester only on home page.....
      public void totalinactive() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where current ='Inactive'";
         try{
               prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
              while(result.next()){
                 String totalinactive=result.getString("count(surname)");
                 total_inactive.setText(totalinactive);
             }
         }catch(Exception e){
            System.out.print(""+e);  
         }
      }
      //total male student .......
       public void totalmale() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where gender ='Male'";
         try{
               prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
              while(result.next()){
                 String totalmale=result.getString("count(surname)");
                 boy.setText(totalmale);
             }
         }catch(Exception e){
            System.out.print(""+e);  
         }
      }
        public void totalfemale() throws ClassNotFoundException, SQLException{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select count(surname) from student where gender ='Female'";
         try{
               prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
              while(result.next()){
                 String totalfemale=result.getString("count(surname)");
                 female.setText(totalfemale);
             }
         }catch(Exception e){
            System.out.print(""+e);  
         }
      }
      @FXML
   
    //home nav
    void change_form(ActionEvent event)throws ClassNotFoundException,SQLException {
   home_page.setVisible(true);
  recored_page.setVisible(false);
  student_page.setVisible(false);
  Data_page.setVisible(false);
  nav_chart.setVisible(false);
  StudentRecordShow();
       
  }
    //student nav
    public void student_form(){
     home_page.setVisible(false);
     recored_page.setVisible(false);
     student_page.setVisible(true);
    Data_page.setVisible(false);
    nav_chart.setVisible(false);
    
    showData();
        
    }
    //record nav
     public void record_form()throws ClassNotFoundException, SQLException{
        home_page.setVisible(false);
         recored_page.setVisible(true);
         student_page.setVisible(false);
         Data_page.setVisible(false);
         nav_chart.setVisible(false);
          totalstudent();
          totalGraduate();
          totalEnrolle();
          totalinactive();
          totalmale();
          totalfemale();
    }
     //data nav
      public void data_form(){
      home_page.setVisible(false);
      recored_page.setVisible(false);
       student_page.setVisible(false);
       Data_page.setVisible(true);
       nav_chart.setVisible(true);
    }
      //dashboard data analysis....
      public void navigationchartButton(){
          if(line_chart_button.isFocused()){
              line_chart_page.setVisible(true);
              bar_chart_page.setVisible(false);
              area_chart_page.setVisible(false);
          }
          else if(bar_chart_button.isFocused()){
               line_chart_page.setVisible(false);
              bar_chart_page.setVisible(true);
              area_chart_page.setVisible(false);
          }
          else if(area_chart_button.isFocused())
          {
              line_chart_page.setVisible(false);
              bar_chart_page.setVisible(false);
              area_chart_page.setVisible(true);
          }
      }
      public void showChart() throws ClassNotFoundException, SQLException{
          Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="SELECT count(surname),date FROM student where current != ''ORDER BY date asc";
         XYChart.Series<String,Integer> chart=new  XYChart.Series<>();
         chart.setName("student");
         while(result.next()){
             Integer count=Integer.parseInt(result.getString("count(surname)"));
             chart.getData().add(new XYChart.Data<>(result.getString("date"),count));
         }
         try{
             prepare=connect.prepareStatement(sql);
             result=prepare.executeQuery();
             if(line_chart_page.isVisible()){
             linechart.getData().add(chart);
             }
             else if(bar_chart_page.isVisible()){
                 barchart.getData().add(chart);
             }
             else if(area_chart_page.isVisible()){
                 areaChart.getData().add(chart);
             }
         }catch(Exception e){
          System.out.println(""+e);   
         }
      }
      //;oug out action here
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
     @Override
    public void initialize(URL url, ResourceBundle rb) {
     
          account();
          navbutton();
          navbuttonhover();
          navbuttonHover();
         showData();
     
      try {
          StudentRecordShow();
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
      }
      
     //combox selecter
     comboBox();
     comboBox2();
    //student record
    SRcomboBox();
    
    } 
}
