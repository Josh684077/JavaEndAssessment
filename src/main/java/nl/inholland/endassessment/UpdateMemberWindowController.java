package nl.inholland.endassessment;

import CustomExceptions.EmptyTextboxException;
import Models.Database;
import Models.Item;
import Models.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class UpdateMemberWindowController implements Initializable {

    Database database;
    Stage stage;
    DateTimeFormatter dateFormatter;
    Member member;

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

    //Constructor
    public UpdateMemberWindowController(Database database, Stage stage, Member member) {
        this.database = database;
        this.stage = stage;
        this.member = member;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Specify texts for updating members
        lblItemTitle.setText("Update Member");
        btnMemberAction.setText("Update Member");

        //Fill fields with selected member data
        txtMemberCode.setText(Integer.toString(member.getId()));
        txtMemberFirstName.setText(member.getFirstName());
        txtMemberLastName.setText(member.getLastName());
        datePicker.getEditor().setText(member.getDateOfBirthAsString());

        //Initialise date formatter
        dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Configure datepicker behaviour
        datePicker.setValue(LocalDate.now());
        configureDatePicker();
    }

    @FXML
    protected void onBtnMemberActionClick(){
        try {
            if (txtMemberFirstName.getText().isEmpty() || txtMemberLastName.getText().isEmpty() || datePicker.getEditor().getText().isEmpty())
                throw new EmptyTextboxException("Enter input into all text-fields.");

            //Fetch input and update the member object
            member.setFirstName(txtMemberFirstName.getText());
            member.setLastName(txtMemberLastName.getText());
            member.setDateOfBirth(datePicker.getConverter().fromString(datePicker.getEditor().getText()));

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
