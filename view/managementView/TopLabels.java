package view.managementView;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 Mar 2020
 */


public class TopLabels {
    private Label team, player, manager;

    public GridPane topLabels(GridPane grid){
        var spacer=new Region();
        spacer.setPrefWidth(40);

        team = new Label("Add new team:");
        setStyle(team);
        grid.add(team, 0, 1);

        player = new Label("Add new Player: ");
        setStyle(player);
        grid.add(player, 0, 2);

        manager = new Label("Add new Manager ");
        setStyle(manager);
        grid.add(manager, 0, 3);

        return grid;

    }

    private void setStyle(Label l){
        l.setFont(Font.font("Verdana", 20));
        l.setTextFill(Color.web("#FFFFFF"));
        l.setStyle("-fx-background-color:#408616");
    }

}
