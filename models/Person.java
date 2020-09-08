package models;

import javax.persistence.MappedSuperclass;

/**
 * 🦄
 *
 * @author Viktoria Cseke
 * R00180598 30/03/2020
 */

/**
 * <h1> This class is a super class for People </h1>
 * It uses the name class and two Strings, one for phone and one for email.
 */

/**
 * The mapped information is inherited in the Player class.
 * @see MappedSuperclass
 * @see models.Name
 */
@MappedSuperclass
public class Person extends Name {
//    @OneToOne
//    private Name name;
    private String phone;
    private String email;

    /**
     * Default constructor
     */

    public Person(){
    }

    /**
     * Creates a person object
     * @param firstName A String for first name.
     * @param middleName A String for middle name.
     * @param lastName A String for last name.
     * @param phone  A String for the phone number.
     * @param email  A String for email address.
     */

    public Person( String firstName, String middleName, String lastName, String phone, String email){
        super(firstName,middleName,lastName);
        setPhone(phone);
        setEmail(email);
    }

    /**
     * Gets the phone number for the person
     * @return A string of the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone for the person.
     * @param phone A string phone number for the person.
     */

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email address for the person
     * @return A string representing the email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the person
     * @param email A string email address.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A formatted string of our person
     * @return the name of the person, its email and phone number.
     */

    public String toString(){
        return super.toString()+" ,Email:"+getEmail()+", "+getPhone();
    }

    /**
     * A print function that calls the toString function and prints it.
     */

    public void printPerson(){
        System.out.println(toString());
    }
}
