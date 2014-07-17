package br.com.diegoliveira.indiana.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Classe responsável por gerenciar o Hibernate
 * @author Diego Potapczuk
 * @version 0.3
 * @since 0.1
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Método que devolve o singleton SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Método que destroi o singleton SessionFactory
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}