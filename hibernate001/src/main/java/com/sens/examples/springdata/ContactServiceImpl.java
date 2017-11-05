package com.sens.examples.springdata;

import com.google.common.collect.Lists;
import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.springdata.interfaces.ContactRepository;
import com.sens.examples.springdata.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

@Service("springDataJpaService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    public List<Contact> findAllByFirstName(String firstName) {
        return contactRepository.findAllByFirstName(firstName);
    }

    @Override
    public List<Contact> findAllByFirstNameAndLastName(String firstName, String lastName) {
        return contactRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }
}
