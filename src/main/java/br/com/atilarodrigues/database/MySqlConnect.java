package br.com.atilarodrigues.database;

import br.com.atilarodrigues.model.CepModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;

public class MySqlConnect {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
                Dotenv dotenv = Dotenv.load();
                Configuration config = new Configuration()
                        .configure(MySqlConnect.class.getResource("/hibernate.cfg.xml"));
                config.setProperty("hibernate.connection.url", dotenv.get("DATABASE_URL"));
                config.setProperty("hibernate.connection.username", dotenv.get("DATABASE_USER"));
                config.setProperty("hibernate.connection.password", dotenv.get("DATABASE_PASSWORD"));
                config.addAnnotatedClass(CepModel.class);
                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(config.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);

            }

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Criação da Sessão falhou." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}