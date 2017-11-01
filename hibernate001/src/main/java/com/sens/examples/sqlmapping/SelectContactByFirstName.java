package com.sens.examples.sqlmapping;

import com.sens.examples.models.jdbc.Contact;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Sensible on 28.10.2017.
 */
public class SelectContactByFirstName extends MappingSqlQuery<Contact> {

    private static final String SQL_FIND_BY_FIRST_NAME = "SELECT * FROM CONTACT WHERE FIRST_NAME = :FIRST_NAME";

    public SelectContactByFirstName(DataSource ds) {
        super(ds, SQL_FIND_BY_FIRST_NAME);
        super.declareParameter(new SqlParameter("FIRST_NAME", Types.VARCHAR));
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
