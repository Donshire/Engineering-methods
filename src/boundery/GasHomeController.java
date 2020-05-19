package boundery;

import Entity.Fuel;
import Entity.GasOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GasHomeController {

	int discount = 0;
	double priceListPrice = ClientCC.getMaxPrice("HOME GAS");
	String supplyDate;
	float gasAmount;
	float priceOfPurchase = 0;
	
	
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
    void DragSlider(MouseEvent event) {
    	
    }

    @FXML
    void TextAmountChanged(InputMethodEvent event) {
    	Double value =  sliderAmount.getValue();
    	textAmount.setText(value.toString());
    	updatePrice();
    }

    private void updatePrice() {
		// TODO Auto-generated method stub
		
	}

	@FXML
	/**
	 * Displays or hides the option to select a date according to the selected radio button
	 * @param event
	 */
    void radioSelected(ActionEvent event) {
		textSupplyDate.setVisible(normalSupply.hasProperties());
		filedSupplyDate.setVisible(normalSupply.hasProperties());
		updatePrice();
    }
	
	@FXML
    void supplyDateSelected(ActionEvent event) {

    }
	
	@FXML
    void makePurchase(ActionEvent event) {
		GasOrder order = new GasOrder(purchaseID, custmoerId, "HOME GAS", supplyDate, gasAmount, date, priceOfPurchase, urgent, status, saleID, currentPrice, companyName)
				// Should we send for payment?
		if (CustomerCC.creatNewOrder(order)) {
			// PopUp "";
		}
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
    
    void SettingDiscount() {
    	Double amount = new Double(textAmount.getText());
    	if (radioImmediat.hasProperties())
    		discount = 2;
    	else if (normalSupply.hasProperties()) {
    		if (amount>600 && amount<801)
    			discount = -3;
    		else if (amount>800)
    			discount = -4;
    		else
    			discount = 0;
    	}
    	else	
    		discount = 0;
    }

}
