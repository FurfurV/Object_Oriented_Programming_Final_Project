package models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 11/04/2020
 */
//@Entity
//@Table(name = "league", schema = "league")
public class League {

//    @Column(name="colour" , nullable = false, length = 50)
//    @Id
    private String colour;

//
//    @OneToMany
    private List<Team> myTeams=new ArrayList<>();

    public League() {
    }


    public void addTeam(Team t){
        myTeams.add(t);
    }

    public void removeTeam(Team t){
        myTeams.remove(t);
    }

    public Team get(int i){
        return myTeams.get(i);

    }


//    public ArrayList<Team> getList(){
//        return myTeams;
//    }


    public List<Team> getMyTeams() {
        return myTeams;
    }

    public void printTeamsInLeague(){
        for(int i=0;i<myTeams.size();i++){
            System.out.println(get(i)+"\n");
        }

    }
}
