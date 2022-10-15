package nl.inholland.endassessment;

import Models.Database;
import Models.User;
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
    private Label lblLoginTitle;
    @FXML
    private Label lblLoginError = new Label();

    //On pressing login button
    @FXML
    protected void onBtnLoginClick(){

        //If both textboxes have input in them, validate login
        if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            try {
                //Validate login
                User loggedInUser = database.validateLogin(txtUsername.getText(), txtPassword.getText());

                LibraryApplication.openMainWindow(loggedInUser);
            }
            catch (Exception ex) {
                lblLoginError.setText(ex.getMessage());
            }
        }
        else {
            lblLoginError.setText("Please enter a username and a password.");
        }
    }
}