package com.pk.Kucharek.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredient> recipes;

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

    public Set<RecipeIngredient> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredient> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
