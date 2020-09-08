package view;

import controller.ManagerController;
import controller.PlayerController;
import controller.TeamController;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.Manager;
import models.Player;
import models.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 17/04/2020
 */

public class ListingTab extends Tab{
    private VBox vBox;
    private GridPane grid;
    private FlowPane pane;
    private TextField playerSrch;
    private Label managerList,playersListed,srchPlayer;
    private ListView managerListView,searchedList;
    private Button btnOrderbyStar,getBtnOrderbyName,search,updateList;
    private HBox hbBtnManager,hbSearch;
    private ManagerController managerController=new ManagerController();
//    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
//    private EntityManager em = emf.createEntityManager();
    private PlayerController playerController=new PlayerController();
    private List<Team> resTeam;
    private List<Manager> res;
    private TeamController tControll=new TeamController();
    private List <Manager> m;
    private List<Player> result;
    private List<Manager> manager;


    public ListingTab(){
        setClosable(false);
        setText("Lists from my League");

        pane = new FlowPane();
        grid=new GridPane();
        managerListView= new ListView();
        hbBtnManager=new HBox(13);
        vBox = new VBox();
        hbSearch=new HBox();

        managerListView.setPrefHeight(200);
        managerListView.setPrefWidth(650);

        managerList=new Label("Managers in database:");
        managerList.setFont(Font.font ("Verdana", 20));
        managerList.setTextFill(Color.web("#FFFFFF"));
        managerList.setStyle("-fx-background-color:#408616");

        res=managerController.findManagers();

//        em.getTransaction().begin();
//        resTeam = em.createQuery("select teamColour from Team teamColour", Team.class).getResultList();
//        System.out.println(resTeam);
//        em.getTransaction().commit();

        resTeam=tControll.getTeams();

        btnOrderbyStar=new Button("Order by stars");
        getBtnOrderbyName=new Button("Order by first name");
        updateList=new Button("Update list");

        updateList.setOnAction(e->{
            res=managerController.findManagers();

            managerListView.getItems().clear();
            for(int i=0;i<res.size();i++){
                managerListView.getItems().add(res.get(i));
            }
        });

        for(int i=0;i<res.size();i++){
            managerListView.getItems().add(res.get(i));
        }

        managerListView.setStyle("-fx-font-size: 15; -fx-font-family: Courier New;");


        btnOrderbyStar.setOnAction(e -> {
           m=managerController.sortStar(res);
            managerListView.getItems().clear();
            for(int i=0;i<m.size();i++){
                managerListView.getItems().add(m.get(i));
            }
        });

        getBtnOrderbyName.setOnAction(e -> {
            m=managerController.sortName(res);
            managerListView.getItems().clear();
            for(int i=0;i<m.size();i++){

                managerListView.getItems().add(m.get(i));
            }
        });


        grid.add(managerListView,0,1);
        hbBtnManager.getChildren().addAll(managerList,btnOrderbyStar,getBtnOrderbyName,updateList);
        grid.add(hbBtnManager,0,0);

        srchPlayer=new Label("Search Player");
        srchPlayer.setFont(Font.font ("Verdana", 20));
        srchPlayer.setTextFill(Color.web("#FFFFFF"));
        srchPlayer.setStyle("-fx-background-color:#408616");

        search=new Button("Search");
        playerSrch=new TextField();

        searchedList=new ListView();

        searchedList.setPrefHeight(150);
        searchedList.setPrefWidth(650);

        searchedList.setStyle("-fx-font-size: 15; -fx-font-family: Courier New;");

        search.setOnAction(e->{

            searchedList.getItems().clear();

            result=playerController.findPlayerByFirstName(playerSrch.getText());

            if(result.size()==0){
                searchedList.getItems().add("Not found");
            }else{
                manager=managerController.findManagerByTeam(result.get(0).getTeamColour().toString());

                for(int i=0;i<result.size();i++){
                    searchedList.getItems().add(result.get(i));
                }
                searchedList.getItems().add("Manager for the Player:");

                searchedList.getItems().add(manager.get(0));

            }
        });

        hbSearch.getChildren().addAll(srchPlayer,playerSrch,search);
        hbSearch.setPadding(new Insets(20, 20, 0, 2));
        grid.add(hbSearch,0,3);
        grid.add(searchedList,0,4);

        vBox.getChildren().addAll(grid);
        vBox.setPadding(new Insets(50, 20, 0, 2));
        pane.setStyle(" -fx-background-color: #719e16;");
        pane.getChildren().add(vBox);

        setContent(pane);
    }


}
