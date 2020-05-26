package boundery;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entity.GasStation;
import Entity.GasStationOrder;
import Entity.Sale;
import Entity.Supplier;
import client.ClientUI;
import client.EmployeeCC;
import client.UserCC;
import enums.MarkitingManagerReport;
import enums.RatesStatus;
import enums.SaleStatus;
import enums.SupplierOrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SupplierController implements Initializable {

    @FXML
    private Pane SupplierOrdersPane;
    
    @FXML
    private Text chooseTypeLbl;

    @FXML
    private ComboBox<SupplierOrderStatus> OrderTypeCombox;

    @FXML
    private Label OrdersLbl;

    @FXML
    private CheckBox SuppliedCBox;

    @FXML
    private TableView<?> OrdersTbl;
    
	@FXML
	private TableColumn<GasStationOrder,Integer> orderId;
	
	@FXML
	private TableColumn<GasStationOrder,GasStation> StationId;
    
	//need to check what king of data the status will be
//	@FXML
//	private TableColumn<GasStationOrder,GasStation> status;
	
	@FXML
	private TableColumn<GasStationOrder,String> Date;
	
	@FXML
	private TableColumn<GasStationOrder,String> orderPrice;
	
	@FXML
	private TableColumn<GasStationOrder,String> FuelType;
	
	@FXML
	private TableColumn<GasStationOrder,Integer> quantity;
	
	@FXML
    private Button LogOutBtn;
	
    @FXML
    private Text OrderHeader;

    @FXML
    private Button MainPaneBtn;

    @FXML
    private Text HelloLbl;

    @FXML
    private Button OrdersBtn;

    @FXML
    private Pane SupplierMainPane;

    @FXML
    private TextArea NotificationText;

    @FXML
    private Button UpdateBtn;

    @FXML
    private Label NotificationLbl;
    private Pane currentPane;
	public static Supplier Supplier;
	private ArrayList<GasStationOrder> selectedOrders = new ArrayList<GasStationOrder>();
	
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SupplierBoundary.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	//Switching Pans - hide the other
    private void switchPanes(Pane newPane) {
		currentPane.setVisible(false);
		currentPane = newPane;
		currentPane.setVisible(true);
	}
    
    //switch to the main pane
    @FXML
	void openMainPane(ActionEvent event) {
		switchPanes(SupplierMainPane);
	}
    
    
    //switch to orders pane
    @FXML
  	void openOrdersPane(ActionEvent event) {
  		switchPanes(SupplierOrdersPane);
  	}
      
    //initializing the scene
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// loading the main window data
		System.out.println("hello");
		Supplier = (Supplier) ClientUI.user;
		System.out.println(Supplier);
		HelloLbl.setText("hello " + Supplier.getFirstName() + " " + Supplier.getLastName());
		//set the current pane as the main
		currentPane = SupplierMainPane;
		// show the main pane and hide the others
		SupplierMainPane.setVisible(true);
		SupplierOrdersPane.setVisible(false);
		
		ObservableList<SupplierOrderStatus> orderTypes = FXCollections.observableArrayList(SupplierOrderStatus.values());
		OrderTypeCombox.setItems(orderTypes);
	}
    
    @FXML
    void logOut(ActionEvent event) {
    	UserCC.logOut(Supplier.getId(),Supplier.getClass().toString());
    }
    
    @FXML
	void chooseOrderType(ActionEvent event) {
	   	if(OrderTypeCombox.getValue()==SupplierOrderStatus.supplied) {
	   		OrderHeader.setText("All Supplied Orders: ");
	   		UpdateBtn.setVisible(false);
	   	}
	   	else {
	   		OrderHeader.setText("All un-Supplied Orders: ");
	   		UpdateBtn.setVisible(true);
	   	}
	   	
	   	String OrderType = OrderTypeCombox.getValue().toString();
	   //	ObservableList<GasStationOrder> data = FXCollections.observableArrayList()
	   	
    }
    
    
}
