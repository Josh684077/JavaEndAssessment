package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import CustomExceptions.WrongInputException;
import Models.Database;
import Models.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AddMemberWindowController implements Initializable {

    Database database;
    Stage stage;
    DateTimeFormatter dateFormatter;

    //FXML Elements
    @FXML
    Label lblItemTitle = new Label();
    @FXML
    Label lblMemberError = new Label();
    @FXML
    Button btnMemberAction = new Button();
    @FXML
    TextField txtMemberCode = new TextField();
    @FXML
    TextField txtMemberFirstName = new TextField();
    @FXML
    TextField txtMemberLastName = new TextField();
    @FXML
    DatePicker datePicker = new DatePicker();

    public AddMemberWindowController(Database database, Stage stage) {
        this.database = database;
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Specify texts for adding members
        lblItemTitle.setText("Add Member");
        btnMemberAction.setText("Add Member");

        //Initialise date formatter
        dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Configure datepicker behaviour
        datePicker.setValue(LocalDate.now());
        configureDatePicker();

        //Put next member ID into uneditable text-field
        txtMemberCode.setText(Integer.toString(database.getNextMemberId()));
    }

    @FXML
    protected void onBtnMemberActionClick(){
        try {
            if (txtMemberFirstName.getText().isEmpty() || txtMemberLastName.getText().isEmpty() || datePicker.getEditor().getText().isEmpty())
                throw new EmptyTextboxException("Enter input into all text-fields.");

            //Fetch input and create (partial) member object
            String firstName = txtMemberFirstName.getText();
            String lastName = txtMemberLastName.getText();
            LocalDate dateOfBirth = datePicker.getConverter().fromString(datePicker.getEditor().getText());

            Member newMember = new Member(firstName, lastName, dateOfBirth);

            //Pass member object to db where member ID will be set
            database.addMember(newMember);

            //Close stage
            stage.close();
        }
        catch (DateTimeParseException e){
            lblMemberError.setText("Date of birth must be formatted as dd-mm-yyyy.");
        }
        catch (Exception e){
            lblMemberError.setText(e.getMessage());
        }
    }

    private void configureDatePicker(){
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {

                return localDate.format(dateFormatter);
            }

            @Override
            public LocalDate fromString(String s) {
                return LocalDate.parse(s, dateFormatter);
            }
        });
    }
}
