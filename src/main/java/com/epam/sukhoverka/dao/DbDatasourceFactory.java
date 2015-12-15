package com.epam.sukhoverka.dao;

public class DbDatasourceFactory implements DataSourceFactory {
    private static DataSourceFactory dataSourceFactory;
    private DataSource dataSource;

    private DbDatasourceFactory(){}

    public static DataSourceFactory getInstance() {
        if(dataSourceFactory == null){
            dataSourceFactory = new DbDatasourceFactory();
        }
        return dataSourceFactory;
    }

    public DataSource getDataSource() {
        if(dataSource == null){
            dataSource = new DbDataSource();
        }
        return dataSource;
    }


}
