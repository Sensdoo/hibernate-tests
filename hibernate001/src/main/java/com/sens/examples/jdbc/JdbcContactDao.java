package com.sens.examples.jdbc;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.models.jdbc.Contact;
import com.sens.examples.models.jdbc.ContactTelDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Sensible on 26.10.2017.
 * Испльзуем jdbcTemplate и namedParameterJdbcTemplate для работы с бд.
 * Внутренние классы rowMapper и rowMapperExtractor для выборки объектов
 * и выборки объектов и ассоциаций соответственно.
 *
 */
public class JdbcContactDao implements ContactDao, InitializingBean {

    private Logger LOG = Logger.getLogger(JdbcContactDao.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String selectFirstNameById = "select first_name from Contact where id=?";
    private String selectLastNameById = "select last_name from Contact where id= :id";
    private String selectAll = "select * from Contact ";
    private String selectAllWithDetail = "select c.*," +
            "t.id as contact_tel_id, t.* from contact c " +
            "left join contact_tel_detail t on c.id = t.contact_id";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Contact> findAll() {
        //стандартный способ через статический внутренний класс
//        return namedParameterJdbcTemplate.query(selectAll, new ContactRowMapper());

        //способ через лямбда-выражения
        return namedParameterJdbcTemplate.query(selectAll, (ResultSet resultSet, int i) -> {
            Contact contact = new Contact();
            contact.setId(resultSet.getLong("id"));
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setFirstName(resultSet.getString("last_name"));
            contact.setBirthDate(resultSet.getDate("birth_date"));
            return contact;
        });
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return namedParameterJdbcTemplate.query(selectAllWithDetail, new ContactWithDetailExtractor());
    }

    @Override
    public List<Contact> findByFirstName(String firstNAme) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        Map<String, Object> parametrs = new HashMap<String, Object>();
        parametrs.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(selectLastNameById, parametrs, String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        return jdbcTemplate.queryForObject(selectFirstNameById, new Object[] {id}, String.class);
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public void insertWithDetail(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Long contactId) {

    }

//    private static class ContactRowMapper implements RowMapper<Contact> {
//
//        @Override
//        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
//            Contact contact = new Contact();
//            contact.setId(resultSet.getLong("id"));
//            contact.setFirstName(resultSet.getString("first_name"));
//            contact.setFirstName(resultSet.getString("last_name"));
//            contact.setBirthDate(resultSet.getDate("birth_date"));
//            return contact;
//        }
//    }

    private static class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {

        @Override
        public List<Contact> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, Contact> map = new HashMap<>();
            Contact contact = null;

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                contact = map.get(id);
                if (contact == null) {
                    contact = new Contact();
                    contact.setId(resultSet.getLong("id"));
                    contact.setFirstName(resultSet.getString("first_name"));
                    contact.setLastName(resultSet.getString("last_name"));
                    contact.setBirthDate(resultSet.getDate("birth_date"));
                    contact.setContactTelDetails(new ArrayList<>());

                    Long contactTelDetailId = resultSet.getLong("contact_tel_id");
                    if (contactTelDetailId > 0) {
                        ContactTelDetail detail = new ContactTelDetail();
                        detail.setId(contactTelDetailId);
                        detail.setContactId(id);
                        detail.setTelType(resultSet.getString("tel_type"));
                        detail.setTelNumber(resultSet.getString("tel_number"));
                        contact.getContactTelDetails().add(detail);
                    }

                    map.put(id, contact);
                }

            }
            return new ArrayList<>(map.values());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            // Свойство dataSource в ContactDao должно быть установлено
            throw new BeanCreationException( "Must set dataSource on ContactDao") ;
        }
        if (jdbcTemplate == null) {
            // Свойство jdbcTemplate в ContactDao должно быть установлено
            throw new BeanCreationException( "Null JdbcTemplate on ContactDao") ;
        }
        if (namedParameterJdbcTemplate == null) {
            // Свойство jdbcTemplate в ContactDao должно быть установлено
            throw new BeanCreationException( "Null namedParameterJdbcTemplate on ContactDao") ;
        }
    }
}
