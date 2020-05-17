package client;

import java.io.IOException;

import boundery.LogInController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {

	public static ClientConsole client = new ClientConsole("localhost", 5555);

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LogInController aFrame = new LogInController();
		// create StudentFrame
		aFrame.start(primaryStage);

	}

}
