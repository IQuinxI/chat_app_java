package ma.emsi.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ma.emsi.App;
import ma.emsi.Database.UserDB;
import ma.emsi.StateManagement.Session;

public class PrimaryController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {    
        usernameTextField.setText("username0");
        passwordPasswordField.setText("password0");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        if(login()) App.setRoot("chatHub");
    }

    // calls UserDB to verify login information
    private boolean login() {
        new UserDB().Login(usernameTextField.getText(), passwordPasswordField.getText());
        if(Session.getCurrentUser() != null)
            return true;
        else return false;
    }
}
