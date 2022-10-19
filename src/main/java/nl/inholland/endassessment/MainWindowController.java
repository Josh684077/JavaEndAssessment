package nl.inholland.endassessment;

import Models.Book;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    User loggedInUser;

    //Main pane
    @FXML
    AnchorPane mainPane;
    @FXML
    Label lblLibraryTitle;
    @FXML
    Label lblWelcomeUser = new Label();
    @FXML
    TabPane tabPane;

    //Lending/Receiving Tab
    @FXML
    Tab lendingTab;
    @FXML
    TextField txtLendingMemberId;
    @FXML
    TextField txtLendingBookId;
    @FXML
    TextField txtReceivingBookId;
    @FXML
    Label lblLendingBookTitle;
    @FXML
    Label lblLendingMemberName;

    //Collection Tab
    @FXML
    Tab collectionTab;
    @FXML
    TableView<Book> tblCollection;
    @FXML
    Button btnBookAdd;
    @FXML
    Button btnBookUpdate;
    @FXML
    Button btnBookDelete;


    //Members Tab
    @FXML
    Tab membersTab;




    public MainWindowController(User user){
        this.loggedInUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblWelcomeUser.setText("Welcome, " + loggedInUser.getName());
    }
}
