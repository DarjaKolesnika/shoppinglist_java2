package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.service.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppinglist")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {

    private ProductNameValidationRule productNameValidationRule;
    private ProductPriceValidationRule productPriceValidationRule;
    private ProductDiscountValidationRule productDiscountValidationRule;
    private ProductUniquenessValidationRule productUniquenessValidationRule;

    @Lazy
    @Autowired
    public AppConfig(ProductNameValidationRule productNameValidationRule, ProductPriceValidationRule
            productPriceValidationRule, ProductDiscountValidationRule productDiscountValidationRule, ProductUniquenessValidationRule
                             productUniquenessValidationRule) {
        this.productNameValidationRule = productNameValidationRule;
        this.productPriceValidationRule = productPriceValidationRule;
        this.productDiscountValidationRule = productDiscountValidationRule;
        this.productUniquenessValidationRule = productUniquenessValidationRule;
    }

    @Bean
    ProductValidationService productValidationService() {
        Set<ProductValidation> validationRules = new HashSet<>();
        validationRules.add(productNameValidationRule);
        validationRules.add(productDiscountValidationRule);
        validationRules.add(productUniquenessValidationRule);
        validationRules.add(productPriceValidationRule);
        return new ProductValidationService(validationRules);
    }

    @Bean("validation for edit")
    ProductValidationServiceForEdit productValidationServiceForEdit() {
        Set<ProductValidation> validationRulesForEdit = new HashSet<>();
        validationRulesForEdit.add(productNameValidationRule);
        validationRulesForEdit.add(productDiscountValidationRule);
        validationRulesForEdit.add(productPriceValidationRule);
        return new ProductValidationServiceForEdit(validationRulesForEdit);
    }

    @Bean
    public DataSource dataSource(
            @Value("${jdbc.url}") String jdbcUrl,
            @Value("${driverClass}") String driverClass,
            @Value("${database.user.name}") String userName,
            @Value("${database.user.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") boolean showSql,
            @Value("${hibernate.format_sql}") boolean formatSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);

        return properties;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource,
                                         @Value("${hibernate.packagesToScan}") String packagesToScan,
                                         Properties hibernateProperties) throws Exception {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan(packagesToScan);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
