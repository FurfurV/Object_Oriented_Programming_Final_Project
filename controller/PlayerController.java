package controller;

import models.League;
import models.Player;
import models.Team;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 18/04/2020
 */
public class PlayerController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");

    public String addPlayer(String colour,String email,String fName,String goals,String lName,String mName,String phone,String goalie){
        Player p = new Player();
        Team t = new Team();
        League league=new League();

//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
//        EntityManager em = emf.createEntityManager();
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();

        t.setTeamColour(colour);
        txn.begin();

        league.addTeam(t);

        p.setTeamColour(t);
        p.setEmail(email);
        p.setFirstName(fName);
        p.setGoals(Integer.parseInt(goals));
        p.setLastName(lName);
        p.setMiddleName(mName);
        p.setPhone(phone);

        if (goalie == "Goalie") {
            p.setGoalie(true);
            System.out.println("true");
        } else {
            p.setGoalie(false);
        }
        System.out.println(p.toString() + p.getTeamColour());
        em.persist(p);
        txn.commit();
        return "Added";
    }


    public void updatePlayer(int id,String fname,String mname,String lname,String phone,String email,String goals,String goalie){
        EntityManager em = emf.createEntityManager();
        Player p =em.find(Player.class,id);
        em.getTransaction().begin();
        p.setFirstName(fname);
        p.setMiddleName(mname);
        p.setLastName(lname);
        p.setPhone(phone);
        p.setEmail(email);
        p.setGoals(Integer.parseInt(goals));
        p.setGoalie(Boolean.parseBoolean(goalie));
        System.out.println("Player after updation :- " + p);
        em.getTransaction().commit();
    }

    public void remPlayer(int id){
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class,id);
        em.getTransaction().begin();
        em.remove(player);
        em.getTransaction().commit();
    }

    public List<Player> findPlayerByColor(String color){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Player e WHERE e.teamColour = :colorName");
        query.setParameter("colorName", new Team(color));
        List<Player> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        return resultList;
    }

    public List<Player> findPlayerByFirstName(String name){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Player e WHERE e.firstName = :name");
        query.setParameter("name",name);
        List<Player> result = query.getResultList();
        result.forEach(System.out::println);
//        em.close();

        return result;
    }

    public List <Player> sortGoals(List <Player> results){
        Collections.sort(results, Comparator.comparingInt(Player::getGoals));
        return results;
    }

    public List <Player> sortName(List <Player> results){
        Collections.sort(results, Comparator.comparing(Player::getFirstName));
        return results;
    }



}
