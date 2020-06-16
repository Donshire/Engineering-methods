package boundery;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Entity.Car;
import Entity.Customer;
import Entity.CustomerModule;
import Entity.Employee;
import Entity.StationManager;
import Entity.Supplier;
import client.ClientUI;
import client.UserCC;
import enums.Commands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LogInController {

	@FXML
	private PasswordField passtxt;

	@FXML
	private TextField usernametxt;

	@FXML
	private TextField carNumberInput;

	@FXML
	private Button fastFuelBtn;

	@FXML
	private Button loginbtb;

	@FXML

	void fastFuel(ActionEvent event) {
		String carNumber = carNumberInput.getText();
		if (carNumber.isEmpty())
			JOptionPane.showMessageDialog(null, "Please enter car number");
		else {
			if(carNumber.replaceAll("[^0-9?!\\.]","").compareTo(carNumber)!=0) {
				JOptionPane.showMessageDialog(null, "Please enter just numbers");
				return;
			}
			// call the server and get the car details
			ArrayList<Object> result = UserCC.fastFuelingLogIn(carNumber);
			if (result == null) {
				JOptionPane.showMessageDialog(null, "Car wasn't found");
				return;
			}
			FastFuelingController fastFueling = new FastFuelingController();
			fastFueling.car = (Car) result.get(0);
			fastFueling.customer = (Customer) result.get(1);
			fastFueling.customerModule = (CustomerModule) result.get(2);
			try {
				MasterGUIController.getMasterGUIController().
				switchWindows("FastFueling.fxml");
				//fastFueling.start(ClientUI.mainStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	@FXML
	void login(ActionEvent event) {
		String username = usernametxt.getText();
		String password = passtxt.getText();

		if (password.isEmpty() == true || username.isEmpty() == true)
			JOptionPane.showMessageDialog(null, "username or password are missing");
		else {
			Object obj = UserCC.login(username, password);
			
			if (obj instanceof Customer) {
				//
				CustomerGuiController.customer=(Customer) obj;
				//
				MasterGUIController.getMasterGUIController().
				switchWindows("CustomerGUI.fxml");
				//
			} else if (obj instanceof Employee) {
				System.out.println("employee");
				ClientUI.user = obj;
				Employee employee = (Employee) obj;
				try {
					System.out.println(employee.getRole().toLowerCase());
					if(employee.getRole().toLowerCase().compareTo("marketing employee")==0) {
						System.out.println("marketing employee");
						MarketingEmployeeController.markem = (Employee) obj;
						MarketingEmployeeController aFrame = new MarketingEmployeeController();
						aFrame.start(ClientUI.mainStage);
					}
					switch (employee.getRole().toLowerCase()) {
					case "marketing manager":
						MarketingManagerController.markitingManager = employee;
						//
						MasterGUIController.getMasterGUIController().
						switchWindows("MarketingManager.fxml");
						break;

					case "ceo":
						 CeoGUIController.ceo= employee;
						 MasterGUIController.getMasterGUIController().
						 switchWindows("CeoGUI.fxml");
						break;

					case "station manager":
						StationManagerController.stationManager=(StationManager) obj;
						//
						MasterGUIController.getMasterGUIController().
						switchWindows("StationManagerGUI.fxml");
						break;
						
					case "markitig employee":
						MarketingEmployeeController.markem=(Employee) obj;
						//
						MasterGUIController.getMasterGUIController().
						switchWindows("MarketingEmployee.fxml");
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (obj instanceof Supplier) {
				//
				SupplierController.supplier=(Supplier) obj;
				//
				MasterGUIController.getMasterGUIController().
				switchWindows("SupplierBoundary.fxml");
				//
			}
			else if (obj == null)
				JOptionPane.showMessageDialog(null, "not exist");
			else if (obj.equals(Commands.UserAlreadyConnected))
				JOptionPane.showMessageDialog(null, "already online");
		}
	}
}