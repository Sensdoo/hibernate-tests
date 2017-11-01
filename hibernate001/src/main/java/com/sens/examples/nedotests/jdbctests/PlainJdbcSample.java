package com.sens.examples.nedotests.jdbctests;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.jdbc.PlainContactDao;
import com.sens.examples.models.jdbc.Contact;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sensible on 26.10.2017.
 * Тестируем класс PlainContactDao
 */

public class PlainJdbcSample {

    private static ContactDao contactDao = new PlainContactDao();

    public static void main(String[] args) {
        //Выводим начальный список контактов
        System.out.println("Listing initial contact data: ");
        listAllContacts();
        System.out.println();

        //Добавляем новый контакт
        System.out.println("Insert a new contact");
        Contact contact = new Contact();
        contact.setFirstName("Jacky");
        contact.setLastName("Chan");
        contact.setBirthDate(new Date(new GregorianCalendar(2001, 10, 1).getTime().getTime()));
        contactDao.insert(contact);

        //Выводим обновленный список контактов
        System.out.println("Listing contact data after new contact created: ");
        listAllContacts();
        System.out.println();

        //Удаляем только что добавленный контакт
        System.out.println("Deleting the previous created contact");
        contactDao.delete(contact.getId());

        //Выводим список после удаления
        System.out.println("Listing contact data after new contact deleted:");
        listAllContacts();
    }

    private static void listAllContacts() {
        List<Contact> contacts = contactDao.findAll();

        for (Contact c : contacts) {
            System.out.println(c);
        }
    }
}
