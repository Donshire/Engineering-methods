package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent.EventType;

import Entity.Employee;
import Entity.Fuel;
import Entity.Rates;
import client.ClientUI;
import enums.RatesStatus;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MarketingManagerController implements Initializable{

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
	private Button logOutBtn;
	@FXML
	private ComboBox<String> salesTypeCombo;
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
		switchPanes(salePane);
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

		//call the server to get the sales data 
		System.out.println("load sales data");
		
	}

	@FXML
	void createNewRate(ActionEvent event) {
		//get the data entered
		String fuelTypeName = (String)fuelTypesRateCombo.getValue();
		String sFuelNewRate=newRatetxt.getText();
		
		//check the data
		if(fuelTypeName.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please choose Fuel Type");
		else if(sFuelNewRate.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "please enter the new rate");
		else {
			float fFuelNewRate=Float.valueOf(sFuelNewRate);
			if(fFuelNewRate<=0)
				JOptionPane.showMessageDialog(null, "Unvalid value for the new rate");
			else {
				//send to server
			}
		}
	}

	@FXML
	void generateReport(ActionEvent event) {

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

	}
	@FXML
	void logOut(ActionEvent event) {
		System.out.println("log Out");
	}
	@FXML
	void chooseFuelTypeForNewRate(ActionEvent event) {
		String fuelType=fuelTypesRateCombo.getValue();
		//get the max price and the current rate for the fuel
		
		maxFuelPricetxt.setText(String.format("for Fuel %s : The Max Price is %.2f and the "
				+ "current rate is %.2f","95",10f,5f));
	}
	@FXML
	void chooserateType(ActionEvent event) {
		RatesStatus selected=rateTypeCombo.getValue();
		//get the data 
		
		ObservableList<Rates> data=
				FXCollections.observableArrayList(new Rates("1", 1.2f,"95", RatesStatus.done.toString(),"20/2/2020", "Paz"));
		//perpare the table
		/*
		 rateCheckBoxSelect.setCellValueFactory( new PropertyValueFactory<Rates,Boolean>("check") );
		 rateCheckBoxSelect.setCellFactory( CheckBoxTableCell.forTableColumn( rateCheckBoxSelect ) );
		 */
		 
		//CheckBox
		
		 rateCheckBoxSelect.setCellValueFactory(new Callback<CellDataFeatures<Rates,Boolean>, ObservableValue<Boolean>>() {
	            @Override
	            public ObservableValue<Boolean> call(CellDataFeatures<Rates,Boolean> param) {
	            	Rates rate = param.getValue();
	                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(rate.getCheck());
	                
	                booleanProp.addListener(new ChangeListener<Boolean>() {
	                    @Override
	                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                            Boolean newValue) {
	                    	if(newValue==true) selectedRates.add(rate);
	                    	else selectedRates.remove(rate);
	                    	rate.setCheck(newValue);
	                    }
	                });
	                return booleanProp;
	            }
	        });
		 
		 rateCheckBoxSelect.setCellFactory(new Callback<TableColumn<Rates,Boolean>, //
			        TableCell<Rates, Boolean>>() {
			            @Override
			            public TableCell<Rates, Boolean> call(TableColumn<Rates, Boolean> p) {
			                CheckBoxTableCell<Rates, Boolean> cell = new CheckBoxTableCell<Rates, Boolean>();
			                cell.setAlignment(Pos.CENTER);
			                return cell;
			            }
			        });
		//End of checkBox
		 rateFuelType.setCellValueFactory(
					new PropertyValueFactory<Rates, String>("fuelType"));
		
		 rateIDrate.setCellValueFactory(
					new PropertyValueFactory<Rates, String>("rateId"));
		
		 rateNewRate.setCellValueFactory(
					new PropertyValueFactory<Rates, Float>("rateValue"));
		
		 rateStatus.setCellValueFactory(
					new PropertyValueFactory<Rates, RatesStatus>("status"));
		//fill the table
		 fuelRatesTable.getItems().setAll(data);
		
	}
	
	
	private Pane currentPane;
	private Employee markitingManager ;
	private ArrayList<Rates> selectedRates=new ArrayList<Rates>();
	
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
		currentPane=newPane;
		currentPane.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//loading the main window data
		markitingManager =(Employee)ClientUI.user;
		
		helloUserTxt.setText("hello "+markitingManager.getFirstName()+" "
		+markitingManager.getLastName());
		
		currentPane=markitingManagerNofPane;
		
		//add on the nofitication
		
		//loading SalePane data 
		
		//initialize saleTypes comboBox
		ObservableList<String> saleTypes = FXCollections.observableArrayList
				("Activated","Not-Activated");
		salesTypeCombo.setItems(saleTypes);
		
		
		//loading RatesPane data
		//call server and get fuel types from company
		ObservableList<String> fuelTypes = FXCollections.observableArrayList
				("95","solar");
		fuelTypesRateCombo.setItems(fuelTypes);
		
		//initialize rateTypeCombo comboBox
		ObservableList<RatesStatus> rateType = FXCollections.observableArrayList
				(RatesStatus.values());
		rateTypeCombo.setItems(rateType);
		
	}

}
