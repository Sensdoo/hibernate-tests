package com.sens.examples.nedotests.jpa;

import com.sens.examples.jpa.interfaces.ContactService;
import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.models.hibernate.ContactTelDetail;
import com.sens.examples.models.hibernate.Hobby;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

/**
 * Created by momo on 02.11.2017.
 */
public class SpringJPASample {
    public static void main(String[] args) {

        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:META-INF/config/app-context.xml");
        context.refresh();

        ContactService service = context.getBean("jpaContactService", ContactService.class);

        listContacts(service.findAll());
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
//            if (contact.getContactTelDetails() != null) {
//                for (ContactTelDetail detail : contact.getContactTelDetails()) {
//                    System.out.println("----" + detail);
//                }
//            }
//            if (contact.getHobbies() != null) {
//                for (Hobby hobby : contact.getHobbies()) {
//                    System.out.println("--------" + hobby);
//                }
//            }
        }
        System.out.println();
    }
}
