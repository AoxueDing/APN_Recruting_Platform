package com.altomni.apn.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.altomni.apn")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.altomni.apn.repository", entityManagerFactoryRef = "entityManager", transactionManagerRef = "transactionManager")
public class AppConfiguration {
	@Resource
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);
		em.setJpaVendorAdapter(vendorAdapter);
		em.setPackagesToScan("com.altomni.apn.model");
		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager().getObject());
		return transactionManager;
	}
}