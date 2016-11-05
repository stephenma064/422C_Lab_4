
package assignment4;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
	static String testOutputString;
	static GridPane grid = new GridPane();


	@Override
	public void start(Stage primaryStage) {
		try {			
            for (int i = 0; i < Params.world_width; i++) {
                ColumnConstraints column = new ColumnConstraints(700/Params.world_width);
                grid.getColumnConstraints().add(column);
            }
            for (int i = 0; i < Params.world_height; i++) {
                RowConstraints row = new RowConstraints(700/Params.world_height);
                grid.getRowConstraints().add(row);
            }
            for (int i = 0; i < Params.world_width; i++) {
                for (int j = 0; j < Params.world_height; j++) {
                    grid.add(new AnchorPane(),i,j);
                }
            }
            for (Node p: grid.getChildren()) {
                p.setStyle("-fx-border-color : red");
                p.prefHeight(700/Params.world_height);
                p.prefWidth(700/Params.world_width);
            }

			Scene scene = new Scene(grid, 700, 700);
//            scene.getStylesheets().add("assignment4/world.css");
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			// Paints the icons.
			Stage secondStage = new Stage();
			secondStage.setTitle("2nd");
			StackPane pane = new StackPane();
			Scene secondScene = new Scene(pane, 200, 200);
			secondStage.setScene(secondScene);
//			secondStage.show();
			
			InputController mainController = new InputController();
		} catch(Exception e) {
			e.printStackTrace();		
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

