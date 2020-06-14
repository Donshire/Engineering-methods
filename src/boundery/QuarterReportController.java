package boundery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import Entity.GenericReport;
import client.EmployeeCC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.FileManagmentSys;

public class QuarterReportController implements Initializable {

	@FXML
	private Text datetxt;

	@FXML
	private TextArea reportdatatxt;

	public static GenericReport report;

	public static File file;

	public void start(Stage primaryStage) throws Exception {
		Pane mainPane;
		Scene s;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("QuarterReportView.fxml"));
		mainPane = loader.load();

		// connect the scene to the file
		s = new Scene(mainPane);

		primaryStage.setTitle("MyFuel ltm");
		primaryStage.setScene(s);
		primaryStage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (report != null)
			file = EmployeeCC.getFile(report);

		datetxt.setText(LocalDate.now().toString());

		if (file != null)
			reportdatatxt.setText(FileManagmentSys.readQuarterReport(file));
		else
			JOptionPane.showMessageDialog(null, "already exist");

	}

}