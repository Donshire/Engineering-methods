
package server;

import boundery.ServerController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Class ServerUI controls the server gui.
 */
public class ServerUI extends Application{

	/** The sv. */
	private static MyFuelServer sv;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {			  		
		ServerController aFrame = new ServerController(); 
		// create StudentFrame
		aFrame.start(primaryStage);
		
	}
	
	/**
	 * Run server.
	 *
	 * @param p the p
	 */
	public static void runServer(String p) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(p); // Set port to 5555

		} catch (Throwable t) {
			ServerController.writeToServerConsole("ERROR - Could not connect!");
		}

		sv = new MyFuelServer(port);
		
		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			ServerController.writeToServerConsole("ERROR - Could not listen for clients!");
		}
	}
	
	/**
	 * Stop server.
	 */
	public static void stopServer() {
		try {
			sv.close(); // Start listening for connections
		} catch (Exception ex) {
			ServerController.writeToServerConsole("ERROR - Could not close the server!");
		}
	}

}

