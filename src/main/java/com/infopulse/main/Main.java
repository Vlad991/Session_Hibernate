package com.infopulse.main;

import com.infopulse.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("hibernate.cfg.xml")) // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();


            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Client client = new Client();
            client.setName("Vasya");
            client.setSurname("Pupkin");
            session.save(client);
            Client client1 = new Client();
            client1.setName("Petya");
            client1.setSurname("Petrov");
            session.save(client1);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
