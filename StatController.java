package assignment5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Stephen on 11/7/2016.
 */


public class StatController implements Initializable {
	
	@FXML
    private ChoiceBox<String> selectCritterStat;

    @FXML
    private Text critterStatText;
    
    @FXML
    private Button runStatsButton;

	
    static ByteArrayOutputStream testOutputString;
    static PrintStream old = System.out;
    
    public void runStats() {
    	String critter = selectCritterStat.getValue();
    	getStats(critter);
    }
    
    private void getStats(String critter) {
    	String myPackage = Critter.class.getPackage().toString().split(" ")[1];
        testOutputString = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(testOutputString);
        old = System.out;
        System.setOut(ps);
        List<Critter> results;
        try {
             results = assignment5.Critter.getInstances(critter);
        } catch (InvalidCritterException e) {
            results = null;
        }
        try {
	        Class<?> temp = Class.forName(myPackage + "." + critter);
	        Class<?>[] types = {List.class};
	        List<Critter> list = Critter.getInstances(critter);
	        Method m = temp.getMethod("runStats", types);
	        m.invoke(null, list);	        
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException 
        		| ClassNotFoundException | InvalidCritterException e) {
        	System.out.println("something wrong");
        }
        System.setOut(old);
        critterStatText.setText(testOutputString.toString());
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> namesOfCritter = FXCollections.observableArrayList(InputController.fetchCritterClasses());
		selectCritterStat.setItems(namesOfCritter);	
	}
}
