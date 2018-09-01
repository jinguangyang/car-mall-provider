package com.car.mall.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.car.mall.dao"}, sqlSessionFactoryRef = "hipiaoSqlSessionFactory")
public class HipiaoDataSourceConfig {

    static final String MAPPER_LOCATION = "classpath*:mybatis/mapper/*.xml";

    @Bean(name = "hipiaoDataSource")
    @ConfigurationProperties(prefix = "datasource.mall")
    @Primary
    public DataSource hipiaoDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "hipiaoTransactionManager")
    @Primary
    public DataSourceTransactionManager hipiaoTransactionManager() {
        return new DataSourceTransactionManager(hipiaoDataSource());
    }

    @Bean(name = "hipiaoSqlSessionFactory")
    @Primary
    public SqlSessionFactory hipiaoSqlSessionFactory(@Qualifier("hipiaoDataSource") DataSource hipiaoDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(hipiaoDataSource);
        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(HipiaoDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name="hipiaoJdbcTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier("hipiaoDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
