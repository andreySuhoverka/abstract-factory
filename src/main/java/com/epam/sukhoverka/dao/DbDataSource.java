package com.epam.sukhoverka.dao;

import com.epam.sukhoverka.dao.extractor.PersonExtractor;
import com.epam.sukhoverka.model.Person;

import java.sql.*;
import java.util.ResourceBundle;

public class DbDataSource implements DataSource {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.jdbc_config");

    static {
        try {
            Class.forName(resourceBundle.getString("DATABASE_DRIVER_NAME"));
        } catch (ClassNotFoundException e) {
            // todo log error
            e.printStackTrace();
        }
    }



    private static final String url =  resourceBundle.getString("jdbc_url");
    private static final String user = resourceBundle.getString("user");
    private static final String password = resourceBundle.getString("password");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }


    public void writePerson(Person person) {
        Connection dbConnection = null;
        try {
            dbConnection = getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement("Insert into Person(name, age) VALUE(?,?)");
                ps.setString(1, person.getName());
                ps.setInt(2, person.getAge());
                ResultSet rs = null;

                // todo replace with logger
                System.out.println(ps.unwrap(PreparedStatement.class).toString());
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException("error while reading db", e);
                }

            } catch (SQLException e) {
                throw new RuntimeException("prepare statement isn't created", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("connection error", e);
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new RuntimeException("dbConnection exception on close");
                }
            }

        }

    }

    public Person readPerson(String name) {
        Person fetchedPerson = null;
        Connection dbConnection = null;
        try {
            dbConnection = getConnection();
            PreparedStatement ps = null;
            try {
                ps = dbConnection.prepareStatement("Select * from Person where name = ?");
                ps.setString(1, name);
                ResultSet rs = null;
                try {
                    // todo replace with log
                    System.out.println(ps.unwrap(PreparedStatement.class).toString());
                    rs = ps.executeQuery();
                    fetchedPerson = new PersonExtractor().extractPerson(rs);
                } catch (SQLException e) {
                    throw new RuntimeException("error while reading db", e);
                }
            } catch (SQLException e) {
                throw new RuntimeException("prepare statement isn't created", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("connection error", e);
        } finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    throw new RuntimeException("dbConnection exception on close");
                }
            }

        }
        return fetchedPerson;
    }

}
