package com.pk.Kucharek.model.repository;

import com.pk.Kucharek.model.database.DBManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface AbstractDAO<T, K> {

    default List<?> findAll(Class cl) {
        EntityManager entityManager = DBManager.getInstance();

        TypedQuery<?> query = entityManager.createQuery(
                "select e from " + cl.getSimpleName() + " e",
                cl
        );

        return query.getResultList();
    }

    default T findById(K id, Class cl) {
        EntityManager entityManager = DBManager.getInstance();

        TypedQuery<?> query = entityManager.createQuery(
                "select e from " + cl.getSimpleName() + " e where e.id like :id",
                cl
        );
        query.setParameter("id", id);

        return (T) query.getSingleResult();
    }

    default T findByName(String name, Class cl) {
        EntityManager entityManager = DBManager.getInstance();

        TypedQuery<?> query = entityManager.createQuery(
                "select e from " + cl.getSimpleName() + " e where e.name like :name",
                cl
        );
        query.setParameter("name", name);

        return (T) query.getSingleResult();
    }

    default T save(T obj) {
        EntityManager entityManager = DBManager.getInstance();

        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return obj;
    }
}
