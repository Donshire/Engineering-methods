package boundery;

import java.io.IOException;

import client.ClientUI;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MasterGUIController {

	private static MasterGUIController single = new MasterGUIController(); 
	private String mainWindow=null;
	private Stage mainStage,lastStage;
	
	/**
	 * swiches bettwen FXML's stages
	 * @param windowFXML the name of the fxml file
	 */
	public void switchWindows(String windowFXML) {
		//not main Window
		if(windowFXML.compareTo(mainWindow)!=0) {
			//
			Pane mainPane;
			Scene s;
			Stage newStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			//
			//the last stage wasn't main
			if(lastStage!=mainStage) {
				lastStage.close();
			}
			else {
				//the last pane was main
				mainStage.hide();
			}
			newStage.initStyle(StageStyle.UNDECORATED);
			loader.setLocation(getClass().getResource(windowFXML));
			//
			try {
				mainPane = loader.load();
				s = new Scene(mainPane);
				newStage.setTitle("MyFuel ltm");
				newStage.setScene(s);
				
				
				lastStage=newStage;
				newStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//the main window
		//just show
		else {
			mainStage.show();
			if(lastStage!=mainStage) {//if by mistake called the main
				lastStage.close();
				lastStage=mainStage;
			}
		}
			
	}
	
	/**
	 * used intialy to create the main stage window<br>
	 * can't be used after that
	 * @throws Exception
	 */
	public void BeginMainWindow() throws Exception {
		if(mainWindow==null)throw new Exception("please setMainStage");

		Pane mainPane;
		Scene s;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(mainWindow));
		//
		try {
			mainPane = loader.load();
			s = new Scene(mainPane);
			mainStage.setTitle("MyFuel ltm");
			mainStage.setScene(s);
			lastStage=mainStage;
			mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static MasterGUIController getMasterGUIController() {
		return single;
	}
	
	public void setMainStage(Stage mainStage,String mainWindow) {
		this.mainStage=mainStage;
		this.mainWindow=mainWindow;
		
		// to close the program
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				Platform.exit();
				System.exit(0);
			}
		});
		
	}
}
