package db.services.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import db.entity.*;
import org.hibernate.service.ServiceRegistry;

public class DBServiceHibernate {
    private static final String HIBERNATE_SHOW_SQL = "true";
    //создаем таблицу если ее не существует, если существует не трогаем.
    private static final String HIBERNATE_HBM_2_DDL_AUTO = "create-drop";

    private final SessionFactory SESSION_FACTORY = createSessionFactory(getH2Configuration());

    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
    //конфигурация подключения к базе.
    private static Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/test");
        configuration.setProperty("hibernate.connection.username", "tully");
        configuration.setProperty("hibernate.connection.password", "tully");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM_2_DDL_AUTO);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
