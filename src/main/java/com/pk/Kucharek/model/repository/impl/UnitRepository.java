package com.pk.Kucharek.model.repository.impl;

import com.pk.Kucharek.model.Unit;
import com.pk.Kucharek.model.database.DBManager;
import com.pk.Kucharek.model.repository.AbstractDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UnitRepository implements AbstractDAO<Unit, Long> {

    private final EntityManager entityManager;

    public UnitRepository() {
        this.entityManager = DBManager.getInstance();
    }

    @Override
    public Unit findByName(String name, Class cl) {
        TypedQuery<Unit> query = entityManager.createQuery(
                "select u from Unit u where name like :name or symbol like :name",
                Unit.class
        );
        query.setParameter("name", name);

        return query.getSingleResult();
    }
}
