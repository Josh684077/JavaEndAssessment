package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import CustomExceptions.ItemAvailabilityException;
import Models.Item;
import Models.Database;
import Models.Member;
import Models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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
    Label lblReceivePopup = new Label();
    @FXML
    Button btnReceive;
    @FXML
    Button btnLendOut;

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
    Button btnItemAdd;
    @FXML
    Button btnItemUpdate;
    @FXML
    Button btnItemDelete;

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
    public MainWindowController(User user, Database database){
        this.loggedInUser = user;
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            loadItems();
            loadMembers();
        }
        catch (Exception e){

        }

        //
        lblWelcomeUser.setText("Welcome, " + loggedInUser.getName());
    }

    private void loadItems(){

        //Load observable list from database
        items = FXCollections.observableArrayList(database.getItems());

        //Load items list into collection tableview
        colBookId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
        colBookTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colBookAuthor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAuthor()));
        colBookAvailable.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAvailableAsString()));
        tblCollection.setItems(items);
    }

    private void loadMembers(){

        //Load observable lists from database
        members = FXCollections.observableArrayList(database.getMembers());

        //Load members list into members tableview
        colMemberId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
        colMemberFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName()));
        colMemberLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastName()));
        colMemberBirthDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateOfBirthAsString()));
        tblMembers.setItems(members);
    }

    @FXML
    protected void onBtnLendOutClick(){
        try{
            if (txtLendingMemberId.getText().isEmpty() || txtLendingBookId.getText().isEmpty())
                throw new EmptyTextboxException("Please enter an Item ID and a Member ID");

            int itemID = Integer.parseInt(txtLendingBookId.getText());
            int memberID = Integer.parseInt(txtLendingMemberId.getText());

            Member borrower = database.getMemberById(memberID);
            Item item = database.getItemById(itemID);

            if(!item.getIsAvailable())
                throw new ItemAvailabilityException("This item is not available for lending.");


            item.setIsAvailable(false);
            database.updateItem(item);
            tblCollection.refresh();

            //Display success popup
            clearElements();
            lblLendingPopup.setTextFill(Color.BLACK);
            lblLendingPopup.setText(item.getTitle() + " has been lent to " + borrower.getFirstName() + " " + borrower.getLastName());
        }
        catch(NumberFormatException e){
            lblLendingPopup.setTextFill(Color.RED);
            lblLendingPopup.setText("Please enter numbers only.");
        }
        catch(Exception e){
            lblLendingPopup.setTextFill(Color.RED);
            lblLendingPopup.setText(e.getMessage());
        }
    }

    @FXML
    protected void onBtnReceiveClick(){
        try{

            if (txtReceivingBookId.getText().isEmpty())
                throw new EmptyTextboxException("Please enter an Item ID");


            int itemID = Integer.parseInt(txtReceivingBookId.getText());

            Item item = database.getItemById(itemID);

            if(item.getIsAvailable())
                throw new ItemAvailabilityException("This item has not been lent out.");

            //Update database and refresh list
            item.setIsAvailable(true);
            database.updateItem(item);
            tblCollection.refresh();

            //Display success popup
            clearElements();
            lblReceivePopup.setTextFill(Color.BLACK);
            lblReceivePopup.setText(item.getTitle() + " has been returned and is available again");

        }
        catch(NumberFormatException e){
            lblReceivePopup.setTextFill(Color.RED);
            lblReceivePopup.setText("Please enter a number only.");
        }
        catch(Exception e){
            lblReceivePopup.setTextFill(Color.RED);
            lblReceivePopup.setText(e.getMessage());
        }

    }

    @FXML
    protected void onBtnItemAddClick(){

        LibraryApplication.openAddItemDialogue(database, this);
    }

    @FXML
    protected void onBtnItemUpdateClick(){
        LibraryApplication.openUpdateItemDialogue(database, this);
    }

    @FXML
    protected void onBtnItemDeleteClick(){

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

    private void clearElements(){
        //Clear textboxes and labels
        txtLendingBookId.setText("");
        txtLendingMemberId.setText("");
        txtReceivingBookId.setText("");
        lblLendingPopup.setText("");
        lblReceivePopup.setText("");
    }
}
