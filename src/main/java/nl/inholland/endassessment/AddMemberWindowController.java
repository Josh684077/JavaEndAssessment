package nl.inholland.endassessment;

import Models.Database;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMemberWindowController implements Initializable {

    Database database;

    public AddMemberWindowController(Database database) {
        this.database = database;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
