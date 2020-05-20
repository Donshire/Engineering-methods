package boundery;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class GasHomeController implements Initializable {

	Integer discount = 0;
	// double priceListPrice = (double) ClientCC.getMaxPrice("HOME GAS");
	double priceListPrice = 4.8;
	String supplyDate;
	double gasAmount;
	double priceOfPurchase = 0;

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
		textAmount.textProperty().bind(
				Bindings.format("%.2f", sliderAmount.valueProperty())
				);

		settingDiscount();
		setPrice();
	}

	@FXML
	void TextAmountChanged(InputMethodEvent event) {
		Double value = new Double(textAmount.getText());
		// sliderAmount.setValue(value);
		settingDiscount();
		setPrice();
	}

	@FXML
	/**
	 * Displays or hides the option to select a date according to the selected radio
	 * button
	 * 
	 * @param event
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
		/*
		 * GasOrder order = new GasOrder(purchaseID, custmoerId, "HOME GAS", supplyDate,
		 * gasAmount, date, priceOfPurchase, urgent, status, saleID, currentPrice,
		 * companyName) // Should we send for payment? if
		 * (CustomerCC.creatNewOrder(order)) { // PopUp ""; }
		 */
	}

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GasHome.fxml"));

		mainPane = loader.load();

		Pane pane = (Pane) mainPane.getChildren().get(8);
		Text t = (Text) pane.getChildren().get(2);
		t.setText(String.format("%.2f", priceListPrice));

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("Order Gas Home");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	void settingDiscount() {
		/*
		textAmount.textProperty().bind(
				Bindings.format("%.2f", sliderAmount.valueProperty())
				);
		*/
		DoubleProperty d = sliderAmount.valueProperty();
		Double amount = d.get();
	//	Double amount = (new DoubleProperty(sliderAmount.valueProperty())).get();
		if (radioImmediat.isSelected())
			discount = 2;
		else {
			if (amount > 600 && amount < 801)
				discount = -3;
			else if (amount > 800)
				discount = -4;
			else
				discount = 0;
		}
		
		textDiscount.textProperty().bind(Bindings.format("%d", sliderAmount.valueProperty()));
		//total.setText(String.format("%.2f", priceOfPurchase));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
