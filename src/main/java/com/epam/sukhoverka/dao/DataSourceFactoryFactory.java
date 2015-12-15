package com.epam.sukhoverka.dao;

public class DataSourceFactoryFactory {
    public enum FactoryType {FILE, DB}

    private static synchronized DataSourceFactory getDataSourceFactoryByType(FactoryType type) {

        DataSourceFactory result;
        switch (type) {
            case FILE:
                result = FileDataSourceFactory.getInstance();
                break;
            case DB:
                result = DbDatasourceFactory.getInstance();
                break;
            default:
                throw new IllegalArgumentException("You chose a type, that does not exists!");
        }
        return result;
    }

    public synchronized static DataSourceFactory getDataSourceFactory(FactoryType factoryType) {
        return getDataSourceFactoryByType(factoryType);
    }
}