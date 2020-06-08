package boundery;

import java.util.ArrayList;

import Entity.PricingModule;
import Entity.Rates;
import helpinigStructForGUI.CheckBoxImplementation;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class GUIBuiltParts {

	public static <T extends CheckBoxImplementation,S> void buildCheckBOXForTable(TableColumn<T,S> CheckBoxSelect,ArrayList<T> selected) {
		CheckBoxSelect
				.setCellValueFactory(new Callback<CellDataFeatures<T,S>, ObservableValue<S>>() {
					@Override
					public ObservableValue<S> call(CellDataFeatures<T,S> param) {
						T row = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(row.getCheck());

						booleanProp.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								if (newValue == true)
									selected.add(row);
								else
									selected.remove(row);
								row.setCheck(newValue);
							}
						});
						return (ObservableValue<S>) booleanProp;
					}
				});

		CheckBoxSelect.setCellFactory(new Callback<TableColumn<T,S>, //
				TableCell<T,S>>() {
			@Override
			public TableCell<T,S> call(TableColumn<T,S> p) {
				CheckBoxTableCell<T,S> cell = new CheckBoxTableCell<T,S>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		
		
		/*
		 * CheckBoxSelect
				.setCellValueFactory(new Callback<CellDataFeatures<Rates, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Rates, Boolean> param) {
						Rates rate = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(rate.getCheck());

						booleanProp.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								if (newValue == true)
									selectedRates.add(rate);
								else
									selectedRates.remove(rate);
								rate.setCheck(newValue);
							}
						});
						return booleanProp;
					}
				});

		rateCheckBoxSelect.setCellFactory(new Callback<TableColumn<Rates, Boolean>, //
				TableCell<Rates, Boolean>>() {
			@Override
			public TableCell<Rates, Boolean> call(TableColumn<Rates, Boolean> p) {
				CheckBoxTableCell<Rates, Boolean> cell = new CheckBoxTableCell<Rates, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		 * */
	}

}
