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
	// response report table
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

	/// added
	@FXML
	private Button analiticDatabtn;

	@FXML
	private Pane analiticDataPane;

	// analitic data tabel start------------------
	@FXML
	private TableView<AnaliticDataReport> AnaliticDataTable;

	@FXML
	private TableColumn<AnaliticDataReport, String> weekCol;

	@FXML
	private TableColumn<AnaliticDataReport, String> monthCol;

	@FXML
	private TableColumn<AnaliticDataReport, String> yearCol;
	
	@FXML
	private TableColumn<AnaliticDataReport, String> typeAnaliticCol;
	// analitic data tabel end------------------

	@FXML
	private TextField getYeartxt;

	@FXML
	private ComboBox<Month> monthComboBox;

	@FXML
	private Button showAnaliticDatabtn;

	// PieChartDiagram start---------------------------

	private int pie_index = 0;

	@FXML
	private Pane piePane;

	@FXML
	private PieChart pieChart1;

	@FXML
	private Button backbtn;

	@FXML
	private Button nextbtn;

	@FXML
	private Button prevbtn;

	@FXML
	private BarChart barChart;
	
	@FXML
	private NumberAxis numberAxis;
	
	@FXML
	private CategoryAxis categoryAxis;

	@FXML
	private PieChart pieChart3;

	// PieChartDiagram end---------------------------

	// ranks table start---------------------------------------------------

	@FXML
	private Pane analiticRankPane;
	
	@FXML
	private TableView<UserAnaliticRanks> analiticRanksTable;

	@FXML
	private TableColumn<UserAnaliticRanks, String> userIDcol;

	@FXML
	private TableColumn<UserAnaliticRanks, Integer> customerTypeAnaleticcol;

	@FXML
	private TableColumn<UserAnaliticRanks, Integer> fuelingHourAnaleticcol;

	@FXML
	private TableColumn<UserAnaliticRanks, Integer> fuelTypeAnaleticcol;

	// ranks table end-------------------------------------------------------

	/**
	 * handle switching Sale panes next and prev 
	 * @param event
	 */
	@FXML
	void nextOrPrevWasClicked(ActionEvent event) {

		Button btn = (Button) event.getSource();

		if (btn.equals(nextbtn)) {
			pie_index++;
			switchCharts(pie_index);
			prevbtn.setDisable(false);
			if (pie_index == 2)
				nextbtn.setDisable(true);
		}

		else if (btn.equals(prevbtn)) {
			pie_index--;
			switchCharts(pie_index);
			nextbtn.setDisable(false);
			if (pie_index == 0)
				prevbtn.setDisable(true);
		}
	}
	
	/**
	 * handle switching Chart panes next and prev 
	 * @param index
	 */
	public void switchCharts(int index) {
		switch(index) {
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
	 * Checks input year and month if valid
	 * @param month
	 * @param year
	 * @return
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
	 * show analitic data on click 
	 * @param event
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
	 * open pane according to report type
	 * @param event
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
	 * handel prev sale button
	 * @param event
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
	 * handel next sale button
	 * @param event
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
	 * update the selected Pricing models status to active in this case if there is
	 * two rates selected to the same Pricing model it wouldn't update the status
	 * and return
	 * 
	 * @param event
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
	 * activate all selected sales
	 * @param event
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
	 * select sale status to show in the table
	 * @param event
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
	 * build Sale Table
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
	 * create new rate
	 * @param event
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
			if(!MarketingEmployeeController.checkIfStringContainsOnlyNumbersInFloatType(sFuelNewRate)) {
				JOptionPane.showMessageDialog(null, "Please enter numbers 0.XX");
				return;
			}
			else {
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
	 * check the kind of the report and create one accordingly 
	 * @param event
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
	 * build Periodic Report Table according to the formate of the report
	 * @param data
	 * @param strData
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
	 * read the string and conert it to arrayList for the Periodic Report formate
	 * @param str
	 * @return
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
	 * build sale Response Report Table
	 */
	private void buildsaleResponseReportTable() {
		customerIDResponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, String>("customerID"));
		amountOfPurchaseresponseReport
				.setCellValueFactory(new PropertyValueFactory<ResponseReportData, Float>("amountOfPurchase"));
	}

	/**
	 * fill data Object From File resArray 
	 * @param resArray
	 * @param data
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

	@FXML
	void openFuelRatesPane(ActionEvent event) {
		switchPanes(fuelRatesPane);
	}

	@FXML
	void openReportGenerationPane(ActionEvent event) {
		switchPanes(reportsPane);
	}

	@FXML
	void openAnaliticDataPane(ActionEvent event) {
		switchPanes(analiticDataPane);
	}

	/**
	 * handle view more sale details
	 * @param event
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
	 * log out
	 * @param event
	 */
	@FXML
	void logOut(ActionEvent event) {
		UserCC.logOut(markitingManager.getId(), markitingManager.getClass().toString());

		// return to login
		MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
	}

	/**
	 * update pricing model rate
	 * @param event
	 */
	@FXML
	void chooseFuelTypeForNewRate(ActionEvent event) {
		CustomerRateTypes rateTypeSelected = rateType.getValue();

		PricingModule pricingModule = EmployeeCC.getCompanyActiveRateAccordingPriceModel(
				new PricingModule(rateTypeSelected.ordinal(), "", markitingManager.getCompanyName(), null));
		if (pricingModule != null)
			maxFuelPricetxt.setText(String.format("the current rate is : %.2f", pricingModule.getSalePercent()));
		else
			JOptionPane.showMessageDialog(null, "system bug there isn't data for this report");
	}

	/**
	 * choose rate Type
	 * @param event
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
			companyRatesTable.setPrefWidth(550);
		} else {
			rateCheckBoxSelect.setVisible(false);
			companyRatesTable.setPrefWidth(550 - rateCheckBoxSelect.getPrefWidth());
		}
		// fill table data
		companyRatesTable.getItems().setAll(data);

		selectedRates.clear();

	}

	/**
	 * build Company Rate Table
	 */
	private void buildCompanyRateTable() {
		// create comboBOX
		GUIBuiltParts.buildCheckBOXForTable(rateCheckBoxSelect, selectedRates);

		modelNameRate.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("modelname"));

		rateNewRate.setCellValueFactory(new PropertyValueFactory<PricingModule, Float>("salePercent"));

		rateStatus.setCellValueFactory(new PropertyValueFactory<PricingModule, RatesStatus>("status"));
	}

	private Pane currentPane;
	public static Employee markitingManager;
	private ArrayList<PricingModule> selectedRates = new ArrayList<PricingModule>();
	private ArrayList<Sale> selectedSales = new ArrayList<Sale>();
	private int currentSaleDataIndex;

	/**
	 * this class is just to show the table of the report
	 * @author iamme
	 *
	 */
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

	/**
	 * load the selected sales details 
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

	/**
	 * initialize Analitic Data table
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
					if(clickedRow.getType().compareTo(FileManagmentSys.statisticData)==0) {
						switchPanes(piePane);
						
						pie_index=0;
						pieChart1.setVisible(true);
						pieChart3.setVisible(false);
						barChart.setVisible(false);
						
						nextbtn.setDisable(false);
						prevbtn.setDisable(true);
						
						analiticDataByYearAndMonth(
								clickedRow.getFileName(),FileManagmentSys.statisticData);
					}
						
					else {
						switchPanes(analiticRankPane);
						analiticDataByYearAndMonth(
								clickedRow.getFileName(),FileManagmentSys.customerAnaliticData);
					}
				}
			});
			return row;
		});

		weekCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("week"));
		monthCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("month"));
		yearCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("year"));
		typeAnaliticCol.setCellValueFactory(new PropertyValueFactory<AnaliticDataReport, String>("type"));
		
		//AnaliticDataTable.setItems(value);
	}
	
	/**
	 * get All Analitic Data By Year And Month
	 * @param month
	 * @param year
	 * @return
	 */
	public ObservableList<AnaliticDataReport> getAllAnaliticDataByYearAndMonth(String month, String year) {

		ArrayList<AnaliticDataReport> data = EmployeeCC.getAllAnaliticDataByYearAndMonth(month, year,markitingManager.getCompanyName());
		if (data.isEmpty())
			JOptionPane.showMessageDialog(null, "there is no data in this year");
		ObservableList<AnaliticDataReport> analiticData = FXCollections.observableArrayList(data);
		return analiticData;
	}
	
	
	/**
	 * analitic Data By Year And Month
	 * @param fileName
	 * @param type
	 */
	public void analiticDataByYearAndMonth(String fileName,String type) {

		File fily = EmployeeCC.getAllAnaliticFileByYearAndMonth(
				fileName,markitingManager.getCompanyName(),type);
		
		if (fily==null)
			JOptionPane.showMessageDialog(null, "there is no data in this period");
		else{
			String array [] ;
			FileReader fr;
			BufferedReader br;
			StringBuilder firstData,secondData,thirdData;
			String str;
			int lineCounter=0;
			
			firstData= new StringBuilder();
			secondData= new StringBuilder();
			thirdData= new StringBuilder();
			//
			if(type.compareTo(FileManagmentSys.statisticData)==0) {
				try {
					fr = new FileReader(fily);
					br = new BufferedReader(fr); // creates a buffering character input stream
					
					while ((str = br.readLine()) != null) {
						if(lineCounter>=3&&lineCounter<=10)firstData.append(str + "\n");
						if(lineCounter>=14&&lineCounter<=18)secondData.append(str + "\n");
						if(lineCounter>=21)thirdData.append(str + "\n");
						
						lineCounter++;
					}
					pieChart1.getData().clear();
					pieChart3.getData().clear();
					barChart.getData().clear();
					
					pieChart1.setData(getpieChartDataForHours(firstData.toString()));
					pieChart3.setData(getpieChartDataForFuelType(thirdData.toString()));
					barChart.getData().addAll(getpieChartDataForCarNumAndTotalPurchase(secondData.toString()));
					
					//
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				//
			}
			//
			else {
				try {
					fr = new FileReader(fily);
					br = new BufferedReader(fr); // creates a buffering character input stream
					ArrayList<UserAnaliticRanks> userDtails= new ArrayList<UserAnaliticRanks>();
					
					while ((str = br.readLine()) != null) {
						if(lineCounter>=2) {
							userDtails.add(new UserAnaliticRanks(
									str.substring(0,15).replaceAll(" ", ""), Integer.parseInt(str.substring(15,30).replaceAll(" ", "")),
									Integer.parseInt(str.substring(30,45).replaceAll(" ", "")), Integer.parseInt(str.substring(45,60).replaceAll(" ", ""))));
						}
						lineCounter++;
					}
					
					//
					System.out.println(userDtails);
					ObservableList<UserAnaliticRanks> analiticData = FXCollections.observableArrayList(userDtails);
					analiticRanksTable.setItems(analiticData);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//
	}

	/**
	 * bar char initilize and fill 
	 * @param data
	 * @return
	 */
	public ArrayList<XYChart.Series<String, Number>> getpieChartDataForCarNumAndTotalPurchase(String data) {
		String array[] = data.split("\n");
		int i;
		
		ArrayList<XYChart.Series<String, Number>> series1 = new ArrayList<XYChart.Series<String, Number>>(); 
		//
		for(i=0;i<array.length;i++) {
			XYChart.Series<String, Number> temp = new XYChart.Series<String, Number>();
			//
			temp.setName(array[i].substring(0,15).replaceAll(" ", "")+"ILS");
			
			temp.getData().add(new XYChart.Data<>(
					array[i].substring(0,15).replaceAll(" ", ""),
							Integer.parseInt(array[i].substring(15,array[i].length()).replaceAll(" ", ""))));
			series1.add(temp);
		}
		
		return series1;
	}
	
	/**
	 * get pie Chart Data For Fuel Type
	 * @param data
	 * @return
	 */
	public ObservableList<PieChart.Data> getpieChartDataForFuelType(String data) {
		String array[] = data.split("\n");
		int countArray[] =new int[array.length],sum=0,i;
		
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList();
		
		for(i=0;i<array.length;i++) {
			countArray[i]= Integer.parseInt(array[i].substring(15).replaceAll(" ", ""));
			sum+=countArray[i];
		}
		
		//
		for(i=0;i<array.length;i++) {
			pieChartData.add(new PieChart.Data(String.format("%s(%d%s)",array[i].substring(0,15).replaceAll(" ", ""),
					(int)(((float)countArray[i]/sum)*100),"%"),countArray[i]));
		}

		return pieChartData;
	}
	
	/**
	 * get pie Chart Data For Hours
	 * @param data
	 * @return
	 */
	public ObservableList<PieChart.Data> getpieChartDataForHours(String data) {
		String array[] = data.split("\n");
		int countArray[] =new int[array.length],sum=0,i;
		
		ObservableList<PieChart.Data> pieChartData =
				FXCollections.observableArrayList();
		
		for(i=0;i<array.length;i++) {
			countArray[i]= Integer.parseInt(array[i].substring(11).replaceAll(" ", ""));
			sum+=countArray[i];
		}
		
		//
		for(i=0;i<array.length;i++) {
			pieChartData.add(new PieChart.Data(String.format("%s(%d%s)",array[i].substring(0,11),
					(int)(((float)countArray[i]/sum)*100),"%"),countArray[i]));
		}

		return pieChartData;
	}

	/**
	 * initialize Ranks Table
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
	 * initilize all the data and the structuers of the tabels
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// loading the main window data
		System.out.println("hello");
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
		
	}
	

}