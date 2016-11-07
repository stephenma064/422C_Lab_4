// InputController.java

package assignment5;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	
	private static String myPackage; 
	
	// Inject the components
	@FXML
	private ChoiceBox<String> selectAnimationMenu;
	@FXML
	private Button setAnimateButton;
	
	@FXML
    private ChoiceBox<String> stepChoiceMenu;	
	@FXML
    private Button stepButton;
	
	@FXML
    private ChoiceBox<String> addCritterCountMenu;
	@FXML
    private ChoiceBox<String> addCritterChoiceMenu;
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
		ObservableList<String> namesOfCritter = FXCollections.observableArrayList(fetchCritterClasses());
		addCritterCountMenu.setItems(list);	
		addCritterChoiceMenu.setItems(namesOfCritter);
		stepChoiceMenu.setItems(steps);
		stepChoiceMenu.setValue("1");
	}	

	public void addCritter() {
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
		Critter.displayWorld();
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
		Critter.displayWorld();
	}
	
	public void doStep() {
		int stepCount = Integer.valueOf(stepChoiceMenu.getValue()).intValue();
		for (int i = 0; i < stepCount; i++) {
            try {
            	Critter.worldTimeStep();
            } catch (InvalidCritterException e) {
            	// Invalid Critter
            }
        }
		Critter.displayWorld();
	}
	
	/**
	 * Parse all files in the directory for only the Critter classes
	 * @return ArrayList of Critter classes
	 */
	private ArrayList<String> fetchCritterClasses() {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
		ArrayList<String> results = new ArrayList<String>();
		File folder = new File("./src/"+myPackage);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".java")) {
				String name = listOfFiles[i].getName();
				results.add(name.substring(0, name.length() - 5));
			}
		}
		ArrayList<String> finalList = new ArrayList<String>();
		for (String s: results) {
			Critter critter;
			try {
				Class<?> tClass = Class.forName(myPackage + "." + s);
				critter = (Critter) tClass.newInstance();
				finalList.add(s);
			} catch (ClassCastException | ClassNotFoundException | NoClassDefFoundError | InstantiationException | IllegalAccessException e) {
				// placeholder
			}
		}
		
		return finalList;		
	}


	
}
