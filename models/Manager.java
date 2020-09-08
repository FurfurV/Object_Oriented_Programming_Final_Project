package models;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 30/03/2020
 */
@Entity
@Table(name = "manager", schema = "league")
public class Manager extends Person {
    @TableGenerator(name = "manager_gen", table = "id_gen",
            pkColumnName = "gen_name",
            pkColumnValue = "Emp_Gen",
            valueColumnName = "gen_val",
            initialValue=0,
            allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "manager_gen")
    private int id;
    private String birthDate;
    private Integer star;

//    @ManyToOne
//    @JoinTable(name="team",joinColumns = @JoinColumn(name = "teamColour"))

//    @ManyToOne
//    @JoinColumn(name="teamColour")
    @OneToOne
    @JoinColumn(name="teamColour")
    private Team teamColour;

    public Manager(){

    }

    public Manager (int id, String firstName, String middleName, String lastName, String phone, String email,String birthDate,int star,Team teamColour){
        super(firstName,middleName,lastName,phone,email);
        setId(id);
        setBirthDate(birthDate);
        setStar(star);
        setTeamColour(teamColour);
    }


    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Team getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(Team teamColour) {
        this.teamColour = teamColour;
    }

    public String toString(){
        return super.toString()+" ⭐"+star+", DoB:"+birthDate+" team:"+teamColour;
    }

//    @OneToOne
//    @Column(name = "teamColour")

//    public Team getTeamColour() {
//        return myTeam;
//    }

//    @OneToOne
//    @JoinColumn(name = "teamColour")
//    public void setTeamColour(Team t) {
//        if (getTeamColour() == null) {
//            this.myTeam = t;
//        } else if (getTeamColour() != null) {
//            if(myTeam.getMyManager()==null){
//                this.myTeam = t;
//            }else{
//                System.out.println("cant add");
//            }
//        }
//    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Manager that = (Manager) o;
//        return person == that.person &&
//                Objects.equals(birthDate, that.birthDate) &&
//                Objects.equals(star, that.star);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(person, birthDate, star);
//    }
}
