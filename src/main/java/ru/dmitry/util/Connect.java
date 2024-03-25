package ru.dmitry.util;



import javax.persistence.EntityManager;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ru.dmitry.model.User;

public class Connect {
    private static final String URL = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static EntityManager getEntityManager() {
        EntityManager entityManager = null;

        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "");

            configuration.setProperties(settings).addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            entityManager = configuration.buildSessionFactory(serviceRegistry).createEntityManager();

        } catch (HibernateException s) {
            s.printStackTrace();
        }
        return entityManager;
    }
}