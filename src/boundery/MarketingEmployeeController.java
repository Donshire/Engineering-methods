package boundery;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.sun.javafx.binding.StringFormatter;

import Entity.Car;
import Entity.Customer;
import Entity.Employee;
import Entity.Sale;
import client.ClientUI;
import client.EmployeeCC;
import client.UserCC;
import enums.CustomerTypes;
import enums.PricingModel;
import enums.PurchaseModel;
import enums.SaleStatus;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private Label customerTypeLbl;

	@FXML
	private ComboBox<CustomerTypes> customerTypeCombox;

	@FXML
	private Label customercompanyNameLbl;

	@FXML
	private TextField customercompanyNameTxt;

	@FXML
	private Label newSaleLbl;

	@FXML
	private Label companyNameLbl;

	@FXML
	private Label FuelTypeLbl;

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
	private CheckBox sunday;

	@FXML
	private CheckBox monday;

	@FXML
	private CheckBox tuesday;

	@FXML
	private CheckBox wednesday;

	@FXML
	private CheckBox thuesday;

	@FXML
	private CheckBox friday;

	@FXML
	private CheckBox saturday;

	@FXML
	private CheckBox allCheckBox;

	@FXML
	private ImageView CVVImage1;

	@FXML
	private Label dividExpDateLbl;

	@FXML
	private Label upAddressNoLbl;

	@FXML
	private TextField upAdrdressNoTxt;

	@FXML
	private Label chExpFormatLbl;

	@FXML
	private Label dividExpDate1;

	@FXML
	private TextField expChangeYearTxt;

	@FXML
	private TextField expChangeMonthTxt;

	@FXML
	private TextField ExpDateYearTxt;

	@FXML
	private TextField ExpDateMonthTxt;

	@FXML
	private ImageView helpImage1;

	@FXML
	private Button saveSaleBtn;

	@FXML
	private TextField addressNoTxt;

	@FXML
	private Label addressNoLbl;

	@FXML
	private Button deleteSaleBtn;

	@FXML
	private Label dateFormatLbl;

	@FXML
	private Button salesBtn;

	@FXML
	private Button createSaleBtn;

	@FXML
	private ImageView CVVImage;

	@FXML
	private Text companyNameTxt;

	@FXML
	private ImageView helpImage;

	@FXML
	private Button helpCVVBtn;

	@FXML
	private ComboBox<String> fuelTypesForSaleCombox;

	@FXML
	private Button replaceCarBtn;

	@FXML
	private Label oldCarNumLbl;

	@FXML
	private TextField oldCarNumTxt;

	@FXML
	private ComboBox<String> chooseCompany1;

	@FXML
	private ComboBox<String> chooseCompany2;

	@FXML
	private ComboBox<String> chooseCompany3;

	@FXML
	private Label secondCarLbl;

	@FXML
	private TextField secondCarTxt;

	@FXML
	private Label chooseCompLbl;

	@FXML
	private Label secfuelTypeLbl;

	@FXML
	private ComboBox<String> secFuelTypeCombox;

	private Pane currentPane;
	public static Employee markem;
	public boolean replaceCarFlag = false, enter2CarsFlag = false;
	Car secCar2 = null;
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private ArrayList<String> fuelTypes = new ArrayList<String>();
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();
	private ArrayList<String> fuelTypesForSale = new ArrayList<String>();
	private ArrayList<String> companys = new ArrayList<String>();

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
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
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
		customercompanyNameLbl.setVisible(false);
		customercompanyNameTxt.setVisible(false);
		CVVImage.setVisible(false);
		CVVImage1.setVisible(false);
		switchPanes(NewClientPane);
	}

	@FXML
	void openCVVImgae(MouseEvent event) {
		if (CVVImage.isVisible())
			CVVImage.setVisible(false);
		else
			CVVImage.setVisible(true);
	}

	@FXML
	void openCVVImgae1(MouseEvent event) {
		if (CVVImage1.isVisible())
			CVVImage1.setVisible(false);
		else
			CVVImage1.setVisible(true);
	}

	// switch to creating new sale pane
	@FXML
	void openNewSalePane(ActionEvent event) {
		switchPanes(createNewSalePane);
		String company = markem.getCompanyName();
		companyNameTxt.setText(company);
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
		expChangeMonthTxt.setVisible(false);
		dividExpDate1.setVisible(false);
		chExpFormatLbl.setVisible(false);
		expChangeYearTxt.setVisible(false);
		upCVVLbl.setVisible(false);
		CVVChangeTxt.setVisible(false);
		upPhoneLbl.setVisible(false);
		phoneChangeTxt.setVisible(false);
		upAddressLbl.setVisible(false);
		addressChangeTxt.setVisible(false);
		changeHeader.setVisible(false);
		helpImage1.setVisible(false);
		CVVImage1.setVisible(false);
		upAddressNoLbl.setVisible(false);
		upAdrdressNoTxt.setVisible(false);
		switchPanes(updateCustomerPane);

	}

	// switch to car and model pane
	@FXML
	void openCarAndModelPane(ActionEvent event) {
		carNumberTxt.setDisable(true);
		FuelTypeCombox.setDisable(true);
		pricingModelCombox.setDisable(true);
		purchaseModelCombox.setDisable(true);
		replaceCarBtn.setDisable(true);
		oldCarNumLbl.setDisable(true);
		oldCarNumTxt.setDisable(true);
		chooseCompany1.setVisible(false);
		chooseCompany2.setVisible(false);
		chooseCompany3.setVisible(false);
		chooseCompLbl.setVisible(false);
		secondCarLbl.setVisible(false);
		secondCarTxt.setVisible(false);
		secfuelTypeLbl.setVisible(false);
		secFuelTypeCombox.setVisible(false);
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
		System.out.println("employee:  " + markem);
		fuelTypes.add("95");
		fuelTypes.add("MOTOR CYCLES");
		fuelTypes.add("Solar");
		// loading the main window data
		System.out.println("hello");
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

		ObservableList<String> fuelType = FXCollections.observableArrayList(fuelTypes);
		FuelTypeCombox.setItems(fuelType);
		secFuelTypeCombox.setItems(fuelType);

		ObservableList<CustomerTypes> customerTypes = FXCollections.observableArrayList(CustomerTypes.values());
		customerTypeCombox.setItems(customerTypes);

		fuelTypesForSale = EmployeeCC.getFuelTypesByCompany(markem.getCompanyName());
		ObservableList<String> fuelTypeForSale = FXCollections.observableArrayList(fuelTypesForSale);
		fuelTypesForSaleCombox.setItems(fuelTypeForSale);

		companys = EmployeeCC.getCompanyNames();
		ObservableList<String> companyForPricingModule = FXCollections.observableArrayList(companys);
		chooseCompany1.setItems(companyForPricingModule);
		chooseCompany2.setItems(companyForPricingModule);
		chooseCompany3.setItems(companyForPricingModule);

		Callback<DatePicker, DateCell> datePicker = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						LocalDate today = LocalDate.now();
						setDisable(empty || item.compareTo(today) < 0);
					}

				};
			}

		};
		startDatePicker.setDayCellFactory(datePicker);
		endDatePicker.setDayCellFactory(datePicker);
	}

	// this method is for new client registration
	@FXML
	void registration(ActionEvent event) {
		int res;
		boolean flag = false;
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		StringBuilder inputMsg = new StringBuilder("The next filleds are in inccorect format: \n");
		StringBuilder expDate = new StringBuilder();
		StringBuilder fullAddress = new StringBuilder();
		CustomerTypes cusType;
		String firstName, lastName, mail, id, phoneNumber, address, addressNo, userName, password, creditCardNo,
				expDateMon, expDateYear, CVV, companyName = null;
		id = IDTxt.getText();
		firstName = FirstNameTxt.getText();
		lastName = LastNameTxt.getText();
		mail = EmailTxt.getText();
		phoneNumber = PhoneTxt.getText();
		address = AddressTXt.getText();
		addressNo = addressNoTxt.getText();
		userName = UserNameTxt.getText();
		password = PasswordTxt.getText();
		creditCardNo = CreditCardTxt.getText();
		expDateMon = ExpDateMonthTxt.getText();
		expDateYear = ExpDateYearTxt.getText();
		System.out.println(expDateMon +"  " + expDateYear);
		expDate.append(expDateMon);
		expDate.append("/");
		expDate.append(expDateYear);
		CVV = CVVTxt.getText();

		cusType = (CustomerTypes) customerTypeCombox.getValue();
		if (cusType == CustomerTypes.Company) {
			companyName = customercompanyNameTxt.getText();
			if (companyName== null)
				JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}
		// check that all the data was entered
		if (id== null || firstName== null || lastName== null || mail== null
				|| phoneNumber== null || address== null || userName== null
				|| password== null|| creditCardNo== null || expDateMon.isEmpty()
				|| expDateYear.isEmpty() || CVV== null || addressNo== null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}

		if (!testId(id)) {
			inputMsg.append("ID, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(firstName)) {
			inputMsg.append("first name, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(lastName)) {
			inputMsg.append("last name, " + "\n");
			flag = true;
		}
		if (!testPhone(phoneNumber)) {
			inputMsg.append("phone, " + "\n");
			flag = true;
		}
		if (!mail.matches(EMAIL_REGEX)) {
			inputMsg.append("mail, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(address)) {
			inputMsg.append("address, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyNumbers(addressNo)) {
			inputMsg.append("address no, " + "\n");
			flag = true;
		}
		if (!testCreditCard(creditCardNo)) {
			inputMsg.append("credit card no, " + "\n");
			flag = true;
		}
		if (!testDateOfExp(expDateMon, expDateYear)) {
			inputMsg.append("Exp Date, " + "\n");
			flag = true;
		}
		if (!testCVV(CVV)) {
			inputMsg.append("CVV, " + "\n");
			flag = true;
		}
		if (checkIfUserNameExist(userName)) {
			inputMsg.append("user name already exist!, " + "\n");
			flag = true;
		}
		if (flag) {
			inputMsg.delete(inputMsg.length() - 3, inputMsg.length() - 2);
			JOptionPane.showMessageDialog(null, inputMsg.toString());
			return;
		}

		// creating new Customer object and sending to server
		else {
			fullAddress.append(address);
			fullAddress.append(" ");
			fullAddress.append(addressNo);
			Customer customer = new Customer(userName, password, firstName, lastName, mail, id, phoneNumber, 0,
					fullAddress.toString(), 0, 0, 0, 0, creditCardNo, expDate.toString(), CVV,0, cusType.toString(),
					companyName);

			System.out.println(customer);
			res = EmployeeCC.checkIfExist(customer.getId());
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "This customer id already exist in the system.");
			} else if (res == -1)
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
			else {
				if (EmployeeCC.AddNewCustomer(customer)) {
					JOptionPane.showMessageDialog(null, "Customer added successfully!");
					IDTxt.clear();
					FirstNameTxt.clear();
					LastNameTxt.clear();
					EmailTxt.clear();
					CreditCardTxt.clear();
					addressNoTxt.clear();
					ExpDateMonthTxt.clear();
					CVVTxt.clear();
					PhoneTxt.clear();
					AddressTXt.clear();
					UserNameTxt.clear();
					PasswordTxt.clear();
					customercompanyNameTxt.clear();
					ExpDateYearTxt.clear();
					customerTypeCombox.setValue(null);
				} else
					JOptionPane.showMessageDialog(null, "Update un-succeded one or more of the data didn't update");
			}
		}
	}

	@FXML
	void openCompany(ActionEvent event) {
		CustomerTypes type = customerTypeCombox.getValue();
		if (type == CustomerTypes.Company) {
			customercompanyNameLbl.setVisible(true);
			customercompanyNameTxt.setVisible(true);
		}
		if (type == CustomerTypes.Private) {
			customercompanyNameLbl.setVisible(false);
			customercompanyNameTxt.setVisible(false);
		}
	}

	// this method is to find if a specific customer exist in the system
	@FXML
	void uploadCustomer(ActionEvent event) {
		String id;
		id = carAndModelIDTxt.getText();
		int res;
		if (id== null || !testId(id))
			JOptionPane.showMessageDialog(null, "ID is empty or incorrect, Please enter id");
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
				replaceCarBtn.setDisable(false);

				// if res == -1 -> there has been an error in the server
			} else
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
		}
	}

	// open the option to change car that exist
	@FXML
	void openReplaceCar(ActionEvent event) {
		if (oldCarNumTxt.isDisable()) {
			oldCarNumLbl.setDisable(false);
			oldCarNumTxt.setDisable(false);
			replaceCarFlag = true;
		} else {
			oldCarNumLbl.setDisable(true);
			oldCarNumTxt.setDisable(true);
			replaceCarFlag = false;
			oldCarNumTxt.clear();
		}

	}

	// this method is to update the car and / or model to the user we uploaded
	@FXML
	void updateCarAndModel(ActionEvent event) {
		String id, carNumber, company1, company2, company3;
		String fuelType, oldNum = null;
		PricingModel pricingM;
		StringBuilder companyNames = new StringBuilder();
		int count = 0;
		PurchaseModel purchaseM;
		id = carAndModelIDTxt.getText();
		carNumber = carNumberTxt.getText();
		fuelType = FuelTypeCombox.getValue();
		pricingM = pricingModelCombox.getValue();
		purchaseM = purchaseModelCombox.getValue();
		if (replaceCarFlag) {
			oldNum = oldCarNumTxt.getText();
			if (oldNum == null) {
				JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
				return;
			}
			if (!testCar(oldNum)) {
				JOptionPane.showMessageDialog(null, "car number is not in the correct format");
				return;
			}
		}

		if (id.isEmpty() || carNumber == null || fuelType == null || pricingM == null || purchaseM == null ) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}
		if (!testCar(carNumber)) {
			JOptionPane.showMessageDialog(null, "car number is not in the correct format");
			return;
		}

		if (pricingM == PricingModel.onlyOneStatione) {
			company1 = chooseCompany1.getValue();
			if (company1 == null) {
				JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
				return;
			}
			companyNames.append(company1);

		}
		if (pricingM == PricingModel.TwoOrThreeStations) {
			company1 = chooseCompany1.getValue();
			company2 = chooseCompany2.getValue();
			company3 = chooseCompany3.getValue();
			if (company1 == null || company2 == null) {
				JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
				return;
			}
			if (company1 == company2  || company2==company3 || company1==company3) {
				JOptionPane.showMessageDialog(null, "You need to choose different companies");
				return;
			}
			if (company3 == null) {
				companyNames.append(company1);
				companyNames.append(", ");
				companyNames.append(company2);
			} else {
				companyNames.append(company1);
				companyNames.append(", ");
				companyNames.append(company2);
				companyNames.append(", ");
				companyNames.append(company3);
			}
		}
		int carCount = EmployeeCC.getCarCount(id);
		if ((purchaseM.toString() == PurchaseModel.MonthlysubscriptioOneCar.toString())
				|| purchaseM.toString() == PurchaseModel.FullMonthlysubscription.toString()) {
			if (carCount != 0) {
				JOptionPane.showMessageDialog(null,
						"In this purchase module you can register only one car\n You already have a car in the system. \n Change car number or purchase module");
				return;
			}
		}

		if (purchaseM.toString() == PurchaseModel.Monthlysubscriptio2OrMoreCars.toString()) {
			if (carCount == 0 && !enter2CarsFlag) {
				enter2CarsFlag = true;
				JOptionPane.showMessageDialog(null,
						"In this purchase module you have to register 2 or more cars\n You don't have cars registered at all \n Please enter second car ");
				secondCarLbl.setVisible(true);
				secondCarTxt.setVisible(true);
				secfuelTypeLbl.setVisible(true);
				secFuelTypeCombox.setVisible(true);
				return;

			}
			if (enter2CarsFlag) {
				String secCar = secondCarTxt.getText();
				String secFuel = secFuelTypeCombox.getValue();
				if (secCar == null || secFuel == null) {
					JOptionPane.showMessageDialog(null,
							"One or more of the details is empty, please fill all the fileds");
					return;
				}
				if (!testCar(secCar)) {
					JOptionPane.showMessageDialog(null, "car number is not in the correct format");
					return;
				}
				secCar2 = new Car(secCar, secFuel, id);

			}
		} else {
			secondCarLbl.setVisible(false);
			secondCarTxt.setVisible(false);
			secfuelTypeLbl.setVisible(false);
			secFuelTypeCombox.setVisible(false);
		}

		Car car = new Car(carNumber, fuelType, id);
		// adding the car
		if (!replaceCarFlag) {
			if (EmployeeCC.addNewCar(car)) {
				JOptionPane.showMessageDialog(null, "Car added successfully to system");
				count++;
			} else {
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
			}
		} else {
			if (EmployeeCC.updateCar(car, oldNum)) {
				JOptionPane.showMessageDialog(null, "Car updated successfully to system");
				count++;

			} else {
				JOptionPane.showMessageDialog(null,
						"There has been an error or the old car number doesn't exist, try again");
				count--;
			}
		}

		if (enter2CarsFlag) {
			if (EmployeeCC.addNewCar(secCar2)) {
				JOptionPane.showMessageDialog(null, "Second car added successfully to system");
			} else {
				JOptionPane.showMessageDialog(null, "There has been an error, try again");
			}
		}

		// adding the model
		if (EmployeeCC.updateModels(pricingM.toString(), purchaseM.toString(), id)
				&& (EmployeeCC.addModule(id, purchaseM.toString(), companyNames.toString()))) {

			JOptionPane.showMessageDialog(null, "Models added successfully to system");
			count++;
		} else
			JOptionPane.showMessageDialog(null, "There has been an error, try again");
		if (count == 2 || count == 3) {
			carNumberTxt.clear();
			pricingModelCombox.setValue(null);
			purchaseModelCombox.setValue(null);
			carAndModelIDTxt.clear();
			FuelTypeCombox.setValue(null);
			oldCarNumTxt.clear();
			chooseCompany1.setValue(null);
			chooseCompany2.setValue(null);
			chooseCompany3.setValue(null);
			secondCarTxt.clear();
			secFuelTypeCombox.setValue(null);
			openCarAndModelPane(null);
		}
	}

	@FXML
	void openCompanyChooser(ActionEvent event) {

		if (pricingModelCombox.getValue() == PricingModel.onlyOneStatione) {
			chooseCompLbl.setVisible(true);
			chooseCompany2.setVisible(false);
			chooseCompany3.setVisible(false);
			chooseCompany1.setVisible(true);
		}
		if (pricingModelCombox.getValue() == PricingModel.TwoOrThreeStations) {
			chooseCompLbl.setVisible(true);
			chooseCompany1.setVisible(true);
			chooseCompany2.setVisible(true);
			chooseCompany3.setVisible(true);
		}
	}

	// this method id to upload all the details of a specific customer
	@FXML
	void showCustomeDetails(ActionEvent event) {
		String id = UpdateIdTxt.getText();
		Customer customer;
		String expDate;
		if (id== null)
			JOptionPane.showMessageDialog(null, "Please enter id");
		if (EmployeeCC.checkIfExist(id) == 0)
			JOptionPane.showMessageDialog(null, "User Id doesn't exist");
		else {
			customer = EmployeeCC.getCustomerDetails(id);
			if (customer == null)
				JOptionPane.showMessageDialog(null, "There has been an error");
			else {
				firstNameChangetxt.setText(customer.getFirstName());
				lastNameChangeTxt.setText(customer.getLastName());
				emailChangTxt.setText(customer.getMail());
				CCNChangeTxt.setText(customer.getVisaNumber());
				expDate = customer.getExpDate();
				expChangeMonthTxt.setText(expDate.substring(0, 2).toString());
				expChangeYearTxt.setText(expDate.substring(3, 7).toString());
				CVVChangeTxt.setText(customer.getCVV());
				phoneChangeTxt.setText(customer.getPhoneNumber());
				addressChangeTxt.setText(customer.getAdress().replaceAll("\\d", ""));
				upAdrdressNoTxt.setText(customer.getAdress().replaceAll("[^0-9]", ""));

				upFirstNameLbl.setVisible(true);
				firstNameChangetxt.setVisible(true);
				upLasttNameLbl.setVisible(true);
				lastNameChangeTxt.setVisible(true);
				upEmailLbl.setVisible(true);
				emailChangTxt.setVisible(true);
				upCreditCardLbl.setVisible(true);
				CCNChangeTxt.setVisible(true);
				upExpDateLbl.setVisible(true);
				expChangeMonthTxt.setVisible(true);
				dividExpDate1.setVisible(true);
				chExpFormatLbl.setVisible(true);
				expChangeYearTxt.setVisible(true);
				upCVVLbl.setVisible(true);
				CVVChangeTxt.setVisible(true);
				upPhoneLbl.setVisible(true);
				phoneChangeTxt.setVisible(true);
				upAddressLbl.setVisible(true);
				addressChangeTxt.setVisible(true);
				changeHeader.setVisible(true);
				helpImage1.setVisible(true);
				upAddressNoLbl.setVisible(true);
				upAdrdressNoTxt.setVisible(true);

			}

		}

	}

	// this method is for updating customers details
	@FXML
	void changeCustomerDetails(ActionEvent event) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		ArrayList<String> upCustomer = new ArrayList<String>();
		String chFirst, chLast, chEmail, chCreditNo, chExpDate, chCvv, chPhone, chAddress, chId, chAddressNo;
		StringBuilder fullAddress = new StringBuilder();
		StringBuilder inputMsg = new StringBuilder("The next filleds are in inccorect format: ");
		StringBuilder expDate = new StringBuilder();
		boolean flag = false;
		// get all the data that the user entered
		chFirst = firstNameChangetxt.getText();
		chLast = lastNameChangeTxt.getText();
		chEmail = emailChangTxt.getText();
		chCreditNo = CCNChangeTxt.getText();
		String expDateMon = expChangeMonthTxt.getText();
		String expDateYear = expChangeYearTxt.getText();
		expDate.append(expDateMon);
		expDate.append("/");
		expDate.append(expDateYear);
		chCvv = CVVChangeTxt.getText();
		chPhone = phoneChangeTxt.getText();
		chAddress = addressChangeTxt.getText();
		chAddressNo = upAdrdressNoTxt.getText();
		System.out.println("no: " + chAddressNo);
		chId = UpdateIdTxt.getText();

		if (chFirst== null || chLast== null || chEmail== null
				|| chCreditNo== null || expDateMon.isEmpty() || expDateYear.isEmpty()
				|| chCvv== null || chPhone== null|| chAddress== null
				|| chId== null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}
		if (!testId(chId)) {
			inputMsg.append("ID, ");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chFirst)) {
			inputMsg.append("first name, ");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chLast)) {
			inputMsg.append("last name, ");
			flag = true;
		}
		if (!testPhone(chPhone)) {
			inputMsg.append("phone, ");
			flag = true;
		}
		if (!chEmail.matches(EMAIL_REGEX)) {
			inputMsg.append("mail, ");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chAddress)) {
			inputMsg.append("address, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyNumbers(chAddressNo)) {
			inputMsg.append("address no, " + "\n");
			flag = true;
		}

		if (!testCreditCard(chCreditNo)) {
			inputMsg.append("credit card no, ");
			flag = true;
		}
		if (!testDateOfExp(expDateMon, expDateYear)) {
			inputMsg.append("Exp Date, " + "\n");
			flag = true;
		}
		if (!testCVV(chCvv)) {
			inputMsg.append("CVV, ");
			flag = true;
		}
		if (flag) {
			inputMsg.delete(inputMsg.length() - 3, inputMsg.length() - 2);
			JOptionPane.showMessageDialog(null, inputMsg.toString());
			return;
		}
		fullAddress.append(chAddress);
		fullAddress.append(" ");
		fullAddress.append(chAddressNo);

		upCustomer.add(chId);
		upCustomer.add(chFirst);
		upCustomer.add(chLast);
		upCustomer.add(chEmail);
		upCustomer.add(chCreditNo);
		upCustomer.add(expDate.toString());
		upCustomer.add(chCvv);
		upCustomer.add(chPhone);
		upCustomer.add(fullAddress.toString());
		// send the data to the server for update
		if (EmployeeCC.updateCustomerDetails(upCustomer)) {
			JOptionPane.showMessageDialog(null, "Updated customer successfully!");
			openUpdateCustomerPane(null);
			UpdateIdTxt.clear();
		} else
			JOptionPane.showMessageDialog(null, "Error ! could not update customer, try again");
	}

	// this method is for uploading all the sales templates that exist in the DB to
	// the gui table
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
		if (EmployeeCC.deleteSales(selectedSales)) {
			JOptionPane.showMessageDialog(null, "Sales deleted!");
			uploadSalesToTable();
		}
		else
			JOptionPane.showMessageDialog(null, "Error! sales could not be deleted, try again");

	}

	// this method if for creating new sale in DB
	@FXML
	void createNewSale(ActionEvent event) throws ParseException {
		String  fuelType, startTime, endTime, startDate = null, endDate = null;
		String TIME_REGEX = "(([0-9]{2}):([0-9]{2}))";
		StringBuilder inputMsg = new StringBuilder("The next filleds are in inccorect format: " + "\n");
		boolean flag = false;
		float percent;
		// compantName = companyNameTxt.getText();
		startTime = startTimeTxt.getText();
		endTime = endTimeTxt.getText();
		fuelType = fuelTypesForSaleCombox.getValue();

		if (startDatePicker.getValue() != null || endDatePicker.getValue() != null) {
			startDate = dateFormatter.format(startDatePicker.getValue());
			endDate = dateFormatter.format(endDatePicker.getValue());

		}

		if (startDate== null || endDate== null || startTime== null || endTime== null || fuelType== null
				|| salePercentTxt.getText()== null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}
		if (!checkIfStringContainsOnlyNumbersInFloatType(salePercentTxt.getText())) {
			JOptionPane.showMessageDialog(null, "You need to enter sale percent in the following format: xx.xx");
			return;
		}
		if (!startTime.matches(TIME_REGEX) || !endTime.matches(TIME_REGEX)) {
			JOptionPane.showMessageDialog(null, "You need to enter time in the following format: \n HH:mm");
			return;
		}
			
		if (!testTime(startTime)) {
			inputMsg.append("start time" + "\n");
			flag = true;
		}
		if (!testTime(endTime)) {
			inputMsg.append("end time");
			flag = true;
		}
		
		if(startDate.compareTo(endDate) > 0)
		{
			JOptionPane.showMessageDialog(null, "End date is earlier than start date");
			return;
		}
		if (flag) {
			JOptionPane.showMessageDialog(null, inputMsg.toString());
			return;
		}
		percent = Float.parseFloat(salePercentTxt.getText());


		StringBuilder days = new StringBuilder();

		if (sunday.isSelected())
			days.append("Sunday ");
		if (monday.isSelected())
			days.append("Monday ");
		if (tuesday.isSelected())
			days.append("Tuesday ");
		if (wednesday.isSelected())
			days.append("Wednesday ");
		if (thuesday.isSelected())
			days.append("Thuesday ");
		if (friday.isSelected())
			days.append("Friday ");
		if (saturday.isSelected())
			days.append("Saturday ");
		if (allCheckBox.isSelected()) {
			days.delete(0, days.length());
			days.append("All");
		}
		Sale newSale = new Sale(null, SaleStatus.not_Activated.toString(), markem.getCompanyName(), fuelType, percent,
				startTime, endTime, startDate, endDate, days.toString());
		if (EmployeeCC.addNewSaleTemp(newSale)) {
			JOptionPane.showMessageDialog(null, "Sale added successfully!");
			salePercentTxt.clear();
			startTimeTxt.clear();
			endTimeTxt.clear();
			fuelTypesForSaleCombox.setValue(null);
			startDatePicker.setValue(null);
			endDatePicker.setValue(null);
			sunday.setSelected(false);
			monday.setSelected(false);
			tuesday.setSelected(false);
			wednesday.setSelected(false);
			thuesday.setSelected(false);
			friday.setSelected(false);
			saturday.setSelected(false);
			allCheckBox.setSelected(false);
			uploadSalesToTable();
		}
		else
			JOptionPane.showMessageDialog(null, "Error! sales could not be added, try again");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// INPUTTESTS///////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean checkIfStringContainsOnlyNumbersInFloatType(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				if (str.charAt(i) == '.') {
					flag = true;
					continue;
				}
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static boolean checkIfStringContainsOnlyNumbers(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static boolean testId(String str) {
		if ((str.length() == 9) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	public static boolean checkIfStringContainsOnlyCharacter(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				if (str.charAt(i) == ' ')
					continue;
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static boolean testPhone(String str) {
		if ((str.length() == 10) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	public static boolean testCar(String str) {
		if ((str.length() == 7 || str.length() == 8) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	public static boolean testCreditCard(String str) {
		if ((str.length() == 16 || str.length() == 10) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	public static boolean testCVV(String str) {
		if ((str.length() == 3) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	public static boolean checkIfUserNameExist(String userName) {
		return EmployeeCC.checkIfUserNameExist(userName);
	}

	public static boolean testDateOfExp(String month, String year) {
		if ((!checkIfStringContainsOnlyNumbers(month)) || (!checkIfStringContainsOnlyNumbers(year)))
			return false;
		if ((Integer.parseInt(month) < 1) || (Integer.parseInt(month) > 12))
			return false;
		if (Integer.parseInt(year) < 2020)
			return false;
		return true;
	}

	public static boolean testTime(String time) {
		if (Integer.parseInt(time.substring(0, 2)) > 23 || Integer.parseInt(time.substring(0, 2)) < 0)
			return false;
		System.out.println(Integer.parseInt(time.substring(3, 4)));
		if (Integer.parseInt(time.substring(3, 5)) > 59 || Integer.parseInt(time.substring(3, 5)) < 0)
			return false;
		return true;
	}

}
