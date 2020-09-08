package view;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 11/04/2020
 */
import controller.PlayerController;
import controller.TeamController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import models.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;



public class PlayerPopUp {
    private static Button btnExit, btnAdd;
    private static Label goalsL,message;
    private static GridPane grid;
    private static TextField fName,mName,lName,phone,email,goals;
    private static RadioButton gYes, gNo;
    private static final ToggleGroup group = new ToggleGroup();
    private static RadioButton chk;
    private static ComboBox combo;
    private static PopupLabels pop;
    private static String color="-fx-background-color:#21848a";
    private static VBox layout;
    private static PlayerController pControll=new PlayerController();
    private static TeamController tControll=new TeamController();
    private static List<Team> res;


    public static void displayPlayerPopUp() {

        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add player popup");

        grid = new GridPane();
        grid.setPadding(new Insets(8, 8, 8, 8));

        res=tControll.getTeams();

        combo=new ComboBox(FXCollections.observableList(res));
        grid.add(combo,1,0);

        fName = new TextField();
        grid.add(fName, 1, 1);

        mName = new TextField();
        grid.add(mName, 1, 2);

        lName = new TextField();
        grid.add(lName, 1, 3);

        phone = new TextField();
        grid.add(phone, 1, 4);

        email = new TextField();
        grid.add(email, 1, 5);

        gYes = new RadioButton("Goalie");
        gYes.setUserData(true);
        gYes.setToggleGroup(group);
        gYes.setFont(Font.font("Calibri", 20));
        gYes.setTextFill(Color.web("#FFFFFF"));
        gYes.setStyle("-fx-background-color:#21848a");

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(combo.getValue() + " selected");
                    }
                };

        combo.setOnAction(event);
        message=new Label();
        setStyle(message);

        gNo = new RadioButton("Not a goalie");
        gNo.setToggleGroup(group);
        gNo.setFont(Font.font("Calibri", 20));
        gNo.setTextFill(Color.web("#FFFFFF"));
        gNo.setStyle("-fx-background-color:#21848a");

        grid.add(gYes, 0, 6);
        grid.add(gNo, 1, 6);

        grid.add(message,0,9);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

                chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                System.out.println("Selected Radio Button - " + chk.getText());

            }
        });

        goalsL = new Label("Goals:");
        goalsL.setFont(Font.font("Calibri", 28));
        goalsL.setTextFill(Color.web("#FFFFFF"));
        goalsL.setStyle("-fx-background-color:#21848a");
        grid.add(goalsL, 0, 7);
        goals = new TextField();
        grid.add(goals, 1, 7);

        btnAdd = new Button("Add");
        grid.add(btnAdd, 0, 8);

        btnAdd.setOnAction(e -> {

            String mess=pControll.addPlayer(combo.getValue().toString(),
                    email.getText(),
                    fName.getText(),
                    goals.getText(),
                    lName.getText(),
                    mName.getText(),
                    phone.getText(),
                    chk.getText());
            message.setText(mess);
        });


        btnExit = new Button("Close this pop up window");
        btnExit.setOnAction(e -> popupwindow.close());
        grid.add(btnExit, 1, 7);

        String color="-fx-background-color:#21848a";
        grid=pop.PopupLabels(grid,color);


        layout = new VBox(10);
        layout.getChildren().addAll(grid, btnExit);

        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url(/images/football.jpg); -fx-background-repeat: stretch;");

        Scene scene1 = new Scene(layout, 500, 550);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    private static void setStyle(Label l){
        l.setFont(Font.font("Calibri", 28));
        l.setTextFill(Color.web("#FFFFFF"));
        l.setStyle(color);
    }

}
