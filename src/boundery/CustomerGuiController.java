package boundery;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.Customer;
import Entity.GasOrder;
import client.CustomerCC;
import enums.OrderStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerGuiController implements Initializable {

	public static Customer customer;
	Integer discount = 0;
	double PricePerUnit;
	String supplyDate;
	double gasAmount;
	double priceOfPurchase = 0;

	@FXML
	private Pane CustomerMainPane;

	@FXML
	private Pane orderHomeGasPane;

	@FXML
	private Pane myOrdersPane;
	

	// ORDER TABLE START -------------------------------

	@FXML
	private TableView<GasOrder> orderTable;

	@FXML
	private TableColumn<GasOrder, Integer> purchaseIDcol;

	@FXML
	private TableColumn<GasOrder, String> supplyDatecol;

	@FXML
	private TableColumn<GasOrder, String> PurchaseDatecol;

	@FXML
	private TableColumn<GasOrder, Float> gasAmountcol;

	@FXML
	private TableColumn<GasOrder, Boolean> urgentcol;

	@FXML
	private TableColumn<GasOrder, OrderStatus> statuscol;

	@FXML
	private TableColumn<GasOrder,String> companyNamecol;

	@FXML
	private TableColumn<GasOrder, Float> PurchasePricecol;

	// ORDER TABLE END ------------------------------------

	@FXML
	private Text hellotxt;

	@FXML
	private Button orderGasbtn;

	@FXML
	private Button myOrdersbtn;

	@FXML
	private RadioButton radioImmediat;

	@FXML
	private ToggleGroup DeliveryDateRadio;

	@FXML
	private RadioButton normalSupply;

	@FXML
	private Text textSupplyDate;

	@FXML
	private DatePicker filedSupplyDate;

	@FXML
	private Slider sliderAmount;

	@FXML
	private TextField textAmount;

	@FXML
	private Text priceList;

	@FXML
	private Text textDiscount;

	@FXML
	private Text total;

	@FXML
	private Button buttonBuy;

	@FXML
	void TextAmountChanged(InputMethodEvent event) {

		gasAmount = new Double(textAmount.getText());
		// gasAmount = (double) spinnerAmount.getValue();
		// sliderAmount.setValue(value);
		settingDiscount();
		setPrice();
	}

	@FXML
	/**
	 * Displays or hides the option to select a date according to the selected radio
	 * button
	 */
	void radioSelected(ActionEvent event) {
		textSupplyDate.setVisible(normalSupply.isSelected());
		filedSupplyDate.setVisible(normalSupply.isSelected());
		settingDiscount();
		setPrice();
	}

	private void setPrice() {
		// double beforeDiscount = priceListPrice * gasAmount;
		double beforeDiscount = 4.8 * gasAmount;
		priceOfPurchase = (beforeDiscount + (beforeDiscount / 100) * discount);
		total.setText(String.format("%.2f", priceOfPurchase));
	}

	@FXML
	void supplyDateSelected(ActionEvent event) {

	}

	@FXML
	void makePurchase(ActionEvent event) {
		
		Button btn = (Button) event.getSource();
		
		if(btn.equals(buttonBuy)) {
			
			System.out.println("orya lox");
		}
		/*
		 * GasOrder order = new GasOrder(purchaseID, custmoerId, "HOME GAS", supplyDate,
		 * gasAmount, date, priceOfPurchase, urgent, status, saleID, currentPrice,
		 * companyName) // Should we send for payment? if
		 * (CustomerCC.creatNewOrder(order)) { // PopUp ""; }
		 */
	}

	@FXML
	void myOrdersClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();

		if (btn.equals(myOrdersbtn)) {
			myOrdersPane.setVisible(true);
			orderHomeGasPane.setVisible(false);
			orderTable.setItems(getOrders());
		}

	}

	@FXML
	void orderGasClicked(ActionEvent event) {
		Button btn = (Button) event.getSource();

		if (btn.equals(orderGasbtn)) {
			myOrdersPane.setVisible(false);
			orderHomeGasPane.setVisible(true);
		}
	}

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CustomerGUI.fxml"));

		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("Order Gas Home");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	void settingDiscount() {
		if (radioImmediat.isSelected())
			discount = 2;
		else {
			if (gasAmount > 600.0 && gasAmount <= 800.0)
				discount = -3;
			else if (gasAmount > 800.0)
				discount = -4;
			else
				discount = 0;
		}
		textDiscount.setText(discount.toString());
	}

	public void orderHomeGasInitialize() {
		priceList.setText("0");
		textDiscount.setText("0");
		

		textAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				try {
					gasAmount = Double.valueOf(newValue);
				} catch (Exception e) {
					gasAmount = 0;
					newValue = "0.0";
				}
				System.out.println("textfield changed from " + oldValue + " to " + newValue);

				settingDiscount();
				setPrice();
			}
		});
	}

	public void myOrdersInitialize() {

		purchaseIDcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Integer>("purchaseID"));
		supplyDatecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("supplyDate"));
		PurchaseDatecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("date"));
		gasAmountcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("gasAmount"));
		urgentcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Boolean>("urgent"));
		statuscol.setCellValueFactory(new PropertyValueFactory<GasOrder, OrderStatus>("status"));
		companyNamecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("companyName"));
		PurchasePricecol.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("priceOfPurchase"));

	}
	
	
	public ObservableList<GasOrder> getOrders() {
		ObservableList<GasOrder> orders = FXCollections.observableArrayList(CustomerCC.GasOrderList(customer.getId()));
		return orders;
	}
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// double plp = (double) ClientCC.getMaxPrice("HOME GAS");
		orderHomeGasPane.setVisible(false);
		myOrdersPane.setVisible(false);
		hellotxt.setText("Hello " + customer.getFirstName());
		orderHomeGasInitialize();
		myOrdersInitialize();

	}

}
