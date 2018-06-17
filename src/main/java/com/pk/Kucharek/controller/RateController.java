package com.pk.Kucharek.controller;

import com.pk.Kucharek.model.Rate;
import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.repository.AbstractDAO;
import com.pk.Kucharek.model.repository.impl.RateRepository;

import java.util.List;

public class RateController {

    private AbstractDAO rateRepository;

    public RateController() {
        this.rateRepository = new RateRepository();
    }

    public boolean add(Recipe recipe, Double value) {
        Rate rate = new Rate();
        rate.setRecipe(recipe);
        rate.setValue(value);

        return rateRepository.save(rate) != null;
    }

    public Double getAverageRateForRecipe(Recipe recipe) {
        List<Rate> rates = rateRepository.findAll(Rate.class);

        Double sum = rates.stream()
                .filter(rate -> rate.getRecipe().getId().equals(recipe.getId()))
                .mapToDouble(Rate::getValue)
                .sum();

        long count = rates.stream()
                .filter(rate -> rate.getRecipe().getId().equals(recipe.getId())).count();

        if (count == 0) {
            return 0.0;
        }

        return sum / count;
    }
}
