// InputController.java

package assignment5;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Controller for main user input menu.
 * @author ericsu
 *
 */
public class InputController implements Initializable {
	
	// Inject the components
	
	@FXML
    private ChoiceBox<String> stepChoiceMenu;	
	@FXML
    private Button stepButton;
	
	@FXML
    private ChoiceBox<String> addCritterCountMenu;
	@FXML
    private ChoiceBox<?> addCritterChoiceMenu;
    @FXML
    private Button addCritterMakeButton;
    
    @FXML
    private TextField inputSeedField;
    @FXML
    private Button setSeedButton;
    
    @FXML @Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList(
			"1", "2", "3", "4", "5", "10", "50", "100");
		ObservableList<String> steps = FXCollections.observableArrayList(
			"1", "2", "3", "4", "5", "10", "50", "100", "500");
		addCritterCountMenu.setItems(list);		
		stepChoiceMenu.setItems(steps);
		stepChoiceMenu.setValue("1");
	}	

	

	public void addCritter(ActionEvent event) {
		int newCritterCount = 0;
		try {
			newCritterCount = Integer.valueOf(addCritterCountMenu.getValue()).intValue();
		} catch (NumberFormatException e) {
			// use pop up or other notification
		}
		for (int i = 0; i < newCritterCount; i++) {
			try {
				Critter.makeCritter(addCritterChoiceMenu.getValue().toString());
			} catch (InvalidCritterException e) {
				// Invalid critter
			}
		}
	}
	
	public void setSeed() {
		Long seed = new Long(0);
		try {
			seed = Long.valueOf(inputSeedField.getText()).longValue();
		} catch (NumberFormatException e) {
			System.out.println("nope");
			return;
			// use pop up or other notification
		}
		Critter.setSeed(seed);
	}
	
	public void doStep() {
		System.out.println("hji");
		int stepCount = Integer.valueOf(stepChoiceMenu.getValue()).intValue();
		for (int i = 0; i < stepCount; i++) {
            try {
            	Critter.worldTimeStep();
            } catch (InvalidCritterException e) {
            	// Invalid Critter
            }
        }
	}
	
//	private ArrayList<String> fetchCritterClasses() {
//		ArrayList<>
//		File folder = new File(".");
//		File[] listOfFiles = folder.listFiles();
//		for (int i = 0; i < listOfFiles.length; i++) {
//			if (listOfFiles[i].isFile() && listOfFiles[i].getName().contains("Critter")) {
//				
//			}
//		}
//	}


	
}
