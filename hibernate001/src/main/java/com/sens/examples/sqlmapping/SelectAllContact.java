package com.sens.examples.sqlmapping;

import com.sens.examples.models.jdbc.Contact;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sensible on 28.10.2017.
 */

public class SelectAllContact extends MappingSqlQuery<Contact> {

    private static final String SQL_SELECT_ALL_CONTACT = "SELECT * FROM CONTACT";

    public SelectAllContact(DataSource ds) {
        super(ds, SQL_SELECT_ALL_CONTACT);
    }

    @Override
    protected Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirstName(resultSet.getString("first_name"));
        contact.setLastName(resultSet.getString("last_name"));
        contact.setBirthDate(resultSet.getDate("birth_date"));
        return contact;
    }
}
