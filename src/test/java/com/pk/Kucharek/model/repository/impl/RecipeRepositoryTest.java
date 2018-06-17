package com.pk.Kucharek.model.repository.impl;

import com.pk.Kucharek.model.Ingredient;
import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.RecipeIngredient;
import com.pk.Kucharek.model.Unit;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RecipeRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    private static RecipeRepository recipeRepository = new RecipeRepository();

    @BeforeClass
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("kucharekTest");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterClass
    public static void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Unit unit;

    @Before
    public void addDataToTest() {
        ingredient1 = new Ingredient();
        ingredient1.setName("a");

        ingredient2 = new Ingredient();
        ingredient2.setName("b");

        unit = new Unit();
        unit.setName("gram");
        unit.setSymbol("g");

        entityManager.getTransaction().begin();
        entityManager.persist(ingredient1);
        entityManager.persist(ingredient2);
        entityManager.persist(unit);
        entityManager.getTransaction().commit();
    }

    @After
    public void clearTable() {
        entityManager.getTransaction().begin();
        entityManager.remove(ingredient1);
        entityManager.remove(ingredient2);
        entityManager.remove(unit);
        entityManager.getTransaction().commit();
    }

    @Test
    public void shouldAddRecipe() {
        Recipe recipe = new Recipe();

        String name = "NewRecipe";
        String author = "Author";
        String description = "Recipe description";

        recipe.setName(name);
        recipe.setAuthor(author);
        recipe.setDescription(description);
        recipe.setIngredients(getDefaultIngredients());

        assertEquals(recipe, recipeRepository.save(recipe));
    }

    @Test(expected = IllegalStateException.class)
    public void ingredientsListShouldNotBeNull() {
        Recipe recipe = new Recipe();

        String name = "NewRecipe2";
        String author = "Author2";
        String description = "Recipe description2";

        recipe.setName(name);
        recipe.setAuthor(author);
        recipe.setDescription(description);

        assertEquals(recipe, recipeRepository.save(recipe));
    }

    @Test(expected = IllegalStateException.class)
    public void nameShouldNotBeNull() {
        Recipe recipe = new Recipe();

        String author = "Author3";
        String description = "Recipe description3";

        recipe.setAuthor(author);
        recipe.setDescription(description);
        recipe.setIngredients(getDefaultIngredients());

        assertEquals(recipe, recipeRepository.save(recipe));
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void authorShouldNotBeNull() {
        Recipe recipe = new Recipe();

        String name = "Name4";
        String description = "Recipe description3";

        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setIngredients(getDefaultIngredients());

        assertEquals(recipe, recipeRepository.save(recipe));
    }

    private Set<RecipeIngredient> getDefaultIngredients() {
        Set<RecipeIngredient> recipeIngredients = new HashSet<>(2);

        recipeIngredients.add(new RecipeIngredient(
                ingredient1,
                200.0,
                unit
        ));
        recipeIngredients.add(new RecipeIngredient(
                ingredient2,
                100.0,
                unit
        ));

        return recipeIngredients;
    }
}