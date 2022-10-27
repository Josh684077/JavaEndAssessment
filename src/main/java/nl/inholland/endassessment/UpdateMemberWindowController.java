package nl.inholland.endassessment;

import Models.Database;
import Models.Item;
import Models.Member;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMemberWindowController implements Initializable {

    Database database;
    Member member;

    public UpdateMemberWindowController(Database database, Member member) {
        this.database = database;
        this.member = member;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
