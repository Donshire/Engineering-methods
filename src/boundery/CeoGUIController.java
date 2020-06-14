package boundery;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.stage.Stage;
import javafx.util.Callback;

public class CeoGUIController implements Initializable {
	ArrayList<PricingModule> selectPricingModuleList = new ArrayList<PricingModule>();

	public static Employee ceo;

	private AnchorPane currentPane;

	// Report table start
	@FXML
	private TableView<GenericReport> ReportTable;

	@FXML
	private TableColumn<GenericReport, String> ReportType;

	@FXML
	private TableColumn<GenericReport, String> ReportDate;

	@FXML
	private TableColumn<GenericReport, String> ReportTime;

	@FXML
	private TextField ReportEnterYear;

	@FXML
	private TableColumn<GenericReport, Integer> ReportStationId;

	@FXML
	private Button ShowReport;

	@FXML
	private Button ReportsHandler;

	@FXML
	private TextField ReportsStationID;

	// Report table end

	@FXML
	private CheckBox select;

	CheckBoxImplementation selecting;

	@FXML
	private AnchorPane MainPane;

	@FXML
	private AnchorPane reportsPane;

	@FXML
	private TextArea NotificationsTextArea;

	@FXML
	private AnchorPane SetMaxPriceOfGasPane;
//@@@@@@@@@@@@@@@@@@Max  Price table @@@@@@@@@@@@@@@@
	@FXML
	private TableView<Fuel> MaxPriceList;

	@FXML
	private TableColumn<Fuel, String> FuelTypeCol;

	@FXML
	private TableColumn<Fuel, Double> CurrentPriceCol;

	@FXML
	private TableColumn<Fuel, String> NewMaxPriceCol;

	@FXML
	private Button MaxPriceRefresh;

	@FXML
	private Button RateApprovalRefresh;

	@FXML
	private Button MaxPriceUpdateBtn;

	@FXML
	private AnchorPane RateApprovalPane;

//@@@@@@@@@@@@@@@@@@@Request Table@@@@@@@@@@@@@@@@@@@@@@@

	@FXML
	private TableView<PricingModule> RequestListTable;

	@FXML
	private TableColumn<PricingModule, Integer> RequestSelectCol;

	@FXML
	private TableColumn<PricingModule, Integer> RequestModelCol;

	@FXML
	private TableColumn<PricingModule, Float> RequestSalePecentCol;

	@FXML
	private TableColumn<PricingModule, String> RequestCompanyNameCol;

	@FXML
	private TableColumn<PricingModule, String> RequestStatusCol;
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	@FXML
	private Button RequestListConfirmBtn;

	@FXML
	private Button RequestListRejectBtn;

	@FXML
	private Button MaxPriceOfGasButton;

	@FXML
	private Button RateApprovalButton;

	@FXML
	private Button MainPaneHandler;

	@FXML
	private Button MainPaneButton;

	// all 3 buttons
	// reports
	boolean checkInput(String year, String id) {
		int val, val2;
		try {
			val2 = Integer.parseInt(year);
			val = Integer.parseInt(id);
			if (val <= 0 || val2 <= 0)
				return false;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

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

	@FXML
	void RateApprovalHandler(ActionEvent event) {
		if (event.getSource() == RateApprovalButton) {
			switchPanes(RateApprovalPane);
		}
		if (event.getSource() == RateApprovalRefresh) {
			ObservableList<PricingModule> data1 = FXCollections
					.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
			RequestListTable.setItems(data1);
		}
		if (event.getSource() == RequestListConfirmBtn) {
			if (EmployeeCC.confirmBuildRateApprovalDetails(selectPricingModuleList)) {
				JOptionPane.showMessageDialog(null, "Success");
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
			if (EmployeeCC.rejectBuildRateApprovalDetails(selectPricingModuleList)) {
				JOptionPane.showMessageDialog(null, "Success");
				ObservableList<PricingModule> data1 = FXCollections
						.observableArrayList(new EmployeeCC().getBuildRateApprovalDetails(ceo.getCompanyName()));
				RequestListTable.setItems(data1);
				selectPricingModuleList = new ArrayList<PricingModule>();
				GUIBuiltParts.buildCheckBOXForTable(RequestSelectCol, selectPricingModuleList);

			} else {
				JOptionPane.showMessageDialog(null, "Fail");
			}
		}

	}

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

	public ObservableList<GenericReport> getAllReportByYearandStationId(String year, int stationId) {
		ArrayList<GenericReport> rp = EmployeeCC.getAllReportByYearandStationId(year, stationId);
		if (rp.isEmpty())
			JOptionPane.showMessageDialog(null, "there is no report in this year");
		ObservableList<GenericReport> reports = FXCollections.observableArrayList(rp);
		return reports;
	}

	@FXML
	void SetMaxPriceOfGasHandler(ActionEvent event) {
		if (event.getSource() == MaxPriceOfGasButton) {
			switchPanes(SetMaxPriceOfGasPane);
		}

		if (event.getSource() == MaxPriceRefresh) {
			ObservableList<Fuel> data = FXCollections
					.observableArrayList(new EmployeeCC().getFuelMaxPriceDetails(ceo.getCompanyName()));
			MaxPriceList.setItems(data);
		}
		if (event.getSource() == MaxPriceUpdateBtn) {
			ArrayList<Fuel> fuels = new ArrayList<Fuel>();
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
				ObservableList<Fuel> data = FXCollections
						.observableArrayList(new EmployeeCC().getFuelMaxPriceDetails(ceo.getCompanyName()));
				MaxPriceList.setItems(data);

			} else {
				JOptionPane.showMessageDialog(null, "Fail");
			}

		}
	}

	@FXML
	void openReportGenerationPane(ActionEvent event) {
		switchPanes(RateApprovalPane);

	}

	// extras

	@FXML
	void MainPaneHandler(ActionEvent event) {
		switchPanes(MainPane);

	}

	@FXML
	void MouseHandler(ActionEvent event) {

	}

	void BuildMaxPriceTable() {

		FuelTypeCol.setCellValueFactory(new PropertyValueFactory<Fuel, String>("fuelType"));
		CurrentPriceCol.setCellValueFactory(new PropertyValueFactory<Fuel, Double>("maxPrice"));

		NewMaxPriceCol.setCellValueFactory(new PropertyValueFactory<>("newMaxPrice"));

		NewMaxPriceCol.setCellFactory(TextFieldTableCell.<Fuel>forTableColumn());

		NewMaxPriceCol.setMinWidth(200);

		// On Cell edit commit (for FullName column)
		NewMaxPriceCol.setOnEditCommit((CellEditEvent<Fuel, String> event) -> {
			TablePosition<Fuel, String> pos = event.getTablePosition();

			String newMaxPrice = event.getNewValue();
			try {
				Double.parseDouble(newMaxPrice);
			} catch (Exception e) {
				// TODO: handle exception
				newMaxPrice = "";
				MaxPriceList.refresh();
			}
			int row = pos.getRow();
			Fuel fuel = event.getTableView().getItems().get(row);

			fuel.setNewMaxPrice(newMaxPrice);

		});
	}

	void BuildRateApprovalTable() {
		RequestModelCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Integer>("modelNumber"));
		RequestSalePecentCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Float>("salePercent"));
		RequestCompanyNameCol.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("CompanyName"));
		RequestStatusCol.setCellValueFactory(new PropertyValueFactory<PricingModule, String>("status"));
		RequestSelectCol.setCellValueFactory(new PropertyValueFactory<PricingModule, Integer>("select"));
		GUIBuiltParts.buildCheckBOXForTable(RequestSelectCol, selectPricingModuleList);

	}

	private void switchPanes(AnchorPane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}

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
		// System.out.println(ceo.getCompanyName());
		currentPane = MainPane;

		// show the main pane and hide the others
		currentPane.setVisible(true);
		RateApprovalPane.setVisible(false);
		reportsPane.setVisible(false);
		SetMaxPriceOfGasPane.setVisible(false);
	}

}
