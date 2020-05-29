package boundery;

import java.net.URL;
import java.util.ResourceBundle;

import Entity.Car;
import Entity.Customer;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FastFuelingController implements Initializable{

	private Car car;
	private Customer customer;
	
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
    private Text purchaseModelTxt;

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
    	//like the log Out
    }
    
    @FXML
    void nextToPayment(ActionEvent event) {
    	String str = fuelAmount.getText();
    	//call server and calculate totale price
    	
    	//
    	dataPane.setVisible(false);
		paymentPane.setVisible(true);
    }

    @FXML
    void selectCompany(ActionEvent event) {
    	String str = companiesCombo.getValue();
    	//get all company stations
    	//set value of StationsIDCombo
    	ObservableList<Integer> stationsID = FXCollections
				.observableArrayList();
    	stationsIDCombo.setItems(stationsID);
    }

    @FXML
    void selectStationId(ActionEvent event) {
    	int stationID = stationsIDCombo.getValue();
    	//get all the rest details
    	//company cur price
    	fuelPricePL.setText("");
    	//search for company sales
    	salePercentTxt.setText("");
    	//the calculated price
    	customerPriceTxt.setText("");
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
		
		car = (Car) ClientUI.user;
		//get the owner of the car
		
		//set the visible panes
		dataPane.setVisible(true);
		paymentPane.setVisible(false);
		
		//set the data for the customer
		carNumberTxt.setText(car.getCarNumber());
		fuelTypeTxt.setText(car.getFuelType());
		purchaseModelTxt.setText(Integer.toString(customer.getPricingModel()));
		
		//fill the combo with companies Names
		//call server
		//Set value of companiesCombo
		ObservableList<String> companies = FXCollections
				.observableArrayList();
		companiesCombo.setItems(companies);
	}

}
