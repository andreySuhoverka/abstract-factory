package com.epam.sukhoverka.dao;

import com.epam.sukhoverka.model.Person;

public interface DataSource {

    void writePerson (Person person);
    Person readPerson (String name);

}
