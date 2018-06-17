package com.pk.Kucharek.service;

import com.pk.Kucharek.model.Ingredient;
import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.RecipeIngredient;
import com.pk.Kucharek.model.Unit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RecipeBuilderTest {

    @Test
    public void shouldReturnCorrectRecipe() {
        RecipeBuilder recipeBuilder = new RecipeBuilder();

        String name = "NewRecipe";
        String author = "Author";
        String description = "Description";

        recipeBuilder.setName(name);
        recipeBuilder.setAuthor(author);
        recipeBuilder.setDescription(description);
        recipeBuilder.addIngredient(new RecipeIngredient(
                new Ingredient(),
                100.0,
                new Unit()
        ));

        Recipe result = recipeBuilder.getRecipe();

        assertEquals(name, result.getName());
        assertEquals(author, result.getAuthor());
        assertEquals(description, result.getDescription());
    }

}