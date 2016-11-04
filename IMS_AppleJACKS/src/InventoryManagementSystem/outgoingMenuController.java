package InventoryManagementSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;


public class outgoingMenuController {

    @FXML
    public Button signOutIMS;

    @FXML
    public Button mainMenuBtn;

    @FXML
    public Button outgoingBtn;

    @FXML
    public Button incomingBtn;

    @FXML
    public Button manageBtn;

    @FXML
    public Button settingsBtn;
<<<<<<< HEAD

    @FXML
    public TableView outgoingDB;

    @FXML
    public Button createOutgoingBtn;

    @FXML
    public Button editOutgoingBtn;



=======
    
    @FXML
    public Button createDepartureBtn;
    
    @FXML
    public Button updateDepartureBtn;
    
    @FXML
    public Button removeDepartureBtn;
>>>>>>> origin/master

    //STAGE AND BUTTON NAVIGATION VARIABLES AND FUNCTIONS:

    Stage stage;
    Parent root;

    public void signOut(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("Sign Out Button Clicked");

        stage=(Stage) signOutIMS.getScene().getWindow();

        stage.setTitle("I.M.S. | Login");

        root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void mainMenu(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("Main Menu Button Clicked");

        stage=(Stage) mainMenuBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Main Menu");

        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void outgoingMenu(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("Outgoing Menu Button Clicked");

        stage=(Stage) outgoingBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Outgoing Shipments Menu");

        root = FXMLLoader.load(getClass().getResource("outgoing.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void incomingMenu(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("Incoming Menu Button Clicked");

        stage=(Stage) incomingBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Incoming Shipments Menu");

        root = FXMLLoader.load(getClass().getResource("incoming.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void manageMenu(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("Manage Menu Button Clicked");

        stage=(Stage) manageBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Manage Inventory Menu");

        root = FXMLLoader.load(getClass().getResource("manageInventory.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void settingsMenu(ActionEvent actionEvent) throws IOException {


        //console output added for QA
        System.out.println("Settings Menu Button Clicked");

        stage=(Stage) settingsBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Settings Menu");

        root = FXMLLoader.load(getClass().getResource("settings.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void createDeparture(ActionEvent actionEvent) throws IOException {

        stage=(Stage) createDepartureBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Create Departure Event Menu");

        root = FXMLLoader.load(getClass().getResource("createDepartureEvent.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void updateDeparture(ActionEvent actionEvent) throws IOException {

        stage=(Stage) updateDepartureBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Update Departure Event Menu");

        root = FXMLLoader.load(getClass().getResource("updateDepartureEvent.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void removeDeparture(ActionEvent actionEvent) throws IOException {

<<<<<<< HEAD
    public void createDepartureEvent(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("CREATE Departure Event Button Clicked");

        stage=(Stage) settingsBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Create Departure Event");

        root = FXMLLoader.load(getClass().getResource("createDeparture.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void modifyDepartureEvent(ActionEvent actionEvent) throws IOException {

        //console output added for QA
        System.out.println("MODIFY Departure Event Button Clicked");

        stage=(Stage) settingsBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Modify Departure Event");

        root = FXMLLoader.load(getClass().getResource("modifyDeparture.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
=======
        stage=(Stage) removeDepartureBtn.getScene().getWindow();

        stage.setTitle("I.M.S. | Remove Departure Event Menu");

        root = FXMLLoader.load(getClass().getResource("removeDepartureEvent.fxml"));
>>>>>>> origin/master

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //TODO: INSERT REMAINING METHODS HERE
}
