package com.sens.examples.hibernate;

import com.sens.examples.interfaces.HibernateContactDao;
import com.sens.examples.models.hibernate.Contact;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by momo on 29.10.2017.
 * Используем hibernate для работы с бд.
 * Бин sessionFactory создан в app-context.xml
 */

@Transactional
@Repository("hibernateContactDao")
public class HibernateContactDaoImpl implements HibernateContactDao {

    private static final Log LOG = LogFactory.getLog(HibernateContactDaoImpl.class);
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return getSession().createQuery("FROM Contact c").list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        return getSession().getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return (Contact) getSession().getNamedQuery("Contact.findById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        getSession().saveOrUpdate(contact);
        LOG.info("Contact saves with id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {

    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
