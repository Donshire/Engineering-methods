package boundery;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Application;

import Entity.GasOrder;
import client.CustomerCC;
import enums.OrderStatus;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MyOrderConrtoller implements Initializable {

	@FXML
	private TableView<GasOrder> orderTable;

	@FXML
	private TableColumn<GasOrder, Integer> purchaseID;

	@FXML
	private TableColumn<GasOrder, String> fuelType;

	@FXML
	private TableColumn<GasOrder, String> supplyDate;

	@FXML
	private TableColumn<GasOrder, String> PurchaseDate;

	@FXML
	private TableColumn<GasOrder, Float> gasAmount;

	@FXML
	private TableColumn<GasOrder, Boolean> urgent;

	@FXML
	private TableColumn<GasOrder, OrderStatus> status;

	@FXML
	private TableColumn<GasOrder, Integer> saleID;

	@FXML
	private TableColumn<GasOrder, Float> PurchasePrice;

	public void start(Stage primaryStage) throws Exception {
		AnchorPane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MyOrderBoundery.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		purchaseID.setCellValueFactory(new PropertyValueFactory<GasOrder, Integer>("purchaseID"));
		fuelType.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("fuelType"));
		supplyDate.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("supplyDate"));
		gasAmount.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("gasAmount"));
		PurchaseDate.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("date"));
		PurchasePrice.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("priceOfPurchase"));
		urgent.setCellValueFactory(new PropertyValueFactory<GasOrder, Boolean>("urgent"));
		status.setCellValueFactory(new PropertyValueFactory<GasOrder, OrderStatus>("status"));
		saleID.setCellValueFactory(new PropertyValueFactory<GasOrder, Integer>("saleID"));

		orderTable.setItems(getOrders());

	}

	public ObservableList<GasOrder> getOrders() {
		ObservableList<GasOrder> orders = FXCollections.observableArrayList(CustomerCC.GasOrderList("22"));

		return orders;
	}

}
