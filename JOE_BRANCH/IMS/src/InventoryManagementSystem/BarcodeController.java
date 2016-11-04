package InventoryManagementSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Aisle;
import Model.Barcode;
import Model.Item;
import Model.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BarcodeController implements Initializable {

	ObservableList<String> aisleChoices = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "O");
	ObservableList<String> sectionChoices = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20");

	@FXML
	private TableView<BarcodeItemTable> table;

	@FXML
	private TableColumn<BarcodeItemTable, String> itemNameCol;

	@FXML
	private TableColumn<BarcodeItemTable, Double> weightCol;

	@FXML
	private TableColumn<BarcodeItemTable, String> expirationCol;

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
	private Button createBtn;

	@FXML
	private TextField itemName;

	@FXML
	private TextField number;

	@FXML
	private Label status;

	@FXML
	private Label aisleLbl;

	@FXML
	private Label sectionLbl;

	@FXML
	private Label numberLbl;

	@FXML
	private ComboBox<String> aisleBox;

	@FXML
	private ComboBox<String> sectionBox;

	// STAGE AND BUTTON NAVIGATION VARIABLES AND FUNCTIONS:
	private Stage stage;
	private Parent root;
	private Connection conn;
	private ObservableList<BarcodeItemTable> list = FXCollections.observableArrayList();
	private Section checkSection = new Section();
	private Aisle checkAisle = new Aisle();
	private Item item = new Item();
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		aisleBox.setVisibleRowCount(4);
		aisleBox.setItems(aisleChoices);
		aisleBox.setValue("A");
		sectionBox.setVisibleRowCount(4);
		sectionBox.setItems(sectionChoices);
		sectionBox.setValue("01");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<BarcodeItemTable, String>("itemName"));
		weightCol.setCellValueFactory(new PropertyValueFactory<BarcodeItemTable, Double>("weight"));
		expirationCol.setCellValueFactory(new PropertyValueFactory<BarcodeItemTable, String>("expiration"));
		loadBarcodeItems();
	}

	public void createBarcodeEvent(ActionEvent event) {
		try {
			conn = SQLiteConnection.Connector();
			Barcode barcode = new Barcode();
			if (item.isItem(itemName.getText())) {
				char aisle = aisleBox.getSelectionModel().getSelectedItem().charAt(0);
				String section = sectionBox.getSelectionModel().getSelectedItem();
				if (checkSection.checkSectionFull(section)) {
					status.setText("Section is full!");
				} else {
					if (checkAisle.checkAisleFull(aisleBox.getSelectionModel().getSelectedItem())) {
						status.setText("Aisle is full!");
					} else {
						barcode.setAisleLetter(aisle);
						barcode.setSectionNumber(section);
						if (!number.getText().isEmpty() && !number.getText().trim().matches(".*\\D.*")) {
							int x = Integer.parseInt(number.getText());
							int length = String.valueOf(x).length();
							if (length != 4) {
								status.setText("Incorrect format!");
							} else {
								String itemnumber = number.getText();
								barcode.setItemNumber(itemnumber);
								int itemN = Integer.parseInt(itemnumber);
								if (!barcode.checkBarcode(barcode.getBarcode())) {
									String query = "SELECT * FROM items WHERE itemname = ? AND barcode IS NULL";
									int check = 0;
									ps = conn.prepareStatement(query);
									ps.setString(1, itemName.getText());
									rs = ps.executeQuery();
									while (rs.next()) {
										check++;
									}
									if (check > 1) {
										query = "UPDATE items SET barcode=?, aisle=?, section=?, itemnumber=? WHERE itemname=? AND barcode IS NULL";
										ps = conn.prepareStatement(query);
										ps.setString(1, barcode.getBarcode());
										ps.setString(2, String.valueOf(aisle));
										ps.setString(3, section);
										ps.setInt(4, itemN);
										ps.setString(5, itemName.getText());
										ps.executeUpdate();
										item.removeDups(itemName.getText());
										list.removeAll(list);
										loadBarcodeItems();
										status.setText("Barcode successfully created!");
										number.clear();
										itemName.clear();
									} else {
										query = "UPDATE items SET barcode=?, aisle=?, section=?, itemnumber=? WHERE itemname=? AND barcode IS NULL";
										ps = conn.prepareStatement(query);
										ps = conn.prepareStatement(query);
										ps.setString(1, barcode.getBarcode());
										ps.setString(2, String.valueOf(aisle));
										ps.setString(3, section);
										ps.setInt(4, itemN);
										ps.setString(5, itemName.getText());
										ps.executeUpdate();
										list.removeAll(list);
										loadBarcodeItems();
										status.setText("Barcode successfully created!");
										number.clear();
										itemName.clear();
									}
								} else {
									status.setText("Barcode exists!");
								}
							}
						} else {
							status.setText("Incorrect format!");
						}
					}
				}
			} else {
				status.setText("Item not found!");
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

	public void loadBarcodeItems() {
		try {
			conn = SQLiteConnection.Connector();
			String query = "SELECT * FROM items WHERE barcode IS NULL";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BarcodeItemTable(rs.getString("itemname"), rs.getDouble("weight"),
						rs.getString("expiration")));
				table.setItems(list);
			}
		} catch (SQLException e) {
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
