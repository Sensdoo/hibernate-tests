package com.sens.examples.nedotests.jdbctests;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.models.jdbc.Contact;
import com.sens.examples.models.jdbc.ContactTelDetail;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**
 * Created by Sensible on 28.10.2017.
 */

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/config/app-context.xml");
        context.refresh();

        ContactDao dao = context.getBean("contactDao", ContactDao.class);

        System.out.println("First name for contact id 1 is: " + dao.findFirstNameById(1L));
        System.out.println("Last name for contact id 1 is: " + dao.findLastNameById(1L));
        System.out.println();

        List<Contact> contacts = dao.findAll();

        for (Contact contact : contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail detail : contact.getContactTelDetails()) {
                    System.out.println("---" + detail);
                }
            }
        }
        System.out.println();

        List<Contact> contacts2 = dao.findAllWithDetail();

        for (Contact contact : contacts2) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail detail : contact.getContactTelDetails()) {
                    System.out.println("---" + detail);
                }
            }
        }
    }
}
