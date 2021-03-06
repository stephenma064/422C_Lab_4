/* CRITTERS GUI <Main.java>
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
package assignment5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	static String testOutputString;
	static GridPane grid = new GridPane();
    static GridPane mainMenu;

	@Override
	public void start(Stage primaryStage) {
		try {
            for (int i = 0; i < Params.world_width; i++) {
                ColumnConstraints column = new ColumnConstraints(700 / Params.world_width);
                grid.getColumnConstraints().add(column);
            }
            for (int i = 0; i < Params.world_height; i++) {
                RowConstraints row = new RowConstraints(700 / Params.world_height);
                grid.getRowConstraints().add(row);
            }
            for (int i = 0; i < Params.world_width; i++) {
                for (int j = 0; j < Params.world_height; j++) {
                    grid.add(new StackPane(), i, j);
                }
            }
            for (Node p : grid.getChildren()) {

                p.setStyle("-fx-border-color : red;"+"-fx-border-width: 1px");
                p.prefHeight(700 / Params.world_height);
                p.prefWidth(700 / Params.world_width);
            }
            Critter.displayWorld();

            Scene scene = new Scene(grid, 700, 700);
            primaryStage.setTitle("Critter World");
            primaryStage.setScene(scene);

            primaryStage.show();

            // Paints the icons.
            Stage secondStage = new Stage();
            secondStage.setTitle("2nd");
            StackPane pane = new StackPane();
            Scene secondScene = new Scene(pane, 200, 200);
            secondStage.setScene(secondScene);

            
			Stage menu = new Stage();
        	mainMenu = FXMLLoader.load(Main.class.getResource("InputController.fxml"));
            Scene scene1 = new Scene(mainMenu);
            menu.setScene(scene1);
            menu.setTitle("Critter World");
            menu.show();
            
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            Stage stat = new Stage();
            GridPane statMenu = FXMLLoader.load(Main.class.getResource("StatController.fxml"));
            Scene statScene = new Scene(statMenu);
            stat.setTitle("Stats");	
            stat.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 450);
            stat.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 150);
            stat.setScene(statScene);
            stat.show();
            

		} catch(Exception e) {
			e.printStackTrace();		
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}

