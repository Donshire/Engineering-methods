package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.FuelPurchase;
import client.CustomerCC;
import client.UserCC;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FastFuelingController implements Initializable {
	
	public static Car car;
	public static Customer customer;
	public static CustomerModule customerModule;
	// usable
	private String companyName;
	private int stationID;
	private float amount;
	private float customerPrice;
	private float currentPrice;
	private ArrayList<Integer> salesID = new ArrayList<Integer>();

	@FXML
	private Pane dataPane;
	@FXML
	private Pane paymentPane;
	@FXML
	private Text carNumberTxt;

	@FXML
	private Text fuelTypeTxt;

	@FXML
	private Text fuelPricePL;

	@FXML
	private TextField fuelAmount;

	@FXML
	private Button nextToPaymentBtn;

	@FXML
	private ComboBox<String> companiesCombo;

	@FXML
	private ComboBox<Integer> stationsIDCombo;

	@FXML
	private Text customerPriceTxt;

	@FXML
	private Text pricingModelTxt;

	@FXML
	private Text salePercentTxt;

	@FXML
	private RadioButton visaRadio;

	@FXML
	private RadioButton cashRadio;

	@FXML
	private Button pay;

	@FXML
	private Text purchaseStatus;

	@FXML
	private Text totalPrice;

	@FXML
	void backToLogInPage(ActionEvent event) {
		// like the log Out
	}

	@FXML
	void handleRadioSelect(ActionEvent event) {
		if(event.getSource().equals(cashRadio))
			visaRadio.setSelected(false);
		else cashRadio.setSelected(false);
	}
	
	@FXML
	void procedPayment(ActionEvent event) {
		// create FyelPurchase Object
		// acording to use and how we want to save sales
		int sales = 0;
		int result=-3;
		if (!salesID.isEmpty())
			sales = salesID.get(0);
		FuelPurchase purchase = new FuelPurchase(null, stationID, car.getCarNumber(), amount, customerPrice*amount, "", "",
				sales, currentPrice*amount, customer.getId());
		
		if (visaRadio.isSelected())
			result=CustomerCC.commitFuelPurchase(customer.getId(), customer.getVisaNumber(), purchase,car.getFuelType());

		else if (cashRadio.isSelected())
			result=CustomerCC.commitFuelPurchase(customer.getId(), "CASH", purchase,car.getFuelType());
		else {
			JOptionPane.showMessageDialog(null,"please select payment method");
			return;
		}

		if (result == -1) JOptionPane.showMessageDialog(null,"Purchase succeded");
		else if (result == -2) JOptionPane.showMessageDialog(null,"Purchase un-succeded");	
		else if (result >= 0)
			JOptionPane.showMessageDialog(null,String.format("Order excede station max qauntity\nthe max quantity is %d", result));

	}

	@FXML
	void nextToPayment(ActionEvent event) { 
		companyName = companiesCombo.getValue();

		if(companyName==null){
			JOptionPane.showMessageDialog(null, "Select company Firts");
			return;
		}
		
		if (stationsIDCombo.getValue()==null) {
			JOptionPane.showMessageDialog(null, "Select station id first");
			return;
		}
		stationID = stationsIDCombo.getValue();
		
		try {
			amount = Float.parseFloat(fuelAmount.getText());
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "please fill numbers");
			return;
		}

		// call server and calculate totale price
		ArrayList<Float> resDetails = CustomerCC.getPurchasePriceDetails(companyName, customerModule,
				customer.getPricingModel());
		customerPrice = resDetails.get(0);
		currentPrice=resDetails.get(1);
		// get all sales ID
		int i = 4;
		while (i < resDetails.size()) {
			salesID.add(resDetails.get(i).intValue());
			i++;
		}
		//
		dataPane.setVisible(false);
		paymentPane.setVisible(true);

		// set the price
		totalPrice.setText(String.format("%.2f",amount * customerPrice));
	}

	@FXML
	void selectCompany(ActionEvent event) {
		companyName = companiesCombo.getValue();
		// get all company stations
		// set value of StationsIDCombo
		fillstationsIDCombo(CustomerCC.getAllCompanyFuelStationID(companyName));
		// get the pricing data
		ArrayList<Float> pricingRes = CustomerCC.getPurchasePriceDetails(companyName, customerModule,
				customer.getPricingModel());
		// the calculated price
		customerPriceTxt.setText(pricingRes.get(0).toString());
		// company cur price
		fuelPricePL.setText(pricingRes.get(1).toString());
		// company sales
		salePercentTxt.setText(pricingRes.get(2).toString());
	}

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("FastFueling.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// set the visible panes
		dataPane.setVisible(true);
		paymentPane.setVisible(false);
		
		// set the data for the customer
		carNumberTxt.setText(car.getCarNumber());
		fuelTypeTxt.setText(car.getFuelType());
		pricingModelTxt.setText(Integer.toString(customer.getPricingModel()));

		// fill the combo with companies Names
		// Set value of companiesCombo
		fillcompaniesCombo();
	}

	private void fillcompaniesCombo() {
		ObservableList<String> companies = FXCollections.observableArrayList();
		// fill the ObservableList from the set
		for (String str : customerModule.getCompanyNamesSubscribed())
			companies.add(str);

		companiesCombo.setItems(companies);
	}

	private void fillstationsIDCombo(ArrayList<Integer> stationsId) {
		ObservableList<Integer> stationsID = FXCollections.observableArrayList();

		for (Integer id : stationsId)
			stationsID.add(id);

		stationsIDCombo.setItems(stationsID);
	}
	
	/*
	private Stage workInProgres() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(savedStage);
        VBox dialogVbox = new VBox(20);
        //text
        Text txt=new Text("Procecing Paiment");
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        dialogVbox.getChildren().add(txt);
        //progres circle
        ProgressIndicator progressIndicator = new ProgressIndicator();
        dialogVbox.getChildren().add(progressIndicator);
        //result of purchase
        Text resTxt=new Text("");
        dialogVbox.getChildren().add(resTxt);
        
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 300, 150);
        dialog.setScene(dialogScene);
        
        return dialog;
	}
	*/

}
