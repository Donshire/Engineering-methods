package boundery;

import java.util.ArrayList;

import Entity.Customer;
import Entity.GasOrder;
import Entity.OrderStatus;
import client.CustomerCC;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyOrderConrtoller extends Application {

	private Stage window;
	TableView<GasOrder> table;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		window.setTitle("orderlist");

		// columns:

		// purchaseID col

		TableColumn<GasOrder, Integer> purchaseIDcol = new TableColumn<GasOrder, Integer>("purchaseID");
		purchaseIDcol.setMinWidth(100);
		purchaseIDcol.setCellValueFactory(new PropertyValueFactory<>("purchaseID"));

		// custmoerId col
		TableColumn<GasOrder, String> custmoerIdcol = new TableColumn<GasOrder, String>("custmoerId");
		custmoerIdcol.setMinWidth(100);
		custmoerIdcol.setCellValueFactory(new PropertyValueFactory<>("custmoerId"));

		// fuelTypecol col
		TableColumn<GasOrder, String> fuelTypecol = new TableColumn<GasOrder, String>("fuelType");
		fuelTypecol.setMinWidth(100);
		fuelTypecol.setCellValueFactory(new PropertyValueFactory<>("fuelType"));

		// supplyDatecol col
		TableColumn<GasOrder, String> supplyDatecol = new TableColumn<GasOrder, String>("supplyDate");
		supplyDatecol.setMinWidth(100);
		supplyDatecol.setCellValueFactory(new PropertyValueFactory<>("supplyDate"));

		// gasAmountcol col
		TableColumn<GasOrder, Float> gasAmountcol = new TableColumn<GasOrder, Float>("gasAmount");
		gasAmountcol.setMinWidth(100);
		gasAmountcol.setCellValueFactory(new PropertyValueFactory<>("gasAmount"));

		// datecol col
		TableColumn<GasOrder, String> datecol = new TableColumn<GasOrder, String>("OrderDate");
		datecol.setMinWidth(100);
		datecol.setCellValueFactory(new PropertyValueFactory<>("date"));

		// priceOfPurchasecol col
		TableColumn<GasOrder, Float> priceOfPurchasecol = new TableColumn<GasOrder, Float>("priceOfPurchase");
		priceOfPurchasecol.setMinWidth(150);
		priceOfPurchasecol.setCellValueFactory(new PropertyValueFactory<>("priceOfPurchase"));

		// urgentcol col
		TableColumn<GasOrder, Boolean> urgentcol = new TableColumn<GasOrder, Boolean>("urgent");
		urgentcol.setMinWidth(100);
		urgentcol.setCellValueFactory(new PropertyValueFactory<>("urgent"));

		// statuscol col
		TableColumn<GasOrder, OrderStatus> statuscol = new TableColumn<GasOrder, OrderStatus>("status");
		statuscol.setMinWidth(100);
		statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

		// saleIDcol col
		TableColumn<GasOrder, Integer> saleIDcol = new TableColumn<GasOrder, Integer>("saleID");
		saleIDcol.setMinWidth(100);
		saleIDcol.setCellValueFactory(new PropertyValueFactory<>("saleID"));

		// currentPricecol col
		TableColumn<GasOrder, Float> currentPricecol = new TableColumn<GasOrder, Float>("currentPrice");
		currentPricecol.setMinWidth(100);
		currentPricecol.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));

		// companyNamecol col
		TableColumn<GasOrder, String> companyNamecol = new TableColumn<GasOrder, String>("companyName");
		companyNamecol.setMinWidth(150);
		companyNamecol.setCellValueFactory(new PropertyValueFactory<>("companyName"));

		table = new TableView<>();
		table.setItems(getOrders());
		table.getColumns().addAll(purchaseIDcol, custmoerIdcol, fuelTypecol, supplyDatecol, gasAmountcol, datecol,
				priceOfPurchasecol, urgentcol, statuscol, saleIDcol, currentPricecol, companyNamecol);

		VBox vbox = new VBox();
		vbox.getChildren().addAll(table);

		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.show();
	}

	public ObservableList<GasOrder> getOrders() {
		ObservableList<GasOrder> orders = FXCollections.observableArrayList(CustomerCC.GasOrderList("22"));

		// orders.add(new GasOrder(1111, "203600820", "95", "16/11/2020", 20,
		// "16/08/2020", 300, false,
		// OrderStatus.processing, 444, 10, "sonol"));

		return orders;
	}

}
