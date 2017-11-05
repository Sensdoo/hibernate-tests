package com.sens.examples.nedotests.springdatatests;

import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.models.springdata.ContactAudit;
import com.sens.examples.springdata.interfaces.ContactAuditService;
import com.sens.examples.springdata.interfaces.ContactService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

public class SpringDataJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/config/app-context.xml");
        context.refresh();

//        ContactService service = context.getBean("springDataJpaService", ContactService.class);
//
//        listContacts("Find all: ", service.findAll());
//        listContacts("Find by first name: ", service.findAllByFirstName("Chris"));
//        listContacts("Find by first and last name: ", service.findAllByFirstNameAndLastName("Scott", "Tiger"));

        ContactAuditService auditService = context.getBean("contactAuditService", ContactAuditService.class);
        List<ContactAudit> contacts = auditService.findAll();
        listAuditContacts("Start", contacts);

        ContactAudit contactAudit = new ContactAudit();
        contactAudit.setFirstName("First4");
        contactAudit.setLastName("Last4");
        contactAudit.setBirthDate(new Date());
        auditService.save(contactAudit);

        List<ContactAudit> contacts2 = auditService.findAll();
        listAuditContacts("Add new contact", contacts2);

        contactAudit = auditService.findById(3L);
        contactAudit.setFirstName("Tom");
        auditService.save(contactAudit);

        List<ContactAudit> contacts3 = auditService.findAll();
        listAuditContacts("Edit contact", contacts3);

    }

    private static void listContacts(String message, List<Contact> contacts) {
        System.out.println();
        System.out.println(message);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private static void listAuditContacts(String message, List<ContactAudit> contacts) {
        System.out.println();
        System.out.println(message);
        for (ContactAudit contact : contacts) {
            System.out.println(contact);
        }
    }
}
