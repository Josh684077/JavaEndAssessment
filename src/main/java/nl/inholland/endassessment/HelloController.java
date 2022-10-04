package nl.inholland.endassessment;

import Models.Database;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {

    private Database database = new Database();

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    //On pressing login button
    @FXML
    protected void onBtnLoginClick(){
        //Retrieve filled in username and password
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        //Create user object
        User loginUser = new User(username, password);

        //Validate login
        int loginCode = database.validateLogin(loginUser);

        switch (loginCode){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }

    }
}