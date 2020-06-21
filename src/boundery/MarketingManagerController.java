/*
 * 
 */
package boundery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent.EventType;

import Entity.AnaliticDataReport;
import Entity.CompanyFuel;
import Entity.Employee;
import Entity.Fuel;
import Entity.GasStationOrder;
import Entity.GenericReport;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import Entity.UserAnaliticRanks;
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
import enums.StationManagerReportsTypes;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import server.FileManagmentSys;

/**
 * The Class MarketingManagerController.
 */
public class MarketingManagerController implements Initializable {
	
	/** The purchase hours. */
	@FXML
	private Text purchase_hours;

	/** The hellomessage. */
	@FXML
	private Text hellomessage;
	
	/** The piechart 2. */
	@FXML
	private Text piechart2;

	/** The piechart 3. */
	@FXML
	private Text piechart3;

	/** The vbox periodic gui table. */
	@FXML
	private VBox vboxPeriodicGuiTable;
	
	/** The Sale ID sale D ata. */
	@FXML
	private Text SaleIDSaleDAta;
	
	/** The status sale D ata. */
	@FXML
	private Text statusSaleDAta;
	
	/** The Company name sale D ata. */
	@FXML
	private Text CompanyNameSaleDAta;
	
	/** The fuel type sale D ata. */
	@FXML
	private Text fuelTypeSaleDAta;
	
	/** The purchase module sale D ata. */
	@FXML
	private Text purchaseModuleSaleDAta;
	
	/** The sale percent sale D ata. */
	@FXML
	private Text salePercentSaleDAta;
	
	/** The start time sale D ata. */
	@FXML
	private Text startTimeSaleDAta;
	
	/** The end time sale D ata. */
	@FXML
	private Text endTimeSaleDAta;
	
	/** The sale data view index. */
	@FXML
	private Text saleDataViewIndex;
	
	/** The enter sale txt. */
	@FXML
	private Text enterSaleTxt;
	
	/** The start date sale D ata. */
	@FXML
	private Text startDateSaleDAta;
	
	/** The end date sale D ata. */
	@FXML
	private Text endDateSaleDAta;
	
	/** The sale days sale D ata. */
	@FXML
	private Text saleDaysSaleDAta;
	
	/** The Sales window btn. */
	@FXML
	private Button SalesWindowBtn;
	
	/** The de activate sale btn. */
	@FXML
	private Button deActivateSaleBtn;
	
	/** The fuel rates window btn. */
	@FXML
	private Button fuelRatesWindowBtn;
	
	/** The report generation window btn. */
	@FXML
	private Button reportGenerationWindowBtn;
	
	/** The hello user txt. */
	@FXML
	private Text helloUserTxt;
	
	/** The sales header. */
	@FXML
	private Text salesHeader;
	
	/** The Notifications text area. */
	@FXML
	private TextArea NotificationsTextArea;
	
	/** The fuel rates pane. */
	@FXML
	private Pane fuelRatesPane;
	
	/** The rate type. */
	@FXML
	private ComboBox<CustomerRateTypes> rateType;
	
	/** The new ratetxt. */
	@FXML
	private TextField newRatetxt;
	
	/** The max fuel pricetxt. */
	@FXML
	private Text maxFuelPricetxt;
	
	/** The rate type combo. */
	@FXML
	private ComboBox<RatesStatus> rateTypeCombo;
	
	/** The company rates table. */
	@FXML
	private TableView<PricingModule> companyRatesTable;
	
	/** The rate check box select. */
	@FXML
	private TableColumn<PricingModule, Boolean> rateCheckBoxSelect;
	
	/** The model name rate. */
	@FXML
	private TableColumn<PricingModule, String> modelNameRate;
	
	/** The rate new rate. */
	@FXML
	private TableColumn<PricingModule, Float> rateNewRate;
	
	/** The rate status. */
	@FXML
	private TableColumn<PricingModule, RatesStatus> rateStatus;
	
	/** The update rates. */
	@FXML
	private Button updateRates;
	
	/** The sale pane. */
	@FXML
	private Pane salePane;
	
	/** The sales details table. */
	@FXML
	private TableView<Sale> salesDetailsTable;
	
	/** The Select sale. */
	@FXML
	private TableColumn<Sale, Boolean> SelectSale;
	
	/** The sale number. */
	@FXML
	private TableColumn<Sale, String> saleNumber;
	
	/** The Sale fuel type. */
	@FXML
	private TableColumn<Sale, String> SaleFuelType;
	
	/** The Sale start date. */
	@FXML
	private TableColumn<Sale, String> SaleStartDate;
	
	/** The sale end date. */
	@FXML
	private TableColumn<Sale, String> saleEndDate;
	
	/** The sale percent. */
	@FXML
	private TableColumn<Sale, Float> salePercent;
	
	/** The view more sale details btn. */
	@FXML
	private Button viewMoreSaleDetailsBtn;
	
	/** The activate sale btn. */
	@FXML
	private Button activateSaleBtn;
	
	/** The log out btn. */
	@FXML
	private Button logOutBtn;
	
	/** The main pane. */
	@FXML
	private Button mainPane;
	
	/** The sales type combo. */
	@FXML
	private ComboBox<SaleStatus> salesTypeCombo;
	
	/** The reports pane. */
	@FXML
	private Pane reportsPane;
	
	/** The report kind combo. */
	@FXML
	private ComboBox<MarkitingManagerReport> reportKindCombo;
	
	/** The reportsale number. */
	@FXML
	private TextField reportsaleNumber;
	
	/** The generate report btn. */
	@FXML
	private Button generateReportBtn;
	
	/** The Periodic report pane. */
	@FXML
	private Pane PeriodicReportPane;
	
	/** The sale response report pane. */
	@FXML
	private Pane saleResponseReportPane;
	
	/** The sale data pane. */
	@FXML
	private Pane saleDataPane;
	
	/** The sale response report table. */
	// response report table
	@FXML
	private TableView<ResponseReportData> saleResponseReportTable;
	
	/** The customer ID response report. */
	@FXML
	private TableColumn<ResponseReportData, String> customerIDResponseReport;
	
	/** The amount of purchaseresponse report. */
	@FXML
	private TableColumn<ResponseReportData, Float> amountOfPurchaseresponseReport;
	
	/** The totale number of customers response report. */
	@FXML
	private Text totaleNumberOfCustomersResponseReport;
	
	/** The totale purchases response report. */
	@FXML
	private Text totalePurchasesResponseReport;
	
	/** The Prev sale details btn. */
	@FXML
	private Button PrevSaleDetailsBtn;
	
	/** The next sale details btn. */
	@FXML
	private Button nextSaleDetailsBtn;
	
	/** The select date txt. */
	@FXML
	private Text selectDateTxt;
	
	/** The start date. */
	@FXML
	private DatePicker startDate;
	
	/** The end date. */
	@FXML
	private DatePicker endDate;

	/** The analitic databtn. */
	/// added
	@FXML
	private Button analiticDatabtn;

	/** The analitic data pane. */
	@FXML
	private Pane analiticDataPane;

	/** The Analitic data table. */
	// analitic data tabel start------------------
	@FXML
	private TableView<AnaliticDataReport> AnaliticDataTable;

	/** The week col. */
	@FXML
	private TableColumn<AnaliticDataReport, String> weekCol;

	/** The month col. */
	@FXML
	private TableColumn<AnaliticDataReport, String> monthCol;

	/** The year col. */
	@FXML
	private TableColumn<AnaliticDataReport, String> yearCol;

	/** The type analitic col. */
	@FXML
	private TableColumn<AnaliticDataReport, String> typeAnaliticCol;
	// analitic data tabel end------------------

	/** The get yeartxt. */
	@FXML
	private TextField getYeartxt;

	/** The month combo box. */
	@FXML
	private ComboBox<Month> monthComboBox;

	/** The show analitic databtn. */
	@FXML
	private Button showAnaliticDatabtn;

	// PieChartDiagram start---------------------------

	/** The pie index. */
	private int pie_index = 0;

	/** The pie pane. */
	@FXML
	private Pane piePane;

	/** The pie chart 1. */
	@FXML
	private PieChart pieChart1;

	/** The backbtn. */
	@FXML
	private Button backbtn;

	/** The nextbtn. */
	@FXML
	private Button nextbtn;

	/** The prevbtn. */
	@FXML
	private Button prevbtn;

	/** The bar chart. */
	@FXML
	private BarChart barChart;

	/** The number axis. */
	@FXML
	private NumberAxis numberAxis;

	/** The category axis. */
	@FXML
	private CategoryAxis categoryAxis;

	/** The pie chart 3. */
	@FXML
	private PieChart pieChart3;

	// PieChartDiagram end---------------------------

	// ranks table start---------------------------------------------------

	/** The analitic rank pane. */
	@FXML
	private Pane analiticRankPane;

	/** The analitic ranks table. */
	@FXML
	private TableView<UserAnaliticRanks> analiticRanksTable;

	/** The user I dcol. */
	@FXML
	private TableColumn<UserAnaliticRanks, String> userIDcol;

	/** The customer type analeticcol. */
	@FXML
	private TableColumn<UserAnaliticRanks, Integer> customerTypeAnaleticcol;

	/** The fueling hour analeticcol. */
	@FXML
	private TableColumn<UserAnaliticRanks, Integer> fuelingHourAnaleticcol;

	/** The fuel type analeticcol. */
	@FXML
	private TableColumn<UserAnaliticRanks, Integer> fuelTypeAnaleticcol;

	// ranks table end-------------------------------------------------------

	/**
	 * handle switching Sale panes next and prev .
	 *
	 * @param event the event
	 */
	@FXML
	void nextOrPrevWasClicked(ActionEvent event) {

		Button btn = (Button) event.getSource();

		if (btn.equals(nextbtn)) {
			pie_index++;
			// purchase_hours
			switchCharts(pie_index);
			prevbtn.setDisable(false);
			if (pie_index == 2) {
				nextbtn.setDisable(true);
			}
		}

		else if (btn.equals(prevbtn)) {
			pie_index--;
			switchCharts(pie_index);
			nextbtn.setDisable(false);
			if (pie_index == 0) {
				prevbtn.setDisable(true);
			}
		}
		switch (pie_index) {
		case 0:
			purchase_hours.setVisible(true);
			piechart2.setVisible(false);
			piechart3.setVisible(false);
			break;
		case 1:
			purchase_hours.setVisible(false);
			piechart2.setVisible(true);
			piechart3.setVisible(false);
			break;
		case 2:
			purchase_hours.setVisible(false);
			piechart2.setVisible(false);
			piechart3.setVisible(true);
			break;
		}

	}

	/**
	 * handle switching Chart panes next and prev .
	 * this function changes between the analytic charts and diagrams and display them
	 * @param index the index
	 */
	public void switchCharts(int index) {
		switch (index) {
		case 0:
			pieChart1.setVisible(true);
			barChart.setVisible(false);
			break;
		case 1:
			pieChart1.setVisible(false);
			barChart.setVisible(true);
			pieChart3.setVisible(false);
			break;
		case 2:
			pieChart3.setVisible(true);
			barChart.setVisible(false);
			break;
		}
	}

	/**
	 * Checks input year and month if valid.
	 * this function checks input of the month and year if they are numbers or correct format
	 * @param month the month
	 * @param year the year
	 * @return true, if successful
	 */
	public boolean checkInput(String month, String year) {
		int val, val2 = 0;

		if (year.isEmpty()) {
			JOptionPane.showMessageDialog(null, "you must enter year");
			return false;
		}

		switch (month.toLowerCase()) {

		case "january":
			val2 = 1;
			break;

		case "february":
			val2 = 2;
			break;

		case "march":
			val2 = 3;
			break;

		case "april":
			val2 = 4;
			break;

		case "may":
			val2 = 5;
			break;

		case "june":
			val2 = 6;
			break;

		case "july":
			val2 = 7;
			break;

		case "august":
			val2 = 8;
			break;

		case "september":
			val2 = 9;
			break;

		case "october":
			val2 = 10;
			break;

		case "november":
			val2 = 11;
			break;

		case "December":
			val2 = 12;
			break;

		}

		try {
			val = Integer.parseInt(getYeartxt.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "input error");
			return false;
		}

		// month value is negative or zero
		if (val <= 0) {
			JOptionPane.showMessageDialog(null, "year value must be greater then zero");
			return false;
		}

		// This year has not yet arrived
		if (LocalDate.now().getYear() < val) {
			JOptionPane.showMessageDialog(null, "This year has not yet arrived , choose another year");
			return false;
		}

		// if the year arrived and month didnt arrived
		if (LocalDate.now().getYear() == val && val2 > LocalDate.now().getMonthValue()) {
			JOptionPane.showMessageDialog(null, "This month has not yet arrived , choose another month");
			return false;
		}

		// all test are passed
		return true;

	}

	/**
	 * show analitic data on click .
	 * this function handles the analytic data buttons
	 * @param event the event
	 */
	@FXML
	void showAnaliticDataClick(ActionEvent event) {
		Button btn = (Button) event.getSource();

		if (btn.equals(showAnaliticDatabtn)) {

			if (monthComboBox.getValue() == null) {
				JOptionPane.showMessageDialog(null, "you must choose month");
				return;
			}

			if (checkInput(monthComboBox.getValue().toString(), getYeartxt.getText()) == true)
				AnaliticDataTable.setItems(
						getAllAnaliticDataByYearAndMonth(monthComboBox.getValue().toString(), getYeartxt.getText()));

			monthComboBox.setValue(null);
			getYeartxt.clear();
		}
	}



	/**
	 * open pane according to report type.
	 * this fucntion displays the chosen reports and displays data
	 * @param event the event
	 */
	@FXML
	void selectReportType(ActionEvent event) {
		if (reportKindCombo.getValue() == MarkitingManagerReport.PeriodicReport) {
			enterSaleTxt.setVisible(false);
			reportsaleNumber.setVisible(false);

			PeriodicReportPane.setVisible(true);
			saleResponseReportPane.setVisible(false);
			// the select data buttons
			selectDateTxt.setVisible(true);
			startDate.setVisible(true);
			endDate.setVisible(true);

		}
		if (reportKindCombo.getValue() == MarkitingManagerReport.saleResponseReport) {
			enterSaleTxt.setVisible(true);
			reportsaleNumber.setVisible(true);

			PeriodicReportPane.setVisible(false);
			saleResponseReportPane.setVisible(true);
			// the select data buttons
			selectDateTxt.setVisible(false);
			startDate.setVisible(false);
			endDate.setVisible(false);

		}
	}

	/**
	 * handel prev sale button.
	 * this function handles the previous sales and displays their details
	 * @param event the event
	 */
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

	/**
	 * handel next sale button.
	 *
	 * @param event the event
	 */
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

	/**
	 * Finish viweing sale details.
	 *
	 * @param event the event
	 */
	@FXML
	void finishViweingSaleDetails(ActionEvent event) {
		switchPanes(salePane);
	}

	/**
	 * Open sale pane.
	 *
	 * @param event the event
	 */
	@FXML
	void OpenSalePane(ActionEvent event) {
		switchPanes(salePane);
		activateSaleBtn.setVisible(false);
		deActivateSaleBtn.setVisible(false);
	}

	/**
	 * update the selected Pricing models status to active in this case if there is
	 * two rates selected to the same Pricing model it wouldn't update the status
	 * and return.
	 *
	 * @param event the event
	 */

	@FXML
	void UpdateSelectedRates(ActionEvent event) {
		// update all the selected rates
		// send to server selectedRates
		boolean flag = false;
		Set<Integer> sets = new HashSet<Integer>();
		for (PricingModule rate : selectedRates) {
			rate.setStatus(RatesStatus.active);
			if (sets.contains(rate.getModelNumber())) {
				flag = true;
				break;
			}
			sets.add(rate.getModelNumber());
		}
		if (flag) {
			for (PricingModule rate : selectedRates) {
				rate.setStatus(RatesStatus.confirmed);
			}
			JOptionPane.showMessageDialog(null, "can't choose the same fuel type twice");
			return;
		}

		if (EmployeeCC.updatePricingModel(selectedRates))
			JOptionPane.showMessageDialog(null, "upate succeded");
		else
			JOptionPane.showMessageDialog(null, "update un-succeded one or more of the data didn't update");
		// refresh the table
		chooserateType(null);
	}

	/**
	 * activate all selected sales.
	 *
	 * @param event the event
	 */
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
				JOptionPane.showMessageDialog(null, "upadting succeded");
			else
				JOptionPane.showMessageDialog(null, "upadting un-succeded one or more of the data didn't upadte");

			// update the table
			chooseSaleType(null);
		}
	}

	/**
	 * select sale status to show in the table.
	 * this function choose sale type and show available and not available sales
	 * @param event the event
	 */
	@FXML
	void chooseSaleType(ActionEvent event) {
		// GUI-structure
		if (salesTypeCombo.getValue() == SaleStatus.activated) {
			deActivateSaleBtn.setVisible(true);
			deActivateSaleBtn.setLayoutX(146);
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
				new Sale(0, saleType, markitingManager.getCompanyName(), null, 0, null, null, null, null, null)));

		// Fill the table

		salesDetailsTable.getItems().setAll(data);

		// empety the selected row
		selectedSales.clear();
	}

	/**
	 * build Sale Table.
	 * this function builds the sales table and displays on the gui
	 */
	private void buildSaleTable() {
		GUIBuiltParts.buildCheckBOXForTable(SelectSale, selectedSales);

		saleNumber.setCellValueFactory(new PropertyValueFactory<Sale, String>("saleID"));
		SaleFuelType.setCellValueFactory(new PropertyValueFactory<Sale, String>("fuelType"));
		SaleStartDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("startDate"));
		saleEndDate.setCellValueFactory(new PropertyValueFactory<Sale, String>("endDate"));
		salePercent.setCellValueFactory(new PropertyValueFactory<Sale, Float>("salePercent"));
	}

	/**
	 * create new rate.
	 * this function creates new rates for the marketing manager and displays them to him
	 * @param event the event
	 */
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
			if (!MarketingEmployeeController.checkIfStringContainsOnlyNumbersInFloatType(sFuelNewRate)) {
				JOptionPane.showMessageDialog(null, "Please enter numbers 0.XX");
				return;
			} else {
				if (EmployeeCC.craeteNewPricingModel(new PricingModule(rateType.getValue().ordinal(), sFuelNewRate,
						markitingManager.getCompanyName(), RatesStatus.created)))
					JOptionPane.showMessageDialog(null, "Creating New Rate done succesfully");
				else
					JOptionPane.showMessageDialog(null, "Creating New Rate un-succesfull");
			}
		}
	}

	/**
	 * generate report handler
	 * check the kind of the report and create one accordingly .
	 * @param event the event
	 */
	@FXML
	void generateReport(ActionEvent event) {
		// get data from gui
		String saleNumber = reportsaleNumber.getText();

		if (reportKindCombo.getSelectionModel() != null) {
			ObservableList<ResponseReportData> data = FXCollections.observableArrayList();
			Object obj;
			// call the server and get the data

			if (reportKindCombo.getValue() == MarkitingManagerReport.PeriodicReport) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				if (startDate.getValue() == null || endDate.getValue() == null) {
					JOptionPane.showMessageDialog(null, "please select dates");
					return;
				}
				String start = formatter.format(startDate.getValue());
				String end = formatter.format(endDate.getValue());

				// start<end
				if (start.compareTo(end) >= 0) {
					JOptionPane.showMessageDialog(null, "Please select end date bigger than start date");
					return;
				}

				obj = EmployeeCC.createPeriodicResport(markitingManager.getCompanyName(), start, end);
				if (obj instanceof Commands) {
					JOptionPane.showMessageDialog(null, "there is no such sale with this ID");
					return;
				}
				ArrayList<String> rows = FileManagmentSys.readMarkitingManagerReport((File) obj,
						FileManagmentSys.periodicReport);
				String[] lines = rows.get(0).split("\\n");
				buildPeriodicReportTable(data, lines);
			} else if (reportKindCombo.getValue() == MarkitingManagerReport.saleResponseReport) {
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

				ArrayList<String> resArray = FileManagmentSys.readMarkitingManagerReport((File) obj,
						FileManagmentSys.responseReport);
				filldataObjectFromFile(resArray, data);
				// fill the table
				saleResponseReportTable.getItems().setAll(data);
				totaleNumberOfCustomersResponseReport.setText(resArray.get(0).replaceAll("[^0-9?!\\.]", ""));
				totalePurchasesResponseReport.setText(resArray.get(1).replaceAll("[^0-9?!\\.]", ""));
				//
			}
		} else
			JOptionPane.showMessageDialog(null, "please Select report kind");

	}

	/**
	 * build Periodic Report Table according to the formate of the report.
	 * @param data the data
	 * @param strData the str data
	 */
	private void buildPeriodicReportTable(ObservableList<ResponseReportData> data, String[] strData) {
		TableView<ObservableList<String>> tableView = new TableView<>();

		ArrayList<String> columnNames = convertRowToArrayListPeriodicReport(strData[0]);
		int i;
		// add colomns
		for (i = 0; i < columnNames.size(); i++) {
			final int finalIdx = i;
			TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnNames.get(i));
			column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
			tableView.getColumns().add(column);
		}
		// add data
		for (i = 1; i < strData.length; i++) {
			ArrayList<String> words = convertRowToArrayListPeriodicReport(strData[i]);
			;

			tableView.getItems().add(FXCollections.observableArrayList(words));
		}
		tableView.setLayoutX(10);
		tableView.setLayoutY(10);
		//
		tableView.setMinWidth(Math.min(600, columnNames.size() * 120));
		tableView.setMaxHeight(300);

		tableView.setEditable(false);
		PeriodicReportPane.getChildren().add(tableView);
	}

	/**
	 * read the string and conert it to arrayList for the Periodic Report formate.
	 * @param str the str
	 * @return the array list
	 */
	private static ArrayList<String> convertRowToArrayListPeriodicReport(String str) {
		int i = 0;
		ArrayList<String> columnData = new ArrayList<String>();
		while (i < str.length()) {
			if (i == 0)
				columnData.add(str.substring(i, (i = i + 12)).replaceAll("\\s+", ""));
			else
				columnData.add(str.substring(i, (i = i + 15)).replaceAll("\\s+", ""));
		}
		return columnData;
	}

	/**
	 * build sale Response Report Table.
	 */
	private void buildsaleResponseReportTable() {
		customerIDResponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, String>("customerID"));
		amountOfPurchaseresponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, Float>("amountOfPurchase"));
	}

	/**
	 * fill data Object From File resArray .
	 * @param resArray the res array
	 * @param data the data
	 */
	private void filldataObjectFromFile(ArrayList<String> resArray, ObservableList<ResponseReportData> data) {
		// sprerate to lines
		String[] lines = resArray.get(2).split("\\n");
		// fill the data object
		for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
			data.add(new ResponseReportData(lines[lineIndex].substring(0, 12).replaceAll("\\s+", ""),
					lines[lineIndex].substring(12, lines[lineIndex].length()).replaceAll("\\s+", "")));
		}
	}

	/**
	 * Open fuel rates pane.
	 * displays the fuel rates pane
	 * @param event the event
	 */
	@FXML
	void openFuelRatesPane(ActionEvent event) {
		switchPanes(fuelRatesPane);
	}

	/**
	 * Open report generation pane.
	 * displays the reports pane
	 * @param event the event
	 */
	@FXML
	void openReportGenerationPane(ActionEvent event) {
		switchPanes(reportsPane);
	}

	/**
	 * Open analytic data pane.
	 * displays the analytic pane
	 * @param event the event
	 */
	@FXML
	void openAnaliticDataPane(ActionEvent event) {
		switchPanes(analiticDataPane);
	}

	/**
	 * handle view more sale details
	 * @param event the event
	 */
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

	/**
	 * log out.
	 * this function performs log out from the system
	 * @param event the event
	 */
	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(markitingManager.getId(), markitingManager.getClass().toString());

		// return to login
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
	}

	/**
	 * this function displays the pricing module and updates it according to current rate
	 * @param event the event
	 */
	@FXML
	void chooseFuelTypeForNewRate(ActionEvent event) {
		CustomerRateTypes rateTypeSelected = rateType.getValue();

		PricingModule pricingModule = EmployeeCC.getCompanyActiveRateAccordingPriceModel(
				new PricingModule(rateTypeSelected.ordinal(), "", markitingManager.getCompanyName(), null));
		if (pricingModule != null)
			maxFuelPricetxt.setText(String.format("the current rate is : %s", pricingModule.getSalePercent()));
		else
			JOptionPane.showMessageDialog(null, "system bug there isn't data for this report");
	}

	/**
	 * choose rate type from check box
	 * @param event the event
	 */
	@FXML
	void chooserateType(ActionEvent event) {
		RatesStatus selected = rateTypeCombo.getValue();
		if (selected == RatesStatus.confirmed)
			updateRates.setVisible(true);
		else
			updateRates.setVisible(false);

		// get company pricing model rates
		ObservableList<PricingModule> data = FXCollections.observableArrayList(EmployeeCC
				.getAllCompanyRatesByStatus(new PricingModule(0, "", markitingManager.getCompanyName(), selected)));
		// set the table according to comboBox
		if (selected.equals(RatesStatus.confirmed)) {
			rateCheckBoxSelect.setVisible(true);
			companyRatesTable.setPrefWidth(631);
		} else {
			rateCheckBoxSelect.setVisible(false);
			companyRatesTable.setPrefWidth(631 - rateCheckBoxSelect.getPrefWidth());
		}
		// fill table data
		companyRatesTable.getItems().setAll(data);

		selectedRates.clear();

	}

	/**
	 * build Company Rate Table.
	 */
	private void buildCompanyRateTable() {
		// create comboBOX
		GUIBuiltParts.buildCheckBOXForTable(rateCheckBoxSelect, selectedRates);

		modelNameRate.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("modelname"));

		rateNewRate.setCellValueFactory(new PropertyValueFactory<PricingModule, Float>("salePercent"));

		rateStatus.setCellValueFactory(new PropertyValueFactory<PricingModule, RatesStatus>("status"));
	}

	/** The current pane. */
	private Pane currentPane;
	
	/** The markiting manager. */
	public static Employee markitingManager;
	
	/** The selected rates. */
	private ArrayList<PricingModule> selectedRates = new ArrayList<PricingModule>();
	
	/** The selected sales. */
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();
	
	/** The current sale data index. */
	private int currentSaleDataIndex;

	/**
	 * this class is just to show the table of the report.
	 *
	 * @author iamme
	 */
	protected class ResponseReportData {
		
		/** The customer ID. */
		String customerID;
		
		/** The price of purchase. */
		String priceOfPurchase;

		/**
		 * Instantiates a new response report data.
		 *
		 * @param customerID the customer ID
		 * @param priceOfPurchase the price of purchase
		 */
		public ResponseReportData(String customerID, String priceOfPurchase) {
			this.customerID = customerID;
			this.priceOfPurchase = priceOfPurchase;
		}

		/**
		 * Gets the customer ID.
		 *
		 * @return the customer ID
		 */
		public String getCustomerID() {
			return customerID;
		}

		/**
		 * Sets the customer ID.
		 *
		 * @param customerID the new customer ID
		 */
		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}

		/**
		 * Gets the amount of purchase.
		 *
		 * @return the amount of purchase
		 */
		public String getAmountOfPurchase() {
			return priceOfPurchase;
		}

		/**
		 * Sets the amount of purchase.
		 *
		 * @param amountOfPurchase the new amount of purchase
		 */
		public void setAmountOfPurchase(String amountOfPurchase) {
			this.priceOfPurchase = amountOfPurchase;
		}

	}

	/**
	 * load the selected sales details .
	 *
	 * @param b to move forward or backward
	 */
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
		salePercentSaleDAta.setText(Float.toString(sale.getSalePercent()));
		startTimeSaleDAta.setText(sale.getStartTime());
		endTimeSaleDAta.setText(sale.getEndTime());
		startDateSaleDAta.setText(sale.getStartDate());
		endDateSaleDAta.setText(sale.getEndDate());
		saleDaysSaleDAta.setText(sale.getSaleDays());
	}

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
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

	/**
	 * this function Switches between available panes.
	 * @param newPane the new pane
	 */
	private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	/**
	 * initialize Analytic Data table.
	 */
	public void initializeAnaliticData() {

		ObservableList<Month> monthsValues = FXCollections.observableArrayList(Month.values());
		monthComboBox.setItems(monthsValues);

		// clicked row-------------------------
		AnaliticDataTable.setRowFactory(tv -> {
			TableRow<AnaliticDataReport> row = new TableRow<>();
			row.setOnMouseClicked(ev -> {
				if (!row.isEmpty() && ev.getButton() == MouseButton.PRIMARY && ev.getClickCount() == 2) {
					AnaliticDataReport clickedRow = row.getItem();
					if (clickedRow.getType().compareTo(FileManagmentSys.statisticData) == 0) {
						switchPanes(piePane);

						pie_index = 0;
						pieChart1.setVisible(true);
						pieChart3.setVisible(false);
						barChart.setVisible(false);

						nextbtn.setDisable(false);
						prevbtn.setDisable(true);

						analiticDataByYearAndMonth(clickedRow.getFileName(), FileManagmentSys.statisticData);
					}

					else {
						switchPanes(analiticRankPane);
						analiticDataByYearAndMonth(clickedRow.getFileName(), FileManagmentSys.customerAnaliticData);
					}
				}
			});
			return row;
		});

		weekCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("week"));
		monthCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("month"));
		yearCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("year"));
		typeAnaliticCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("type"));

		// AnaliticDataTable.setItems(value);
	}

	
	/**
	 * get All Analitic Data By Year And Month.
	 *
	 * @param month the month
	 * @param year the year
	 * @return the all analitic data by year and month
	 */
	public ObservableList<AnaliticDataReport> getAllAnaliticDataByYearAndMonth(String month, String year) {

		ArrayList<AnaliticDataReport> data = EmployeeCC.getAllAnaliticDataByYearAndMonth(month, year,
				markitingManager.getCompanyName());
		if (data.isEmpty())
			JOptionPane.showMessageDialog(null, "there is no data in this year");
		ObservableList<AnaliticDataReport> analiticData = FXCollections.observableArrayList(data);
		return analiticData;
	}

	
	
	/**
	 * analitic Data By Year And Month.
	 *
	 * @param fileName the file name
	 * @param type the type
	 */

	public void analiticDataByYearAndMonth(String fileName, String type) {

		File fily = EmployeeCC.getAllAnaliticFileByYearAndMonth(fileName, markitingManager.getCompanyName(), type);

		if (fily == null)
			JOptionPane.showMessageDialog(null, "there is no data in this period");
		else {
			String array[];
			FileReader fr;
			BufferedReader br;
			StringBuilder firstData, secondData, thirdData;
			String str;
			int lineCounter = 0;

			firstData = new StringBuilder();
			secondData = new StringBuilder();
			thirdData = new StringBuilder();
			//
			if (type.compareTo(FileManagmentSys.statisticData) == 0) {
				try {
					fr = new FileReader(fily);
					br = new BufferedReader(fr); // creates a buffering character input stream

					while ((str = br.readLine()) != null) {
						if (lineCounter >= 3 && lineCounter <= 10)
							firstData.append(str + "\n");
						if (lineCounter >= 14 && lineCounter <= 18)
							secondData.append(str + "\n");
						if (lineCounter >= 21)
							thirdData.append(str + "\n");

						lineCounter++;
					}
					pieChart1.getData().clear();
					pieChart3.getData().clear();
					barChart.getData().clear();

					pieChart1.setData(getpieChartDataForHours(firstData.toString()));
					pieChart3.setData(getpieChartDataForFuelType(thirdData.toString()));
					barChart.getData().addAll(getpieChartDataForCarNumAndTotalPurchase(secondData.toString()));

					//
				} catch (IOException e) {
					e.printStackTrace();
				}
				//
			}
			//
			else {
				try {
					fr = new FileReader(fily);
					br = new BufferedReader(fr); // creates a buffering character input stream
					ArrayList<UserAnaliticRanks> userDtails = new ArrayList<UserAnaliticRanks>();

					while ((str = br.readLine()) != null) {
						if (lineCounter >= 2) {
							userDtails.add(new UserAnaliticRanks(str.substring(0, 15).replaceAll(" ", ""),
									Integer.parseInt(str.substring(15, 30).replaceAll(" ", "")),
									Integer.parseInt(str.substring(30, 45).replaceAll(" ", "")),
									Integer.parseInt(str.substring(45, 60).replaceAll(" ", ""))));
						}
						lineCounter++;
					}

					//
					System.out.println(userDtails);
					ObservableList<UserAnaliticRanks> analiticData = FXCollections.observableArrayList(userDtails);
					analiticRanksTable.setItems(analiticData);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//
	}

	/**
	 * bar char initilize and fill .
	 *
	 * @param data the data
	 * @return the pie chart data for car num and total purchase
	 */
	public ArrayList<XYChart.Series<String, Number>> getpieChartDataForCarNumAndTotalPurchase(String data) {
		String array[] = data.split("\n");
		int i;

		ArrayList<XYChart.Series<String, Number>> series1 = new ArrayList<XYChart.Series<String, Number>>();
		//
		for (i = 0; i < array.length; i++) {
			XYChart.Series<String, Number> temp = new XYChart.Series<String, Number>();
			//
			temp.setName(array[i].substring(0, 15).replaceAll(" ", "") + "ILS");

			temp.getData().add(new XYChart.Data<>(array[i].substring(0, 15).replaceAll(" ", ""),
					Integer.parseInt(array[i].substring(15, array[i].length()).replaceAll(" ", ""))));
			series1.add(temp);
		}

		return series1;
	}

	/**
	 * get pie Chart Data For Fuel Type.
	 *
	 * @param data the data
	 * @return the pie chart data for fuel type
	 */
	public ObservableList<PieChart.Data> getpieChartDataForFuelType(String data) {
		String array[] = data.split("\n");
		int countArray[] = new int[array.length], sum = 0, i;

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		for (i = 0; i < array.length; i++) {
			countArray[i] = Integer.parseInt(array[i].substring(15).replaceAll(" ", ""));
			sum += countArray[i];
		}

		//
		for (i = 0; i < array.length; i++) {
			pieChartData.add(new PieChart.Data(String.format("%s(%d%s)", array[i].substring(0, 15).replaceAll(" ", ""),
					(int) (((float) countArray[i] / sum) * 100), "%"), countArray[i]));
		}

		return pieChartData;
	}

	/**
	 * get pie Chart Data For Hours.
	 *
	 * @param data the data
	 * @return the pie chart data for hours
	 */
	public ObservableList<PieChart.Data> getpieChartDataForHours(String data) {
		String array[] = data.split("\n");
		int countArray[] = new int[array.length], sum = 0, i;

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		for (i = 0; i < array.length; i++) {
			countArray[i] = Integer.parseInt(array[i].substring(11).replaceAll(" ", ""));
			sum += countArray[i];
		}

		//
		for (i = 0; i < array.length; i++) {
			pieChartData.add(new PieChart.Data(String.format("%s(%d%s)", array[i].substring(0, 11),
					(int) (((float) countArray[i] / sum) * 100), "%"), countArray[i]));
		}

		return pieChartData;
	}

	/**
	 * initialize Ranks Table.
	 */
	public void initializeRanksTable() {

		userIDcol.setCellValueFactory(new PropertyValueFactory<UserAnaliticRanks, String>("userId"));
		customerTypeAnaleticcol
				.setCellValueFactory(new PropertyValueFactory<UserAnaliticRanks, Integer>("customerTypeAnaleticRank"));
		fuelingHourAnaleticcol
				.setCellValueFactory(new PropertyValueFactory<UserAnaliticRanks, Integer>("fuelingHourAnaleticRank"));
		fuelTypeAnaleticcol
				.setCellValueFactory(new PropertyValueFactory<UserAnaliticRanks, Integer>("fuelTypeAnaleticRank"));
	}

	/**
	 * initilize all the data and the structuers of the tabels.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// loading the main window data
		System.out.println("hello");
		// if agreed we can use a file to load and save the nofitications

		// helloUserTxt.setText("hello " + markitingManager.getFirstName() + " " +
		// markitingManager.getLastName());

		currentPane = fuelRatesPane;
		hellomessage.setText("Hello "+ markitingManager.getFirstName());
		// show the main pane and hide the others

		fuelRatesPane.setVisible(true);
		saleDataPane.setVisible(false);
		salePane.setVisible(false);
		reportsPane.setVisible(false);
		PeriodicReportPane.setVisible(false);
		saleResponseReportPane.setVisible(false);
		updateRates.setVisible(false);
		// add on the nofitication

		// loading Reports data
		buildsaleResponseReportTable();

		// loading SalePane data

		// Build sale Table
		buildSaleTable();
		// initialize saleTypes comboBox
		ObservableList<SaleStatus> saleTypes = FXCollections.observableArrayList(SaleStatus.values());
		salesTypeCombo.setItems(saleTypes);

		// loading RatesPane data

		// build company Rate Table
		buildCompanyRateTable();
		// call server and get fuel types from company
		CustomerRateTypes customerArray[] = new CustomerRateTypes[3];
		CustomerRateTypes customerArray1[] = CustomerRateTypes.values();
		for (int i = 0; i < 3; i++) {
			customerArray[i] = customerArray1[i + 1];
		}

		ObservableList<CustomerRateTypes> fuelTypes = FXCollections.observableArrayList(customerArray);
		rateType.setItems(fuelTypes);

		// build Analitic data Table
		initializeAnaliticData();

		// build Analitic Ranks table
		initializeRanksTable();

		// initialize rateTypeCombo comboBox
		ObservableList<RatesStatus> rateType = FXCollections.observableArrayList(RatesStatus.values());
		rateTypeCombo.setItems(rateType);

		// initialize reportKindCombo comboBox
		ObservableList<MarkitingManagerReport> MarkitingReportType = FXCollections
				.observableArrayList(MarkitingManagerReport.values());
		reportKindCombo.setItems(MarkitingReportType);

		pieChart1.setVisible(true);
		purchase_hours.setVisible(true);

	}

}