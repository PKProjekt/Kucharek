package com.pk.Kucharek.model.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBManager {

    private static final String PERSISTENCE_UNIT_NAME = "kucharek";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private DBManager() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            new DBManager();
        }
        return entityManager;
    }

    public static void closeConnection() {
        if (entityManager != null) {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
