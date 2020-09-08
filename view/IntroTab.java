package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 16/04/2020
 */
public class IntroTab extends Tab {
    private VBox vBox;
    private GridPane grid;

    public IntroTab(){
        setClosable(false);
        setText("My intro");

        FlowPane pane = new FlowPane();

        Label l1=new Label("Comp 7013-OOP project by Viktoria Cseke." +
                "\nA Soccer League application");

        l1.setFont(Font.font("Calibri", 28));
        l1.setTextFill(Color.web("#FFFFFF"));

        vBox = new VBox(5,l1);
        vBox.setPadding(new Insets(80, 30, 0, 80));
//        vBox.setAlignment(Pos.CENTER);
        pane.setStyle(" -fx-background-color: #2f9e0b;");

        pane.getChildren().add(vBox);

        setContent(pane);

    }

}
