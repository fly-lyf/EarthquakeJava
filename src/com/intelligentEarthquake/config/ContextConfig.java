package com.intelligentEarthquake.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FLY on 2016/2/18 0018.
 */

@Configuration
@ComponentScan(
        basePackages = "com.intelligentEarthquake",
        excludeFilters = {
                @ComponentScan.Filter(Controller.class),
                @ComponentScan.Filter(ControllerAdvice.class)
        }
)
@EnableTransactionManagement(
        mode = AdviceMode.PROXY, proxyTargetClass = false,
        order = Ordered.LOWEST_PRECEDENCE
)
@EnableJpaRepositories(
        basePackages = {"com.intelligentEarthquake"},
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "jpaTransactionManager"
//        repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class
)
public class ContextConfig {
    static String driver = "com.mysql.jdbc.Driver";
    static String jdbcUrl = "jdbc:mysql://61.155.7.140:3308/imonitor";
    static String username = "root";
    static String password = "shield";

    //原来的JNDI数据源
    @Bean
    public DataSource jndiDataSource() {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource("java:comp/env/jdbc/cg");
    }

    //c3p0数据源
    @Bean
    public DataSource springJpaDataSource() throws PropertyVetoException {
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        c3p0.setDriverClass(driver);
        c3p0.setJdbcUrl(jdbcUrl);
        c3p0.setUser(username);
        c3p0.setPassword(password);
        return c3p0;
    }

    //容器管理类型的JPA，实现为Hibernate
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws PropertyVetoException {
        //Hibernate适配器
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        //JPA容器
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();

        Map<String, Object> properties = new HashMap<>();
        //do not use schema generation in production
        properties.put("javax.persistence.schema-generation.database.action", "none");
        //properties.put("hibernate.hbm2ddl.auto", "create");
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setDataSource(this.springJpaDataSource());
        factoryBean.setPackagesToScan("com.intelligentEarthquake.entity");
        factoryBean.setValidationMode(ValidationMode.NONE);
        factoryBean.setJpaPropertyMap(properties);

        return factoryBean;
    }

    //JPA事务
    @Bean
    public PlatformTransactionManager jpaTransactionManager() throws PropertyVetoException {
        return new JpaTransactionManager(
                this.entityManagerFactoryBean().getObject()
        );
    }

    //视图解析
    @Bean
    public InternalResourceViewResolver resourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("web/resources/views");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
