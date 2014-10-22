/**
 * 
 */
package com.learn.model;

/**
 * @author HY
 *
 */
public class Person {
    private String email;
    private String firstName;
    private String lastName;
  
    public Person() {
    }
    
    public Person(Person person) {
    	this.email = person.email;
    	this.firstName = person.firstName;
    	this.lastName = person.lastName;
    }
 
    public Person( final String email ) {
        this.email = email;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail( final String email ) {
        this.email = email;
    }
  
    public String getFirstName() {
        return firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setFirstName( final String firstName ) {
        this.firstName = firstName;
    }
 
    public void setLastName( final String lastName ) {
        this.lastName = lastName;
    } 
}
