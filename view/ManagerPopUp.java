package view;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 11/04/2020
 */

import controller.ManagerController;
import controller.TeamController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import models.League;
import models.Manager;
import models.Player;
import models.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class ManagerPopUp {

    private static Label starL,dateL,message;
    private static Button btnExit;
    private static GridPane grid;
    private static VBox layout;
    private static ComboBox combo;
    private static TextField fName,mName,lName,phone,email,star;
    private static DatePicker datePicker;
    private static PopupLabels pop;
    private static String color="-fx-background-color:#814e8a";
    private static Button btnAdd;
    private static ComboBox comboBox;
    private static ManagerController managerController=new ManagerController();
    private static TeamController tControll=new TeamController();
    private static List<Team> res;

    public static void displayManagerPopUp() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Add manager popup");

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

        starL= new Label("Stars:");
        setStyle(starL);
        grid.add(starL, 0, 6);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1", "2", "3", "4", "5", "6", "7","8","9","10"
                );
        comboBox = new ComboBox(options);
//        star= new TextField();
        grid.add(comboBox, 1, 6);

        message=new Label();
        setStyle(message);

        dateL = new Label("Date ");
        setStyle(dateL);

        grid.add(dateL, 0, 7);
        datePicker = new DatePicker();
        grid.add(datePicker,1,7);

        btnAdd = new Button("Add");
        grid.add(btnAdd, 0, 8);

        grid.add(message,0,9);

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(combo.getValue() + " selected");
                    }
                };

        combo.setOnAction(event);

        EventHandler<ActionEvent> starEvent =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(comboBox.getValue() + " selected");
                    }
                };

        combo.setOnAction(starEvent);

        btnAdd.setOnAction(e -> {

            String mess=managerController.addManager(combo.getValue().toString(),
                    email.getText(),
                    fName.getText(),
                    comboBox.getValue().toString(),
                    lName.getText(),
                    mName.getText(),
                    phone.getText(),
                    datePicker.getValue().toString());
            message.setText(mess);

        });

        btnExit = new Button("Close this pop up window");
        btnExit.setOnAction(e -> popupwindow.close());


        grid=pop.PopupLabels(grid,color);

        layout = new VBox(10);
        layout.getChildren().addAll(grid,btnExit);

        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-image: url(/images/fieldp.jpg); -fx-background-repeat: stretch;");

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
