package com.epam.sukhoverka.dao.extractor;

import com.epam.sukhoverka.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonExtractor {

    public Person extractPerson(ResultSet rs) throws SQLException {
        Person fetchedPerson = null;
        if (rs.next()) {
            String fetchedName = rs.getString("name");
            int fetchedAge = rs.getInt("age");
            fetchedPerson = new Person(fetchedName, fetchedAge);
        }
        return fetchedPerson;
    }
}
