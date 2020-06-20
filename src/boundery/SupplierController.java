package boundery;


/**
 * This class contains all the supplier gui functionality
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Entity.GasStationOrder;
import Entity.PricingModule;
import Entity.Supplier;
import client.ClientUI;
import client.EmployeeCC;
import client.SupplierCC;
import client.UserCC;
import enums.RatesStatus;
import enums.SupplierOrderStatus;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SupplierController implements Initializable {

	@FXML
	private Pane SupplierOrdersPane;

	@FXML
	private Label OrdersLbl;

	@FXML
	private Text chooseTypeLbl;

	@FXML
	private ComboBox<SupplierOrderStatus> OrderTypeCombox;

	@FXML
	private Text OrderHeader;

	@FXML
	private TableView<GasStationOrder> OrdersTbl;

	@FXML
	private TableColumn<GasStationOrder, Boolean> select;

	@FXML
	private TableColumn<GasStationOrder, Integer> orderId;

	@FXML
	private TableColumn<GasStationOrder, Integer> stationId;

	@FXML
	private TableColumn<GasStationOrder, String> status;

	@FXML
	private TableColumn<GasStationOrder, String> date;

	@FXML
	private TableColumn<GasStationOrder, String> orderPrice;

	@FXML
	private TableColumn<GasStationOrder, String> fuelType;

	@FXML
	private TableColumn<GasStationOrder, Float> quantity;

	@FXML
	private Pane SupplierMainPane;

	@FXML
	private Label NotificationLbl;

	@FXML
	private TextArea NotificationText;

	@FXML
	private Button LogOutBtn;

	@FXML
	private Button MainPaneBtn;

	@FXML
	private Button OrdersBtn;

	@FXML
	private Button UpdateBtn;

	@FXML
	private Text HelloLbl;

	private Pane currentPane;
	public static Supplier supplier;
	private ArrayList<GasStationOrder> selectedOrders = new ArrayList<GasStationOrder>();

	/**
	 * Define the scene of the supplier and load the fxml file
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SupplierBoundary.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	/**
	 * Show the suitable data in the table according to the selected status the
	 * function ask for all the orders according to the selected status (
	 * supplied/confirmed) from the DB and insert to the orders table and display to
	 * the user
	 * @param event
	 */
	
	@FXML
	void chooseOrderType(ActionEvent event) {
		//show the suitable data according to the selected status
		if (OrderTypeCombox.getValue() == SupplierOrderStatus.supplied) {
			OrderHeader.setText("All Supplied Orders: ");
			UpdateBtn.setVisible(false);
		} else {
			OrderHeader.setText("All un-Supplied Orders: ");
			UpdateBtn.setVisible(true);
		}

		String orderType = OrderTypeCombox.getValue().toString();
		// bring all the data of this supplier with this orderType
		//checkbox initializing
		select.setCellValueFactory(
				new Callback<CellDataFeatures<GasStationOrder, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<GasStationOrder, Boolean> param) {
						GasStationOrder order = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(order.getSelect());

						booleanProp.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								if (newValue == true)
									selectedOrders.add(order);
								else
									selectedOrders.remove(order);
								order.setSelect(newValue);
							}
						});
						return booleanProp;
					}
				});

		select.setCellFactory(
				new Callback<TableColumn<GasStationOrder, Boolean>, TableCell<GasStationOrder, Boolean>>() {

					@Override
					public TableCell<GasStationOrder, Boolean> call(TableColumn<GasStationOrder, Boolean> param) {
						CheckBoxTableCell<GasStationOrder, Boolean> cell = new CheckBoxTableCell<GasStationOrder, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		//column define
		orderId.setCellValueFactory(new PropertyValueFactory<GasStationOrder, Integer>("orderID"));
		stationId.setCellValueFactory(new PropertyValueFactory<GasStationOrder, Integer>("stationID"));
		status.setCellValueFactory(new PropertyValueFactory<GasStationOrder, String>("status"));
		date.setCellValueFactory(new PropertyValueFactory<GasStationOrder, String>("date"));
		orderPrice.setCellValueFactory(new PropertyValueFactory<GasStationOrder, String>("orderPrice"));
		fuelType.setCellValueFactory(new PropertyValueFactory<GasStationOrder, String>("fuelType"));
		quantity.setCellValueFactory(new PropertyValueFactory<GasStationOrder, Float>("quantity"));
		//upload all data to the table
		OrdersTbl.setItems(getOrders(supplier.getId(), orderType));
	}

	/**
	 * This function send suplierId and the chosen order type to server to
	 * get back all matching orders The chooseOrderType() function activate it
	 * @param supplierId
	 * @param orderType
	 * @return
	 */
	
	public ObservableList<GasStationOrder> getOrders(String supplierId, String orderType) {
		ArrayList<GasStationOrder> newOrders = SupplierCC.getAllOrdersByStatus(supplierId, orderType);

		ObservableList<GasStationOrder> orders = FXCollections.observableArrayList(newOrders);
		return orders;
	}

	/**
	 * This function is to sigh out the user from the system
	 * @param event
	 */
	
	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(supplier.getId(), supplier.getClass().toString());
		
		//logOut
		MasterGUIController.getMasterGUIController().
		switchWindows("LogIn.fxml");
	}

	/**
	 * Switching Pans - hide the current pane and make the new pane visible
	 * 
	 * @param newPane
	 */
	
	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	/**
	 * Switch to the main pane
	 * 
	 * @param event
	 */
	
	@FXML
	void openMainPane(ActionEvent event) {
		switchPanes(SupplierMainPane);
	}

	/**
	 * Switch to orders pane
	 * 
	 * @param event
	 */
	
	@FXML
	void openOrdersPane(ActionEvent event) {
		switchPanes(SupplierOrdersPane);
	}

	/**
	 * Initializing the scene and define the current pane.
	 * Set values into the order type combo box
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// loading the main window data
		System.out.println("hello");
		System.out.println(supplier);
		// set the current pane as the main
		currentPane = SupplierMainPane;
		// show the main pane and hide the others
		SupplierMainPane.setVisible(true);
		SupplierOrdersPane.setVisible(false);

		ObservableList<SupplierOrderStatus> orderTypes = FXCollections
				.observableArrayList(SupplierOrderStatus.confirmed,SupplierOrderStatus.supplied);
		OrderTypeCombox.setItems(orderTypes);
	}
	
	/**
	 * The function send all the orders that was selected by the user to the server
	 * and update their status
	 * @param event
	 */
	
	@FXML
	void updateSuppliedOrders(ActionEvent event) {
		//for each row that the supplier selected we update the data
		for (GasStationOrder order : selectedOrders) {
			order.setStatus(SupplierOrderStatus.supplied.toString());
		}
		//show the suitable message
		if (SupplierCC.updateGasOrdersStatus(selectedOrders)) 
			JOptionPane.showMessageDialog(null,"Update succeded!");
		else  JOptionPane.showMessageDialog(null,"Update un-succeded one or more of the data didn't update");
		
		//move the order from the table
		OrdersTbl.setItems(getOrders(supplier.getId(), SupplierOrderStatus.confirmed.toString()));
	}

}
