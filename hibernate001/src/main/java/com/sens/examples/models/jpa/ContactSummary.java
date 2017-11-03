package com.sens.examples.models.jpa;

import java.io.Serializable;

/**
 * Created by Sensible on 03.11.2017.
 */

public class ContactSummary implements Serializable {
    
    private String firstName;
    private String lastName;
    private String telNumber;

    public ContactSummary(String firstName, String lastName, String telNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    @Override
    public String toString() {
        return "ContactSummary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}
