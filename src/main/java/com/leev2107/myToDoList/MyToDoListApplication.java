package com.leev2107.myToDoList;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, SecurityAutoConfiguration.class })

@EnableTransactionManagement
@ComponentScan(basePackages = "com.leev2107.myToDoList")
@PropertySource({ "classpath:persistence-mysql.properties" })

public class MyToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyToDoListApplication.class, args);
	}

	@Autowired
	private Environment env;

	// set up entity factory
	@Bean(name = "entityManagerFactory")
	public LocalSessionFactoryBean localSessionFactoryBean() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(packagesToScan());
		return sessionFactory;
	}

	// setup data source
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dSource = new ComboPooledDataSource();
		try {
			dSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		dSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dSource.setUser(env.getProperty("jdbc.user"));
		dSource.setPassword(env.getProperty("jdbc.password"));
		dSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		dSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		dSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		return dSource;
	}

	// setup hibernate properties
	private Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.setProperty("hibernate.ddl_auto", "validate ");
		return prop;
	}

	// setup packages
	private String packagesToScan() {
		return env.getProperty("hibernate.packagesToScan");
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
}
