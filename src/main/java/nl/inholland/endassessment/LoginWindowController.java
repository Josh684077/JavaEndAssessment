package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Database;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {

    private Database database;
    private String optionalDatabaseErrorMessage;

    //FXML Elements
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblLoginError = new Label();



    //Constructor
    public LoginWindowController(Database database) {
        this.database = database;
        //No database error message is passed
        optionalDatabaseErrorMessage = "";
    }
    //Constructor with error message in case of db error
    public LoginWindowController(Database database, String errorMessage) {
        this.database = database;
        //When database error message is passed, store into variable for use in initialization method
        optionalDatabaseErrorMessage = errorMessage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblLoginError.setText(optionalDatabaseErrorMessage);
    }

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
