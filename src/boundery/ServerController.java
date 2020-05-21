package boundery;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ServerUI;

public class ServerController {

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
	private TextArea serverConsole;
	@FXML
	private TableView<?> UsersTable;
	@FXML
	private TableColumn<?, ?> userID;
	@FXML
	private TableColumn<?, ?> userType;

	private boolean serverStatus = false;
	private String serverPortNumber;
	private Scene s;
	private Stage stage;

	@FXML
	public void OnOff(ActionEvent event) throws Exception {
		if (!serverStatus) {
			// run server
			ServerUI.runServer(serverPortNumber);
			serverScreen.setText("Server is running");
			serverStatus = true;
		} else {
			// stop server
			serverScreen.setText("Server Stop");
			ServerUI.stopServer();
			serverStatus = false;
		}
	}

	@FXML
	public void seTPort(ActionEvent event) {
		serverPortNumber=portNumber.getText();
		serverPortPane.setVisible(false);
		
		mainPane.setMinWidth(800);
		mainPane.resize(800, 600);
		
		//s.setMinWidth(800);
		//s.setMinHeight(600);
		System.out.println(stage);
		serverPane.setVisible(true);
		s = new Scene(serverPane);

		
	}

	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ServerGUI.fxml"));
		try {
			mainPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s = new Scene(mainPane);
		
		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}


}
