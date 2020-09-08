package view;

//import controller.Crasher;
//import controller.ManagementController;

import controller.PlayerController;
import controller.TeamController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.League;
import models.Player;
import models.Team;
import view.managementView.TopLabels;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import model.Activity;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 11/04/2020
 */
public class Management {
    private TextField teamTextField;
    private TextField fNameTextField, mNameTxtF, lNameTxtF, emailTxtF, phoneTxtF, goalsTxtF, goalieTxtF;
    private Label teamsList, playersListed;
    private GridPane buttonsPane, buttonsBottomPane, grid, textFieldPad, tabsPane;
    private ListView teamsListView, playerListView;
    private TabPane tabPane = new TabPane();
    private Tab tabMang;
    private Button btnAddTeam, btnAddPlayer, btnAddManager, btnRem, btnSortName, btnSortGoals, btnEdit;
    private HBox hbBtnAddTeam, hbBtnAddPlayer, hbBtnAddManager, hbMid, hbEdit;
    private VBox vBox;
    private Scene scene;
    private int idNum, goals;
    private Boolean goalie;
    private String fName, mName, lName, phone, email;
    private List<Player> results;
    private PlayerController pControll = new PlayerController();
    private TopLabels t = new TopLabels();
    private TeamController tControll = new TeamController();
    private List<Team> res;
    private List<Team> res2;

    public Scene management(Stage primaryStage){
        League league=new League();

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ padding added ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        var spacer = new Region();
        var spacer2 = new Region();

        spacer.setPrefWidth(40);
        spacer2.setPrefWidth(20);
        VBox.setVgrow(spacer, Priority.ALWAYS);
        VBox.setVgrow(spacer2, Priority.ALWAYS);

        buttonsPane=new GridPane();
        buttonsPane.setHgap(10);
        buttonsPane.setPadding(new Insets(0,0,2,5));

        buttonsBottomPane=new GridPane();
        buttonsBottomPane.setHgap(2);
        buttonsBottomPane.setPadding(new Insets(0,5,2,0));

        grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(5);
        grid.setPadding(new Insets(5, 5, 0, 5));

        textFieldPad=new GridPane();
        textFieldPad.setHgap(5);
        textFieldPad.setPadding(new Insets(5,5,5,5));

        tabsPane=new GridPane();
        tabsPane.setPadding(new Insets(5,5,0,5));

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ tabs▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        tabMang = new Tab("Soccer management");
        tabMang.setClosable(false);

        tabPane.getTabs().add(new IntroTab());
        tabPane.getTabs().add(tabMang);
        tabPane.getTabs().add(new ListingTab());

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ set tab size ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        tabPane.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            tabPane.setTabMinWidth(tabPane.getWidth() / 4);
            tabPane.setTabMaxWidth(tabPane.getWidth() / 4);
        });

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ the top labels in the application▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        grid=t.topLabels(grid);

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ the top fields in the application▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        teamTextField = new TextField();
        grid.add(teamTextField, 1, 1);


        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ buttons under the app ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);


        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ middle textfield▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

//        em.getTransaction().begin();
//        List<Team> res = em.createQuery("select teamColour from Team teamColour", Team.class).getResultList();
//        //System.out.println(res);
//        em.getTransaction().commit();
        res=tControll.getTeams();

        teamsListView = new ListView();
        teamsListView.setPrefHeight(100);
        teamsListView.setPrefWidth(200);

        teamsList=new Label("Teams in database:");
        teamsList.setFont(Font.font ("Verdana", 20));
        teamsList.setTextFill(Color.web("#FFFFFF"));
        teamsList.setStyle("-fx-background-color:#408616");

        printListTeam(res);

        textFieldPad.add(teamsList,0,0);
        textFieldPad.add(teamsListView,0,1);

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ middle textfield for players ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        EventHandler<ActionEvent> selectTeam =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        System.out.println(teamsListView.getSelectionModel() + " selected");
                    }
                };

        playerListView=new ListView();

        playerListView.setPrefHeight(150);
        playerListView.setPrefWidth(650);

        playersListed=new Label("Players");
        playersListed.setFont(Font.font ("Verdana", 20));
        playersListed.setTextFill(Color.web("#FFFFFF"));
        playersListed.setStyle("-fx-background-color:#408616");

        //Code snippet from stackoverflow ,
        // https://stackoverflow.com/questions/12459086/how-to-perform-an-action-by-selecting-an-item-from-listview-in-javafx-2

        teamsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Team>() {

            @Override
            public void changed(ObservableValue<? extends Team> observable, Team oldValue, Team newValue) {

                results=pControll.findPlayerByColor(newValue.toString());

                playersListed.setText("Players in the "+newValue+" team.");
                System.out.println("Selected item: " + newValue);

                playerListView.getItems().clear();
                playerListView.setStyle("-fx-font-size: 15; -fx-font-family: Courier New;");

                results=pControll.sortName(results);
                printListPlayer(results);
            }
        });

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚bottom textfields for edit ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        fNameTextField=new TextField();
        mNameTxtF=new TextField();
        lNameTxtF=new TextField();
        phoneTxtF=new TextField();
        phoneTxtF.setPrefWidth(130);
        emailTxtF=new TextField();
        emailTxtF.setPrefWidth(210);
        goalsTxtF=new TextField();
        goalsTxtF.setPrefWidth(100);
        goalieTxtF=new TextField();

        playerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {

            @Override
            public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {

                if(newValue==null){
                    System.out.println("clear");
                }else{
                    System.out.println("Selected item: " + newValue.getId());
                    idNum=newValue.getId();
                    fName=newValue.getFirstName();
                    mName=newValue.getMiddleName();
                    lName=newValue.getLastName();
                    phone=newValue.getPhone();
                    email=newValue.getEmail();
                    goals=newValue.getGoals();
                    goalie=newValue.getGoalie();

                    //set texts for the textfields for editing
                    fNameTextField.setText(fName);
                    mNameTxtF.setText(mName);
                    lNameTxtF.setText(lName);
                    phoneTxtF.setText(phone);
                    emailTxtF.setText(email);
                    goalsTxtF.setText(String.valueOf(goals));
                    goalieTxtF.setText(String.valueOf(goalie));
                }
            }
        });

        btnSortName=new Button("Sort by name");
        btnSortGoals=new Button("Sort by goals");
        hbMid=new HBox(10);

        hbMid.setPadding(new Insets(20, 20, 0, 2));

//▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ bottom buttons ▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        btnRem = new Button("Remove Player");
        btnEdit=new Button("Update");

        hbMid.getChildren().addAll(playersListed,btnSortName,btnSortGoals,spacer2,btnRem);
        textFieldPad.add(hbMid,0,2);
        textFieldPad.add(playerListView,0,3);

        hbEdit=new HBox();
        hbEdit.getChildren().addAll(fNameTextField,mNameTxtF,lNameTxtF,phoneTxtF,emailTxtF,goalsTxtF,goalieTxtF);
        hbEdit.setPadding(new Insets(20, 20, 5, 2));

        buttonsBottomPane.add(hbEdit, 0, 1);
        buttonsBottomPane.add(btnEdit, 0, 2);

        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ button actions▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        btnSortGoals.setOnAction(e-> {
            playerListView.getItems().clear();
            results=pControll.sortGoals(results);
            printListPlayer(results);

        });

        btnSortName.setOnAction(e-> {
            playerListView.getItems().clear();
            results=pControll.sortName(results);
            printListPlayer(results);

        });

        btnRem.setOnAction(e-> {
                pControll.remPlayer(idNum);
                playerListView.getItems().clear();
                playerListView.getItems().add("Player removed.");

        });

        btnEdit.setOnAction(e-> {
            pControll.updatePlayer(idNum,
                    fNameTextField.getText(),
                    mNameTxtF.getText(),
                    lNameTxtF.getText(),
                    phoneTxtF.getText(),
                    emailTxtF.getText(),
                    goalsTxtF.getText(),
                    goalieTxtF.getText());

            playerListView.getItems().clear();
            playerListView.getItems().add("Updated player");
        });


        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚  top buttons▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚
        spacer.setPrefWidth(40);

        btnAddTeam = new Button("Add");
        hbBtnAddTeam = new HBox(3);
        hbBtnAddTeam.getChildren().add(btnAddTeam);
        grid.add(hbBtnAddTeam, 3, 1);

        Team t=new Team();

        btnAddTeam.setOnAction(e -> {
            tControll.addTeam(t,league,teamTextField.getText());
            System.out.println(t+"prints t");
            System.out.println(league+"prints league");
            System.out.println(teamTextField.getText()+"prints textfield");

            teamsListView.getItems().clear();
            res2=tControll.getTeams();
            List<Team> resTeam=tControll.sortColour(res2);

            printListTeam(resTeam);
        });

        btnAddPlayer = new Button("Add Player");
        hbBtnAddPlayer = new HBox(3);
        hbBtnAddPlayer.getChildren().add(btnAddPlayer);
        grid.add(hbBtnAddPlayer, 1, 2);

        btnAddManager = new Button("Add Manager");
        hbBtnAddManager = new HBox(3);
        hbBtnAddManager.getChildren().add(btnAddManager);
        grid.add(hbBtnAddManager, 1, 3);


        //▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ top buttons for popups▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        btnAddPlayer.setOnAction(e -> PlayerPopUp.displayPlayerPopUp());
        btnAddManager.setOnAction(e -> ManagerPopUp.displayManagerPopUp());

//▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚ scene▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚▚

        vBox = new VBox(5,grid,buttonsPane,textFieldPad,buttonsBottomPane);
        vBox.setStyle("-fx-background-image: url(/images/pitch_green.jpg); -fx-background-repeat: stretch;");
        vBox.setPadding(new Insets(50, 20, 0, 2));

        tabMang.setContent(vBox);
        scene = new Scene(tabPane, 700, 650);

        return scene;
    }

    public void printListTeam(List<Team> res){

        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
            teamsListView.getItems().add(res.get(i));
        }
    }
    public void printListPlayer(List<Player> results){
        for(int i=0;i<results.size();i++){
            playerListView.getItems().add(results.get(i));
        }

    }
}
