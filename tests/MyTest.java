package tests;

import static junit.framework.Assert.*;


import models.*;
import org.junit.Test;


public class MyTest {

    @Test
    public void createName(){
        Name n1=new Name();
        n1.setFirstName("Sample");
        n1.setMiddleName("Testing");
        n1.setLastName("Something");

        assertEquals("Sample",n1.getFirstName());
        assertEquals("Testing",n1.getMiddleName());
        assertEquals("Something",n1.getLastName());
        assertNotSame("x",n1.getFirstName());
    }

    @Test
    public void printName(){
        Name n1=new Name();
        n1.setFirstName("Sample");
        n1.setMiddleName("Testing");
        n1.setLastName("Something");

        assertEquals("Name: Sample Testing Something",n1.toString());
        assertNotSame("x",n1.toString());
    }

    @Test
    public void createPerson(){
        Person p1=new Person();
        p1.setFirstName("Sample");
        p1.setMiddleName("Testing");
        p1.setLastName("Something");

        p1.setEmail("email@email.com");
        p1.setPhone("0123456");

        assertEquals("Sample",p1.getFirstName());
        assertEquals("email@email.com",p1.getEmail());
        assertEquals("0123456",p1.getPhone());
        assertNotSame("x",p1.getMiddleName());
    }

    @Test
    public void createPlayer(){
        Player player=new Player();
        Team t1=new Team();

        player.setFirstName("Sample");
        player.setMiddleName("Testing");
        player.setLastName("Something");

        player.setEmail("email@email.com");
        player.setPhone("0123456");

        t1.setTeamColour("pink");
        player.setTeamColour(t1);

        player.setGoalie(false);
        player.setGoals(8);

        assertEquals(8,(int)player.getGoals());
        assertEquals(false,(boolean)player.getGoalie());
    }

    //this is the test class asked in the brief, creating a league, some teams and players/managers
    //then testing if added to the league etc.
    @Test
    public void createLeague(){
        League league=new League();
        Player player=new Player();
        Manager m1=new Manager();
        Team t1=new Team();
        Team t2=new Team();

        t1.setTeamColour("Cyan");
        t2.setTeamColour("Brown");
        league.addTeam(t1);
        league.addTeam(t2);

        player.setFirstName("Sample");
        player.setMiddleName("Testing");
        player.setLastName("Something");
        player.setEmail("email@email.com");
        player.setPhone("0123456");
        player.setGoalie(false);
        player.setGoals(8);
        player.setTeamColour(t1);

        m1.setFirstName("Sample2");
        m1.setMiddleName("Testing2");
        m1.setLastName("Something2");
        m1.setEmail("email2@email.com");
        m1.setPhone("034567");
        m1.setStar(5);
        m1.setBirthDate("4/4/1980");
        m1.setTeamColour(t1);

        assertEquals(t1,m1.getTeamColour());
        assertEquals(t1,player.getTeamColour());
        assertEquals(t1,league.get(0));

    }

}