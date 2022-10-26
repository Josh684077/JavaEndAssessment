package nl.inholland.endassessment;

import Models.Book;
import Models.Database;
import Models.Member;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    private User loggedInUser;

    private Database database;

    //Main pane
    @FXML
    AnchorPane mainPane;
    @FXML
    Label lblLibraryTitle;
    @FXML
    Label lblWelcomeUser = new Label();
    @FXML
    TabPane tabControl;

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
    Label lblLendingBookTitle = new Label();
    @FXML
    Label lblLendingMemberName = new Label();
    @FXML
    Label lblLendingError = new Label();
    @FXML
    Label lblReceivingError = new Label();

    //---Collection Tab---
    @FXML
    Tab collectionTab;

    //Collection table
    @FXML
    TableView<Book> tblCollection;
    @FXML
    TableColumn<Book, String> colBookId;
    @FXML
    TableColumn<Book, String> colBookTitle;
    @FXML
    TableColumn<Book, String> colBookAuthor;
    @FXML
    TableColumn<Book, String> colBookAvailable;

    //Collection buttons
    @FXML
    Button btnBookAdd;
    @FXML
    Button btnBookUpdate;
    @FXML
    Button btnBookDelete;



    //---Members Tab---
    @FXML
    Tab membersTab;

    //Members table
    @FXML
    TableView<Member> tblMembers;
    @FXML
    TableColumn<Member, String> colMemberId;
    @FXML
    TableColumn<Member, String> colMemberFirstName;
    @FXML
    TableColumn<Member, String> colMemberLastName;
    @FXML
    TableColumn<Member, String> dateOfBirth;




    public MainWindowController(User user){
        this.loggedInUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblWelcomeUser.setText("Welcome, " + loggedInUser.getName());
        database = new Database();
    }

    @FXML
    protected void onBtnLendOutClick(){

        clearTextboxes();
    }

    @FXML
    protected void onBtnReceiveClick(){

        clearTextboxes();
    }

    @FXML
    protected void onBtnBookAddClick(){

    }

    @FXML
    protected void onBtnBookUpdateClick(){

    }

    @FXML
    protected void onBtnBookDeleteClick(){

    }

    @FXML
    protected void onBtnMemberAddClick(){

    }

    @FXML
    protected void onBtnMemberUpdateClick(){

    }

    @FXML
    protected void onBtnMemberDeleteClick(){

    }

    private void loadAddBookPane(){

    }

    private void loadAddMemberPane(){

    }

    private void loadUpdateBookPane(){

    }

    private void loadUpdateMemberPane(){

    }

    private void clearTextboxes(){
        //Clear textboxes and labels
        txtLendingBookId.setText("");
        txtLendingMemberId.setText("");
        txtReceivingBookId.setText("");
        lblLendingBookTitle.setText("");
        lblLendingMemberName.setText("");
    }
}
