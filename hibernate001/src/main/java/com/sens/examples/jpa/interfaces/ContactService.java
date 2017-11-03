package com.sens.examples.jpa.interfaces;

import com.sens.examples.models.hibernate.Contact;

import java.util.List;

/**
 * Created by momo on 02.11.2017.
 */

public interface ContactService {

    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    List<Contact> findAllByNativeQuery();
    Contact findById(long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
