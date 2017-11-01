package com.sens.examples.interfaces;

import com.sens.examples.models.hibernate.Contact;

import java.util.List;

/**
 * Created by momo on 29.10.2017.
 */

public interface HibernateContactDao {

    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
