package models;

import org.eclipse.persistence.annotations.Array;
import org.eclipse.persistence.annotations.ExcludeDefaultMappings;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 30/03/2020
 */
@Entity
@Table(name = "team", schema = "league")
public class Team {
    @Id
    private String teamColour;

    @OneToMany
    private List<Player> players=new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name="teamColour")
//    League league;

    @OneToMany
    private  List<Manager> myManager=new ArrayList<>();

//    @OneToOne
//            (cascade = CascadeType.ALL)
//    @JoinTable(name="manager", joinColumns =
//            { @JoinColumn(name = "id", referencedColumnName = "teamColour") })
//    private Manager myManager;


//
    public Team(){}

    public Team(String teamColour){
        setTeamColour(teamColour);
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String colour) {
        this.teamColour = colour;
    }

//    @OneToOne
//    public Manager getMyManager() {
//        return myManager;
//    }
//
//    public void setMyManager(Manager myManager) {
//        this.myManager = myManager;
//    }
//
//
    public List<Manager> getMyManager() {
        return myManager;
    }

    public void setMyManager(List<Manager> myManager) {
        this.myManager = myManager;
    }

    public void add(Player p){
        players.add(p);
    }

    public void remove(Player p){
        players.remove(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }



    //    public ArrayList<Player> getList()
//    {
//        return players;
//    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Team that = (Team) o;
//        return managerPerson == that.managerPerson &&
//                Objects.equals(colour, that.colour);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(colour, managerPerson);
//    }
    public String toString(){
        return teamColour;
    }

    public void printTeam(){
        System.out.println(toString()+"\n");
    }
}
