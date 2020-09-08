package models;

import javax.persistence.*;

/**
 *
 *
 * @author Viktoria Cseke
 * R00180598 30/03/2020
 * @version 1.0
 */

/**
 * <h1> This class models a Name  </h1>
 * It consists of 3 Strings that is for the first name, middle name and last name.
 *
 */

@MappedSuperclass
/**
 * The mapped information is inherited in the Person class.
 * @see MappedSuperclass
 * @link https://docs.oracle.com/javaee/7/api/javax/persistence/MappedSuperclass.html
 *
 */
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * Default constructor
     */

    public Name(){

    }

    /**
     * Creates a name
     * @param myFirstName fist name of our name.
     * @param myMiddleName middle name of our name.
     * @param myLastName last name of our name.
     */

    public Name(String myFirstName, String myMiddleName, String myLastName){
        setFirstName(myFirstName);
        setMiddleName(myMiddleName);
        setLastName(myLastName);
    }

    /**
     * Gets the first name.
     * @return A string representing the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name for the name object.
     * @param firstName A string representing the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name.
     * @return A string representing the middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name for the name object.
     * @param middleName A string representing the middle name.
     */

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     *Gets the last name.
     * @return A string representing the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name for the name object.
     * @param lastName A string representing the last name.
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Creates a formatted string of the name
     * @return A string, if the first name is empty, then print no name. Else it prints the first name, middle and last name.
     */
    public String toString(){
        if(getFirstName()==null){
            return "no name";
        }
        return "Name: "+getFirstName()+" "+getMiddleName()+" "+getLastName();
    }

}
