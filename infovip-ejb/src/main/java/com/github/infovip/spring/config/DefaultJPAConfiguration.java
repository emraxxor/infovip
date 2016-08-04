/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.spring.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static com.github.infovip.xml.DefaultResourceReader.getOption;

/**
 * Configuration for spring jpa-data repositories
 *
 * @author attila
 */
@Configuration
@EnableJpaRepositories("com.github.infovip.spring.repositories")
@EnableTransactionManagement
public class DefaultJPAConfiguration {

    private DriverManagerDataSource ds;

    /**
     * Creates a new EntityManagerFactory that is used by the repositories
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName("infovipPU");
        emf.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaDialect(jpaDialect());
        emf.setPackagesToScan("com.github.infovip.entities");
        emf.setLoadTimeWeaver(new ReflectiveLoadTimeWeaver());
        return emf;
    }

    /**
     * Creates a new post processor that is used by the repositories
     *
     * @return
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor annotationPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    /**
     * Needs for the EntityManagerFactory
     *
     * @return
     */
    @Bean
    public JpaDialect jpaDialect() {
        return new EclipseLinkJpaDialect();
    }

    /**
     * Default transaction-manager that is used by the repositories
     *
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }

    /**
     * Default vendor
     *
     * @return
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
        adapter.setGenerateDdl(false);
        return adapter;
    }

    /**
     * Default data-source
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        if (ds == null) {
            ds = new DriverManagerDataSource();
            ds.setDriverClassName(getOption("driverClass"));
            ds.setUrl(getOption("URL"));
            ds.setUsername(getOption("User"));
            ds.setPassword(getOption("Password"));
        }
        return ds;
    }

}
