package com.sens.examples.jpa;

import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.jpa.interfaces.ContactService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by momo on 02.11.2017.
 */

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private Log LOG = LogFactory.getLog(ContactServiceImpl.class);

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        List<Contact> contacts =  em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
        return contacts;
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return null;
    }

    @Override
    public Contact findById(long id) {
        return null;
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public void delete(Contact contact) {

    }
}
