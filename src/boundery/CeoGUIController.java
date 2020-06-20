package boundery;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Entity.CompanyFuel;
import Entity.Employee;
import Entity.Fuel;
import Entity.GenericReport;
import Entity.Message;
import Entity.PricingModule;
import Entity.Rates;
import Entity.Sale;
import Entity.PricingModule;
import client.ClientUI;
import client.EmployeeCC;
import client.UserCC;
import enums.Commands;
import enums.MarkitingManagerReport;
import enums.RatesStatus;
import enums.SaleStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
// TODO: Auto-generated Javadoc

/**
 * This is a simple class for the interface of the CEO
 * It has the buttons, tables and all the things in the CEO gui.
 *
 * @author yosse
 */
public class CeoGUIController implements Initializable {
	
	/** The select pricing module list. */
	ArrayList<PricingModule> selectPricingModuleList = new ArrayList<PricingModule>();

	/** The ceo. */
	public static Employee ceo;

	/** The ceo hello. */
	@FXML
	private Text ceo_hello;

	/** The current pane. */
	private AnchorPane currentPane;

	/** The Report table. */
	// Report table start
	@FXML
	private TableView<GenericReport> ReportTable;

	/** The Report type. */
	@FXML
	private TableColumn<GenericReport, String> ReportType;

	/** The Report date. */
	@FXML
	private TableColumn<GenericReport, String> ReportDate;

	/** The Report time. */
	@FXML
	private TableColumn<GenericReport, String> ReportTime;

	/** The Report enter year. */
	@FXML
	private TextField ReportEnterYear;

	/** The Report station id. */
	@FXML
	private TableColumn<GenericReport, Integer> ReportStationId;

	/** The Show report. */
	@FXML
	private Button ShowReport;

	/** The log out. */
	@FXML
	private Button logOut;

	/** The Reports handler. */
	@FXML
	private Button ReportsHandler;

	/** The Reports station ID. */
	@FXML
	private TextField ReportsStationID;

	// Report table end

	/** The select. */
	@FXML
	private CheckBox select;

	/** The selecting. */
	CheckBoxImplementation selecting;

	/** The Main pane. */
	@FXML
	private AnchorPane MainPane;

	/** The reports pane. */
	@FXML
	private AnchorPane reportsPane;

	/** The Notifications text area. */
	@FXML
	private TextArea NotificationsTextArea;

	/** The Set max price of gas pane. */
	@FXML
	private AnchorPane SetMaxPriceOfGasPane;

/** The Max price list. */
//@@@@@@@@@@@@@@@@@@Max  Price table @@@@@@@@@@@@@@@@
	@FXML
	private TableView<CompanyFuel> MaxPriceList;

	/** The Fuel type col. */
	@FXML
	private TableColumn<CompanyFuel, String> FuelTypeCol;

	/** The Current price col. */
	@FXML
	private TableColumn<CompanyFuel, Float> CurrentPriceCol;

	/** The New max price col. */
	@FXML
	private TableColumn<CompanyFuel, String> NewMaxPriceCol;

	/** The Max price refresh. */
	@FXML
	private Button MaxPriceRefresh;

	/** The Rate approval refresh. */
	@FXML
	private Button RateApprovalRefresh;

	/** The Max price update btn. */
	@FXML
	private Button MaxPriceUpdateBtn;

	/** The Rate approval pane. */
	@FXML
	private AnchorPane RateApprovalPane;

//@@@@@@@@@@@@@@@@@@@Request Table@@@@@@@@@@@@@@@@@@@@@@@

	/** The Request list table. */
@FXML
	private TableView<PricingModule> RequestListTable;

	/** The Request select col. */
	@FXML
	private TableColumn<PricingModule, Integer> RequestSelectCol;

	/** The Request model col. */
	@FXML
	private TableColumn<PricingModule, Integer> RequestModelCol;

	/** The Request sale pecent col. */
	@FXML
	private TableColumn<PricingModule, Float> RequestSalePecentCol;

	/** The Request company name col. */
	@FXML
	private TableColumn<PricingModule, String> RequestCompanyNameCol;

	/** The Request status col. */
	@FXML
	private TableColumn<PricingModule, String> RequestStatusCol;
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	/** The Request list confirm btn. */
@FXML
	private Button RequestListConfirmBtn;

	/** The Request list reject btn. */
	@FXML
	private Button RequestListRejectBtn;

	/** The Max price of gas button. */
	@FXML
	private Button MaxPriceOfGasButton;

	/** The Rate approval button. */
	@FXML
	private Button RateApprovalButton;

	/** The Main pane button. */
	@FXML
	private Button MainPaneButton;

	// all 3 buttons
	/**
	 * Check input.
	 *
	 * @param year the year
	 * @param id the id
	 * @return true, if successful
	 */
	// reports
	boolean checkInput(String year, String id) {
		int val, val2;
		try {
			val2 = Integer.parseInt(year);
			val = Integer.parseInt(id);
			if (val <= 0 || val2 <= 0)
				return false;

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * a function that handles all the buttons that inside the reports pane the ceo
	 * can choose station id to display or choose a specific year to see the reports
	 * in it by pressing on the show button.
	 *
	 * @param event this event checks which button was clicked or pressed
	 */
	@FXML
	void ReportFunctionHandler(ActionEvent event) {
		if (event.getSource() == ReportsHandler) {
			switchPanes(reportsPane);
		}
		if (event.getSource() == ShowReport) {
			if (checkInput(ReportEnterYear.getText(), ReportsStationID.getText())) {
				{
					ReportTable.setItems(getAllReportByYearandStationId(ReportEnterYear.getText(),
							Integer.parseInt(ReportsStationID.getText())));
				}

			} else {
				JOptionPane.showMessageDialog(null, "input error");
			}
		}
	}

	/**
	 * a function that handles all the buttons that inside the rates pane the ceo
	 * can view the list of the rates from the station manager the ceo can approve
	 * or reject rates the refresh button refreshes the table.
	 *
	 * @param event this event checks which button was clicked or pressed
	 */

	@FXML
	void RateApprovalHandler(ActionEvent event) {
		if (event.getSource() == RateApprovalButton) {
			switchPanes(RateApprovalPane);
		}
		if (event.getSource() == RateApprovalRefresh || event.getSource() == RateApprovalButton) {
			ObservableList<PricingModule> data1 = FXCollections
					.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
			RequestListTable.setItems(data1);
		}
		if (event.getSource() == RequestListConfirmBtn) {
			if(selectPricingModuleList.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please select at least one rate");
				return;
			}
			if (EmployeeCC.confirmBuildRateApprovalDetails(selectPricingModuleList)) {
				JOptionPane.showMessageDialog(null, "Success");

				selectPricingModuleList.clear();

				ObservableList<PricingModule> data1 = FXCollections
						.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
				RequestListTable.setItems(data1);
				selectPricingModuleList = new ArrayList<PricingModule>();
				GUIBuiltParts.buildCheckBOXForTable(RequestSelectCol, selectPricingModuleList);

			} else {
				JOptionPane.showMessageDialog(null, "Fail");
			}
		}
		if (event.getSource() == RequestListRejectBtn) {
			if(selectPricingModuleList.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please select at least one rate");
				return;
			}
			if (EmployeeCC.rejectBuildRateApprovalDetails(selectPricingModuleList)) {
				JOptionPane.showMessageDialog(null, "Success");
				//
				selectPricingModuleList.clear();
				//
				ObservableList<PricingModule> data1 = FXCollections
						.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
				RequestListTable.setItems(data1);
				selectPricingModuleList = new ArrayList<PricingModule>();
				GUIBuiltParts.buildCheckBOXForTable(RequestSelectCol, selectPricingModuleList);
				//
			} else {
				JOptionPane.showMessageDialog(null, "Fail");
			}
		}

	}

	/**
	 * A function that initializes the reports table, it gives each column the
	 * appropriate variable when clicked twice on the row it displays the report of
	 * the chosen station and id for the same ceo companies.
	 */
	public void initializeReportTable() {

		// clicked row-------------------------
		ReportTable.setRowFactory(tv -> {
			TableRow<GenericReport> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					GenericReport clickedRow = row.getItem();
					showReport(null, clickedRow);

					System.out.println(clickedRow.toString());
				}
			});
			return row;
		});
		// ----------------------

		// report table start

		ReportType.setCellValueFactory(new PropertyValueFactory<GenericReport, String>("reportType"));
		ReportDate.setCellValueFactory(new PropertyValueFactory<GenericReport, String>("year"));
		ReportTime.setCellValueFactory(new PropertyValueFactory<GenericReport, String>("quarter"));
		ReportStationId.setCellValueFactory(new PropertyValueFactory<GenericReport, Integer>("stationId"));
		// report table end

	}

	/**
	 * this function starts the reports and shows them when needed.
	 *
	 * @param f the file
	 * @param r the report
	 */
	public void showReport(File f, GenericReport r) {

		QuarterReportController.file = f;
		QuarterReportController.report = r;
		QuarterReportController c = new QuarterReportController();
		Stage st = new Stage();

		try {
			c.start(st);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * this function returns the reports by the chosen year and the station year if
	 * there are no reports a message says there is no report in this year will pop
	 * up if there are reports it returns them to the table.
	 *
	 * @param year      chosen year
	 * @param stationId chosen Id
	 * @return the all report by yearand station id
	 */
	public ObservableList<GenericReport> getAllReportByYearandStationId(String year, int stationId) {
		ArrayList<GenericReport> rp = EmployeeCC.getAllReportByYearandStationId(year, stationId);
		if (rp.isEmpty())
			JOptionPane.showMessageDialog(null, "there is no report in this year");
		ObservableList<GenericReport> reports = FXCollections.observableArrayList(rp);
		return reports;
	}

	/**
	 * this function handles all the button clicks inside the max price gas pane for
	 * all the gas types the ceo to set the max price of the gas for his companies
	 * by clicking twice on the new max price row he wants to change after changing
	 * he has to click update the ceo can also refresh the list the table.
	 *
	 * @param event this event checks which button was clicked or pressed
	 */
	@FXML
	void SetMaxPriceOfGasHandler(ActionEvent event) {
		if (event.getSource() == MaxPriceOfGasButton) {
			switchPanes(SetMaxPriceOfGasPane);
		}

		if (event.getSource() == MaxPriceRefresh || event.getSource() == MaxPriceOfGasButton) {
			ObservableList<CompanyFuel> data = FXCollections
					.observableArrayList(new EmployeeCC().getFuelMaxPriceDetails(ceo.getCompanyName()));
			MaxPriceList.setItems(data);
		}

		if (event.getSource() == MaxPriceUpdateBtn) {
			ArrayList<CompanyFuel> fuels = new ArrayList<CompanyFuel>();
			int i = 0;
			while (i < MaxPriceList.getItems().size()) {
				if (MaxPriceList.getItems().get(i).getNewMaxPrice() != null
						&& !MaxPriceList.getItems().get(i).getNewMaxPrice().isEmpty()) {
					MaxPriceList.getItems().get(i).setCompanyName(ceo.getCompanyName());
					fuels.add(MaxPriceList.getItems().get(i));
				}
				i++;
			}
			if (fuels.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter values");
				return;
			}
			if (EmployeeCC.updateFuelMaxPriceDetails(fuels)) {
				JOptionPane.showMessageDialog(null, "Success");
				ObservableList<CompanyFuel> data = FXCollections
						.observableArrayList(new EmployeeCC().getFuelMaxPriceDetails(ceo.getCompanyName()));
				MaxPriceList.setItems(data);

			} else {
				JOptionPane.showMessageDialog(null, "Fail");
			}

		}
	}

	/**
	 * this fucntion switches to the report pane.
	 *
	 * @param event the event
	 */
	@FXML
	void openReportGenerationPane(ActionEvent event) {
		switchPanes(RateApprovalPane);

	}

	/**
	 * this function handles the log out button and other buttons if they exist.
	 *
	 * @param event this event checks which button was clicked or pressed
	 */
	@FXML
	void MainPaneHandler(ActionEvent event) {
		if (event.getSource() == logOut) {
			UserCC.logOut(ceo.getId(), ceo.getClass().toString());
			MasterGUIController.getMasterGUIController().switchWindows("LogIn.fxml");
		} else if (event.getSource() == MainPaneButton) {
			switchPanes(MainPane);
		}

	}

	/**
	 * this function builds the max price gas table, columns and variables.
	 */
	void BuildMaxPriceTable() {

		FuelTypeCol.setCellValueFactory(new PropertyValueFactory<CompanyFuel, String>("fuelType"));
		CurrentPriceCol.setCellValueFactory(new PropertyValueFactory<CompanyFuel, Float>("companyPrice"));

		NewMaxPriceCol.setCellValueFactory(new PropertyValueFactory<>("newMaxPrice"));

		NewMaxPriceCol.setCellFactory(TextFieldTableCell.<CompanyFuel>forTableColumn());

		NewMaxPriceCol.setMinWidth(200);

		// On Cell edit commit (for FullName column)
		NewMaxPriceCol.setOnEditCommit((CellEditEvent<CompanyFuel, String> event) -> {
			TablePosition<CompanyFuel, String> pos = event.getTablePosition();

			String newMaxPrice = event.getNewValue();
			try {
				Double.parseDouble(newMaxPrice);
			} catch (Exception e) {
				// TODO: handle exception
				newMaxPrice = "";
				MaxPriceList.refresh();
			}
			int row = pos.getRow();
			CompanyFuel fuel = event.getTableView().getItems().get(row);

			fuel.setNewMaxPrice(newMaxPrice);

		});
	}

	/**
	 * this function builds the rates table.
	 */
	void BuildRateApprovalTable() {
		RequestModelCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Integer>("modelNumber"));
		RequestSalePecentCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Float>("salePercent"));
		RequestCompanyNameCol.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("CompanyName"));
		RequestStatusCol.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("status"));
		RequestSelectCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Integer>("select"));
		GUIBuiltParts.buildCheckBOXForTable(RequestSelectCol, selectPricingModuleList);

	}

	/**
	 * this function receives a pane and switches to it, by making the old one
	 * invisable and the new one visiable.
	 *
	 * @param newPane the new pane to be visiable
	 */
	private void switchPanes(AnchorPane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

	/**
	 * a function to start the gui fxml gets the appropriate fxml file sets title
	 * for it and displays it.
	 *
	 * @param primaryStage the start primary stage
	 * @throws Exception the exception
	 */
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("CeoGUI.fxml"));
		mainPane = loader.load();
		// connect the scene to the file
		s = new Scene(mainPane);
		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();
	}

	/**
	 * this function starts the tables, variables and the hello message sets the
	 * unwanted panes to be invisible.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// loading the main window data
		BuildMaxPriceTable();
		BuildRateApprovalTable();
		initializeReportTable();

//		ObservableList<Fuel> data = FXCollections
//				.observableArrayList(new EmployeeCC().getFuelMaxPriceDetails(ceo.getCompanyName()));
//		MaxPriceList.setItems(data);
		ObservableList<PricingModule> data1 = FXCollections
				.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
		RequestListTable.setItems(data1);
		// ArrayList<PricingModule> companyPricingModuleByStatus;
		currentPane = MainPane;
		ceo_hello.setText(ceo.getFirstName());
		// show the main pane and hide the others1
		currentPane.setVisible(true);
		RateApprovalPane.setVisible(false);
		reportsPane.setVisible(false);
		SetMaxPriceOfGasPane.setVisible(false);
	}

}
