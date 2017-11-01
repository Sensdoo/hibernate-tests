package com.sens.examples.sqlmapping;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by momo on 29.10.2017.
 */
public class InsertContact extends SqlUpdate {

    private static final String SQL_INSERT_CONTACT = "INSERT INTO CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) " +
                                                        "VALUES (:FIRST_NAME, :LAST_NAME, :BIRTH_DATE)";

    public InsertContact(DataSource ds) {
        super(ds, SQL_INSERT_CONTACT);
        super.declareParameter(new SqlParameter("FIRST_NAME", Types.VARCHAR));
        super.declareParameter(new SqlParameter("LAST_NAME", Types.VARCHAR));
        super.declareParameter(new SqlParameter("BIRTH_DATE", Types.DATE));
        super.setGeneratedKeysColumnNames(new String[] {"ID"});
        super.setReturnGeneratedKeys(true);
    }
}
