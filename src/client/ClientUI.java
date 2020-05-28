package client;

import boundery.LogInController;
import boundery.SupplierController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {

	public static Stage mainStage;
	public static Object user;
	public static ClientConsole client = new ClientConsole("localhost", 5555);

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage=primaryStage;
		
		LogInController aFrame = new LogInController();
		aFrame.start(primaryStage);
		
//		SupplierController aFrame = new SupplierController();
//		aFrame.start(primaryStage);

	}

}
