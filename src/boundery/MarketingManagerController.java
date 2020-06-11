package boundery;

import java.awt.List;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent.EventType;

import Entity.CompanyFuel;
import Entity.Employee;
import Entity.Fuel;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import client.ClientUI;
import client.CustomerCC;
import client.EmployeeCC;
import client.MyFuelClient;
import client.UserCC;
import enums.Commands;
import enums.CustomerRateTypes;
import enums.MarkitingManagerReport;
import enums.RatesStatus;
import enums.SaleStatus;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.FileManagmentSys;

public class MarketingManagerController implements Initializable {

	@FXML
	private VBox vboxPeriodicGuiTable;
	@FXML
	private Text SaleIDSaleDAta;
	@FXML
	private Text statusSaleDAta;
	@FXML
	private Text CompanyNameSaleDAta;
	@FXML
	private Text fuelTypeSaleDAta;
	@FXML
	private Text purchaseModuleSaleDAta;
	@FXML
	private Text salePercentSaleDAta;
	@FXML
	private Text startTimeSaleDAta;
	@FXML
	private Text endTimeSaleDAta;
	@FXML
	private Text saleDataViewIndex;
	@FXML
	private Text enterSaleTxt;
	@FXML
	private Text startDateSaleDAta;
	@FXML
	private Text endDateSaleDAta;
	@FXML
	private Text saleDaysSaleDAta;
	@FXML
	private Button SalesWindowBtn;
	@FXML
	private Button deActivateSaleBtn;
	@FXML
	private Button fuelRatesWindowBtn;
	@FXML
	private Button reportGenerationWindowBtn;
	@FXML
	private Pane markitingManagerNofPane;
	@FXML
	private Text helloUserTxt;
	@FXML
	private Text salesHeader;
	@FXML
	private TextArea NotificationsTextArea;
	@FXML
	private Pane fuelRatesPane;
	@FXML
	private ComboBox<CustomerRateTypes> rateType;
	@FXML
	private TextField newRatetxt;
	@FXML
	private Text maxFuelPricetxt;
	@FXML
	private ComboBox<RatesStatus> rateTypeCombo;
	@FXML
	private TableView<PricingModule> companyRatesTable;
	@FXML
	private TableColumn<PricingModule, Boolean> rateCheckBoxSelect;
	@FXML
	private TableColumn<PricingModule, String> modelNameRate;
	@FXML
	private TableColumn<PricingModule, Float> rateNewRate;
	@FXML
	private TableColumn<PricingModule, RatesStatus> rateStatus;
	@FXML
	private Button updateRates;
	@FXML
	private Pane salePane;
	@FXML
	private TableView<Sale> salesDetailsTable;
	@FXML
	private TableColumn<Sale, Boolean> SelectSale;
	@FXML
	private TableColumn<Sale, String> saleNumber;
	@FXML
	private TableColumn<Sale, String> SaleFuelType;
	@FXML
	private TableColumn<Sale, String> SaleStartDate;
	@FXML
	private TableColumn<Sale, String> saleEndDate;
	@FXML
	private TableColumn<Sale, Float> salePercent;
	@FXML
	private Button viewMoreSaleDetailsBtn;
	@FXML
	private Button activateSaleBtn;
	@FXML
	private Button logOutBtn;
	@FXML
	private Button mainPane;
	@FXML
	private ComboBox<SaleStatus> salesTypeCombo;
	@FXML
	private Pane reportsPane;
	@FXML
	private ComboBox<MarkitingManagerReport> reportKindCombo;
	@FXML
	private TextField reportsaleNumber;
	@FXML
	private Button generateReportBtn;
	@FXML
	private Pane PeriodicReportPane;
	@FXML
	private Pane saleResponseReportPane;
	@FXML
	private Pane saleDataPane;
	//response report table
	@FXML
	private TableView<ResponseReportData> saleResponseReportTable;
	@FXML
	private TableColumn<ResponseReportData, String> customerIDResponseReport;
	@FXML
	private TableColumn<ResponseReportData, Float> amountOfPurchaseresponseReport;
	@FXML
	private Text totaleNumberOfCustomersResponseReport;
	@FXML
	private Text totalePurchasesResponseReport;
	@FXML
	private Button PrevSaleDetailsBtn;
	@FXML
	private Button nextSaleDetailsBtn;
	@FXML
	private Text selectDateTxt;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

	@FXML
	void selectReportType(ActionEvent event) { 
		if (reportKindCombo.getValue() == MarkitingManagerReport.PeriodicReport) {
			enterSaleTxt.setVisible(false);
			reportsaleNumber.setVisible(false);

			PeriodicReportPane.setVisible(true);
			saleResponseReportPane.setVisible(false);
			//the select data buttons
			selectDateTxt.setVisible(true);
		    startDate.setVisible(true);
		    endDate.setVisible(true);
			

		}
		if (reportKindCombo.getValue() == MarkitingManagerReport.saleResponseReport) {
			enterSaleTxt.setVisible(true);
			reportsaleNumber.setVisible(true);

			PeriodicReportPane.setVisible(false);
			saleResponseReportPane.setVisible(true);
			//the select data buttons
			selectDateTxt.setVisible(false);
		    startDate.setVisible(false);
		    endDate.setVisible(false);
			
		}
	}

	@FXML
	void PrevSaleDetails(ActionEvent event) {
		// logics
		if (currentSaleDataIndex == selectedSales.size() - 1)
			nextSaleDetailsBtn.setDisable(false);
		if (currentSaleDataIndex == 1)
			PrevSaleDetailsBtn.setDisable(true);
		// get the data
		loadSaleFullDetails(false);
	}

	@FXML
	void nextSaleDetails(ActionEvent event) {
		// logics
		if (currentSaleDataIndex == selectedSales.size() - 2)
			nextSaleDetailsBtn.setDisable(true);
		if (currentSaleDataIndex == 0)
			PrevSaleDetailsBtn.setDisable(false);
		// get the data
		loadSaleFullDetails(true);
	}

	@FXML
	void finishViweingSaleDetails(ActionEvent event) {
		switchPanes(salePane);
	}

	@FXML
	void openMainPane(ActionEvent event) {
		switchPanes(markitingManagerNofPane);
	}

	@FXML
	void OpenSalePane(ActionEvent event) {
		switchPanes(salePane);
		activateSaleBtn.setVisible(false);
		deActivateSaleBtn.setVisible(false);
	}

	/**
	 * update the selected Pricing models status to active
	 * in this case if there is two rates selected to the same
	 * Pricing model it wouldn't update the status and return
	 * @param event
	 */
	
	@FXML
	void UpdateSelectedRates(ActionEvent event) {
		// update all the selected rates
		// send to server selectedRates
		boolean flag=false;
		Set<Integer> sets = new HashSet<Integer>();
		for (PricingModule rate : selectedRates) {
			rate.setStatus(RatesStatus.active);
			if(sets.contains(rate.getModelNumber())) {
				flag=true;
				break;
			}
			sets.add(rate.getModelNumber());
		}
		if(flag) {
			for (PricingModule rate : selectedRates) {
				rate.setStatus(RatesStatus.confirmed);
			}
			JOptionPane.showMessageDialog(null, "can't choose the same fuel type twice");
			return;
		}
		
		if (EmployeeCC.updatePricingModel(selectedRates)) 
			JOptionPane.showMessageDialog(null,"upate succeded");
		else  JOptionPane.showMessageDialog(null,"update un-succeded one or more of the data didn't update");	
		//refresh the table
		chooserateType(null);
	}

	@FXML
	void activateSale(ActionEvent event) {
		if (selectedSales.size() <= 0)
			JOptionPane.showMessageDialog(null, "please select at least one sale");
		else {
			if (event.getSource().equals(activateSaleBtn)) {
				for (Sale sale : selectedSales)
					sale.setStatus(SaleStatus.activated);
			} else if (event.getSource().equals(deActivateSaleBtn)) {
				for (Sale sale : selectedSales)
					sale.setStatus(SaleStatus.not_Activated);
			}
			
			if (EmployeeCC.updateSale(selectedSales)) 
				JOptionPane.showMessageDialog(null,"upadting succeded");
			else  JOptionPane.showMessageDialog(null,"upadting un-succeded one or more of the data didn't upadte");	
			
			//update the table
			chooseSaleType(null);
		}
	}

	@FXML
	void chooseSaleType(ActionEvent event) {
		// GUI-structure
		if (salesTypeCombo.getValue() == SaleStatus.activated) {
			deActivateSaleBtn.setVisible(true);
			deActivateSaleBtn.setLayoutX(43);
			activateSaleBtn.setVisible(false);
			salesHeader.setText("Available Activated Sales:");
		} else {
			deActivateSaleBtn.setVisible(false);
			activateSaleBtn.setVisible(true);
			salesHeader.setText("Available Non-Activated Sales:");
		}

		// get the data for gui
		String saleType = salesTypeCombo.getValue().toString();
		// call the server to get the sales data
		ObservableList<Sale> data = FXCollections.observableArrayList(EmployeeCC.getCompanySalesByStatus(
				new Sale(0, saleType, markitingManager.getCompanyName(), null, null, 0, null, null, null, null, null)));		

		// Fill the table
		salesDetailsTable.getItems().setAll(data);

		// empety the selected row
		selectedSales.clear();
	}
	
	private void buildSaleTable() {
		GUIBuiltParts.buildCheckBOXForTable(SelectSale, selectedSales);

		saleNumber.setCellValueFactory(new PropertyValueFactory<Sale, String>("saleID"));
		SaleFuelType.setCellValueFactory(new PropertyValueFactory<Sale, String>("fuelType"));
		SaleStartDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("startDate"));
		saleEndDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("endDate"));
		salePercent.setCellValueFactory(new PropertyValueFactory<Sale, Float>("salePercent"));
	} 

	@FXML
	void createNewRate(ActionEvent event) {
		// get the data entered
		String fuelTypeName = rateType.getValue().toString();
		String sFuelNewRate = newRatetxt.getText();

		// check the data
		if (fuelTypeName.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please choose Fuel Type");
		else if (sFuelNewRate.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please enter the new rate");
		else {
			float fFuelNewRate = Float.valueOf(sFuelNewRate);
			if (fFuelNewRate <= 0)
				JOptionPane.showMessageDialog(null, "Unvalid value for the new rate");
			else {
				
				if (EmployeeCC.craeteNewPricingModel(new PricingModule(rateType.getValue().ordinal(),
						fFuelNewRate, markitingManager.getCompanyName(), RatesStatus.created))) 
					JOptionPane.showMessageDialog(null,"Creating New Rate done succesfully");
				else  JOptionPane.showMessageDialog(null,"Creating New Rate un-succesfull");	
			}
		}
	}

	// not done
	@FXML
	void generateReport(ActionEvent event) {
		// get data from gui
		String saleNumber = reportsaleNumber.getText();
		
		if(reportKindCombo.getSelectionModel() != null){
			ObservableList<ResponseReportData> data = FXCollections.observableArrayList();
			Object obj;
			// call the server and get the data

			if (reportKindCombo.getValue() == MarkitingManagerReport.PeriodicReport) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				if(startDate.getValue()==null||endDate.getValue()==null) {
					JOptionPane.showMessageDialog(null, "please select dates");
					return;
				}
				String start=formatter.format(startDate.getValue());
				String end=formatter.format(endDate.getValue());
				
				obj=EmployeeCC.createPeriodicResport(markitingManager.getCompanyName(),start,end);
				if (obj instanceof Commands) {
					JOptionPane.showMessageDialog(null, "there is no such sale with this ID");
					return;
				}
				ArrayList<String> rows=FileManagmentSys.readMarkitingManagerReport((File)obj,FileManagmentSys.periodicReport);
				String[] lines = rows.get(0).split("\\n");
				buildPeriodicReportTable(data,lines);
			} 
			else if (reportKindCombo.getValue() == MarkitingManagerReport.saleResponseReport) {
				if (saleNumber.isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "please enter sale number");
					return;
				}
				obj = EmployeeCC.createSaleResponseResport(saleNumber, markitingManager.getCompanyName());
				if (obj == null) {
					JOptionPane.showMessageDialog(null, "there is no such sale with this ID");
					totaleNumberOfCustomersResponseReport.setText("0");
					totalePurchasesResponseReport.setText("0");
					saleResponseReportTable.getItems().setAll(data);
					return;
				}
				
				ArrayList<String> resArray = FileManagmentSys.readMarkitingManagerReport((File)obj,FileManagmentSys.responseReport);
				filldataObjectFromFile(resArray,data);
				// fill the table
				saleResponseReportTable.getItems().setAll(data);
				totaleNumberOfCustomersResponseReport.setText(resArray.get(0).replaceAll("[^0-9?!\\.]",""));
				totalePurchasesResponseReport.setText(resArray.get(1).replaceAll("[^0-9?!\\.]",""));
				//
			}
		}
		else JOptionPane.showMessageDialog(null, "please Select report kind");

	}
	
	private void buildPeriodicReportTable(ObservableList<ResponseReportData> data,String []strData) {
		TableView<ObservableList<String>> tableView = new TableView<>();
		 
		ArrayList<String> columnNames=convertRowToArrayListPeriodicReport(strData[0]);
		int i;
		//add colomns
		for (i = 0; i < columnNames.size(); i++) {
		    final int finalIdx = i;
		    TableColumn<ObservableList<String>, String> column = new TableColumn<>(
		            columnNames.get(i)
		    );
		    column.setCellValueFactory(param ->
		            new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
		    );
		    tableView.getColumns().add(column);
		}
		// add data
        for (i = 1; i < strData.length; i++) {
        	ArrayList<String> words = convertRowToArrayListPeriodicReport(strData[i]);;
        	
            tableView.getItems().add(
                    FXCollections.observableArrayList(words));
        }
        tableView.setLayoutX(10);
        tableView.setLayoutY(10);
        //
        tableView.setMinWidth(Math.min(600,columnNames.size()*120));
        tableView.setMaxHeight(300);
        
        tableView.setEditable(false);
        PeriodicReportPane.getChildren().add(tableView);
	}
	
	private static ArrayList<String> convertRowToArrayListPeriodicReport(String str){
		int i=0;
		ArrayList<String> columnData = new ArrayList<String>();
		while(i<str.length()) {
			if(i==0)columnData.add(str.substring(i, (i=i+12)).replaceAll("\\s+", ""));
			else columnData.add(str.substring(i, (i=i+15)).replaceAll("\\s+", ""));
		}
		return columnData;
	}
	
	private void buildsaleResponseReportTable() {
		customerIDResponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, String>("customerID"));
		amountOfPurchaseresponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, Float>("amountOfPurchase"));
	}
	
	private void filldataObjectFromFile(ArrayList<String> resArray,ObservableList<ResponseReportData> data) {
		// sprerate to lines
		String[] lines = resArray.get(2).split("\\n");
		// fill the data object
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
			data.add(new ResponseReportData(lines[lineIndex].substring(0, 12).replaceAll("\\s+", ""),
					lines[lineIndex].substring(12, lines[lineIndex].length()).replaceAll("\\s+", "")));
		}
	}
	
	@FXML
	void openFuelRatesPane(ActionEvent event) {
		switchPanes(fuelRatesPane);
	}

	@FXML
	void openReportGenerationPane(ActionEvent event) {
		switchPanes(reportsPane);
	}

	@FXML
	void viewMoreSaleDetails(ActionEvent event) {
		currentSaleDataIndex = -1;
		if (selectedSales.size() <= 0)
			JOptionPane.showMessageDialog(null, "please select at least one sale");
		else {
			switchPanes(saleDataPane);
			nextSaleDetailsBtn.setDisable(false);
			PrevSaleDetailsBtn.setDisable(false);
			if (currentSaleDataIndex >= selectedSales.size() - 2)
				nextSaleDetailsBtn.setDisable(true);
			if (currentSaleDataIndex <= 0)
				PrevSaleDetailsBtn.setDisable(true);
			loadSaleFullDetails(true);
		}
	}

	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(markitingManager.getId(), markitingManager.getClass().toString());

	}

//not done
	@FXML
	void chooseFuelTypeForNewRate(ActionEvent event) {
		CustomerRateTypes rateTypeSelected = rateType.getValue();

		PricingModule pricingModule = EmployeeCC.getCompanyActiveRateAccordingPriceModel(new PricingModule(rateTypeSelected.ordinal(), 0, markitingManager.getCompanyName(), null));
		
		maxFuelPricetxt.setText(String.format("the current rate is : %.2f",pricingModule.getSalePercent()));
	}

	@FXML
	void chooserateType(ActionEvent event) {
		RatesStatus selected = rateTypeCombo.getValue();
		if (selected == RatesStatus.confirmed)
			updateRates.setVisible(true);
		else
			updateRates.setVisible(false);

		//get company pricing model rates
		ObservableList<PricingModule> data = FXCollections.observableArrayList(EmployeeCC
				.getAllCompanyRatesByStatus(new PricingModule(0,0, markitingManager.getCompanyName(),selected)));
		//set the table according to comboBox
		if(selected.equals(RatesStatus.confirmed)) {
			rateCheckBoxSelect.setVisible(true);
			companyRatesTable.setPrefWidth(550);
		}else {
			rateCheckBoxSelect.setVisible(false);
			companyRatesTable.setPrefWidth(550-rateCheckBoxSelect.getPrefWidth());
		}
		//fill table data
		companyRatesTable.getItems().setAll(data);
		
		selectedRates.clear();

	}
	
	private void buildCompanyRateTable(){
		//create comboBOX
		GUIBuiltParts.buildCheckBOXForTable(rateCheckBoxSelect,selectedRates);

		modelNameRate.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("modelname"));

		rateNewRate.setCellValueFactory(new PropertyValueFactory<PricingModule, Float>("salePercent"));

		rateStatus.setCellValueFactory(new PropertyValueFactory<PricingModule, RatesStatus>("status"));
	}

	private Pane currentPane;
	public static Employee markitingManager;
	private ArrayList<PricingModule> selectedRates = new ArrayList<PricingModule>();
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();
	private int currentSaleDataIndex;

	// this class is just to show the table of the report
	protected class ResponseReportData {
		String customerID;
		String priceOfPurchase;

		public ResponseReportData(String customerID, String priceOfPurchase) {
			this.customerID = customerID;
			this.priceOfPurchase = priceOfPurchase;
		}

		public String getCustomerID() {
			return customerID;
		}

		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}

		public String getAmountOfPurchase() {
			return priceOfPurchase;
		}

		public void setAmountOfPurchase(String amountOfPurchase) {
			this.priceOfPurchase = amountOfPurchase;
		}

	}

	private void loadSaleFullDetails(boolean b) {
		Sale sale;

		if (b)
			currentSaleDataIndex++;
		else
			currentSaleDataIndex--;
		sale = selectedSales.get(currentSaleDataIndex);

		saleDataViewIndex.setText(String.format("Page %d/%d", currentSaleDataIndex + 1, selectedSales.size()));
		// show the data
		SaleIDSaleDAta.setText(Integer.toString(sale.getSaleID()));
		if (sale.getStatus() == SaleStatus.activated)
			statusSaleDAta.setText(SaleStatus.activated.toString());
		else
			statusSaleDAta.setText(SaleStatus.not_Activated.toString());
		CompanyNameSaleDAta.setText(sale.getCompanyName());
		fuelTypeSaleDAta.setText(sale.getFuelType());
		purchaseModuleSaleDAta.setText(sale.getPurchaseModule());
		salePercentSaleDAta.setText(Float.toString(sale.getSalePercent()));
		startTimeSaleDAta.setText(sale.getStartTime());
		endTimeSaleDAta.setText(sale.getEndTime());
		startDateSaleDAta.setText(sale.getStartDate());
		endDateSaleDAta.setText(sale.getEndDate());
		saleDaysSaleDAta.setText(sale.getSaleDays());
	}

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MarketingManager.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// loading the main window data
		System.out.println("hello");
		markitingManager = (Employee) ClientUI.user;
		// if agreed we can use a file to load and save the nofitications

		helloUserTxt.setText("hello " + markitingManager.getFirstName() + " " + markitingManager.getLastName());

		currentPane = markitingManagerNofPane;

		// show the main pane and hide the others
		markitingManagerNofPane.setVisible(true);
		fuelRatesPane.setVisible(false);
		saleDataPane.setVisible(false);
		salePane.setVisible(false);
		reportsPane.setVisible(false);
		PeriodicReportPane.setVisible(false);
		saleResponseReportPane.setVisible(false);
		updateRates.setVisible(false);
		// add on the nofitication

		//loading Reports data
		buildsaleResponseReportTable();
		
		// loading SalePane data
		
		//Build sale Table 
		buildSaleTable();
		// initialize saleTypes comboBox
		ObservableList<SaleStatus> saleTypes = FXCollections.observableArrayList(SaleStatus.values());
		salesTypeCombo.setItems(saleTypes);
		
		// loading RatesPane data
		
		//build company Rate Table
		buildCompanyRateTable();
		// call server and get fuel types from company
		ObservableList<CustomerRateTypes> fuelTypes = FXCollections
				.observableArrayList(CustomerRateTypes.values());
		rateType.setItems(fuelTypes);

		// initialize rateTypeCombo comboBox
		ObservableList<RatesStatus> rateType = FXCollections.observableArrayList(RatesStatus.values());
		rateTypeCombo.setItems(rateType);

		// initialize reportKindCombo comboBox
		ObservableList<MarkitingManagerReport> MarkitingReportType = FXCollections
				.observableArrayList(MarkitingManagerReport.values());
		reportKindCombo.setItems(MarkitingReportType);

	}

}
