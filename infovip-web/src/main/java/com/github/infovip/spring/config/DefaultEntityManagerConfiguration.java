package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;


/**
 * 
 * @author Attila Barna
 *
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class DefaultEntityManagerConfiguration {

    /**
     * Creates a new EntityManagerFactory that is used by the repositories
     *
     * @return
     */
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("infovipPU");
        emf.setJpaDialect(jpaDialect());
        emf.setPackagesToScan("com.github.infovip.entities");
        emf.setLoadTimeWeaver(new JBossLoadTimeWeaver());
        return emf;
    }
   
    
    /**
     * Default transaction-manager that is used by the repositories
     *
     * @return
     */
    @Bean(name="transactionManager")
    public JtaTransactionManager platformTransactionManager() {
        JtaTransactionManager tm = new JtaTransactionManager();
        tm.setTransactionManagerName("java:/TransactionManager");
        return tm;
    }
    
    @Bean
    public JpaDialect jpaDialect() {
        return new EclipseLinkJpaDialect();
    }
}
