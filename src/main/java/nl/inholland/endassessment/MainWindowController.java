package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Item;
import Models.Database;
import Models.Member;
import Models.User;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

public class MainWindowController implements Initializable{

    private User loggedInUser;

    //Data
    private Database database;
    private ObservableList<Item> items;
    private ObservableList<Member> members;

    //Main pane
    @FXML
    AnchorPane mainPane;
    @FXML
    Label lblLibraryTitle;
    @FXML
    Label lblWelcomeUser = new Label();
    @FXML
    TabPane tabControl;

    //---Lending/Receiving Tab---
    @FXML
    Tab lendingTab;
    @FXML
    TextField txtLendingMemberId;
    @FXML
    TextField txtLendingBookId;
    @FXML
    TextField txtReceivingBookId;
    @FXML
    Label lblLendingPopup = new Label();
    @FXML
    Label lblReceivingPopup = new Label();

    //---Collection Tab---
    @FXML
    Tab collectionTab;

    //Collection table
    @FXML
    TableView<Item> tblCollection;
    @FXML
    TableColumn<Item, String> colBookId;
    @FXML
    TableColumn<Item, String> colBookTitle;
    @FXML
    TableColumn<Item, String> colBookAuthor;
    @FXML
    TableColumn<Item, String> colBookAvailable;

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
    TableColumn<Member, String> colMemberBirthDate;

    //Constructor
    public MainWindowController(User user){
        this.loggedInUser = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Instantiate database
        database = new Database();

        try{
            //Load observable lists from database
            items = FXCollections.observableArrayList(database.getItems());
            members = FXCollections.observableArrayList(database.getMembers());

            //Load items list into collection tableview
            colBookId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
            colBookTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
            colBookAuthor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAuthor()));
            colBookAvailable.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAvailableAsString()));
            tblCollection.setItems(items);

            //Load members list into members tableview
            colMemberId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
            colMemberFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName()));
            colMemberLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastName()));
            colMemberBirthDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateOfBirthAsString()));
            tblMembers.setItems(members);

        }
        catch (Exception e){

        }


        //
        lblWelcomeUser.setText("Welcome, " + loggedInUser.getName());

    }

    @FXML
    protected void onBtnLendOutClick(){
        try{
            if (txtLendingMemberId.getText().isEmpty() || txtLendingBookId.getText().isEmpty()){
                throw new EmptyTextboxException("");
            }

            Member borrower;
            Item item;
        }
        catch(Exception e){
            lblLendingPopup.setText(e.getMessage());
        }

        clearTextboxes();
    }

    @FXML
    protected void onBtnReceiveClick(){
        try{

        }
        catch(Exception e){
            lblReceivingPopup.setText(e.getMessage());
        }
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

    //If enter is pressed after entering book or member id, trigger lend out btn
    @FXML
    protected void onTxtLendingBookIdAction(){
        onBtnLendOutClick();
    }
    @FXML
    protected void onTxtLendingMemberIdAction(){
        onBtnLendOutClick();
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
    }
}
