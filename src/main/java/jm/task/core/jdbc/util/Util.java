package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/test?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getConnection() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size","5")
                    .setProperty("hibernate.c3p0.max_size","200")
                    .setProperty("hibernate.c3p0.max_statements","200");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}