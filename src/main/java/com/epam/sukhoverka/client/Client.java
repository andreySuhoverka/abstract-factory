package com.epam.sukhoverka.client;

import com.epam.sukhoverka.dao.DataSource;
import com.epam.sukhoverka.dao.DataSourceFactory;
import com.epam.sukhoverka.dao.DataSourceFactoryFactory;
import com.epam.sukhoverka.model.Person;

public class Client {

    public static void main(String[] args) {
        DataSourceFactory dataSourceFactory = DataSourceFactoryFactory.getDataSourceFactory(DataSourceFactoryFactory.FactoryType.DB);
        DataSource dataSource = dataSourceFactory.getDataSource();

        dataSource.writePerson(new Person("scrooge"));
        Person p = dataSource.readPerson();
        assert p.getName().equals("scrooge");
    }
}
