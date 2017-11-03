package com.sens.examples.nedotests.jpatests;

import com.sens.examples.jpa.ContactSummaryServiceImpl;
import com.sens.examples.jpa.ContactSummaryUntypeImpl;
import com.sens.examples.jpa.interfaces.ContactService;
import com.sens.examples.jpa.interfaces.ContactSummaryService;
import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.models.hibernate.ContactTelDetail;
import com.sens.examples.models.hibernate.Hobby;
import com.sens.examples.models.jpa.ContactSummary;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by momo on 02.11.2017.
 */
public class SpringJPASample {
    public static void main(String[] args) {

        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/config/app-context.xml");
        context.refresh();

        ContactService service = context.getBean("jpaContactService", ContactService.class);

        //Add Contact
//        System.out.println("ADD CONTACT");
//        Contact contact = new Contact();
//        contact.setFirstName("Fedya");
//        contact.setLastName("S`elMedvedya");
//        contact.setBirthDate(new Date());
//
//        ContactTelDetail detail = new ContactTelDetail("Home", "8129843993493");
//        contact.addContactTelDetail(detail);
//        detail = new ContactTelDetail("Mobile", "8787825252");
//        contact.addContactTelDetail(detail);
//
//        service.save(contact);
//
//        listContacts(service.findAllWithDetail());
//        System.out.println();

        //edit Contact
//        System.out.println("EDIT CONTACT");
//        Contact contact = service.findById(4L);
//        System.out.println();
//        System.out.println("Contact with id: " + contact.getId());
//        System.out.println();
//
//        contact.setFirstName("Second");
//
//        Set<ContactTelDetail> details = contact.getContactTelDetails();
//        ContactTelDetail toDelete = null;
//        for (ContactTelDetail contactTelDetail : details) {
//            if (contactTelDetail.getTelType().equals("Home")) {
//                toDelete = contactTelDetail;
//            }
//            details.remove(toDelete);
//        }
//        service.save(contact);
//
        listContacts(service.findAllWithDetail());

        //delete contact
//        Contact contact = service.findById(4L);
//        service.delete(contact);
//        listContacts(service.findAllWithDetail());
//        System.out.println();

//        System.out.println("FIND BY ID");
//        System.out.println(service.findById(3L));
//        System.out.println();
//
//        System.out.println("SUMMARY UNTYPE");
//        ContactSummaryUntypeImpl summaryUntype = context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
//        summaryUntype.displayAllContactSummary();
//        System.out.println();
//
//        System.out.println("CONTACT SUMMARY");
//        ContactSummaryService contactSummaryService = context.getBean("contactSummaryService", ContactSummaryService.class);
//        for (ContactSummary summary : contactSummaryService.findAll()) {
//            System.out.println(summary);
//        }
//        System.out.println();

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
