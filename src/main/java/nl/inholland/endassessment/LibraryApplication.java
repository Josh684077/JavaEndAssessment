package nl.inholland.endassessment;

import Models.Database;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

        //Set scene and load into stage
        Scene scene = new Scene(fxmlLoader.load(), 360, 250);
        this.stage.setTitle("Login Screen");
        this.stage.setScene(scene);
        this.stage.show();
    }

    //When application is closed
    @Override
    public void stop(){

    }
    public static void main(String[] args) {
        launch();
    }

    public static void openMainWindow(User user, Database database) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("main-view.fxml"));
            fxmlLoader.setController(new MainWindowController(user, database));
            Scene scene = new Scene(fxmlLoader.load(), 730, 400);
            stage.setTitle("Library System");
            stage.setScene(scene);
        }
        catch (Exception e){
            throw e;
        }
    }

    public static void openAddItemDialogue(Database database, MainWindowController controller){
        try{
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("item-view.fxml"));
            fxmlLoader.setController(new AddItemWindowController(database, stage, controller));

            //
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("Add New Item");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void openUpdateItemDialogue(Database database, MainWindowController controller){
        Stage stage = new Stage();

    }

    public static void openAddMemberDialogue(Database database, MainWindowController controller){
        Stage stage = new Stage();

    }

    public static void openUpdateMemberDialogue(Database database, MainWindowController controller){
        Stage stage = new Stage();

    }
}