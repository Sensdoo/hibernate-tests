package com.sens.examples.jdbc;

import com.sens.examples.interfaces.ContactDao;
import com.sens.examples.models.jdbc.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sensible on 26.10.2017.
 * Класс jdbc реализации подключения к бд
 * Регистрируем драйвер "com.mysql.jdbc.Driver"
 * создаем два метода для подключения и закрытия связи с бд
 * реализуем интерфасе ContactDao
 */

public class PlainContactDao implements ContactDao  {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/example001?useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&amp&serverTimezone=UTC&useSSL=false",
                "root",
                "root"
        );
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> result = new ArrayList<Contact>();
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contact");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));

                result.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return null;
    }

    public List<Contact> findByFirstName(String firstNAme) {
        return null;
    }

    public String findLastNameById(Long id) {
        return null;
    }

    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Contact contact) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Contact (first_name, last_name, birth_date) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setDate(3, contact.getBirthDate());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if(generatedKeys.next()) {
                contact.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insertWithDetail(Contact contact) {

    }

    public void update(Contact contact) {

    }

    @Override
    public void delete(Long contactId) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("delete FROM contact WHERE id=?");
            statement.setLong(1, contactId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
