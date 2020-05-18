package boundery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ServerUI;

public class ServerController {

	@FXML
	private TextField serverScreen;
	@FXML
	private Button OnOff;
	
	private boolean serverStatus=false;
	final public static String DEFAULT_PORT = "5555";
	
	public void OnOff(ActionEvent event) throws Exception {
		if(!serverStatus) {
			//run server
			ServerUI.runServer(DEFAULT_PORT);
			serverScreen.setText("Server is running");
			serverStatus=true;
		}
		else {
			//stop server
			serverScreen.setText("Server Stop");
			ServerUI.stopServer();
			serverStatus=false;
		}
	}
	
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ServerGUI.fxml"));
		mainPane = loader.load();
		//serverScreen=(TextField)mainPane.getChildren().get(0);
		
		
		// connect the scene to the file
	    s = new Scene(mainPane);
	    
		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}
	
}
