package models;

import javax.persistence.*;
import java.sql.Date;


/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 30/03/2020
 */

/** <h1> This class is a subclass for Person and contains player details </h1>
 *  The player can be a goalie and shows how many goals he defended.
 *  If hes a player, then shows how many goals he/she scored.
 */

@Entity
/**
 * It is an etity for jpa, see entity for more details
 * @see Entity
 */
public class Player extends Person{
    @TableGenerator(name = "player_gen", table = "id_gen",
            pkColumnName = "gen_name",
            pkColumnValue = "Emp_Gen",
            valueColumnName = "gen_val",
            initialValue=0,
            allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "player_gen")
    private int id;
    private Integer goals;
    private Boolean goalie;

    @ManyToOne
    @JoinColumn(name="teamColour")
    private Team teamColour;

    /**
     * default constructor
     */
    public Player(){

    }

    /**
     *
     * @param id an int that jpa generates as our id for the player
     * @param firstName string representing the first name
     * @param middleName string for our middle name
     * @param lastName string for our last name
     * @param phone a string for our phone number
     * @param email string for the email address
     * @param goalie boolean that shows if our player is a goalie or not
     * @param goals int for how many goals scored/defended
     * @param teamColour shows which team out player is in
     */
    public Player (int id, String firstName,String middleName,String lastName, String phone, String email,Boolean goalie,int goals,Team teamColour){
        super(firstName,middleName,lastName,phone,email);
        setId(id);
        setGoalie(goalie);
        setGoals(goals);
        setTeamColour(teamColour);
    }

    /**
     * Gets the id for our player
     * @return an int for our id
     */
    public int getId() {
        return id;
    }

    /**
     * sets the id for our player
     * @param id int representing the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets the goal number scored/defended
     * @return int representing out goals defended/scored
     */

    public Integer getGoals() {
        return goals;
    }

    /**
     * sets the number of goals scored/defended for the player
     * @param goals int representing out goals defended/scored
     */
    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    /**
     * gets the value if our player is a goalie or not
     * @return boolean if the player is goalie false if not
     */
    public Boolean getGoalie() {
        return goalie;
    }

    /**
     * sets the value for our player if he/she is a goalie
     * @param goalie booelan for being goalie or not
     */

    public void setGoalie(Boolean goalie) {
        this.goalie = goalie;
    }

    /**
     * gets the team colour for our player
     * @return Team object with the colour
     */
    public Team getTeamColour() {
        return teamColour;
    }

    /**
     * sets the team for our player
     * @param teamColour Team that is our player in
     */
    public void setTeamColour(Team teamColour) {
        this.teamColour = teamColour;
    }

    /**
     * A function to be called when we want to print player details
     * @return String represeting our player details
     */

    public String toString(){
        return super.toString()+" goals:"+goals+" goalie:"+goalie+" ";
    }

}
