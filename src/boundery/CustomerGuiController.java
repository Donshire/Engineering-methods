package boundery;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeDBFormat = new SimpleDateFormat("HH:mm:ss");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	Integer discount = 0;
	float PricePerUnit;
	String supplyDate;
	float gasAmount;
	float priceOfPurchase = 0;

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
	private TableColumn<GasOrder,String> PurchaseTimecol;

	@FXML
	private TableColumn<GasOrder, Float> PurchasePricecol;
	
   @FXML
    private TableColumn<GasOrder,String> Status;


	// ORDER TABLE END ------------------------------------

	@FXML
	private Text hellotxt;
	
	@FXML
	private Text noteAmount;
	
	@FXML
	private Text noteDate;

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

		gasAmount = new Float(textAmount.getText());
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
		if(radioImmediat.isSelected()) noteDate.setVisible(false);
		settingDiscount();
		setPrice();
	}

	private void setPrice() {
		// double beforeDiscount = priceListPrice * gasAmount;
		float beforeDiscount = 4.8f * gasAmount;
		priceOfPurchase = (beforeDiscount + (beforeDiscount / 100) * discount);
		total.setText(String.format("%.2f", priceOfPurchase));
	}

	@FXML
	void logOut(ActionEvent event) {
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
	}
	
	@FXML
	void supplyDateSelected(ActionEvent event) {
		supplyDate=formatter.format(filedSupplyDate.getValue());
		System.out.println(supplyDate);
	}

	@FXML
	void makePurchase(ActionEvent event) {
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String currentTime = timeDBFormat.format(date);
		//
		if (isInputCorrect()) {
			if (!normalSupply.isSelected())
				supplyDate = currentDate;
			GasOrder order = new GasOrder(-1, customer.getId(), supplyDate, currentTime, gasAmount, currentDate, priceOfPurchase,
					!normalSupply.isSelected(), OrderStatus.processing);
			
			System.out.println(order.toString());
			
			if (CustomerCC.createNewOrder(order)) 
			JOptionPane.showMessageDialog(null,"Order created succesfully");
		else  JOptionPane.showMessageDialog(null,"clouldn't create order");	
			// HandelMessageResult.handelMessage(CustomerCC.createNewOrder(order), "Order created succesfully",
			//		"clouldn't create order");
		}
	}

	@FXML
	void myOrdersClicked(ActionEvent event) {
		//Button btn = (Button) event.getSource();

		//if (btn.equals(myOrdersbtn)) {
			myOrdersPane.setVisible(true);
			orderHomeGasPane.setVisible(false);
			orderTable.setItems(getOrders());
		//}

	}

	@FXML
	void orderGasClicked(ActionEvent event) {
		//Button btn = (Button) event.getSource();

		//if (btn.equals(orderGasbtn)) {
			myOrdersPane.setVisible(false);
			orderHomeGasPane.setVisible(true);
		//}
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
	
	
	public boolean isInputCorrect() {
		boolean proper = true;
		
		noteDate.setVisible(false);
		noteAmount.setVisible(false);

		if (normalSupply.isSelected()) {
			if (filedSupplyDate.getValue() == null) {
				noteDate.setText("Delivery date must be set.");
				noteDate.setVisible(true);
				proper = false;
			} else if (filedSupplyDate.getValue().isBefore(LocalDate.now())) {
				noteDate.setText("Must be set a future date.");
				noteDate.setVisible(true);
				proper = false;
			}
		}

		if (!amountIsNumber()) {
			noteAmount.setText("Must be Number");
			noteAmount.setVisible(true);
			proper = false;
		}

		return proper;
	}
	

	private boolean amountIsNumber() {
		String amountStr = textAmount.getText();
		
		try {
			@SuppressWarnings("unused")
			Float amountNum = new Float(amountStr);
			if(amountNum<=0)return false;
		} catch (Exception e) {
			return false;
		}
		return true;
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
		priceList.setText("4.6");
		//textDiscount.setText("0");
		

		textAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				try {
					gasAmount = Float.valueOf(newValue);
					if(gasAmount>0) noteAmount.setVisible(false);
					else {
						gasAmount = 0;
						newValue = "0.0";
						noteAmount.setText("Must be Number");
						noteAmount.setVisible(true);
					}
				} catch (Exception e) {
					gasAmount = 0;
					newValue = "0.0";
					noteAmount.setText("Must be Number");
					noteAmount.setVisible(true);
				}
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
		PurchaseTimecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("time"));
		PurchasePricecol.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("priceOfPurchase"));
		Status.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("status"));
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
		
		myOrdersClicked(null);
		/*myOrdersPane.setVisible(true);		
		orderHomeGasPane.setVisible(false);
		orderTable.setItems(getOrders());*/
		//

	}

}
