package com.epam.sukhoverka.client;

import com.epam.sukhoverka.dao.DataSource;
import com.epam.sukhoverka.dao.DataSourceFactory;
import com.epam.sukhoverka.dao.DataSourceFactoryFactory;
import com.epam.sukhoverka.model.Person;

public class Client {

    public static void main(String[] args) {
        DataSourceFactory dataSourceFactory = DataSourceFactoryFactory.getDataSourceFactory(DataSourceFactoryFactory.FactoryType.FILE);
        DataSource dataSource = dataSourceFactory.getDataSource();

        dataSource.writePerson(new Person("scrooge", 21));
        Person p = dataSource.readPerson("scrooge");
        assert p.getName().equals("scrooge");
    }
}
