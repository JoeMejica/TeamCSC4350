package InventoryManagementSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.DepartureEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RemoveDepartureEventController implements Initializable {

	@FXML
	private TableView<DepartureItemTable> table;

	@FXML
	private TableColumn<DepartureItemTable, String> itemNameCol;

	@FXML
	private TableColumn<DepartureItemTable, String> barcodeCol;

	@FXML
	private TableColumn<DepartureItemTable, Boolean> shippedCol;

	@FXML
	private Button signOutIMS;

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
	private Button removeBtn;

	@FXML
	private TextField barcode;

	@FXML
	private Label status;

	// STAGE AND BUTTON NAVIGATION VARIABLES AND FUNCTIONS:

	private Stage stage;
	private Parent root;
	private Connection conn;
	private ObservableList<DepartureItemTable> list = FXCollections.observableArrayList();
	private DepartureEvent departEvent = new DepartureEvent();
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemNameCol.setCellValueFactory(new PropertyValueFactory<DepartureItemTable, String>("itemName"));
		barcodeCol.setCellValueFactory(new PropertyValueFactory<DepartureItemTable, String>("barcode"));
		shippedCol.setCellValueFactory(new PropertyValueFactory<DepartureItemTable, Boolean>("shipped"));
		loadShippedItems();
	}

	public void removeEvent(ActionEvent event) {
		try {
			conn = SQLiteConnection.Connector();
			if (departEvent.isDepartItem(barcode.getText())) {
				String query = "DELETE FROM departures WHERE barcode = ?";
				ps = conn.prepareStatement(query);
				ps.setString(1, barcode.getText());
				ps.executeUpdate();
//				query = "UPDATE items SET reserved = ? WHERE barcode = ?";
				query = "DELETE FROM items WHERE barcode = ?";
				ps = conn.prepareStatement(query);
//				ps.setBoolean(1, false);
//				ps.setString(2, barcode.getText());
				ps.setString(1, barcode.getText());
				ps.executeUpdate();
				status.setText("Departure event successfully removed!");
				list.removeAll(list);
				loadShippedItems();
				barcode.clear();
			} else {
				status.setText("Barcode not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}
	}

	public void loadShippedItems() {
		try {
			departEvent.createDepartureTable();
			conn = SQLiteConnection.Connector();
			String query = "SELECT * FROM departures WHERE shipped = ?";
			ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new DepartureItemTable(rs.getString("itemname"), rs.getString("barcode"),
						rs.getBoolean("reserved"), rs.getBoolean("pending"), rs.getBoolean("ready"),
						rs.getBoolean("shipped")));
				table.setItems(list);
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */}
			}
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

		root = FXMLLoader.load(getClass().getResource("incomingSubMenu.fxml"));

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
