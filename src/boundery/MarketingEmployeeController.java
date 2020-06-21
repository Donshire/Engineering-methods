package boundery;

/**
 * This class contains all the marketing employee gui functionality
 */

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

/**
 * The Class MarketingEmployeeController.
 */
public class MarketingEmployeeController implements Initializable {

	/** The hellomessage. */
	@FXML
	private Text hellomessage;

	/** The Notifications lbl. */
	@FXML
	private Label NotificationsLbl;

	/** The add customer btn. */
	@FXML
	private Button addCustomerBtn;

	/** The New client pane. */
	@FXML
	private Pane NewClientPane;

	/** The Id lbl. */
	@FXML
	private Label IdLbl;

	/** The First name lbl. */
	@FXML
	private Label FirstNameLbl;

	/** The Last name lbl. */
	@FXML
	private Label LastNameLbl;

	/** The Email lbl. */
	@FXML
	private Label EmailLbl;

	/** The Credit card no lbl. */
	@FXML
	private Label CreditCardNoLbl;

	/** The Exp date lbl. */
	@FXML
	private Label ExpDateLbl;

	/** The CVV lbl. */
	@FXML
	private Label CVVLbl;

	/** The Registration next btn. */
	@FXML
	private Button RegistrationNextBtn;

	/** The OR lbl. */
	@FXML
	private Label ORLbl;

	/** The Registration finish btn. */
	@FXML
	private Button RegistrationFinishBtn;

	/** The ID txt. */
	@FXML
	private TextField IDTxt;

	/** The First name txt. */
	@FXML
	private TextField FirstNameTxt;

	/** The Last name txt. */
	@FXML
	private TextField LastNameTxt;

	/** The Email txt. */
	@FXML
	private TextField EmailTxt;

	/** The Credit card txt. */
	@FXML
	private TextField CreditCardTxt;

	/** The Exp date txt. */
	@FXML
	private TextField ExpDateTxt;

	/** The CVV txt. */
	@FXML
	private TextField CVVTxt;

	/** The Phone lbl. */
	@FXML
	private Label PhoneLbl;

	/** The Address lbl. */
	@FXML
	private Label AddressLbl;

	/** The Phone txt. */
	@FXML
	private TextField PhoneTxt;

	/** The Address T xt. */
	@FXML
	private TextField AddressTXt;

	/** The User name lbl. */
	@FXML
	private Label UserNameLbl;

	/** The User name txt. */
	@FXML
	private TextField UserNameTxt;

	/** The password lbl. */
	@FXML
	private Label passwordLbl;

	/** The Password txt. */
	@FXML
	private TextField PasswordTxt;

	/** The car and modle btn. */
	@FXML
	private Button carAndModleBtn;

	/** The car and model pane. */
	@FXML
	private Pane carAndModelPane;

	/** The car number lbl. */
	@FXML
	private Label carNumberLbl;

	/** The car number txt. */
	@FXML
	private TextField carNumberTxt;

	/** The pricing model combox. */
	@FXML
	private ComboBox<PricingModel> pricingModelCombox;

	/** The pricing model lbl. */
	@FXML
	private Label pricingModelLbl;

	/** The purchase model lbl. */
	@FXML
	private Label purchaseModelLbl;

	/** The purchase model combox. */
	@FXML
	private ComboBox<PurchaseModel> purchaseModelCombox;

	/** The car and model finish btn. */
	@FXML
	private Button carAndModelFinishBtn;

	/** The car and model ID lbl. */
	@FXML
	private Label carAndModelIDLbl;

	/** The car and model ID txt. */
	@FXML
	private TextField carAndModelIDTxt;

	/** The fuel type lbl. */
	@FXML
	private Label fuelTypeLbl;

	/** The Fuel type combox. */
	@FXML
	private ComboBox<String> FuelTypeCombox;

	/** The car and model lbl. */
	@FXML
	private Label carAndModelLbl;

	/** The upload customer btn. */
	@FXML
	private Button uploadCustomerBtn;

	/** The open update customer pane. */
	@FXML
	private Button openUpdateCustomerPane;

	/** The update customer pane. */
	@FXML
	private Pane updateCustomerPane;

	/** The update customer lbl. */
	@FXML
	private Label updateCustomerLbl;

	/** The enter customer ID lbl. */
	@FXML
	private Label enterCustomerIDLbl;

	/** The Update id txt. */
	@FXML
	private TextField UpdateIdTxt;

	/** The up first name lbl. */
	@FXML
	private Label upFirstNameLbl;

	/** The up phone lbl. */
	@FXML
	private Label upPhoneLbl;

	/** The up credit card lbl. */
	@FXML
	private Label upCreditCardLbl;

	/** The up email lbl. */
	@FXML
	private Label upEmailLbl;

	/** The up lastt name lbl. */
	@FXML
	private Label upLasttNameLbl;

	/** The up exp date lbl. */
	@FXML
	private Label upExpDateLbl;

	/** The up address lbl. */
	@FXML
	private Label upAddressLbl;

	/** The up CVV lbl. */
	@FXML
	private Label upCVVLbl;

	/** The id change txt. */
	@FXML
	private TextField idChangeTxt;

	/** The first name changetxt. */
	@FXML
	private TextField firstNameChangetxt;

	/** The last name change txt. */
	@FXML
	private TextField lastNameChangeTxt;

	/** The email chang txt. */
	@FXML
	private TextField emailChangTxt;

	/** The CCN change txt. */
	@FXML
	private TextField CCNChangeTxt;

	/** The exp change txt. */
	@FXML
	private TextField expChangeTxt;

	/** The CVV change txt. */
	@FXML
	private TextField CVVChangeTxt;

	/** The phone change txt. */
	@FXML
	private TextField phoneChangeTxt;

	/** The address change txt. */
	@FXML
	private TextField addressChangeTxt;

	/** The save changes btn. */
	@FXML
	private Button saveChangesBtn;

	/** The change header. */
	@FXML
	private Text changeHeader;

	/** The sales pane. */
	@FXML
	private Pane salesPane;

	/** The sale header. */
	@FXML
	private Label saleHeader;

	/** The sales tbl. */
	@FXML
	private TableView<Sale> salesTbl;

	/** The select column. */
	@FXML
	private TableColumn<Sale, Boolean> selectColumn;

	/** The Sale ID column. */
	@FXML
	private TableColumn<Sale, Integer> SaleIDColumn;

	/** The status column. */
	@FXML
	private TableColumn<Sale, String> statusColumn;

	/** The company column. */
	@FXML
	private TableColumn<Sale, String> companyColumn;

	/** The fuel type column. */
	@FXML
	private TableColumn<Sale, String> fuelTypeColumn;

	/** The purchase mo column. */
	@FXML
	private TableColumn<Sale, String> purchaseMoColumn;

	/** The sale precent column. */
	@FXML
	private TableColumn<Sale, Float> salePrecentColumn;

	/** The start T column. */
	@FXML
	private TableColumn<Sale, String> startTColumn;

	/** The end T column. */
	@FXML
	private TableColumn<Sale, String> endTColumn;

	/** The start D column. */
	@FXML
	private TableColumn<Sale, String> startDColumn;

	/** The end D column. */
	@FXML
	private TableColumn<Sale, String> endDColumn;

	/** The days column. */
	@FXML
	private TableColumn<Sale, String> daysColumn;

	/** The create new sale pane. */
	@FXML
	private Pane createNewSalePane;

	/** The customer type lbl. */
	@FXML
	private Label customerTypeLbl;

	/** The customer type combox. */
	@FXML
	private ComboBox<CustomerTypes> customerTypeCombox;

	/** The customercompany name lbl. */
	@FXML
	private Label customercompanyNameLbl;

	/** The customercompany name txt. */
	@FXML
	private TextField customercompanyNameTxt;

	/** The new sale lbl. */
	@FXML
	private Label newSaleLbl;

	/** The company name lbl. */
	@FXML
	private Label companyNameLbl;

	/** The Fuel type lbl. */
	@FXML
	private Label FuelTypeLbl;

	/** The sale percents lbl. */
	@FXML
	private Label salePercentsLbl;

	/** The sale percent txt. */
	@FXML
	private TextField salePercentTxt;

	/** The start time lbl. */
	@FXML
	private Label startTimeLbl;

	/** The start time txt. */
	@FXML
	private TextField startTimeTxt;

	/** The end time lbl 1. */
	@FXML
	private Label endTimeLbl1;

	/** The end time txt. */
	@FXML
	private TextField endTimeTxt;

	/** The fuel type txt. */
	@FXML
	private TextField fuelTypeTxt;

	/** The start date lbl. */
	@FXML
	private Label startDateLbl;

	/** The end date lbl. */
	@FXML
	private Label endDateLbl;

	/** The start date picker. */
	@FXML
	private DatePicker startDatePicker;

	/** The end date picker. */
	@FXML
	private DatePicker endDatePicker;

	/** The days lbl. */
	@FXML
	private Label daysLbl;

	/** The sunday. */
	@FXML
	private CheckBox sunday;

	/** The monday. */
	@FXML
	private CheckBox monday;

	/** The tuesday. */
	@FXML
	private CheckBox tuesday;

	/** The wednesday. */
	@FXML
	private CheckBox wednesday;

	/** The thuesday. */
	@FXML
	private CheckBox thuesday;

	/** The friday. */
	@FXML
	private CheckBox friday;

	/** The saturday. */
	@FXML
	private CheckBox saturday;

	/** The all check box. */
	@FXML
	private CheckBox allCheckBox;

	/** The CVV image 1. */
	@FXML
	private ImageView CVVImage1;

	/** The divid exp date lbl. */
	@FXML
	private Label dividExpDateLbl;

	/** The up address no lbl. */
	@FXML
	private Label upAddressNoLbl;

	/** The up adrdress no txt. */
	@FXML
	private TextField upAdrdressNoTxt;

	/** The ch exp format lbl. */
	@FXML
	private Label chExpFormatLbl;

	/** The divid exp date 1. */
	@FXML
	private Label dividExpDate1;

	/** The exp change year txt. */
	@FXML
	private TextField expChangeYearTxt;

	/** The exp change month txt. */
	@FXML
	private TextField expChangeMonthTxt;

	/** The Exp date year txt. */
	@FXML
	private TextField ExpDateYearTxt;

	/** The Exp date month txt. */
	@FXML
	private TextField ExpDateMonthTxt;

	/** The help image 1. */
	@FXML
	private ImageView helpImage1;

	/** The save sale btn. */
	@FXML
	private Button saveSaleBtn;

	/** The address no txt. */
	@FXML
	private TextField addressNoTxt;

	/** The address no lbl. */
	@FXML
	private Label addressNoLbl;

	/** The delete sale btn. */
	@FXML
	private Button deleteSaleBtn;

	/** The date format lbl. */
	@FXML
	private Label dateFormatLbl;

	/** The sales btn. */
	@FXML
	private Button salesBtn;

	/** The create sale btn. */
	@FXML
	private Button createSaleBtn;

	/** The CVV image. */
	@FXML
	private ImageView CVVImage;

	/** The company name txt. */
	@FXML
	private Text companyNameTxt;

	/** The help image. */
	@FXML
	private ImageView helpImage;

	/** The help CVV btn. */
	@FXML
	private Button helpCVVBtn;

	/** The fuel types for sale combox. */
	@FXML
	private ComboBox<String> fuelTypesForSaleCombox;

	/** The replace car btn. */
	@FXML
	private Button replaceCarBtn;

	/** The old car num lbl. */
	@FXML
	private Label oldCarNumLbl;

	/** The old car num txt. */
	@FXML
	private TextField oldCarNumTxt;

	/** The choose company 1. */
	@FXML
	private ComboBox<String> chooseCompany1;

	/** The choose company 2. */
	@FXML
	private ComboBox<String> chooseCompany2;

	/** The choose company 3. */
	@FXML
	private ComboBox<String> chooseCompany3;

	/** The second car lbl. */
	@FXML
	private Label secondCarLbl;

	/** The second car txt. */
	@FXML
	private TextField secondCarTxt;

	/** The choose comp lbl. */
	@FXML
	private Label chooseCompLbl;

	/** The secfuel type lbl. */
	@FXML
	private Label secfuelTypeLbl;

	/** The sec fuel type combox. */
	@FXML
	private ComboBox<String> secFuelTypeCombox;

	/** The current pane. */
	private Pane currentPane;

	/** The markem. */
	public static Employee markem;

	/** The enter 2 cars flag. */
	public boolean replaceCarFlag = false, enter2CarsFlag = false;

	/** The sec car 2. */
	Car secCar2 = null;

	/** The date formatter. */
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/** The fuel types. */
	private ArrayList<String> fuelTypes = new ArrayList<String>();

	/** The selected sales. */
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();

	/** The fuel types for sale. */
	private ArrayList<String> fuelTypesForSale = new ArrayList<String>();

	/** The companys. */
	private ArrayList<String> companys = new ArrayList<String>();

	/**
	 * Define the scene of the marketing employee and load the fxml file.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */

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

	/**
	 * This function is to sigh out the user from the system.
	 *
	 * @param event the event
	 */

	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(markem.getId(), markem.getClass().toString());
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
	}

	/**
	 * Switching Pans - hide the current pane and make the new pane visible.
	 *
	 * @param newPane the new pane
	 */

	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	/**
	 * Switch to the registration pane.
	 *
	 * @param event the event
	 */
	@FXML
	void openNewClientPane(ActionEvent event) {
		customercompanyNameLbl.setVisible(false);
		customercompanyNameTxt.setVisible(false);
		CVVImage.setVisible(false);
		CVVImage1.setVisible(false);
		switchPanes(NewClientPane);
	}

	/**
	 * When the user click on the help button (?) in the registration pane the
	 * function set the CVV image as visible/not visible.
	 *
	 * @param event the event
	 */

	@FXML
	void openCVVImgae(MouseEvent event) {
		if (CVVImage.isVisible())
			CVVImage.setVisible(false);
		else
			CVVImage.setVisible(true);
	}

	/**
	 * When the user click on the help button (?) in the update customer pane the
	 * function set the CVV image as visible/not visible.
	 *
	 * @param event the event
	 */

	@FXML
	void openCVVImgae1(MouseEvent event) {
		if (CVVImage1.isVisible())
			CVVImage1.setVisible(false);
		else
			CVVImage1.setVisible(true);
	}

	/**
	 * Switch to the creating new sale pane.
	 *
	 * @param event the event
	 */

	@FXML
	void openNewSalePane(ActionEvent event) {
		switchPanes(createNewSalePane);
		String company = markem.getCompanyName();
		companyNameTxt.setText(company);
	}

	/**
	 * Switch to update customers pane - for detail update.
	 *
	 * @param event the event
	 */

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

	/**
	 * Switch to car and model pane.
	 *
	 * @param event the event
	 */

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

	/**
	 * Switch to sales view pane.
	 *
	 * @param event the event
	 */

	@FXML
	void openSalesPane(ActionEvent event) {
		switchPanes(salesPane);
		uploadSalesToTable();
	}

	/**
	 * Initializing the scene and define the current pane. Set values into
	 * pricingModel, purchaseModel, FuelType, customerType, fuelTypesForSale combo
	 * boxes Define data pickers
	 *
	 * @param location  the location
	 * @param resources the resources
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CVVImage.setVisible(false);
		CVVImage1.setVisible(false);
		System.out.println("employee:  " + markem);
		fuelTypes.add("95");
		fuelTypes.add("MOTOR CYCLES");
		fuelTypes.add("Solar");
		// loading the main window data
		System.out.println("hello");
		System.out.println(markem);
		// set the current pane as the main
		currentPane = NewClientPane;
		// show the main pane and hide the others

		NewClientPane.setVisible(true);
		carAndModelPane.setVisible(false);
		updateCustomerPane.setVisible(false);
		salesPane.setVisible(false);
		createNewSalePane.setVisible(false);
		hellomessage.setText(markem.getFirstName());
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

	/**
	 * Receive all the registration date from the user Check all the input for
	 * validation Create new customer object and send to server for update.
	 *
	 * @param event the event
	 */

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
		System.out.println(expDateMon + "  " + expDateYear);

		CVV = CVVTxt.getText();

		cusType = (CustomerTypes) customerTypeCombox.getValue();
		if (cusType == CustomerTypes.Company) {
			companyName = customercompanyNameTxt.getText();
			if (companyName == null) {
				JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
				return;
			}
		}
		// check that all the data was entered
		if (id == null || firstName == null || lastName == null || mail == null || phoneNumber == null
				|| address == null || userName == null || password == null || creditCardNo == null
				|| expDateMon.isEmpty() || expDateYear.isEmpty() || CVV == null || addressNo == null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}

		if (expDateMon.length() == 1) {
			StringBuilder build = new StringBuilder();
			build.append(0);
			build.append(expDateMon);
			expDateMon = build.toString();
		}

		if (!testId(id)) {
			inputMsg.append("ID - 9 digits, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(firstName)) {
			inputMsg.append("first name - only characters, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(lastName)) {
			inputMsg.append("last name - only characters, " + "\n");
			flag = true;
		}
		if (!testPhone(phoneNumber)) {
			inputMsg.append("phone - 10 digits, " + "\n");
			flag = true;
		}
		if (!mail.matches(EMAIL_REGEX)) {
			inputMsg.append("mail - CC@CC.CC, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(address)) {
			inputMsg.append("address - only characters, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyNumbers(addressNo)) {
			inputMsg.append("address no - only digits, " + "\n");
			flag = true;
		}
		if (!testCreditCard(creditCardNo)) {
			inputMsg.append("credit card no - 10 or 16 digits, " + "\n");
			flag = true;
		}
		if (!testDateOfExp(expDateMon, expDateYear)) {
			inputMsg.append("Exp Date, " + "\n");
			flag = true;
		}
		if (!testCVV(CVV)) {
			inputMsg.append("CVV - 3 digits, " + "\n");
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
			expDate.append(expDateMon);
			expDate.append("/");
			expDate.append(expDateYear);

			fullAddress.append(address);
			fullAddress.append(" ");
			fullAddress.append(addressNo);
			Customer customer = new Customer(userName, password, firstName, lastName, mail, id, phoneNumber, 0,
					fullAddress.toString(), 0, 0, creditCardNo, expDate.toString(), CVV, cusType.toString(),
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

	/**
	 * Open / hide the company fields according to the customer type.
	 *
	 * @param event the event
	 */

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

	/**
	 * Receives id , send to server and notify the user if the customer exist in the
	 * system.
	 *
	 * @param event the event
	 */

	@FXML
	void uploadCustomer(ActionEvent event) {
		String id;
		id = carAndModelIDTxt.getText();
		int res;
		if (id == null || !testId(id))
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

	/**
	 * Open / hide the replacing car fields when the user clicks on the replacing
	 * car button.
	 *
	 * @param event the event
	 */

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

	/**
	 * Receive all the car and module data from the user Check all the input for
	 * validation Create new car object and send to server for update Send all the
	 * module data to the server for update.
	 *
	 * @param event the event
	 */

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

		if (id.isEmpty() || carNumber == null || fuelType == null || pricingM == null || purchaseM == null) {
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
			if (company1 == company2 || company2 == company3 || company1 == company3) {
				JOptionPane.showMessageDialog(null, "You need to choose different companies");
				return;
			}
			if (company3 == null) {
				companyNames.append(company1);
				companyNames.append(",");
				companyNames.append(company2);
			} else {
				companyNames.append(company1);
				companyNames.append(",");
				companyNames.append(company2);
				companyNames.append(",");
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

	/**
	 * Open one or three company comboBoxes according to the selected pricing model
	 * by the user.
	 *
	 * @param event the event
	 */

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

	/**
	 * Receive customer id, send to server to check if exist Upload all the user
	 * data to the gui for the user.
	 *
	 * @param event the event
	 */

	@FXML
	void showCustomeDetails(ActionEvent event) {
		String id = UpdateIdTxt.getText();
		Customer customer;
		String expDate;
		if (id == null)
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

	/**
	 * Receive customer data from the user Check all the input for validation Send
	 * to server for update details of existing customer.
	 *
	 * @param event the event
	 */

	@FXML
	void changeCustomerDetails(ActionEvent event) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		ArrayList<String> upCustomer = new ArrayList<String>();
		String chFirst, chLast, chEmail, chCreditNo, chExpDate, chCvv, chPhone, chAddress, chId, chAddressNo;
		StringBuilder fullAddress = new StringBuilder();
		StringBuilder inputMsg = new StringBuilder("The next filleds are in inccorect format: " + "\n");
		StringBuilder expDate = new StringBuilder();
		boolean flag = false;
		// get all the data that the user entered
		chFirst = firstNameChangetxt.getText();
		chLast = lastNameChangeTxt.getText();
		chEmail = emailChangTxt.getText();
		chCreditNo = CCNChangeTxt.getText();
		String expDateMon = expChangeMonthTxt.getText();
		String expDateYear = expChangeYearTxt.getText();

		chCvv = CVVChangeTxt.getText();
		chPhone = phoneChangeTxt.getText();
		chAddress = addressChangeTxt.getText();
		chAddressNo = upAdrdressNoTxt.getText();
		System.out.println("no: " + chAddressNo);
		chId = UpdateIdTxt.getText();

		if (chFirst == null || chLast == null || chEmail == null || chCreditNo == null || expDateMon.isEmpty()
				|| expDateYear.isEmpty() || chCvv == null || chPhone == null || chAddress == null || chId == null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;

		}

		if (expDateMon.length() == 1) {
			StringBuilder build = new StringBuilder();
			build.append(0);
			build.append(expDateMon);
			expDateMon = build.toString();
		}

		if (!testId(chId)) {
			inputMsg.append("ID - 9 digits, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chFirst)) {
			inputMsg.append("first name - only characters, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chLast)) {
			inputMsg.append("last name - only characters, " + "\n");
			flag = true;
		}
		if (!testPhone(chPhone)) {
			inputMsg.append("phone - 10 digits, " + "\n");
			flag = true;
		}
		if (!chEmail.matches(EMAIL_REGEX)) {
			inputMsg.append("mail - CC@CC.CC, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyCharacter(chAddress)) {
			inputMsg.append("address - only characters, " + "\n");
			flag = true;
		}
		if (!checkIfStringContainsOnlyNumbers(chAddressNo)) {
			inputMsg.append("address no - only digits, " + "\n");
			flag = true;
		}

		if (!testCreditCard(chCreditNo)) {
			inputMsg.append("credit card no - 10 or 16 digits, " + "\n");
			flag = true;
		}
		if (!testDateOfExp(expDateMon, expDateYear)) {
			inputMsg.append("Exp Date, " + "\n");
			flag = true;
		}
		if (!testCVV(chCvv)) {
			inputMsg.append("CVV - 3 digits, " + "\n");
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

		expDate.append(expDateMon);
		expDate.append("/");
		expDate.append(expDateYear);

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

	/**
	 * Initialize the sales table ,using getSales() function to get all the data
	 * from the server Display all the sales.
	 */

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

	/**
	 * This function asks for all the sales data from the server and return it to
	 * uploadSalesToTable().
	 *
	 * @return ObservableList<Sale>
	 */

	public ObservableList<Sale> getSales() {
		ArrayList<Sale> sale = EmployeeCC.getAllSales();
		ObservableList<Sale> sales = FXCollections.observableArrayList(sale);

		return sales;
	}

	/**
	 * Sends array list of sales to the server to delete them from the DB.
	 *
	 * @param event the event
	 */

	@FXML
	void deleteSals(ActionEvent event) {
		if (EmployeeCC.deleteSales(selectedSales)) {
			JOptionPane.showMessageDialog(null, "Sales deleted!");
			uploadSalesToTable();
		} else
			JOptionPane.showMessageDialog(null, "Error! sales could not be deleted, try again");
	}

	/**
	 * Receive data for new sale from the user Check all the input for validation
	 * Create new sale object and send to sever to insert to the DB.
	 *
	 * @param event the event
	 * @throws ParseException the parse exception
	 */

	@FXML
	void createNewSale(ActionEvent event) throws ParseException {
		String fuelType, startTime, endTime, startDate = null, endDate = null;
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

		if (startDate == null || endDate == null || startTime == null || endTime == null || fuelType == null
				|| salePercentTxt.getText() == null) {
			JOptionPane.showMessageDialog(null, "One or more of the details is empty, please fill all the fileds");
			return;
		}

		if (!checkIfStringContainsOnlyNumbersInFloatType(salePercentTxt.getText())) {
			JOptionPane.showMessageDialog(null, "You need to enter sale percent in the following format: 0.xx");
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
		if (startDate.compareTo(endDate) == 0) {
			if (startTime.compareTo(endTime) > 0)
				JOptionPane.showMessageDialog(null, "End time is earlier than start time in the same day");
			return;
		}

		if (startDate.compareTo(endDate) > 0) {
			JOptionPane.showMessageDialog(null, "End date is earlier than start date");
			return;
		}

		if (flag) {
			JOptionPane.showMessageDialog(null, inputMsg.toString());
			return;
		}
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
		percent = Float.parseFloat(salePercentTxt.getText());
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
		} else
			JOptionPane.showMessageDialog(null, "Error! sales could not be added, try again");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////// INPUTTESTS///////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Check if the string is build as float number type.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean checkIfStringContainsOnlyNumbersInFloatType(String str) {
		boolean flag = false;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				if (str.charAt(i) == '.') {
					flag = true;
					continue;
				}
				flag = false;
				break;
			} else if (!flag) {
				if (str.charAt(i) != '0')
					return false;
			}
		}
		return flag;
	}

	/**
	 * Check if the string contains only numbers.
	 *
	 * @param str the str
	 * @return true or false
	 */

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

	/**
	 * Test ID format.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean testId(String str) {
		if ((str.length() == 9) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	/**
	 * Check if the string contains only characters.
	 *
	 * @param str the str
	 * @return true or false
	 */

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

	/**
	 * Test phone number format.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean testPhone(String str) {
		if ((str.length() == 10) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	/**
	 * Test car number format.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean testCar(String str) {
		if ((str.length() == 7 || str.length() == 8) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	/**
	 * Test credit card number format.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean testCreditCard(String str) {
		if ((str.length() == 16 || str.length() == 10) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	/**
	 * Test CVV number format.
	 *
	 * @param str the str
	 * @return true or false
	 */

	public static boolean testCVV(String str) {
		if ((str.length() == 3) && (checkIfStringContainsOnlyNumbers(str)))
			return true;
		return false;
	}

	/**
	 * Check id user name exist in the DB.
	 *
	 * @param userName the user name
	 * @return true or false
	 */

	public static boolean checkIfUserNameExist(String userName) {
		return EmployeeCC.checkIfUserNameExist(userName);
	}

	/**
	 * Test Expire Date values.
	 *
	 * @param month the month
	 * @param year  the year
	 * @return true or false
	 */

	public static boolean testDateOfExp(String month, String year) {
		if ((!checkIfStringContainsOnlyNumbers(month)) || (!checkIfStringContainsOnlyNumbers(year)))
			return false;
		if ((Integer.parseInt(month) < 1) || (Integer.parseInt(month) > 12))
			return false;
		if ((Integer.parseInt(year) == 2020) && (Integer.parseInt(month) < 6))
			return false;
		if (Integer.parseInt(year) < 2020 || year.length() > 4)
			return false;
		return true;
	}

	/**
	 * \ Test time value.
	 *
	 * @param time the time
	 * @return true or false
	 */

	public static boolean testTime(String time) {
		if (Integer.parseInt(time.substring(0, 2)) > 23 || Integer.parseInt(time.substring(0, 2)) < 0)
			return false;
		System.out.println(Integer.parseInt(time.substring(3, 4)));
		if (Integer.parseInt(time.substring(3, 5)) > 59 || Integer.parseInt(time.substring(3, 5)) < 0)
			return false;
		return true;
	}

}
