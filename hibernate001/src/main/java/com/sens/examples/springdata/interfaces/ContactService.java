package com.sens.examples.springdata.interfaces;

import com.sens.examples.models.hibernate.Contact;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

@NoRepositoryBean
public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllByFirstName(String firstName);
    List<Contact> findAllByFirstNameAndLastName(String firstName, String lastName);
}
