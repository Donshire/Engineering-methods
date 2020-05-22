package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent.EventType;

import Entity.Employee;
import Entity.Fuel;
import Entity.Rates;
import Entity.Sale;
import client.ClientUI;
import enums.MarkitingManagerReport;
import enums.RatesStatus;
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
import javafx.scene.control.ComboBox;
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
	private Text startDateSaleDAta;
	@FXML
	private Text endDateSaleDAta;
	@FXML
	private Text saleDaysSaleDAta;
	@FXML
	private Button SalesWindowBtn;
	@FXML
	private Button fuelRatesWindowBtn;
	@FXML
	private Button reportGenerationWindowBtn;
	@FXML
	private Pane markitingManagerNofPane;
	@FXML
	private Text helloUserTxt;
	@FXML
	private TextArea NotificationsTextArea;
	@FXML
	private Pane fuelRatesPane;
	@FXML
	private ComboBox<String> fuelTypesRateCombo;
	@FXML
	private TextField newRatetxt;
	@FXML
	private Text maxFuelPricetxt;
	@FXML
	private ComboBox<RatesStatus> rateTypeCombo;
	@FXML
	private TableView<Rates> fuelRatesTable;
	@FXML
	private TableColumn<Rates, Boolean> rateCheckBoxSelect;
	@FXML
	private TableColumn<Rates, String> rateFuelType;
	@FXML
	private TableColumn<Rates, String> rateIDrate;
	@FXML
	private TableColumn<Rates, Float> rateNewRate;
	@FXML
	private TableColumn<Rates, RatesStatus> rateStatus;
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
	@FXML
	private TableView<responseReportData> saleResponseReportTable;
	@FXML
	private TableColumn<responseReportData, String> customerIDResponseReport;
	@FXML
	private TableColumn<responseReportData, Float> amountOfPurchaseresponseReport;
	@FXML
	private Text totaleNumberOfCustomersResponseReport;
	@FXML
	private Text totalePurchasesResponseReport;
	@FXML
	private Button PrevSaleDetailsBtn;
	@FXML
	private Button nextSaleDetailsBtn;

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
	void OpenSalePane(ActionEvent event) {
		switchPanes(salePane);
	}

	@FXML
	void UpdateSelectedRates(ActionEvent event) {
		// update all the selected rates
		// sond to server selectedRates
		for (Rates rate : selectedRates) {
			rate.setStatus(RatesStatus.active.toString());
		}

	}

	@FXML
	void activateSale(ActionEvent event) {
		if (selectedSales.size() <= 0)
			JOptionPane.showMessageDialog(null, "please select at least one sale");
		else {
			for (Sale sale : selectedSales) {
				sale.setStatus(true);
			}

			// call the serever and update the data
		}
	}

	@FXML
	void chooseSaleType(ActionEvent event) {
		// get the data from gui
		SaleStatus guiData = salesTypeCombo.getValue();
		// call the server to get the sales data

		// to get company name and all that stuf there is object that contains employee
		System.out.println("load sales data");

		ObservableList<Sale> data = FXCollections.observableArrayList(
				new Sale("1", false, "Paz", "95", "non", 10.2f, "10:30", "12:00", "1/2/2020", "10/10/2020", "Sunday"),
				new Sale("1", false, "Sonol", "95", "non", 10.2f, "10:30", "12:00", "1/2/2020", "10/10/2020", "Sunday"),
				new Sale("1", false, "yellow", "95", "non", 10.2f, "10:30", "12:00", "1/2/2020", "10/10/2020",
						"Sunday"));

		// build table structure

		// Check Box
		SelectSale.setCellValueFactory(new Callback<CellDataFeatures<Sale, Boolean>, ObservableValue<Boolean>>() {
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

		SelectSale.setCellFactory(new Callback<TableColumn<Sale, Boolean>, //
				TableCell<Sale, Boolean>>() {
			@Override
			public TableCell<Sale, Boolean> call(TableColumn<Sale, Boolean> p) {
				CheckBoxTableCell<Sale, Boolean> cell = new CheckBoxTableCell<Sale, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});

		// End of check box

		saleNumber.setCellValueFactory(new PropertyValueFactory<Sale, String>("saleID"));
		SaleFuelType.setCellValueFactory(new PropertyValueFactory<Sale, String>("fuelType"));
		SaleStartDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("startDate"));
		saleEndDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("endDate"));
		salePercent.setCellValueFactory(new PropertyValueFactory<Sale, Float>("salePercent"));

		// Fill the table
		salesDetailsTable.getItems().setAll(data);
	}

	@FXML
	void createNewRate(ActionEvent event) {
		// get the data entered
		String fuelTypeName = (String) fuelTypesRateCombo.getValue();
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
				// send to server
			}
		}
	}

	@FXML
	void generateReport(ActionEvent event) {
		// get data from gui
		String reportKind = reportKindCombo.getValue().toString();
		String saleNumber = reportsaleNumber.getText();

		if (saleNumber.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please enter sale number");
		else if (reportKind.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please Select report kind");
		else {
			ObservableList<responseReportData> data = FXCollections.observableArrayList();
			// call the server and get the data

			if (reportKindCombo.getValue() == MarkitingManagerReport.PeriodicReport) {
				PeriodicReportPane.setVisible(true);
				
				//use this to fill
				
			}
			else if (reportKindCombo.getValue() == MarkitingManagerReport.saleResponseReport) {
				saleResponseReportPane.setVisible(true);
				
				
				saleResponseReportTable.getItems().setAll(data);
				//
			}
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
	void selectedSaleadd(ActionEvent event) {

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
		System.out.println("log Out");
	}

	@FXML
	void chooseFuelTypeForNewRate(ActionEvent event) {
		String fuelType = fuelTypesRateCombo.getValue();
		// get the max price and the current rate for the fuel

		maxFuelPricetxt.setText(
				String.format("for Fuel %s : The Max Price is %.2f and the " + "current rate is %.2f", "95", 10f, 5f));
	}

	@FXML
	void chooserateType(ActionEvent event) {
		RatesStatus selected = rateTypeCombo.getValue();
		if (selected == RatesStatus.confirmed)
			updateRates.setVisible(true);
		else
			updateRates.setVisible(false);
		// get the data

		ObservableList<Rates> data = FXCollections.observableArrayList(
				new Rates(1, 1.2f, new Fuel("95", 10f), RatesStatus.active.toString(), "20/2/2020", "Paz"));
		// perpare the table
		/*
		 * rateCheckBoxSelect.setCellValueFactory( new
		 * PropertyValueFactory<Rates,Boolean>("check") );
		 * rateCheckBoxSelect.setCellFactory( CheckBoxTableCell.forTableColumn(
		 * rateCheckBoxSelect ) );
		 */

		// CheckBox

		rateCheckBoxSelect
				.setCellValueFactory(new Callback<CellDataFeatures<Rates, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Rates, Boolean> param) {
						Rates rate = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(rate.getCheck());

						booleanProp.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								if (newValue == true)
									selectedRates.add(rate);
								else
									selectedRates.remove(rate);
								rate.setCheck(newValue);
							}
						});
						return booleanProp;
					}
				});

		rateCheckBoxSelect.setCellFactory(new Callback<TableColumn<Rates, Boolean>, //
				TableCell<Rates, Boolean>>() {
			@Override
			public TableCell<Rates, Boolean> call(TableColumn<Rates, Boolean> p) {
				CheckBoxTableCell<Rates, Boolean> cell = new CheckBoxTableCell<Rates, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		// End of checkBox
		rateFuelType.setCellValueFactory(new PropertyValueFactory<Rates, String>("fuelType"));

		rateIDrate.setCellValueFactory(new PropertyValueFactory<Rates, String>("rateId"));

		rateNewRate.setCellValueFactory(new PropertyValueFactory<Rates, Float>("rateValue"));

		rateStatus.setCellValueFactory(new PropertyValueFactory<Rates, RatesStatus>("status"));
		// fill the table
		fuelRatesTable.getItems().setAll(data);

	}

	private Pane currentPane;
	private Employee markitingManager;
	private ArrayList<Rates> selectedRates = new ArrayList<Rates>();
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();
	private int currentSaleDataIndex;

	private class responseReportData{
		String customerID;
		float amountOfPurchase;
		
		public responseReportData(String customerID,float amountOfPurchase) {
			this.customerID=customerID;
			this.amountOfPurchase=amountOfPurchase;
		}
		
		public String getCustomerID() {
			return customerID;
		}
		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}
		public float getAmountOfPurchase() {
			return amountOfPurchase;
		}
		public void setAmountOfPurchase(float amountOfPurchase) {
			this.amountOfPurchase = amountOfPurchase;
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
		SaleIDSaleDAta.setText(sale.getSaleID());
		if (sale.getStatus())
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
		markitingManager = (Employee) ClientUI.user;

		helloUserTxt.setText("hello " + markitingManager.getFirstName() + " " + markitingManager.getLastName());

		currentPane = markitingManagerNofPane;

		// show the maon pane and hide the others
		markitingManagerNofPane.setVisible(true);
		fuelRatesPane.setVisible(false);
		saleDataPane.setVisible(false);
		salePane.setVisible(false);
		reportsPane.setVisible(false);
		PeriodicReportPane.setVisible(false);
		saleResponseReportPane.setVisible(false);

		// add on the nofitication

		// loading SalePane data

		// initialize saleTypes comboBox
		ObservableList<SaleStatus> saleTypes = FXCollections.observableArrayList(SaleStatus.values());
		salesTypeCombo.setItems(saleTypes);

		// loading RatesPane data
		// call server and get fuel types from company
		ObservableList<String> fuelTypes = FXCollections.observableArrayList("95", "solar");
		fuelTypesRateCombo.setItems(fuelTypes);

		// initialize rateTypeCombo comboBox
		ObservableList<RatesStatus> rateType = FXCollections.observableArrayList(RatesStatus.values());
		rateTypeCombo.setItems(rateType);

		// initialize reportKindCombo comboBox
		ObservableList<MarkitingManagerReport> MarkitingReportType = FXCollections
				.observableArrayList(MarkitingManagerReport.values());
		reportKindCombo.setItems(MarkitingReportType);

	}

}
