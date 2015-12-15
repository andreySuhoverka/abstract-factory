package com.epam.sukhoverka.dao;

public class FileDataSourceFactory implements DataSourceFactory {
    private static DataSourceFactory dataSourceFactory;
    private static DataSource dataSource;

    private FileDataSourceFactory(){}

    public static DataSourceFactory getInstance() {
        if(dataSourceFactory == null){
            dataSourceFactory = new FileDataSourceFactory();
        }
        return dataSourceFactory;
    }

    public DataSource getDataSource() {
        if(dataSource == null){
            dataSource = new FileDataSource();
        }
        return dataSource;
    }
}
