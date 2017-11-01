package com.sens.examples.jdbc;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.models.jdbc.Contact;
import com.sens.examples.models.jdbc.ContactTelDetail;
import com.sens.examples.sqlmapping.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sensible on 28.10.2017.
 * Используем аннотации для получения бина dataSource
 * и используем sqlMapping классы для выборки и вставки объектов в бд
 */

@Repository("contactDaoAnnotation")
public class JdbcContactDaoAnnotation implements ContactDao {

    private Log LOG = LogFactory.getLog(JdbcContactDaoAnnotation.class);
    private DataSource dataSource;
    private SelectAllContact selectAllContact;
    private SelectContactByFirstName selectContactByFirstName;
    private UpdateContact updateContact;
    private InsertContact insertContact;
    private InsertContactWithDetail insertContactWithDetail;
    private JdbcContactDao jdbcContactDao;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllContact = new SelectAllContact(dataSource);
        this.selectContactByFirstName = new SelectContactByFirstName(dataSource);
        this.updateContact = new UpdateContact(dataSource);
        this.insertContact = new InsertContact(dataSource);
        this.insertContactWithDetail = new InsertContactWithDetail(dataSource);
        this.jdbcContactDao = new JdbcContactDao();
        jdbcContactDao.setDataSource(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<Contact> findAll() {
        return selectAllContact.execute();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return jdbcContactDao.findAllWithDetail();
    }

    @Override
    public List<Contact> findByFirstName(String firstNAme) {
        Map<String, Object> map = new HashMap<>();
        map.put("FIRST_NAME", firstNAme);
        return selectContactByFirstName.executeByNamedParam(map);
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Contact contact) {
        Map<String, Object> param = new HashMap<>();
        param.put("FIRST_NAME", contact.getFirstName());
        param.put("LAST_NAME", contact.getLastName());
        param.put("BIRTH_DATE", contact.getBirthDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        insertContact.updateByNamedParam(param, keyHolder);
        contact.setId(keyHolder.getKey().longValue());

        LOG.info("New contact inserted with id" + contact.getId());
    }

    @Override
    public void insertWithDetail(Contact contact) {
        insert(contact);
        List<ContactTelDetail> contacts = contact.getContactTelDetails();
        if (contacts != null) {
            for (ContactTelDetail detail : contacts) {
                Map<String, Object> param = new HashMap<>();
                param.put("CONTACT_ID", contact.getId());
                param.put("TEL_TYPE", detail.getTelType());
                param.put("TEL_NUMBER", detail.getTelNumber());
                insertContactWithDetail.updateByNamedParam(param);
            }
        }
        insertContactWithDetail.flush();
    }

    @Override
    public void update(Contact contact) {
        Map<String, Object> param = new HashMap<>();
        param.put("FIRST_NAME", contact.getFirstName());
        param.put("LAST_NAME", contact.getLastName());
        param.put("BIRTH_DATE", contact.getBirthDate());
        param.put("ID", contact.getId());
        updateContact.updateByNamedParam(param);
        LOG.info("Existing contact updating with id: " + contact.getId());
    }

    @Override
    public void delete(Long contactId) {

    }
}
