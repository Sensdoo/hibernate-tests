package com.sens.examples.models.hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by momo on 29.10.2017.
 */

@Entity
@Table(name = "HOBBY")
public class Hobby implements Serializable {

    private String id;
    private Set<Contact> contacts = new HashSet<>();

    public Hobby() {
    }

    @Id
    @Column(name = "HOBBY_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
               joinColumns = @JoinColumn(name = "HOBBY_ID"),
               inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id='" + id + '\'' + '}';
    }
}
