package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Entity.GasStationOrder;
import Entity.Supplier;
import client.ClientUI;
import client.SupplierCC;
import client.UserCC;
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

	public ObservableList<GasStationOrder> getOrders(String supplierId, String orderType) {
		ArrayList<GasStationOrder> newOrders = SupplierCC.getAllOrdersByStatus(supplierId, orderType);

		ObservableList<GasStationOrder> orders = FXCollections.observableArrayList(newOrders);
		return orders;
	}

	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(supplier.getId(), supplier.getClass().toString());
	}

	// Switching Pans - hide the other
	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	
	// switch to the main pane
	@FXML
	void openMainPane(ActionEvent event) {
		switchPanes(SupplierMainPane);
	}

	// switch to orders pane
	@FXML
	void openOrdersPane(ActionEvent event) {
		switchPanes(SupplierOrdersPane);
	}

	// initializing the scene
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// loading the main window data
		System.out.println("hello");
		supplier = (Supplier) ClientUI.user;
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
	
	@FXML
	void updateSuppliedOrders(ActionEvent event) {
		//for each row that the supplier selected we update the data
		for (GasStationOrder order : selectedOrders) {
			order.setStatus(SupplierOrderStatus.supplied.toString());
		}
		//show the suitable message
		HandelMessageResult.handelMessage(SupplierCC.updateGasOrdersStatus(selectedOrders)
				, "Update succeded!",
				"Update un-succeded one or more of the data didn't update");
		
		//move the order from the table
		OrdersTbl.setItems(getOrders(supplier.getId(), SupplierOrderStatus.confirmed.toString()));
	}

}
