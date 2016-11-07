package assignment5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Stephen on 11/7/2016.
 */


public class StatController {

    static ByteArrayOutputStream testOutputString;
    static PrintStream old = System.out;

    public StatController() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Stage statStage = new Stage();
        GridPane grid = new GridPane();
        statStage.setTitle("Stats");
        Text t = new Text(getStats());
        Button b = new Button();
        b.setText("Quit");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        grid.getChildren().add(t);
        grid.getChildren().add(b);
        Scene temp = new Scene(grid,400,100);
        statStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 450);
        statStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 150);
        statStage.setScene(temp);
        statStage.show();
    }

    private String getStats() {
        testOutputString = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(testOutputString);
        old = System.out;
        System.setOut(ps);
        List<Critter> xd;
        try {
             xd = assignment5.Critter.getInstances("Craig");
        } catch (InvalidCritterException e) {
            xd = null;
            System.out.println("stop");
        }
        Craig.runStats(xd);
        System.setOut(old);
        return testOutputString.toString();
    }
}
