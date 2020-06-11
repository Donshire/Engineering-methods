package boundery;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Entity.Customer;
import Entity.Employee;
import Entity.GasOrder;
import client.ClientUI;
import client.CustomerCC;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GasHomeController implements Initializable {
	public Customer customer;
	private Integer discount = 0;
	private String supplyDate;
	private float gasAmount;
	private float priceOfPurchase = 0;
	private float pricePerUnit = 0;
	private String contemporaryDateStr;

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
	private TextField textAmount;

	@FXML
	private Text priceList;

	@FXML
	private Text textDiscount;

	@FXML
	private Text total;

	@FXML
	private Button buttonBuy;
	
	/**
	 * Changing the parameter "gasAmount" and update display of the components that
	 * belongs has.
	 */
	void TextAmountChanged(InputMethodEvent event) {
		gasAmount = new Float(textAmount.getText());
		settingDiscount();
		setPrice();
	}

	@FXML
	/**
	 * Displays or hides the option to select a date according to the selected radio
	 * button.
	 */
	void radioSelected(ActionEvent event) {
		textSupplyDate.setVisible(normalSupply.isSelected());
		filedSupplyDate.setVisible(normalSupply.isSelected());
		settingDiscount();
		setPrice();
	}

	@FXML
	/**
	 * Converting a date to a string and save it.
	 */
	void supplyDateSelected(ActionEvent event) {
		LocalDate date = filedSupplyDate.getValue();
		supplyDate = "" + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}

	@FXML
	/**
	 * Sending the purchase order to server.
	 */
	void makePurchase(ActionEvent event) {
		if (!normalSupply.isSelected())
			supplyDate = contemporaryDateStr;
		GasOrder order = new GasOrder(-1, customer.getId(), supplyDate, gasAmount, contemporaryDateStr, priceOfPurchase,
				!normalSupply.isSelected());
		System.out.println(order.toString());
		
		if (CustomerCC.createNewOrder(order)) 
			JOptionPane.showMessageDialog(null,"Order created succesfully");
		else  JOptionPane.showMessageDialog(null,"clouldn't create order");	

	}

	/**
	 * Setting final price.
	 */
	private void setPrice() {
		float beforeDiscount = pricePerUnit * gasAmount;
		priceOfPurchase = (float) (beforeDiscount + ((beforeDiscount / 100) * discount));
		total.setText(String.format("%.2f", priceOfPurchase));
	}
	
	/**
	 * Set discount by order quantity.
	 */
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


	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GasHome.fxml"));
		customer = (Customer) ClientUI.user;

		mainPane = loader.load();
		
		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("Order Gas Home");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Loading the price of per unit.
		pricePerUnit = (float) CustomerCC.getMaxPrice("HOME GAS");
		priceList.setText(Float.toString(pricePerUnit));
		
		customer = (Customer) ClientUI.user;
		
		textDiscount.setText("0");

		// Loading the today's date
		LocalDate contemporaryDate = LocalDate.now();
		contemporaryDateStr = "" + contemporaryDate.getDayOfMonth() + "/" + contemporaryDate.getMonthValue() + "/"
				+ contemporaryDate.getYear();

		textAmount.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				try {
					gasAmount = Float.valueOf(newValue);
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
	
}
