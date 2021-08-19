package com.Spring.stud.demo.factory;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootConfiguration
public class SessionFactoryDAO {
    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
