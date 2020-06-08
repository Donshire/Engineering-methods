package boundery;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Entity.GasStationOrder;
import Entity.Sale;
import Entity.Supplier;
import client.ClientUI;
import client.EmployeeCC;
import client.SupplierCC;
import client.UserCC;
import enums.PricingModel;
import enums.PurchaseModel;
import enums.SaleStatus;
import enums.SupplierOrderStatus;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private TextField idChangeTxt;

	@FXML
	private TextField firstNameChangetxt;

	@FXML
	private TextField lastNameChangeTxt;

	@FXML
	private TextField emailChangTxt;

	@FXML
	private TextField CCNChangeTxt;

	@FXML
	private TextField expChangeTxt;

	@FXML
	private TextField CVVChangeTxt;

	@FXML
	private TextField phoneChangeTxt;

	@FXML
	private TextField addressChangeTxt;

	@FXML
	private Button saveChangesBtn;

	@FXML
	private Text changeHeader;

	@FXML
	private Pane salesPane;

	@FXML
	private Label saleHeader;

	@FXML
	private TableView<Sale> salesTbl;

	@FXML
	private TableColumn<Sale, Boolean> selectColumn;

	@FXML
	private TableColumn<Sale, Integer> SaleIDColumn;

	@FXML
	private TableColumn<Sale, String> statusColumn;

	@FXML
	private TableColumn<Sale, String> companyColumn;

	@FXML
	private TableColumn<Sale, String> fuelTypeColumn;

	@FXML
	private TableColumn<Sale, String> purchaseMoColumn;

	@FXML
	private TableColumn<Sale, Float> salePrecentColumn;

	@FXML
	private TableColumn<Sale, String> startTColumn;

	@FXML
	private TableColumn<Sale, String> endTColumn;

	@FXML
	private TableColumn<Sale, String> startDColumn;

	@FXML
	private TableColumn<Sale, String> endDColumn;

	@FXML
	private TableColumn<Sale, String> daysColumn;

	@FXML
	private Pane createNewSalePane;

	@FXML
	private Label newSaleLbl;

	@FXML
	private Label companyNameLbl;

	@FXML
	private TextField companyNameTxt;

	@FXML
	private Label FuelTypeLbl;

	@FXML
	private Label salePurchaseLbl;

	@FXML
	private ChoiceBox<PurchaseModel> salePurchaseCombox;

	@FXML
	private Label salePercentsLbl;

	@FXML
	private TextField salePercentTxt;

	@FXML
	private Label startTimeLbl;

	@FXML
	private TextField startTimeTxt;

	@FXML
	private Label endTimeLbl1;

	@FXML
	private TextField endTimeTxt;

	@FXML
	private TextField fuelTypeTxt;

	@FXML
	private Label startDateLbl;

	@FXML
	private Label endDateLbl;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;
	
	@FXML
	private Label daysLbl;

	@FXML
	private TextField daysTxt;

	@FXML
	private Button saveSaleBtn;

	@FXML
	private Button deleteSaleBtn;

	@FXML
	private Button salesBtn;

	@FXML
	private Button createSaleBtn;

	private Pane currentPane;
	public static Employee markem;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private ArrayList<String> fuelTypes = new ArrayList<String>();
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();

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

	// switch to registration pane
	@FXML
	void openNewClientPane(ActionEvent event) {
		switchPanes(NewClientPane);
	}

	// switch to creating new sale pane
	@FXML
	void openNewSalePane(ActionEvent event) {
		switchPanes(createNewSalePane);
	}

	// switch to customers pane - for detail update
	@FXML
	void openUpdateCustomerPane(ActionEvent event) {
		upFirstNameLbl.setVisible(false);
		firstNameChangetxt.setVisible(false);
		upLasttNameLbl.setVisible(false);
		lastNameChangeTxt.setVisible(false);
		upEmailLbl.setVisible(false);
		emailChangTxt.setVisible(false);
		upCreditCardLbl.setVisible(false);
		CCNChangeTxt.setVisible(false);
		upExpDateLbl.setVisible(false);
		expChangeTxt.setVisible(false);
		upCVVLbl.setVisible(false);
		CVVChangeTxt.setVisible(false);
		upPhoneLbl.setVisible(false);
		phoneChangeTxt.setVisible(false);
		upAddressLbl.setVisible(false);
		addressChangeTxt.setVisible(false);
		changeHeader.setVisible(false);
		switchPanes(updateCustomerPane);

	}

	// switch to car and model pane
	@FXML
	void openCarAndModelPane(ActionEvent event) {
		carNumberTxt.setDisable(true);
		FuelTypeCombox.setDisable(true);
		pricingModelCombox.setDisable(true);
		purchaseModelCombox.setDisable(true);
		switchPanes(carAndModelPane);
	}

	// switch to sales pane
	@FXML
	void openSalesPane(ActionEvent event) {
		switchPanes(salesPane);
		uploadSalesToTable();
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
		salesPane.setVisible(false);
		createNewSalePane.setVisible(false);

		ObservableList<PricingModel> pricing = FXCollections.observableArrayList(PricingModel.values());
		pricingModelCombox.setItems(pricing);

		ObservableList<PurchaseModel> purchase = FXCollections.observableArrayList(PurchaseModel.values());
		purchaseModelCombox.setItems(purchase);
		salePurchaseCombox.setItems(purchase);

		ObservableList<String> fuelType = FXCollections.observableArrayList(fuelTypes);
		FuelTypeCombox.setItems(fuelType);
	}

	// this method is for new client registration
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
		// check that all the data was entered
		if (id.isEmpty() == true || firstName.isEmpty() == true || lastName.isEmpty() == true || mail.isEmpty() == true
				|| phoneNumber.isEmpty() == true || address.isEmpty() == true || userName.isEmpty() == true
				|| password.isEmpty() == true || creditCardNo.isEmpty() == true || expDate.isEmpty() == true
				|| CVV.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");

		// creating new Customer object and sending to server
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

	// this method is to find if a specific customer exist in the system
	@FXML
	void uploadCustomer(ActionEvent event) {
		String id;
		id = carAndModelIDTxt.getText();
		int res;
		Customer customer;
		if (id.isEmpty())
			JOptionPane.showMessageDialog(null, "Please enter id");
		else {
			// sending the customer id to server to check if exist
			res = EmployeeCC.checkIfExist(id);
			// if res == 0 -> customer doesn't exist in the system
			if (res == 0) {
				JOptionPane.showMessageDialog(null,
						"Customer ID doen't exist in the system, can not add car and model. Please add customer first");
				// if res == 1 - > customer exist , the user can continue
			} else if (res == 1) {
				JOptionPane.showMessageDialog(null, "Continue adding car number and models");
				carNumberTxt.setDisable(false);
				FuelTypeCombox.setDisable(false);
				pricingModelCombox.setDisable(false);
				purchaseModelCombox.setDisable(false);
				// if res == -1 -> there has been an error in the server
			} else
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
		}
	}

	// this method is to update the car and / or model to the user we uploaded
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
		// adding the car
		Car car = new Car(carNumber, fuelType, id);
		if (EmployeeCC.addNewCar(car)) {
			JOptionPane.showMessageDialog(null, "Car added successfully to system");
		} else {
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
		}

		// adding the model
		if (EmployeeCC.updateModels(pricingM.toString(), purchaseM.toString(), id))

			JOptionPane.showMessageDialog(null, "Models added successfully to system");
		else
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
	}

	// this method id to upload all the details of a specific customer
	@FXML
	void showCustomeDetails(ActionEvent event) {
		String id = UpdateIdTxt.getText();
		Customer customer;
		if (id.isEmpty())
			JOptionPane.showMessageDialog(null, "Please enter id");
		else {
			customer = EmployeeCC.getCustomerDetails(id);
			if (customer.equals(null))
				JOptionPane.showMessageDialog(null, "There has been an error");
			else {
				firstNameChangetxt.setText(customer.getFirstName());
				lastNameChangeTxt.setText(customer.getLastName());
				emailChangTxt.setText(customer.getMail());
				CCNChangeTxt.setText(customer.getVisaNumber());
				expChangeTxt.setText(customer.getExpDate());
				CVVChangeTxt.setText(customer.getCVV());
				phoneChangeTxt.setText(customer.getPhoneNumber());
				addressChangeTxt.setText(customer.getAdress());

				upFirstNameLbl.setVisible(true);
				firstNameChangetxt.setVisible(true);
				upLasttNameLbl.setVisible(true);
				lastNameChangeTxt.setVisible(true);
				upEmailLbl.setVisible(true);
				emailChangTxt.setVisible(true);
				upCreditCardLbl.setVisible(true);
				CCNChangeTxt.setVisible(true);
				upExpDateLbl.setVisible(true);
				expChangeTxt.setVisible(true);
				upCVVLbl.setVisible(true);
				CVVChangeTxt.setVisible(true);
				upPhoneLbl.setVisible(true);
				phoneChangeTxt.setVisible(true);
				upAddressLbl.setVisible(true);
				addressChangeTxt.setVisible(true);
				changeHeader.setVisible(true);

			}

		}

	}

	// this method is for updating customers details
	@FXML
	void changeCustomerDetails(ActionEvent event) {
		ArrayList<String> upCustomer = new ArrayList<String>();
		String chFirst, chLast, chEmail, chCreditNo, chExpDate, chCvv, chPhone, chAddress, chId;
		// get all the data that the user entered
		chFirst = firstNameChangetxt.getText();
		chLast = lastNameChangeTxt.getText();
		chEmail = emailChangTxt.getText();
		chCreditNo = CCNChangeTxt.getText();
		chExpDate = expChangeTxt.getText();
		chCvv = CVVChangeTxt.getText();
		chPhone = phoneChangeTxt.getText();
		chAddress = addressChangeTxt.getText();
		chId = UpdateIdTxt.getText();

		upCustomer.add(chId);
		upCustomer.add(chFirst);
		upCustomer.add(chLast);
		upCustomer.add(chEmail);
		upCustomer.add(chCreditNo);
		upCustomer.add(chExpDate);
		upCustomer.add(chCvv);
		upCustomer.add(chPhone);
		upCustomer.add(chAddress);
		// send the data to the server for update
		if (EmployeeCC.updateCustomerDetails(upCustomer))
			JOptionPane.showMessageDialog(null, "Updated customer successfully!");
		else
			JOptionPane.showMessageDialog(null, "Error ! could not update customer, try again");
	}

	
	// this method is for uploading all the sales templates that exist in the DB to the gui table
	@FXML
	void uploadSalesToTable() {
		// checkBox initializing
		selectColumn.setCellValueFactory(new Callback<CellDataFeatures<Sale, Boolean>, ObservableValue<Boolean>>() {

			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Sale, Boolean> param) {
				Sale sale = param.getValue();
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(sale.getSelect());

				booleanProp.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if (newValue == true)
							selectedSales.add(sale);
						else
							selectedSales.remove(sale);
						sale.setSelect(newValue);
					}
				});
				return booleanProp;
			}
		});

		selectColumn.setCellFactory(new Callback<TableColumn<Sale, Boolean>, TableCell<Sale, Boolean>>() {

			@Override
			public TableCell<Sale, Boolean> call(TableColumn<Sale, Boolean> param) {
				CheckBoxTableCell<Sale, Boolean> cell = new CheckBoxTableCell<Sale, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});

		
		// column define
		SaleIDColumn.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("saleID"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("status"));
		companyColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("companyName"));
		fuelTypeColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("fuelType"));
		purchaseMoColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("purchaseModule"));
		salePrecentColumn.setCellValueFactory(new PropertyValueFactory<Sale, Float>("salePercent"));
		startTColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("startTime"));
		endTColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("endTime"));
		startDColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("startDate"));
		endDColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("endDate"));
		daysColumn.setCellValueFactory(new PropertyValueFactory<Sale, String>("saleDays"));

		salesTbl.setItems(getSales());
	}

	// this method bring the sale data from DB
	public ObservableList<Sale> getSales() {
		ArrayList<Sale> sale = EmployeeCC.getAllSales();
		ObservableList<Sale> sales = FXCollections.observableArrayList(sale);

		return sales;
	}

	// this method if for deleting sales from DB
	@FXML
	void deleteSals(ActionEvent event) {
		if (EmployeeCC.deleteSales(selectedSales))
			JOptionPane.showMessageDialog(null, "Sales deleted!");
		else
			JOptionPane.showMessageDialog(null, "Error! sales could not be deleted, try again");
	}

	
	// this method if for creating new sale in DB
	@FXML
	void createNewSale(ActionEvent event) {
		String compantName, fuelType, startTime, endTime, startDate, endDate, days, purchaseNum = null;
		PurchaseModel purchaseM;
		float percent;

		compantName = companyNameTxt.getText();

		fuelType = fuelTypeTxt.getText();
		startTime = startTimeTxt.getText();
		endTime = endTimeTxt.getText();
		if(startDatePicker.getValue()==null||endDatePicker.getValue()==null) {
			JOptionPane.showMessageDialog(null, "please select dates");
			return;
		}
		startDate = formatter.format(startDatePicker.getValue());
		endDate = formatter.format(endDatePicker.getValue());
		days = daysTxt.getText();
		purchaseM = salePurchaseCombox.getValue();
		percent = Float.parseFloat(salePercentTxt.getText());

		switch (purchaseM) {
		case Casualfueling:
			purchaseNum = "1";
			break;
		case MonthlysubscriptioOneCar:
			purchaseNum = "2";
			break;
		case Monthlysubscriptio2OrMoreCars:
			purchaseNum = "3";
			break;
		case FullMonthlysubscription:
			purchaseNum = "4";
			break;
		}
		Sale newSale = new Sale(null, SaleStatus.not_Activated.toString(), compantName, fuelType, purchaseNum, percent,
				startTime, endTime, startDate, endDate, days);
		if (EmployeeCC.addNewSaleTemp(newSale))
			JOptionPane.showMessageDialog(null, "Sale added successfully!");
		else
			JOptionPane.showMessageDialog(null, "Error! sales could not be added, try again");

	}
}
