package ma.emsi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.emsi.database.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private Button login;


    public void login(){
       validatLogin();
    }


    public void validatLogin(){
        DbConnection connect = new DbConnection();
        Connection conn = connect.getConnection();

        String auth = "SELECT count(1) FROM user WHERE username = '"+username.getText()+ "' AND password = '"+password.getText()+"'";

        try{
            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(auth);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){

                    Stage stage = (Stage) username.getScene().getWindow();
                    FXMLLoader fx = new FXMLLoader(App.class.getResource("room.fxml"));
                    Scene room = new Scene(fx.load());
                    stage.setScene(room);
                }
                else{

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Authentication failed");
                    alert.setHeaderText("Username or Password is not correct");
                    alert.show();
                }
            }


        }catch(Exception e){

        }
    }



}
