package controller;

import models.League;
import models.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 18/04/2020
 */
public class TeamController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");

    public void addTeam(Team team, League league,String teamText){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");

        team.setTeamColour(teamText);
        team.printTeam();
        System.out.println(league.toString());

        /* Create EntityManager */
        EntityManager em = emf.createEntityManager();

        /* Persist entity */
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
    }

    public List<Team> sortColour(List <Team> res){
        Collections.sort(res, Comparator.comparing(Team::getTeamColour));
        return res;
    }

    public List <Team> getTeams(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Team> res = em.createQuery("select teamColour from Team teamColour", Team.class).getResultList();
        System.out.println(res);
        em.getTransaction().commit();

        return res;
    }
}
