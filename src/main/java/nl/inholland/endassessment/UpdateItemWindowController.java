package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Database;
import Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateItemWindowController implements Initializable {

    Database database;
    Stage stage;
    Item item;

    //FXML Elements
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

    public UpdateItemWindowController(Database database, Stage stage, Item item) {
        this.database = database;
        this.stage = stage;
        this.item = item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Specify labels for updating items
        btnItemAction.setText("Update Item");
        lblItemTitle.setText("Update Item");

        //Insert item code into uneditable text-field
        txtItemCode.setText(Integer.toString(item.getId()));

        //Insert item data into editable text-fields
        txtItemName.setText(item.getTitle());
        txtItemAuthor.setText(item.getAuthor());
    }

    @FXML
    private void onBtnItemActionClick(){
        try{
            if (txtItemName.getText().isEmpty() || txtItemAuthor.getText().isEmpty())
                throw new EmptyTextboxException("Please enter input into all text fields.");

            //Retrieve edited data and update the Item on the stack
            item.setTitle(txtItemName.getText());
            item.setAuthor(txtItemAuthor.getText());

            //Close stage
            stage.close();
        }
        catch (Exception e){
            lblItemError.setText(e.getMessage());
        }
    }

}
