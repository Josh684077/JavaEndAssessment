module nl.inholland.endassessment {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.inholland.endassessment to javafx.fxml;
    exports nl.inholland.endassessment;
}