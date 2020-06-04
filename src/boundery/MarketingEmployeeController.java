package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Entity.GasStationOrder;
import Entity.Supplier;
import client.ClientUI;
import client.EmployeeCC;
import client.UserCC;
import enums.PricingModel;
import enums.PurchaseModel;
import enums.SupplierOrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MarketingEmployeeController implements Initializable {

	@FXML
	private Button MainPaneBtn;

	@FXML
	private Pane MainPane;

	@FXML
	private Label NotificationsLbl;

	@FXML
	private Button addCustomerBtn;

	@FXML
	private Pane NewClientPane;

	@FXML
	private Label IdLbl;

	@FXML
	private Label FirstNameLbl;

	@FXML
	private Label LastNameLbl;

	@FXML
	private Label EmailLbl;

	@FXML
	private Label CreditCardNoLbl;

	@FXML
	private Label ExpDateLbl;

	@FXML
	private Label CVVLbl;

	@FXML
	private Button RegistrationNextBtn;

	@FXML
	private Label ORLbl;

	@FXML
	private Button RegistrationFinishBtn;

	@FXML
	private TextField IDTxt;

	@FXML
	private TextField FirstNameTxt;

	@FXML
	private TextField LastNameTxt;

	@FXML
	private TextField EmailTxt;

	@FXML
	private TextField CreditCardTxt;

	@FXML
	private TextField ExpDateTxt;

	@FXML
	private TextField CVVTxt;

	@FXML
	private Label PhoneLbl;

	@FXML
	private Label AddressLbl;

	@FXML
	private TextField PhoneTxt;

	@FXML
	private TextField AddressTXt;

	@FXML
	private Label UserNameLbl;

	@FXML
	private TextField UserNameTxt;

	@FXML
	private Label passwordLbl;

	@FXML
	private TextField PasswordTxt;

	@FXML
	private Button carAndModleBtn;

	@FXML
	private Pane carAndModelPane;

	@FXML
	private Label carNumberLbl;

	@FXML
	private TextField carNumberTxt;

	@FXML
	private ComboBox<PricingModel> pricingModelCombox;

	@FXML
	private Label pricingModelLbl;

	@FXML
	private Label purchaseModelLbl;

	@FXML
	private ComboBox<PurchaseModel> purchaseModelCombox;

	@FXML
	private Button carAndModelFinishBtn;

	@FXML
	private Label carAndModelIDLbl;

	@FXML
	private TextField carAndModelIDTxt;

	@FXML
	private Label fuelTypeLbl;

	@FXML
	private ComboBox<String> FuelTypeCombox;

	@FXML
	private Label carAndModelLbl;

	@FXML
	private Button uploadCustomerBtn;

    @FXML
    private Button openUpdateCustomerPane;

    @FXML
    private Pane updateCustomerPane;

    @FXML
    private Label updateCustomerLbl;

    @FXML
    private Label enterCustomerIDLbl;

    @FXML
    private TextField UpdateIdTxt;

    @FXML
    private Label upFirstNameLbl;

    @FXML
    private Label upPhoneLbl;

    @FXML
    private Label upCreditCardLbl;

    @FXML
    private Label upEmailLbl;

    @FXML
    private Label upLasttNameLbl;

    @FXML
    private Label upExpDateLbl;

    @FXML
    private Label upAddressLbl;

    @FXML
    private Label upCVVLbl;

    @FXML
    private Text upFirstNameTxt;

    @FXML
    private Text upLastNameTxt;

    @FXML
    private Text upEmailTxt;

    @FXML
    private Text upFCreditCardTxt;

    @FXML
    private Text upExpDateTxt;

    @FXML
    private Text upCVVTxt;

    @FXML
    private Text upPhoneTxt;

    @FXML
    private Text upAdderssTxt;

	private Pane currentPane;
	public static Employee markem;
	private ArrayList<String> fuelTypes = new ArrayList<String>();

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MarketingEmployee.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(markem.getId(), markem.getClass().toString());
	}

	// Switching Pans - hide the other
	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	// switch to the main pane
	@FXML
	void openMainPane(ActionEvent event) {
		switchPanes(MainPane);
	}

	// switch to orders pane
	@FXML
	void openNewClientPane(ActionEvent event) {
		switchPanes(NewClientPane);
	}

	@FXML
	void openCarAndModelPane(ActionEvent event) {
		switchPanes(carAndModelPane);
	}

	
	@FXML
	void openUpdateCustomerPane(ActionEvent event) {
		upFirstNameLbl.setVisible(false);
		upFirstNameTxt.setVisible(false);
		upLasttNameLbl.setVisible(false);
		upLastNameTxt.setVisible(false);
		upEmailLbl.setVisible(false);
		upEmailTxt.setVisible(false);
		upCreditCardLbl.setVisible(false);
		upFCreditCardTxt.setVisible(false);
		upExpDateLbl.setVisible(false);
		upExpDateTxt.setVisible(false);
		upCVVLbl.setVisible(false);
		upCVVTxt.setVisible(false);
		upPhoneLbl.setVisible(false);
		upPhoneTxt.setVisible(false);
		upAddressLbl.setVisible(false);
		upAdderssTxt.setVisible(false);
		switchPanes(updateCustomerPane);
		
	}
	
	// initializing the scene
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fuelTypes.add("95");
		fuelTypes.add("98");
		fuelTypes.add("Solar");
		// loading the main window data
		System.out.println("hello");
		markem = (Employee) ClientUI.user;
		System.out.println(markem);
		// set the current pane as the main
		currentPane = MainPane;
		// show the main pane and hide the others
		MainPane.setVisible(true);
		NewClientPane.setVisible(false);
		carAndModelPane.setVisible(false);
		updateCustomerPane.setVisible(false);

		ObservableList<PricingModel> pricing = FXCollections.observableArrayList(PricingModel.values());
		pricingModelCombox.setItems(pricing);

		ObservableList<PurchaseModel> purchase = FXCollections.observableArrayList(PurchaseModel.values());
		purchaseModelCombox.setItems(purchase);

		ObservableList<String> fuelType = FXCollections.observableArrayList(fuelTypes);
		FuelTypeCombox.setItems(fuelType);
	}

	@FXML
	void registration(ActionEvent event) {
		int res;
		String firstName, lastName, mail, id, phoneNumber, address, userName, password, creditCardNo, expDate, CVV;
		id = IDTxt.getText();
		firstName = FirstNameTxt.getText();
		lastName = LastNameTxt.getText();
		mail = EmailTxt.getText();
		phoneNumber = PhoneTxt.getText();
		address = AddressTXt.getText();
		userName = UserNameTxt.getText();
		password = PasswordTxt.getText();
		creditCardNo = CreditCardTxt.getText();
		expDate = ExpDateTxt.getText();
		CVV = CVVTxt.getText();
		if (id.isEmpty() == true || firstName.isEmpty() == true || lastName.isEmpty() == true || mail.isEmpty() == true
				|| phoneNumber.isEmpty() == true || address.isEmpty() == true || userName.isEmpty() == true
				|| password.isEmpty() == true || creditCardNo.isEmpty() == true || expDate.isEmpty() == true
				|| CVV.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");

		else {
			Customer customer = new Customer(userName, password, firstName, lastName, mail, id, phoneNumber, 0, address,
					0, 0, 0, 0, creditCardNo, expDate, CVV);
			System.out.println(customer);
			res = EmployeeCC.checkIfExist(customer.getId());
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "This customer id already exist in the system.");
			} else if (res == -1)
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
			else {
				if (EmployeeCC.AddNewCustomer(customer))
					JOptionPane.showMessageDialog(null, "Customer added successfully!");
				else
					JOptionPane.showMessageDialog(null, "Update un-succeded one or more of the data didn't update");
			}
		}
	}

	@FXML
	void uploadCustomer(ActionEvent event) {
		String id;
		id = carAndModelIDTxt.getText();
		int res;
		res = EmployeeCC.checkIfExist(id);
		if (res == 0) {
			JOptionPane.showMessageDialog(null,
					"Customer ID doen't exist in the system, can not add car and model. Please add customer first");
			carNumberTxt.setDisable(true);
			FuelTypeCombox.setDisable(true);
			pricingModelCombox.setDisable(true);
			purchaseModelCombox.setDisable(true);
		} else if (res == 1) {
			JOptionPane.showMessageDialog(null, "Continue adding car number and models");
			carNumberTxt.setDisable(false);
			FuelTypeCombox.setDisable(false);
			pricingModelCombox.setDisable(false);
			purchaseModelCombox.setDisable(false);
		} else
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
	}

	@FXML
	void updateCarAndModel(ActionEvent event) {
		String id, carNumber;
		String fuelType;
		PricingModel pricingM;
		int pricingNum = 0, purchaseNum = 0;
		PurchaseModel purchaseM;
		id = carAndModelIDTxt.getText();
		carNumber = carNumberTxt.getText();
		fuelType = FuelTypeCombox.getValue();
		pricingM = pricingModelCombox.getValue();
		System.out.println(" 1 " + pricingM);
		purchaseM = purchaseModelCombox.getValue();

		Car car = new Car(carNumber, fuelType, id);
		if (EmployeeCC.addNewCar(car)) {
			JOptionPane.showMessageDialog(null, "Car added successfully to system");
		} else {
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
		}

		if (EmployeeCC.updateModels(pricingM.toString(), purchaseM.toString(), id))

			JOptionPane.showMessageDialog(null, "Models added successfully to system");
		else
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
	}
	
	void updateCustomer(ActionEvent event) {
		String id = UpdateIdTxt.getText();
		
	}
}
