package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import CustomExceptions.ItemAvailabilityException;
import CustomExceptions.NoSelectedElementException;
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
    @FXML
    Label lblCollectionError = new Label();

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
    @FXML
    Label lblMembersError = new Label();

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

    //Members buttons
    @FXML
    Button btnMemberAdd;
    @FXML
    Button btnMemberUpdate;
    @FXML
    Button btnMemberDelete;

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

    public void loadItems(){

        //Reload observable list from database
        items = null;
        items = FXCollections.observableArrayList(database.getItems());

        //Load items list into collection tableview
        colBookId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
        colBookTitle.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        colBookAuthor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAuthor()));
        colBookAvailable.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAvailableAsString()));
        tblCollection.setItems(items);

        tblCollection.refresh();
    }

    public void loadMembers(){

        //Reload observable lists from database
        members = null;
        members = FXCollections.observableArrayList(database.getMembers());

        //Load members list into members tableview
        colMemberId.setCellValueFactory(cell -> new SimpleStringProperty(Integer.toString(cell.getValue().getId())));
        colMemberFirstName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFirstName()));
        colMemberLastName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getLastName()));
        colMemberBirthDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateOfBirthAsString()));
        tblMembers.setItems(members);

        tblMembers.refresh();
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

            //Update item and refresh tableview
            item.setIsAvailable(false);
            tblCollection.refresh();

            //Display success popup
            clearElements();
            lblLendingPopup.setTextFill(Color.BLACK);
            lblLendingPopup.setText(item.getTitle() + " has been lent to " + borrower.getFirstName() + " " + borrower.getLastName());
        }
        catch(NumberFormatException e){ //If int parse fails
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
                throw new EmptyTextboxException("Please enter an Item ID.");

            int itemID = Integer.parseInt(txtReceivingBookId.getText());

            Item item = database.getItemById(itemID);

            if(item.getIsAvailable())
                throw new ItemAvailabilityException("This item has not been lent out.");

            //Update item and refresh tableview
            item.setIsAvailable(true);
            tblCollection.refresh();

            //Display success popup
            clearElements();
            lblReceivePopup.setTextFill(Color.BLACK);
            lblReceivePopup.setText(item.getTitle() + " has been returned and is available again.");

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
        clearElements();
    }

    @FXML
    protected void onBtnItemUpdateClick(){
        try {
            if(tblCollection.getSelectionModel().selectedItemProperty().isNull().get())
                throw new NoSelectedElementException("Select an item to update.");

            Item item = tblCollection.getSelectionModel().getSelectedItem();

            LibraryApplication.openUpdateItemDialogue(database, this, item);
            clearElements();

        }
        catch (Exception e){
            lblCollectionError.setText(e.getMessage());
        }

    }

    @FXML
    protected void onBtnItemDeleteClick(){
        try {
            if(tblCollection.getSelectionModel().selectedItemProperty().isNull().get())
                throw new NoSelectedElementException("Select an item to delete.");

            Item item = tblCollection.getSelectionModel().getSelectedItem();
            database.deleteItem(item);
            clearElements();
            loadItems();

        }
        catch (Exception e){
            lblCollectionError.setText(e.getMessage());
        }
    }

    @FXML
    protected void onBtnMemberAddClick(){
        LibraryApplication.openAddMemberDialogue(database, this);
        clearElements();
    }

    @FXML
    protected void onBtnMemberUpdateClick(){
        try {
            if(tblMembers.getSelectionModel().selectedItemProperty().isNull().get())
                throw new NoSelectedElementException("Select a member to update.");

            Member member = tblMembers.getSelectionModel().getSelectedItem();
            LibraryApplication.openUpdateMemberDialogue(database, this, member);
            clearElements();
        }
        catch (Exception e){
            lblMembersError.setText(e.getMessage());
        }
    }

    @FXML
    protected void onBtnMemberDeleteClick(){
        try {
            if(tblMembers.getSelectionModel().selectedItemProperty().isNull().get())
                throw new NoSelectedElementException("Select a member to delete.");

            Member member = tblMembers.getSelectionModel().getSelectedItem();
            database.deleteMember(member);
            clearElements();
            loadMembers();
        }
        catch (Exception e){
            lblMembersError.setText(e.getMessage());
        }
    }

    //Pressing enter after typing input into a text-field will trigger the corresponding button
    @FXML
    protected void onTxtLendingBookIdAction(){
        onBtnLendOutClick();
    }
    @FXML
    protected void onTxtLendingMemberIdAction(){
        onBtnLendOutClick();
    }
    @FXML
    protected void onTxtReceivingBookIdAction(){
        onBtnReceiveClick();
    }



    private void clearElements(){
        //Clear textboxes and labels
        txtLendingBookId.setText("");
        txtLendingMemberId.setText("");
        txtReceivingBookId.setText("");
        lblLendingPopup.setText("");
        lblReceivePopup.setText("");
        lblCollectionError.setText("");
        lblMembersError.setText("");
    }
}
