package com.sens.examples.jpa;

import com.sens.examples.models.criteria.Contact_;
import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.jpa.interfaces.ContactService;
import com.sens.examples.models.hibernate.Hobby;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by momo on 02.11.2017.
 */

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private final static String ALL_CONTACT_NATIVE_QUERY = "select * from Contact";

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
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {

        List<Contact> contacts =  em.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(long id) {
        TypedQuery<Contact> query = em.createNamedQuery("Contact.findById", Contact.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            em.persist(contact);
            LOG.info("Inserting new contact");
        } else {
            em.merge(contact);
            LOG.info("Updating existing contact");
        }
        LOG.info("Contact saved wi th id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact mergedContact = em.merge(contact);
        em.remove(mergedContact);
    }
}
