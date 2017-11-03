package com.sens.examples.models.criteria;

import com.sens.examples.models.hibernate.Contact;
import com.sens.examples.models.hibernate.ContactTelDetail;
import com.sens.examples.models.hibernate.Hobby;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * Created by Sensible on 03.11.2017.
 */

@StaticMetamodel(Contact.class)
public abstract class Contact_ {

    public static volatile SingularAttribute<Contact, Long> id;
    public static volatile SingularAttribute<Contact, String> firstName;
    public static volatile SingularAttribute<Contact, String> lastName;
    public static volatile SingularAttribute<Contact, Integer> version;
    public static volatile SingularAttribute<Contact, Date> birthDate;
    public static volatile SetAttribute<Contact, ContactTelDetail> contactTelDetails;
    public static volatile SetAttribute<Contact, Hobby> hobbies;
}
