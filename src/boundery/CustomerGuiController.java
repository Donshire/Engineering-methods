package boundery;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Entity.Customer;
import Entity.GasOrder;
import client.CustomerCC;
import client.UserCC;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stubs.CustomerCCI;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerGuiController controls all the customer interface button and operations.
 */
public class CustomerGuiController implements Initializable {

	public static CustomerCCI customerCCI;
	
	public static void setContoller(CustomerCCI customercc) {
		customerCCI=customercc;
	}
	
	/** The customer. */
	public static Customer customer;
	
	/** The date format. */
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/** The time DB format. */
	DateFormat timeDBFormat = new SimpleDateFormat("HH:mm:ss");
	
	/** The formatter. */
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/** The discount. */
	Integer discount = 0;
	
	/** The Price per unit. */
	float PricePerUnit;
	
	/** The supply date. */
	String supplyDate;
	
	/** The gas amount. */
	float gasAmount;
	
	/** The price of purchase. */
	float priceOfPurchase = 0;
	
	/** The price per unit. */
	float pricePerUnit;

	/** The Customer main pane. */
	@FXML
	private Pane CustomerMainPane;

	/** The order home gas pane. */
	@FXML
	private Pane orderHomeGasPane;

	/** The my orders pane. */
	@FXML
	private Pane myOrdersPane;
	

   // @FXML
   // private Text hellomessage;

	// ORDER TABLE START -------------------------------

	/** The order table. */
   @FXML
	private TableView<GasOrder> orderTable;

	/** The purchase I dcol. */
	@FXML
	private TableColumn<GasOrder, Integer> purchaseIDcol;

	/** The supply datecol. */
	@FXML
	private TableColumn<GasOrder, String> supplyDatecol;

	/** The Purchase datecol. */
	@FXML
	private TableColumn<GasOrder, String> PurchaseDatecol;

	/** The gas amountcol. */
	@FXML
	private TableColumn<GasOrder, Float> gasAmountcol;

	/** The urgentcol. */
	@FXML
	private TableColumn<GasOrder, Boolean> urgentcol;

	/** The Purchase timecol. */
	@FXML
	private TableColumn<GasOrder,String> PurchaseTimecol;

	/** The Purchase pricecol. */
	@FXML
	private TableColumn<GasOrder, Float> PurchasePricecol;
	
   /** The Status. */
   @FXML
    private TableColumn<GasOrder,String> Status;

	// ORDER TABLE END ------------------------------------

   
    /** The hellotxt. */
	// ---------------- General Components ----------------------
	@FXML
	private Text hellotxt;
	
	/** The order gasbtn. */
	@FXML
	private Button orderGasbtn;

	/** The my ordersbtn. */
	@FXML
	private Button myOrdersbtn;

	
	
	
	/** The note amount. */
	@FXML
	private Text noteAmount;
	
	/** The note date. */
	@FXML
	private Text noteDate;


	/** The radio immediat. */
	@FXML
	private RadioButton radioImmediat;

	/** The Delivery date radio. */
	@FXML
	private ToggleGroup DeliveryDateRadio;

	/** The normal supply. */
	@FXML
	private RadioButton normalSupply;

	/** The text supply date. */
	@FXML
	private Text textSupplyDate;

	/** The filed supply date. */
	@FXML
	private DatePicker filedSupplyDate;

	/** The text amount. */
	@FXML
	private TextField textAmount;

	/** The price list. */
	@FXML
	private Text priceList;

	/** The text discount. */
	@FXML
	private Text textDiscount;

	/** The total. */
	@FXML
	private Text total;

	/** The button buy. */
	@FXML
	private Button buttonBuy;

    /** The discount 1. */
    @FXML
    private Text discount1;

    /** The discount 2. */
    @FXML
    private Text discount2;

	
	
	/**
	 * Text amount changed.
	 *
	 * @param event the event
	 */
	@FXML
	void TextAmountChanged(InputMethodEvent event) {
		gasAmount = new Float(textAmount.getText());
		settingDiscount();
		setPrice();
	}

	/**
	 * Radio selected.
	 *
	 * @param event the event
	 */
	@FXML
	/**
	 * Displays or hides the option to select a date <br>
	 * according to the selected radio button.
	 */
	void radioSelected(ActionEvent event) {
		textSupplyDate.setVisible(normalSupply.isSelected());
		filedSupplyDate.setVisible(normalSupply.isSelected());
		discount1.setVisible(normalSupply.isSelected());
		discount2.setVisible(normalSupply.isSelected());
		if(radioImmediat.isSelected()) noteDate.setVisible(false);
		settingDiscount();
		setPrice();
	}

	

	/**
	 * Log out.
	 *
	 * @param event the event
	 */
	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(customer.getId(), customer.getClass().toString());
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
	}
	
	/**
	 * Supply date selected.
	 *
	 * @param event the event
	 */
	@FXML
	void supplyDateSelected(ActionEvent event) {
		supplyDate=formatter.format(filedSupplyDate.getValue());
	}

	/**
	 * Make purchase.
	 *
	 * @param event the event
	 */
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
			
			
			if (customerCCI.createNewOrder(order)) {
			JOptionPane.showMessageDialog(null,"Order created succesfully");
			myOrdersClicked(null);
			orderTable.setItems(getOrders());
			}
		else  JOptionPane.showMessageDialog(null,"clouldn't create order");	
			// HandelMessageResult.handelMessage(CustomerCC.createNewOrder(order), "Order created succesfully",
			//		"clouldn't create order");
		}
	}

	/**
	 * My orders clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void myOrdersClicked(ActionEvent event) {
			myOrdersPane.setVisible(true);
			orderHomeGasPane.setVisible(false);
	}

	/**
	 * Order gas clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void orderGasClicked(ActionEvent event) {
			myOrdersPane.setVisible(false);
			orderHomeGasPane.setVisible(true);
	}

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
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
	
	
	/**
	 * Checks if is input correct.
	 *
	 * @return true, if is input correct
	 */
	private boolean isInputCorrect() {
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
			noteAmount.setText("Must be a positive number");
			noteAmount.setVisible(true);
			proper = false;
		}

		return proper;
	}
	

	/**
	 * Amount is number.
	 *
	 * @return true, if successful
	 */
	private boolean amountIsNumber() {
		String amountStr = textAmount.getText();
		String str = amountStr.replaceAll("\\D+","");
		return (amountStr.compareTo(str) == 0);
			
/*		
		
		try {
			@SuppressWarnings("unused")
			Float amountNum = new Float(amountStr);
			if(amountNum<=0)return false;
		} catch (Exception e) {
			return false;
		}
		return true;
*/
	}
	
	/**
	 * Sets the price.
	 */
	public void setPrice() {
		total.setText(String.format("%.2f", calculatePrice(pricePerUnit, gasAmount, radioImmediat.isSelected())));
	}
	
	/**
	 * 
	 * @param pricePerUnit
	 * @param gasAmount
	 * @param flag if the ugret selected it is true
	 * @return
	 */
	public static float calculatePrice(float pricePerUnit,float gasAmount,boolean flag) {
		float beforeDiscount = pricePerUnit * gasAmount;
		return (beforeDiscount + (beforeDiscount / 100) * getDiscount(flag, gasAmount));
	}
	
	/**
	 * Setting discount.
	 */
	private void settingDiscount() {
		textDiscount.setText(getDiscount(radioImmediat.isSelected(),gasAmount) + "%");
	}
	
	//created function
	public static int getDiscount(boolean flag,float gasAmount) {
		int discount;
		if (flag)
			discount = 2;
		else {
			if (gasAmount > 600.0 && gasAmount <= 800.0)
				discount = -3;
			else if (gasAmount > 800.0)
				discount = -4;
			else
				discount = 0;
		}
		return discount;
	}

	
	public static float getHomeGasPrice() {
		return (float) customerCCI.getMaxPrice("HOME GAS");
	}
	
	/**
	 * Order home gas initialize.
	 */
	public void orderHomeGasInitialize() {
		
		// Loading the price of per unit.
		pricePerUnit = getHomeGasPrice();
		priceList.setText(Float.toString(pricePerUnit));
		

		textAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				
					
					String str = newValue.replaceAll("\\D+","");
					if (newValue.compareTo(str) != 0) {
						noteAmount.setVisible(true);
						gasAmount = 0;
						newValue = "0.0";
					} else {
						try {
							gasAmount = Float.valueOf(newValue);
							noteAmount.setVisible(false);
						} catch (Exception e) {
							gasAmount = 0;
							newValue = "0.0";
							noteAmount.setVisible(true);
						}
					}
				
				settingDiscount();
				setPrice();
			}
		});
	}

	/**
	 * My orders initialize.
	 */
	public void myOrdersInitialize() {

		purchaseIDcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Integer>("purchaseID"));
		supplyDatecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("supplyDate"));
		PurchaseDatecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("date"));
		gasAmountcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("gasAmount"));
		urgentcol.setCellValueFactory(new PropertyValueFactory<GasOrder, Boolean>("urgent"));
		PurchaseTimecol.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("time"));
		PurchasePricecol.setCellValueFactory(new PropertyValueFactory<GasOrder, Float>("priceOfPurchase"));
		Status.setCellValueFactory(new PropertyValueFactory<GasOrder, String>("status"));
		
		orderTable.setItems(getOrders());
	}
	
	
	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public ObservableList<GasOrder> getOrders() {
		ObservableList<GasOrder> orders = FXCollections.observableArrayList(customerCCI
				.GasOrderList(customer.getId()));
		return orders;
	}
	
	


	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// double plp = (double) ClientCC.getMaxPrice("HOME GAS");
		orderHomeGasPane.setVisible(false);
		myOrdersPane.setVisible(false);
	//	hellomessage.setText(customer.getFirstName());
		hellotxt.setText("Hello " + customer.getFirstName());
		orderHomeGasInitialize();
		myOrdersInitialize();
		
		myOrdersClicked(null);

	}

}
