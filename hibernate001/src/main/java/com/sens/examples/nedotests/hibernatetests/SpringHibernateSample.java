package com.sens.examples.nedotests.hibernatetests;

import com.sens.examples.interfaces.HibernateContactDao;
import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.models.hibernate.ContactTelDetail;
import com.sens.examples.models.hibernate.Hobby;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by momo on 29.10.2017.
 */
public class SpringHibernateSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("META-INF/config/app-context.xml");
        context.refresh();

        HibernateContactDao dao = context.getBean("hibernateContactDao", HibernateContactDao.class);

//        //Add Contact
//        Contact contact = new Contact();
//        contact.setFirstName("First");
//        contact.setLastName("Last");
//        contact.setBirthDate(new Date());
//
//        ContactTelDetail detail = new ContactTelDetail("Home", "22352525252");
//        contact.addContactTelDetail(detail);
//        detail = new ContactTelDetail("Mobile", "99952525252");
//        contact.addContactTelDetail(detail);
//
//        dao.save(contact);

//        //Edit Contact
//        Contact contact = dao.findById(1L);
//        contact.setFirstName("Kim Fung");
//        Set<ContactTelDetail> details = contact.getContactTelDetails();
//        ContactTelDetail toDelete = null;
//
//        for (ContactTelDetail detail : details) {
//            if (detail.getTelType().equals("Home")) {
//                toDelete = detail;
//            }
//        }
//
//        contact.removeContactTelDetail(toDelete);
//        dao.save(contact);

//        //Delete Contact
        Contact contact = dao.findById(2L);
        dao.delete(contact);

        listContacts(dao.findAllWithDetail());

        System.out.println(dao.findById(1L));
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
            if (contact.getHobbies() != null) {
                for (Hobby hobby : contact.getHobbies()) {
                    System.out.println("--------" + hobby);
                }
            }
        }
        System.out.println();
    }
}
