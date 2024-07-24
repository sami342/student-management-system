/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardTeacherController implements Initializable {

    @FXML
    private Label user;
    @FXML
    private FontAwesomeIcon cog_btn;
    @FXML
    private AnchorPane logout_card;
    @FXML
    private Button logout_button;
    @FXML
    private FontAwesomeIcon edit;
    @FXML
    private TextField Stud_id;

    @FXML
    private Button searech;

    @FXML
    private TextField stude_name;

       @FXML
    private TextField stude_gen;

    @FXML
    private TextField stude_age;

    @FXML
    private TextField stude_current;
        @FXML
    private TextField java;

    @FXML
    private TextField net;

    @FXML
    private TextField db;

    @FXML
    private TextField ip;

    @FXML
    private TextField auto;

    @FXML
    private TextField phyton;
        @FXML

    
    private TableView<Data> table_view;
    
    @FXML
    private TableColumn<Data,Integer> col_sid;

    @FXML
    private TableColumn<Data, String> col_sname;

    @FXML
    private TableColumn<Data,Integer> col_java;

    @FXML
    private TableColumn<Data,Integer>col_net;

    @FXML
    private TableColumn<Data,Integer>col_db;

    @FXML
    private TableColumn<Data,Integer>co_ip;

    @FXML
    private TableColumn<Data,Integer>col_auto;

    @FXML
    private TableColumn<Data,Integer> col_phy;

    @FXML
    private TableColumn<Data,Integer>col_tot;

    @FXML
    private TableColumn<Data,String> col_grad;
    
    @FXML
    private Label studentlable;
     //tool for connect  datatbase
    private Connection connect=null;
    private Statement statement=null;
    private PreparedStatement prepare;
    private ResultSet result=null;
  
        @FXML
    private AnchorPane navbar;
    @FXML
    private Button home;
    @FXML
    private Button updatebtn;

   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   showData();
    }    
    
  
    @FXML
    private void Exit(ActionEvent event) {
       System.exit(0); 
    }
    public ObservableList<Data> listData()throws SQLException, ClassNotFoundException{
         ObservableList<Data> dataList=FXCollections.observableArrayList();
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="SELECT * FROM `studentmark`";
         try{
             statement=connect.createStatement();
             result= statement.executeQuery(sql); 
             Data data1;
             while(result.next()){
                 data1 =new Data(result.getInt("studentid"),
                           result.getString("studentname"),
                           result.getInt("java"),
                           result.getInt("networking"),
                           result.getInt("db"),
                           result.getInt("ip"),
                            result.getInt("automata"),
                         result.getInt("phy"),
                              result.getInt("total"),
                         result.getString("grade"));
                 dataList.addAll(data1); 
                 
             }
         }catch(Exception e){
           e.printStackTrace(); 
         }
        return dataList;  
     }
       public void showData(){
        
      try {
          ObservableList<Data> show= listData();
       
          col_sid.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_sname.setCellValueFactory(new PropertyValueFactory<>("surname"));
          col_java.setCellValueFactory(new PropertyValueFactory<>("java"));
          col_net.setCellValueFactory(new PropertyValueFactory<>("netw"));
          col_db.setCellValueFactory(new PropertyValueFactory<>("databas"));
          co_ip.setCellValueFactory(new PropertyValueFactory<>("interpr"));
          col_auto.setCellValueFactory(new PropertyValueFactory<>("automat"));
           col_phy.setCellValueFactory(new PropertyValueFactory<>("phyton"));
            col_tot.setCellValueFactory(new PropertyValueFactory<>("total"));
             col_grad.setCellValueFactory(new PropertyValueFactory<>("grade"));
        table_view.setItems(show);
      } catch (SQLException | ClassNotFoundException ex) {
        System.out.println(""+ex);
      }
 
    }
      public void clear(){
      Stud_id.setText("");
      stude_name.setText("");
      stude_age.setText("");
      stude_gen.setText("");
      stude_current.setText("");
      java.setText("");
      net.setText("");
      db.setText("");
      ip.setText("");
      auto.setText("");
      phyton.setText("");
      
  }
     public void update() throws ClassNotFoundException, SQLException{
                try{
        Class.forName("com.mysql.cj.jdbc.Driver");
     Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
      String value1=java.getText();
       String value2=net.getText();
       String value3=db.getText();
       String value5=ip.getText();
        String value6=auto.getText();
         String value7=phyton.getText();
         String value8=Stud_id.getText();
         //syntax used to update value on database 
         String sql="update studentmark set java='"+value1+"',networking='"+value2+"',db= '"+value3+
                "',ip='"+value5+
                  "',automata='"+value6+"',phy='"+value7+
                 "'where studentid='"+value8+"'";        
       
            
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
    @FXML
    public void search(){
        try{
       Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql="select * from student where studentid= ?";
         prepare=connect.prepareStatement(sql);
         prepare.setString(1,Stud_id.getText());
         result=prepare.executeQuery();
         if(result.next()){
             stude_name.setText(result.getString(2));
              stude_gen.setText(result.getString(4));
              stude_age.setText(result.getString(3));
              stude_current.setText(result.getString(8));          
         }
         else{
             JOptionPane.showMessageDialog(null,"welcom");
         }
    }
        catch(Exception e){
            System.out.println(""+e);
        }
    }
     public void selectData(){
        
        int num=table_view.getSelectionModel().getSelectedIndex();
        Data data1=table_view.getSelectionModel().getSelectedItem();
         Date date=new Date();
         DateFormat format=new SimpleDateFormat("YYYY-MM-dd");
         String dateFormat=format.format(date);
        if(num-1 < -1)
           return;
        Stud_id.setText(String.valueOf(data1.getId()));
       java.setText(String.valueOf(data1.getJava()));
         net.setText(String.valueOf(data1.getNetw()));
         db.setText(String.valueOf(data1.getDatabas()));
          ip.setText(String.valueOf(data1.getInterpr()));
           auto.setText(String.valueOf(data1.getAutomat()));
          phyton.setText(String.valueOf(data1.getPhyton()));
        
    }
    //used to clear all information insert on the field ........
 
 
    public void insert(){
       int mark1=Integer.parseInt(java.getText());
       int mark2=Integer.parseInt(net.getText());
       int mark3=Integer.parseInt(db.getText());
       int mark4=Integer.parseInt(ip.getText());
       int mark5=Integer.parseInt(auto.getText());
       int mark6=Integer.parseInt(phyton.getText());
       int total=mark1+mark2+mark3+mark4+mark5+mark6;
       int totava=total/6;
       String gra;
        if(totava<=100&&totava>89){
           gra="A+";
          
        }
        else if(totava<=89&&totava>84){
           gra="A";
        }
        else if(totava<=84&&totava>79){
           gra="A-";
           
        }
        else if(totava<=79&&totava>74){
           gra="B+";
          
        }
        else if(totava<=74&&totava>69){
           gra="B";
           
        }
        else if(totava<=69&&totava>64){
           gra="B";
         
        }
        else if(totava<=64&&totava>59){
           gra="B-";
            
        }
        else if(totava<=59&&totava>54){
           gra="C+";
            
        }
        else if(totava<=54&&totava>59){
           gra="C";
           
        }
        else if(totava<=49&&totava>44){
           gra="C-";
            
        }
        else if(totava<=44&&totava>40){
           gra="D";
         
        }
        else if(totava<40&&totava>0){
           gra="F";
           
        }
        else
            gra="unvalid";
           
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connect= DriverManager.getConnection("jdbc:mysql://localhost/scholmanagment","root","");
         String sql=("INSERT INTO studentmark(studentid,studentname,java,networking,db,ip,automata,phy,total,grade) VALUES (?,?,?,?,?,?,?,?,?,?)");

         prepare=connect.prepareStatement(sql);
           JOptionPane.showMessageDialog(null,"thired");
            prepare.setString(1,Stud_id.getText());
            prepare.setString(2,stude_name.getText());
            prepare.setString(3,java.getText());
            prepare.setString(4, net.getText());
            prepare.setString(5,db.getText());
             prepare.setString(6,ip.getText());
             prepare.setString(7,auto.getText());
               prepare.setString(8,phyton.getText());
               
             prepare.setInt(9,totava);
              prepare.setString(10,gra);
           prepare.executeUpdate();  
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Valid..");
                alert.setHeaderText(null);
                alert.setContentText("insert successfully ......");
                alert.showAndWait();
                showData();
        }catch(Exception e){
            System.out.println(""+e);
        }
    }

    @FXML
    private void openlogoutcard(MouseEvent event) {
         cog_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event) {
                 if(!logout_card.isVisible()){
                     logout_card.setVisible(true);
                 }
                 else{
                     logout_card.setVisible(false);
                 }
             }
         });
    }
 private double x=0;
    private double y=0;
    @FXML
    private void logoutAccount(ActionEvent event) {
           logout_button.getScene().getWindow().hide();
          try{
           Parent root=FXMLLoader.load(getClass().getResource("studentloginFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   x=event.getSceneX();
                   y=event.getSceneY();
               }
           });
           
             root.setOnMouseDragged(new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   stage.setX(event.getScreenX()-x);
                   stage.setY(event.getScreenY()-y);
               }
           });
       stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
          }catch(Exception e){
              System.out.println(""+e);
          }
    }

}
