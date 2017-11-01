package com.sens.examples.sqlmapping;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by momo on 29.10.2017.
 */
public class InsertContactWithDetail extends BatchSqlUpdate {

    private static final String SQL_INSERT_CONTACT_TEL_DETAIL = "INSERT INTO CONTACT_TEL_DETAIL " +
                                "(CONTACT_ID, TEL_TYPE, TEL_NUMBER) VALUES (:CONTACT_ID, :TEL_TYPE, :TEL_NUMBER)";
    private static final int BATCH_SIZE = 10;

    public InsertContactWithDetail(DataSource ds) {
        super(ds, SQL_INSERT_CONTACT_TEL_DETAIL);
        declareParameter(new SqlParameter("CONTACT_ID", Types.INTEGER));
        declareParameter(new SqlParameter("TEL_TYPE", Types.VARCHAR));
        declareParameter(new SqlParameter("TEL_NUMBER", Types.VARCHAR));
        setBatchSize(BATCH_SIZE);
    }
}
