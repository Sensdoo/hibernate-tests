package com.sens.examples.nedotests.jdbctests;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.jdbc.JdbcContactDaoAnnotation;
import com.sens.examples.models.jdbc.Contact;
import com.sens.examples.models.jdbc.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sensible on 28.10.2017.
 */

public class JdbcContactDaoAnnotationSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/config/app-context.xml");
        context.refresh();

        ContactDao dao = context.getBean("contactDaoAnnotation", JdbcContactDaoAnnotation.class);

        List<Contact> contacts = dao.findAll();
        listContacts(contacts);
        System.out.println();

        List<Contact> contacts2 = dao.findByFirstName("Chris");
        listContacts(contacts2);
        System.out.println();

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("Chris");
        contact.setLastName("Bohn");
        contact.setBirthDate(new Date(new GregorianCalendar(1977, 10, 1).getTime().getTime()));
        dao.update(contact);

        listContacts(dao.findAll());
        System.out.println();

//        Contact contact2 = new Contact();
//        contact2.setFirstName("Rod");
//        contact2.setLastName("Johnson");
//        contact2.setBirthDate(new Date(new GregorianCalendar(2001, 0, 5).getTime().getTime()));
//        dao.insert(contact2);

//        listContacts(dao.findAll());
//        System.out.println();

        Contact contact3 = new Contact();
        contact3.setFirstName("Michael");
        contact3.setLastName("Jackson");
        contact3.setBirthDate(new Date(new GregorianCalendar(1964, 9, 11).getTime().getTime()));

        List<ContactTelDetail> contactTelDetails = new ArrayList<>();

        ContactTelDetail contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Home");
        contactTelDetail.setTelNumber("1111111111111111");
        contactTelDetails.add(contactTelDetail);

        contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Mobile");
        contactTelDetail.setTelNumber("99999999999");
        contactTelDetails.add(contactTelDetail);

        contact3.setContactTelDetails(contactTelDetails);

        dao.insertWithDetail(contact3);

        listContacts(dao.findAllWithDetail());
        System.out.println();
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail detail : contact.getContactTelDetails()) {
                    System.out.println("----" + detail);
                }
            }
        }
    }
}
