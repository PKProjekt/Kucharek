package com.pk.Kucharek.service;

import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.RecipeIngredient;

import java.util.HashSet;
import java.util.Set;

public class RecipeBuilder {

    private final Recipe recipe;
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public RecipeBuilder() {
        recipe = new Recipe();
    }

    public Recipe getRecipe() {
        recipe.setIngredients(ingredients);

        return recipe;
    }

    public void setName(String name) {
        recipe.setName(name);
    }

    public void setAuthor(String author) {
        recipe.setAuthor(author);
    }

    public void setDescription(String description) {
        recipe.setDescription(description);
    }

    public void addIngredient(RecipeIngredient recipeIngredient) {
        ingredients.add(recipeIngredient);
    }
}
