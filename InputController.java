/* CRITTERS GUI <InputController.java>
 * EE422C Project 4b submission by
 * Replace <...> with your actual data.
 * Stephen Ma
 * szm99
 * 16480
 * Slip days used: <1>
 * Eric Su
 * es25725
 * 16475
 * Slip days used: <2>
 * Fall 2016
 */
// InputController.java


package assignment5;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for main user input menu.
 *
 * @author ericsu
 */
public class InputController implements Initializable {

    private static String myPackage;
    private static ScheduledService animation;
    private static boolean isAnimationRunning = false;

    // Inject the components
    @FXML
    private Button exitProgram;

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

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3", "4", "5", "10", "50", "100");
        ObservableList<String> steps = FXCollections.observableArrayList("1", "2", "3", "4", "5", "10", "50", "100",
                "500");
        ObservableList<String> animSteps = FXCollections.observableArrayList("1", "2", "5");
        ObservableList<String> namesOfCritter = FXCollections
                .observableArrayList(InputController.fetchCritterClasses());
        addCritterCountMenu.setItems(list);
        addCritterChoiceMenu.setItems(namesOfCritter);
        stepChoiceMenu.setItems(steps);
        stepChoiceMenu.setValue("1");
        // animationInit(Integer.parseInt(selectAnimationMenu.getValue()));
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

    public void killProgram() {
        Platform.exit();
    }

    public void animationInit(int times) {
        // animation = new ScheduledService() {
        // @Override
        // protected Task createTask() {
        // for (int i = 0; i < times; i++) {
        // try {
        // Critter.worldTimeStep();
        // Critter.displayWorld();
        // } catch (InvalidCritterException e) {
        // //foo
        // }
        // }
        // return null;
        // }
        // };
        // animation.setPeriod(Duration.seconds(1));
    }

//	public void animationHandler() {
//		if (isAnimationRunning) {
//			stopAnimation();
//		} else {
//			startAnimation();
//		}
//	}

//	private void startAnimation() {
//		int animSpeed = Integer.parseInt(selectAnimationMenu.getValue());
//
//		// Disable buttons
//		for (Node n : Main.mainMenu.getChildren()) {
//			String ID = n.getId();
//			if (ID != null) {
//				if (ID.equals("setAnimateButton")) {
//					Button b = (Button) n;
//					b.setText("Stop");
//				} else {
//					n.setOpacity(.5);
//					n.setDisable(true);
//				}
//			}
//		}
//		
//		// Begin animation
//		
//	}

    private void stopAnimation() {
        animation.cancel();
        for (Node n : Main.mainMenu.getChildren()) {
            if (n.getId().equals("setAnimateButton")) {
                Button b = (Button) n;
                b.setText("Start");
            } else {
                n.setOpacity(0.0);
                n.setDisable(false);
            }
        }
    }

    /**
     * Parse all files in the directory for only the Critter classes
     *
     * @return ArrayList of Critter classes
     */
    public static ArrayList<String> fetchCritterClasses() {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
        ArrayList<String> results = new ArrayList<String>();
//		 File folder = new File("./assignment5");
        File folder = new File("./src/" + myPackage);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".java")) {
                String name = listOfFiles[i].getName();
                results.add(name.substring(0, name.length() - 5));
            }
        }
        ArrayList<String> finalList = new ArrayList<String>();
        for (String s : results) {
            try {
                Class<?> tClass = Class.forName(myPackage + "." + s);
                @SuppressWarnings("unused")
                Critter critter = (Critter) tClass.newInstance();
                finalList.add(s);
            } catch (ClassCastException | ClassNotFoundException | NoClassDefFoundError | InstantiationException
                    | IllegalAccessException e) {
                // placeholder
            }
        }

        return finalList;
    }

}
