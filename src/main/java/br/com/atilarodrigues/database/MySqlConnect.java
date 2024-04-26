package br.com.atilarodrigues.database;

import br.com.atilarodrigues.model.CepModel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MySqlConnect {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {

                Configuration config = new Configuration()
                        .configure(MySqlConnect.class.getResource("/hibernate.cfg.xml"));
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