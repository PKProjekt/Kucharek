package com.pk.Kucharek.model.repository.impl;

import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.database.DBManager;
import com.pk.Kucharek.model.repository.AbstractDAO;

import javax.persistence.EntityManager;

public class RecipeRepository implements AbstractDAO<Recipe, Long> {

    private final EntityManager entityManager;

    public RecipeRepository() {
        entityManager = DBManager.getInstance();
    }

    @Override
    public Recipe save(Recipe recipe) {
        entityManager.getTransaction().begin();
        entityManager.persist(recipe);
        recipe.getIngredients().forEach(recipeIngredient -> {
            recipeIngredient.setRecipe(recipe);
            entityManager.persist(recipeIngredient);
        });
        entityManager.getTransaction().commit();

        return recipe;
    }
}
