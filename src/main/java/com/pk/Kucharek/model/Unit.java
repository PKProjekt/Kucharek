package com.pk.Kucharek.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Unit {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false, length = 3)
    private String symbol;

    @OneToMany(mappedBy = "unit")
    private Set<RecipeIngredient> recipeIngredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
