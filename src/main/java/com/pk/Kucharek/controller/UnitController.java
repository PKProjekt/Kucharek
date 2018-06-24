package com.pk.Kucharek.controller;

import com.pk.Kucharek.model.Unit;
import com.pk.Kucharek.model.repository.AbstractDAO;
import com.pk.Kucharek.model.repository.RepositoryFactory;
import com.pk.Kucharek.model.repository.impl.UnitRepository;

public class UnitController {

    private final AbstractDAO unitRepository;

    public UnitController() {
        this.unitRepository = RepositoryFactory.getRepository("Unit");
    }

    public Unit getByNameOrSymbol(String name) {
        return (Unit) unitRepository.findByName(name, Unit.class);
    }
}
