package boundery;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Entity.GasOrder;
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

	Integer discount = 0;
	double PricePerUnit;
	String supplyDate;
	float gasAmount;
	float priceOfPurchase = 0;
	String contemporaryDateStr;
	

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

	@FXML
	void TextAmountChanged(InputMethodEvent event) {
		gasAmount = new Float(textAmount.getText());
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
		priceOfPurchase = (float) (beforeDiscount + (beforeDiscount / 100) * discount);
		total.setText(String.format("%.2f", priceOfPurchase));
	}

	@FXML
	void supplyDateSelected(ActionEvent event) {
		LocalDate date = filedSupplyDate.getValue();
		supplyDate = "" + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}

	@FXML
	void makePurchase(ActionEvent event) {	
		if (!normalSupply.isSelected())
			supplyDate = contemporaryDateStr;	
		GasOrder order = new GasOrder(-1, "", supplyDate, gasAmount, contemporaryDateStr, priceOfPurchase,
				!normalSupply.isSelected());
		System.out.println(order.toString());
		
		
	}

	
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GasHome.fxml"));

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Float plp = new Float((float) CustomerCC.getMaxPrice("HOME GAS"));
		priceList.setText(plp.toString());
		textDiscount.setText("0");
		
		LocalDate contemporaryDate = LocalDate.now();
		contemporaryDateStr = "" + contemporaryDate.getDayOfMonth() + "/" + contemporaryDate.getMonthValue() + "/" + contemporaryDate.getYear();

		textAmount.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	
		    	try {
		    		gasAmount = Float.valueOf(newValue);
				} catch (Exception e) {
					gasAmount = 0;
					newValue="0.0";
				}
		    	 System.out.println("textfield changed from " + oldValue + " to " + newValue);
		    	 
		    	 settingDiscount();
		    	 setPrice();
		    }
		});


	}

}
