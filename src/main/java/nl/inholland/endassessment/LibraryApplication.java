package nl.inholland.endassessment;

import CustomExceptions.DatabaseException;
import Models.Database;
import Models.Item;
import Models.Member;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {

    private static Stage stage;
    private Database database;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("login-view.fxml"));

        try{
            //Instantiate database
            database = new Database();
            database.loadData();

            //Load Login screen
            fxmlLoader.setController(new LoginWindowController(database));
        }
        catch (DatabaseException exception){
            //If exception is thrown by database, load LoginWindowController with error message
            String errorMessage = "There was a database error. Dummy data loaded. You can still log in.";
            fxmlLoader.setController(new LoginWindowController(database, errorMessage));
        }

        //Set scene and load into stage
        Scene scene = new Scene(fxmlLoader.load(), 360, 250);
        this.stage.setTitle("Login Screen");
        this.stage.setScene(scene);
        this.stage.show();
    }

    //When application is closed
    @Override
    public void stop(){
        database.serialiseEverything();
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
            fxmlLoader.setController(new AddItemWindowController(database, stage));

            //Set scene
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("Add new item");
            stage.setScene(scene);

            //Disable main until stage is closed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            //After stage is closed, update tbl in mainviewcontroller
            controller.loadItems(database.getItems());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void openUpdateItemDialogue(Database database, MainWindowController controller, Item item){
        try{
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("item-view.fxml"));
            fxmlLoader.setController(new UpdateItemWindowController(database, stage, item));

            //Set scene
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("Update item");
            stage.setScene(scene);

            //Disable main until stage is closed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //After stage is closed, update tbl in mainviewcontroller
            controller.loadItems(database.getItems());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void openAddMemberDialogue(Database database, MainWindowController controller){
        try{
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("member-view.fxml"));
            fxmlLoader.setController(new AddMemberWindowController(database, stage));

            //Set scene
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("Add new member");
            stage.setScene(scene);

            //Disable main until stage is closed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //After stage is closed, update tbl in mainviewcontroller
            controller.loadMembers(database.getMembers());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void openUpdateMemberDialogue(Database database, MainWindowController controller, Member member){
        try{
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("member-view.fxml"));
            fxmlLoader.setController(new UpdateMemberWindowController(database, stage, member));

            //Set scene
            Scene scene = new Scene(fxmlLoader.load(), 500, 300);
            stage.setTitle("Update member");
            stage.setScene(scene);

            //Disable main until stage is closed
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //After stage is closed, update tbl in mainviewcontroller
            controller.loadMembers(database.getMembers());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}