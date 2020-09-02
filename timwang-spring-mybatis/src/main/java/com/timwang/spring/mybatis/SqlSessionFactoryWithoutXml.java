package com.timwang.spring.mybatis;

import com.timwang.spring.mybatis.mapper.NewsMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @author wangjun
 * @date 2020-08-01
 */
public class SqlSessionFactoryWithoutXml {
    public static void main(String[] args) {
        DataSource dataSource = DataSourceFactory.getDataSource();
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment development = new Environment("development", jdbcTransactionFactory, dataSource);
        Configuration configuration = new Configuration(development);
        configuration.addMapper(NewsMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            Integer result = mapper.selectIds();
            System.out.println(result);
        }
    }
}
