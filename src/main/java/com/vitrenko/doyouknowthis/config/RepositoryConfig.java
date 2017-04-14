package com.vitrenko.doyouknowthis.config;

import com.vitrenko.doyouknowthis.domain.repository.QuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = QuestionRepository.class)
public class RepositoryConfig {

    public static final String JNDI_NAME = "jdbc/dataSource";

    @Bean
    public JndiObjectFactoryBean jndiObjectFactoryBean() {
        JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
        factory.setProxyInterface(DataSource.class);
        factory.setJndiName(JNDI_NAME);
        factory.setResourceRef(true);
        factory.setLookupOnStartup(true);
        return factory;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        jpaVendorAdapter.getJpaPropertyMap().put("javax.persistence.schema-generation.database.action", "drop-and-create");
        return jpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("com.vitrenko.doyouknowthis.domain.entity");
        entityManagerFactory.setJpaProperties(hibirnateProperties());
        return entityManagerFactory;
    }

    public Properties hibirnateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        return properties;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
