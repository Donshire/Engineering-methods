package server;

import boundery.ServerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application{

	private static MyFuelServer sv;
	
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {			  		
		ServerController aFrame = new ServerController(); 
		// create StudentFrame
		aFrame.start(primaryStage);
		
	}
	
	public static void runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p); // Set port to 5555

		} catch (Throwable t) {
			System.out.println("ERROR - Could not connect!");
		}

		sv = new MyFuelServer(port);
		
		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
	
	public static void stopServer() {
		try {
			sv.close(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not close the server!");
		}
	}

}
