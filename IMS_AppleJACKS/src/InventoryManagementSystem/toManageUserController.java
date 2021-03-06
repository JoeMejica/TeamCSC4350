package InventoryManagementSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;

public class toManageUserController {
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Button mainMenuBtn;
	@FXML
	private Button outgoingBtn;
	@FXML
	private Button incomingBtn;
	@FXML
	private Button manageBtn;
	@FXML
	private Button settingsBtn;
	@FXML
	private Button signOutIMS;
	@FXML
	public Button loginBtn;
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtUserName;
	@FXML
	private PasswordField loginPassword;

	Stage stage;
	Parent root;

	public void Login(ActionEvent actionEvent) throws IOException {
		try {
			if (loginModel.isLogin(txtUserName.getText(), loginPassword.getText())) {
				if (loginModel.isAdmin(txtUserName.getText(), loginPassword.getText())) {
					stage = (Stage) loginBtn.getScene().getWindow();

					stage.setTitle("I.M.S. | Manage Users Menu");

					// load mainMenu scene
					root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

					Scene scene = new Scene(root, 600, 400);
					stage.setScene(scene);
					stage.show();
				} else {
					lblStatus.setText("ADMIN ONLY!");
				}
			} else {
				lblStatus.setText("Incorrect Password or Username!");
			}
		} catch (SQLException e) {
			lblStatus.setText("Incorrect Password or Username!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void signOut(ActionEvent actionEvent) throws IOException {

		stage = (Stage) signOutIMS.getScene().getWindow();

		stage.setTitle("I.M.S. | Login");

		root = FXMLLoader.load(getClass().getResource("login.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void mainMenu(ActionEvent actionEvent) throws IOException {

		stage = (Stage) mainMenuBtn.getScene().getWindow();

		stage.setTitle("I.M.S. | Main Menu");

		root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void outgoingMenu(ActionEvent actionEvent) throws IOException {

		stage = (Stage) outgoingBtn.getScene().getWindow();

		stage.setTitle("I.M.S. | Outgoing Shipments Menu");

		root = FXMLLoader.load(getClass().getResource("outgoing.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void incomingMenu(ActionEvent actionEvent) throws IOException {

		stage = (Stage) incomingBtn.getScene().getWindow();

		stage.setTitle("I.M.S. | Incoming Shipments Menu");

		root = FXMLLoader.load(getClass().getResource("incoming.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void manageMenu(ActionEvent actionEvent) throws IOException {

		stage = (Stage) manageBtn.getScene().getWindow();

		stage.setTitle("I.M.S. | Manage Inventory Menu");

		root = FXMLLoader.load(getClass().getResource("manageInventory.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void settingsMenu(ActionEvent actionEvent) throws IOException {

		stage = (Stage) settingsBtn.getScene().getWindow();

		stage.setTitle("I.M.S. | Settings Menu");

		root = FXMLLoader.load(getClass().getResource("settings.fxml"));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
