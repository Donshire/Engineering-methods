package boundery;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.MyFuelServer;
import server.MyFuelServer.UserOnline;
import server.ServerUI;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import Entity.Sale;

public class ServerController implements Initializable {

	@FXML
	private Pane serverPortPane;
	@FXML
	private Pane serverPane;
	@FXML
	private TextField portNumber;
	@FXML
	private TextField dbPassword;
	@FXML
	private TextField schemaName;
	@FXML
	private Button setPortNumberBtn;
	@FXML
	private TextField serverScreen;
	@FXML
	private Button OnOffBtn;
	@FXML
	private TableView<UserOnline> UsersTable;
	@FXML
	private TableColumn<UserOnline, String> userID;
	@FXML
	private TableColumn<UserOnline, String> userType;
	@FXML
	private TextArea serverConsole;

	private static TextArea serverConsoleStatic;
	private static TableView<UserOnline> UsersTableStatic; 
	private static TableColumn<UserOnline, String> userIDStatic;
	private static TableColumn<UserOnline, String> userTypeStatic;
	private boolean serverStatus = false;
	private String serverPortNumber;

	/**
	 * handle the on of button of the server
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void OnOff(ActionEvent event) throws Exception {
		if (!serverStatus) {
			// run server
			ServerUI.runServer(serverPortNumber);
			serverScreen.setText("Server is running");
			writeToServerConsole("Server is running");
			serverStatus = true;
		} else {
			// stop server
			serverScreen.setText("Server Stop");
			writeToServerConsole("Server Stoped");
			ServerUI.stopServer();
			serverStatus = false;
		}
	}

	/**
	 * when the server start
	 * @param event
	 */
	@FXML
	public void setServerData(ActionEvent event) {
		serverPortNumber = portNumber.getText();
		MyFuelServer.schemaName=schemaName.getText();
		MyFuelServer.dbPassword=dbPassword.getText();
		
		if(serverPortNumber.isEmpty()||MyFuelServer.schemaName.isEmpty()||MyFuelServer.dbPassword.isEmpty())
			JOptionPane.showMessageDialog(null, "Please fill all the server details");
		else {
			serverPortPane.setVisible(false);
			serverPane.setVisible(true);
		}
	}

	/**
	 * write To Server Console
	 * @param str
	 */
	public static void writeToServerConsole(String str) {
		StringBuilder sb = new StringBuilder(serverConsoleStatic.getText());
		sb.append(String.format("<%s><server> %s\n", LocalTime.now(), str));
		serverConsoleStatic.setText(sb.toString());
	}
	
	/**
	 * if user exist delete else add
	 * @param ID
	 * @param userType
	 */
	public static void onlineUserTableCont(String ID,String userType) {
		UserOnline user = new UserOnline(ID, userType);
		if(UsersTableStatic.getItems().contains(user)) {
			MyFuelServer.usersOnline.remove(user);
			UsersTableStatic.getItems().remove(user);
		}
		else {
			UsersTableStatic.getItems().add(user);
			MyFuelServer.usersOnline.add(user);
		}
		//UsersTableStatic.refresh();
	}

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ServerGUI.fxml"));
		mainPane = loader.load();

		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	/**
	 * initialize all the data needed
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serverConsoleStatic=serverConsole;
		UsersTableStatic=UsersTable;
		
		userIDStatic=userID;
		userTypeStatic=userType;
		
		userIDStatic.setCellValueFactory(new PropertyValueFactory<UserOnline, String>("UserID"));
		userTypeStatic.setCellValueFactory(new PropertyValueFactory<UserOnline, String>("userType"));
	}

}
