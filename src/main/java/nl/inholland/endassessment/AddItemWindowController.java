package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Database;
import Models.Item;
import Models.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemWindowController implements Initializable {

    Database database;
    Stage stage;

    @FXML
    Label lblItemTitle = new Label();
    @FXML
    Label lblItemError = new Label();
    @FXML
    TextField txtItemCode = new TextField();
    @FXML
    TextField txtItemName = new TextField();
    @FXML
    TextField txtItemAuthor = new TextField();
    @FXML
    Button btnItemAction = new Button();


    public AddItemWindowController(Database database, Stage stage) {
        this.database = database;
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Specify labels for adding items
        btnItemAction.setText("Add Item");
        lblItemTitle.setText("Add Item");

        //Insert item code into uneditable text-field
        txtItemCode.setText(Integer.toString(database.getNextItemId()));
    }

    @FXML
    protected void onBtnItemActionClick(){
        try {
            if (txtItemName.getText().isEmpty() || txtItemAuthor.getText().isEmpty())
                throw new EmptyTextboxException("Please enter input into all text fields.");

            //Fetch input from text-fields and create Item object
            Item newItem = new Item(txtItemName.getText(), txtItemAuthor.getText());
            database.addItem(newItem);

            //Close stage
            stage.close();
        }
        catch (Exception e){

        }

    }

}
