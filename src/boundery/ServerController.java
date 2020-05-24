package boundery;

import java.io.IOException;
import java.net.URL;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ServerUI;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ServerController{

    @FXML
    private Pane mainPane;
	@FXML
	private Pane serverPortPane;
	@FXML
    private Pane serverPane;
	@FXML
	private TextField portNumber;
	@FXML
	private Button setPortNumberBtn;
	@FXML
	private TextField serverScreen;
	@FXML
	private Button OnOffBtn;
	@FXML
	private TableView<?> UsersTable;
	@FXML
	private TableColumn<?, ?> userID;
	@FXML
	private TableColumn<?, ?> userType;

	
	private static TextArea serverConsole;
	private boolean serverStatus = false;
	private String serverPortNumber;

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
			writeToServerConsole("Server Stop");
			ServerUI.stopServer();
			serverStatus = false;
		}
	}

	@FXML
	public void seTPort(ActionEvent event) {
		serverPortNumber=portNumber.getText();
		serverPortPane.setVisible(false);
		serverPane.setVisible(true);
	}
	
	public static void writeToServerConsole(String str) {
		StringBuilder sb=new StringBuilder(serverConsole.getText());
		sb.append(String.format("<%s><server> %s\n",LocalTime.now()
				, str));
		serverConsole.setText(sb.toString());
	}

	public void start(Stage primaryStage) throws Exception {
		
		Scene s;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ServerGUI.fxml"));
		try {
			mainPane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//get the Text Area "ServerConsole"
		Pane pane=(Pane)mainPane.getChildren().get(1);
		serverConsole=(TextArea) pane.getChildren().get(2);
				
		s = new Scene(mainPane);
		
		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

}
