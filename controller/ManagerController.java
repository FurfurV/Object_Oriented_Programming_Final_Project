package controller;

import models.League;
import models.Manager;
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
public class ManagerController {

    public String addManager(String colour,String email,String fName,String star,String lName,String mName,String phone,String dob){
        Manager m = new Manager();
        Team t = new Team();
        League league=new League();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
//        EntityManager em = emf.createEntityManager();
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();



        List <Manager> m2=findManagerByTeam(colour);
        System.out.println(m2);

        if(m2.size()==0){
            t.setTeamColour(colour);
            txn.begin();

            league.addTeam(t);

            m.setTeamColour(t);
            m.setEmail(email);
            m.setFirstName(fName);
            m.setLastName(lName);
            m.setBirthDate(dob);
            m.setMiddleName(mName);
            m.setPhone(phone);
            m.setStar(Integer.parseInt(star));

            System.out.println(m.toString());
            em.persist(m);
            txn.commit();

            findManagers();

            return "added";

        }else{
            System.out.println("Cant add");
            return "cant add,team already has a manager";
        }
    }

    public static List<Manager> sortStar(List<Manager> res){
        Collections.sort(res, Comparator.comparing(Manager::getStar));
        return res;
    }

    public static List<Manager> sortName(List<Manager> res){
        Collections.sort(res, Comparator.comparing(Manager::getFirstName));
        return res;
    }

    public List<Manager> findManagerByTeam(String color){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Manager e WHERE e.teamColour = :color");
        query.setParameter("color",new Team(color));
        List<Manager> result = query.getResultList();
        result.forEach(System.out::println);
        return result;
    }

    public List<Manager> findManagers(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExamples");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT e FROM Manager e ");
        List<Manager> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        return resultList;
    }
}
