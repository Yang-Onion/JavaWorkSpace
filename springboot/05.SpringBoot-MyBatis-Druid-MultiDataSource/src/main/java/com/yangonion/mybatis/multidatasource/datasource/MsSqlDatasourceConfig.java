package com.yangonion.mybatis.multidatasource.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MsSqlDatasourceConfig.PACKAGE,sqlSessionFactoryRef = "mssqlSqlSessionFactory")
public class MsSqlDatasourceConfig  {
    static final  String PACKAGE ="com.yangonion.mybatis.multidatasource.datasource.dao.mssqldao";
    static final  String MAPPER_LOCATION ="classpath:mapper/mssql/*.xml";

    @Primary
    @Bean("mssqldatasource")
    @ConfigurationProperties("spring.datasource.druid.sqlserver")
    public DataSource mssqlDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("mssqlTransactionManager")
    @Primary
    public DataSourceTransactionManager mssqlTransactionManager(){
        return  new DataSourceTransactionManager(mssqlDataSource());
    }

    @Bean("mssqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mssqlSqlSessionFactory(@Qualifier("mssqldatasource") DataSource dataSource) throws  Exception{
        final SqlSessionFactoryBean sessionFactory= new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MsSqlDatasourceConfig.MAPPER_LOCATION));
        return  sessionFactory.getObject();
    }
}
