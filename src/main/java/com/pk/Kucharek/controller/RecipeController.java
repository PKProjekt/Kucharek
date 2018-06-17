package com.pk.Kucharek.controller;

import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.RecipeIngredient;
import com.pk.Kucharek.model.repository.AbstractDAO;
import com.pk.Kucharek.model.repository.impl.RecipeRepository;
import com.pk.Kucharek.service.RecipeBuilder;

import java.util.List;
import java.util.Set;

public class RecipeController {

    private final AbstractDAO recipeRepository;

    public RecipeController() {
        this.recipeRepository = new RecipeRepository();
    }

    public boolean add(String name,
                       String author,
                       String description,
                       Set<RecipeIngredient> ingredients) {

        RecipeBuilder recipeBuilder = new RecipeBuilder();
        recipeBuilder.setName(name);
        recipeBuilder.setAuthor(author);
        recipeBuilder.setDescription(description);
        ingredients.forEach(recipeBuilder::addIngredient);

        return recipeRepository.save(recipeBuilder.getRecipe()) != null;
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll(Recipe.class);
    }

    public Recipe getById(Long id) {
        return (Recipe) recipeRepository.findById(id, Recipe.class);
    }
}
