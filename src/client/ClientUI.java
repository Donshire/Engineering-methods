package client;

import java.io.IOException;

import boundery.CeoGUIController;
import boundery.GasHomeController;
import boundery.LogInController;
import boundery.MarketingEmployeeController;
import boundery.MarketingManagerController;
import boundery.MasterGUIController;
import boundery.SupplierController;
import boundery.MyOrderConrtoller;
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
		MasterGUIController.getMasterGUIController().setMainStage(primaryStage, "LogIn.fxml");
		MasterGUIController.getMasterGUIController().BeginMainWindow();
	}

}
