package com.timwang.spring.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

/**
 * @author wangjun
 * @date 2020-08-01
 */
public class DataSourceFactory {

    public static DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdb");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/zp");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");
        return pooledDataSource;
    }

}
