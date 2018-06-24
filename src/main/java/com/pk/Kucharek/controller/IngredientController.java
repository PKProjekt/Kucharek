package com.pk.Kucharek.controller;

import com.pk.Kucharek.model.Ingredient;
import com.pk.Kucharek.model.repository.AbstractDAO;
import com.pk.Kucharek.model.repository.RepositoryFactory;
import com.pk.Kucharek.model.repository.impl.IngredientRepository;

import java.util.List;

public class IngredientController {

    private final AbstractDAO ingredientRepository;

    public IngredientController() {
        this.ingredientRepository = RepositoryFactory.getRepository("Ingredient");
    }

    public List<?> getAll() {
        return ingredientRepository.findAll(Ingredient.class);
    }

    public Ingredient getById(Long id) {
        return (Ingredient) ingredientRepository.findById(id, Ingredient.class);
    }

    public boolean add(String name) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);

        return ingredientRepository.save(ingredient) != null;
    }
}
