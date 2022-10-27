package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Database;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {

    private Database database = new Database();

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblLoginError = new Label();

    //On pressing login button
    @FXML
    protected void onBtnLoginClick(){
        try {
            if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty())
                throw new EmptyTextboxException("Please enter a username and a password.");

            //Validate login
            User loggedInUser = database.loginUser(txtUsername.getText(), txtPassword.getText());

            LibraryApplication.openMainWindow(loggedInUser, database);
        }
        catch (Exception ex) {
            lblLoginError.setText(ex.getMessage());
        }
    }

    @FXML
    protected void onTxtUsernameAction(){
        onBtnLoginClick();
    }

    @FXML
    protected void onTxtPasswordAction(){
        onBtnLoginClick();
    }
}
