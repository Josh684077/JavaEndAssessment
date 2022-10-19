package nl.inholland.endassessment;

import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {

    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("login-view.fxml"));

        //Set login controller
        fxmlLoader.setController(new LoginWindowController());
        Scene scene = new Scene(fxmlLoader.load(), 360, 250);
        this.stage.setTitle("Login Screen");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openMainWindow(User user) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("main-view.fxml"));
            fxmlLoader.setController(new MainWindowController(user));
            Scene scene = new Scene(fxmlLoader.load(), 730, 400);
            stage.setScene(scene);
        }
        catch (Exception e){
            throw e;
        }
    }
}