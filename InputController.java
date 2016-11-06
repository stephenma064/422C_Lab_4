// InputController.java

package assignment5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Controller for main user input menu.
 * @author ericsu
 *
 */
public class InputController {
	private static final int OFFSET = 5;
	
	private static final int SEED_ROW_INDEX = 5;
	private static final int STEP_ROW_INDEX = 10;
	
	public InputController() {
		Stage inputStage = new Stage();
		
		// Main root pane, configure spacing between components and other features
		Label title = new Label("Critter World");
		title.setFont(new Font("System Bold", 25));
		GridPane inputPane = new GridPane();
		inputPane.setPadding(new Insets(10, 10, 10, 10));
		inputPane.setVgap(5);
		inputPane.setHgap(5);		
		inputStage.setTitle("CritterWorld");
		inputPane.getChildren().add(title);
		Scene inputScene = new Scene(inputPane, 500, 500);
		
		
		// Create Set Seed components
		Label setSeedLabel = new Label("Set Seed: ");
		TextField seedField = new TextField();
		Button setSeedButton = new Button("Set");
		setSeedButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				try {
					Integer.parseInt(seedField.getText());
				} catch (NumberFormatException f) {
					final Stage popup = new Stage();
					StackPane pane = new StackPane();
					Scene popScene = new Scene(pane, 50, 50);
					Label label = new Label("Invalid input");
					pane.getChildren().add(label);
					popup.setScene(popScene);
					popup.show();
				}
				Critter.setSeed(Long.valueOf(seedField.getText()).longValue());
			}
		});
		GridPane.setConstraints(setSeedLabel, 0, InputController.SEED_ROW_INDEX);
		GridPane.setConstraints(seedField, 1, InputController.SEED_ROW_INDEX);
		GridPane.setConstraints(setSeedButton, 3, InputController.SEED_ROW_INDEX);
		inputPane.getChildren().addAll(setSeedLabel, seedField, setSeedButton);
		
		// Create Step components
		Label stepLabel = new Label("Step: ");	
		ObservableList<String> options = FXCollections.observableArrayList(
	        "Option 1",
	        "Option 2",
	        "Option 3"
		);
		Button stepButton = new Button("Step");
		ComboBox<String> stepChoices = new ComboBox<String>(options);
		GridPane.setConstraints(stepLabel, 0, InputController.STEP_ROW_INDEX);
		GridPane.setConstraints(stepChoices, 1, InputController.STEP_ROW_INDEX);
		GridPane.setConstraints(stepButton, 2, InputController.STEP_ROW_INDEX);
		inputPane.getChildren().addAll(stepLabel, stepChoices, stepButton);
		
		
		
		// Add the scene to the main stage and display it
		inputStage.setScene(inputScene);
		inputStage.show();
	}
	
	
}
