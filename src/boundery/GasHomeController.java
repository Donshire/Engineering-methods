package boundery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GasHomeController {

	int discount = 0;
	
    @FXML
    private RadioButton radioImmediat;

    @FXML
    private ToggleGroup DeliveryDate;

    @FXML
    private RadioButton radioSimple;

    @FXML
    private Slider sliderAmount;

    @FXML
    private TextField textAmount;
    
    @FXML
    private Text priceList;

    @FXML
    private Text textDiscount;

    @FXML
    private Text total;

    @FXML
    private Button buttonBuy;

    @FXML
    void DragSlider(MouseEvent event) {
    	
    }

    @FXML
    void TextAmountChanged(InputMethodEvent event) {
    	Double value =  sliderAmount.getValue();
    	textAmount.setText(value.toString());
    	priceUpdate();
    }

    @FXML
    void radioSelected(ActionEvent event) {

    }
    
    void SettingDiscount() {
    	Double amount = new Double(textAmount.getText());
    	if (radioImmediat.hasProperties())
    		discount = 2;
    	else if (radioSimple.hasProperties()) {
    		if (amount>600 && amount<801)
    			discount = -3;
    		else if (amount>800)
    			discount = -4;
    		else
    			discount = 0;
    	}
    	else	
    		discount = 0;
    }

}
