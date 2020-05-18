package client;

import java.io.IOException;

import boundery.LogInController;
import boundery.MyOrderConrtoller;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {

	public static ClientConsole client = new ClientConsole("localhost", 5555);

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MyOrderConrtoller aFrame = new MyOrderConrtoller();
		// create StudentFrame
		aFrame.start(primaryStage);

	}

}
