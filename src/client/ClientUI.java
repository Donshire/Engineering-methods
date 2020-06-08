package client;

import java.io.IOException;

import com.sun.corba.se.spi.ior.MakeImmutable;

import boundery.GasHomeController;
import boundery.LogInController;
import boundery.MarketingEmployeeController;
import boundery.MarketingManagerController;
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
//		LogInController aFrame = new LogInController();
//		mainStage=primaryStage;
		// GasHomeController aFrame = new GasHomeController();
		// MyOrderConrtoller aFrame = new MyOrderConrtoller();
		// SupplierController aFrame = new SupplierController();
		MarketingEmployeeController aFrame = new MarketingEmployeeController();
		aFrame.start(primaryStage);

		// SupplierController aFrame = new SupplierController();
		// aFrame.start(primaryStage);

	}

}
