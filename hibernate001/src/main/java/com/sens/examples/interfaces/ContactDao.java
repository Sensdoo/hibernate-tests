package com.sens.examples.interfaces;

import com.sens.examples.models.jdbc.Contact;

import java.util.List;

/**
 * Created by Sensible on 26.10.2017.
 */

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    List<Contact> findByFirstName(String firstNAme);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void insertWithDetail(Contact contact);
    void update(Contact contact);
    void delete(Long contactId);
}
