package boundery;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MarketingManagerController {

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
	private ComboBox<?> fuelTypesCombo;
	@FXML
	private TextField newRatetxt;
	@FXML
	private Text maxFuelPricetxt;
	@FXML
	private ComboBox<?> rateTypeCombo;
	@FXML
	private TableView<?> fuelRatesTable;
	@FXML
	private TableColumn<?, ?> rateCheckBoxSelect;
	@FXML
	private TableColumn<?, ?> rateFuelType;
	@FXML
	private TableColumn<?, ?> rateOldRate;
	@FXML
	private TableColumn<?, ?> rateNewRate;
	@FXML
	private TableColumn<?, ?> rateStatus;
	@FXML
	private Button updateRates;
	@FXML
	private Pane salePane;
	@FXML
	private TableColumn<?, ?> SelectSale;
	@FXML
	private TableColumn<?, ?> saleNumber;
	@FXML
	private TableColumn<?, ?> SaleGasType;
	@FXML
	private TableColumn<?, ?> SaleStartDate;
	@FXML
	private TableColumn<?, ?> saleEndDate;
	@FXML
	private TableColumn<?, ?> salePercent;
	@FXML
	private Button viewMoreSaleDetailsBtn;
	@FXML
	private Button activateSaleBtn;
	@FXML
	private ComboBox<?> salesTypeCombo;
	@FXML
	private Pane reportsPane;
	@FXML
	private ComboBox<?> reportKindCombo;
	@FXML
	private TextField reportsaleNumber;
	@FXML
	private Button generateReportBtn;
	@FXML
	private Pane PeriodicReportPane;
	@FXML
	private TableView<?> periodicReportTable;
	@FXML
	private TableColumn<?, ?> customerIDPeriodicReport;
	@FXML
	private TableColumn<?, ?> totalPurchasePeriodicReport;
	@FXML
	private Pane saleResponseReportPane;
	@FXML
	private TableView<?> saleResponseReportTable;
	@FXML
	private TableColumn<?, ?> customerIDResponseReport;
	@FXML
	private TableColumn<?, ?> amountOfPurchaseresponseReport;
	@FXML
	private Text totaleNumberOfCustomersResponseReport;
	@FXML
	private Text totalePurchasesResponseReport;

	
	@FXML
	void OpenSalePane(ActionEvent event) {

	}

	@FXML
	void UpdateSelectedRates(ActionEvent event) {

	}

	@FXML
	void activateSale(ActionEvent event) {

	}

	@FXML
	void chooseReportType(ActionEvent event) {

	}

	@FXML
	void chooseSaleType(ActionEvent event) {

	}

	@FXML
	void createNewRate(ActionEvent event) {

	}

	@FXML
	void generateReport(ActionEvent event) {

	}

	@FXML
	void openFuelRatesPane(ActionEvent event) {

	}

	@FXML
	void openReportGenerationPane(ActionEvent event) {

	}

	@FXML
	void selectedSaleadd(ActionEvent event) {

	}

	@FXML
	void viewMoreSaleDetails(ActionEvent event) {

	}

}
