package client;

import Entity.Employee;
import boundery.LogInController;
import boundery.MarketingManagerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {

	public static Stage mainStage;
	public static Object user=new Employee("emp", "123", "Saleem", "Saiegh", "mail", "123", "054",
			"manager", "Markitig Manager", 0, "1");
	public static ClientConsole client = new ClientConsole("localhost", 5555);

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage=primaryStage;
		
		MarketingManagerController aFrame = new MarketingManagerController();
		aFrame.start(primaryStage);

	}

}
