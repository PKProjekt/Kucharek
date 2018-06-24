package com.pk.Kucharek.model.repository;

import com.pk.Kucharek.model.repository.impl.IngredientRepository;
import com.pk.Kucharek.model.repository.impl.RateRepository;
import com.pk.Kucharek.model.repository.impl.RecipeRepository;
import com.pk.Kucharek.model.repository.impl.UnitRepository;

public class RepositoryFactory {

    public static AbstractDAO getRepository(String criteria) {
        if (criteria.equals("Ingredient")) {
            return new IngredientRepository();
        } else if (criteria.equals("Rate")) {
            return new RateRepository();
        } else if (criteria.equals("Recipe")) {
            return new RecipeRepository();
        } else if (criteria.equals("Unit")) {
            return new UnitRepository();
        } else {
            return null;
        }
    }
}
