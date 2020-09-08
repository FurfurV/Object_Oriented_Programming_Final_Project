package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 16/04/2020
 */
public class PopupLabels {
    private static Label teamCol,fNameL,mNameL,lNameL,phoneL,emailL;
//    private static GridPane grid;

    public static GridPane PopupLabels(GridPane grid,String color){

        teamCol= new Label("Team colour:");
        setStyle(teamCol,color);
        grid.add(teamCol,0,0);

        fNameL = new Label("First name:");
        setStyle(fNameL,color);
        grid.add(fNameL, 0, 1);

        mNameL = new Label("Middle name:");
        setStyle(mNameL,color);
        grid.add(mNameL, 0, 2);

        lNameL = new Label("Last name:");
        setStyle(lNameL,color);
        grid.add(lNameL, 0, 3);

        phoneL = new Label("Phone:");
        setStyle(phoneL,color);
        grid.add(phoneL, 0, 4);

        emailL = new Label("Email:");
        setStyle(emailL,color);
        grid.add(emailL, 0, 5);

        return grid;
    }

    public static void setStyle(Label label,String color){
        label.setFont(Font.font("Calibri", 28));
        label.setTextFill(Color.web("#FFFFFF"));
        label.setStyle(color);

    }
}
