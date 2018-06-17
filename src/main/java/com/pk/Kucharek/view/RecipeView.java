package com.pk.Kucharek.view;

import com.pk.Kucharek.controller.IngredientController;
import com.pk.Kucharek.controller.RateController;
import com.pk.Kucharek.controller.RecipeController;
import com.pk.Kucharek.controller.UnitController;
import com.pk.Kucharek.model.Recipe;
import com.pk.Kucharek.model.RecipeIngredient;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class RecipeView {

    private final RecipeController recipeController;
    private final IngredientController ingredientController;
    private final UnitController unitController;
    private final RateController rateController;
    private final IngredientView ingredientView;

    private final Scanner scanner;

    RecipeView() {
        this.recipeController = new RecipeController();
        this.ingredientController = new IngredientController();
        this.unitController = new UnitController();
        this.rateController = new RateController();
        this.ingredientView = new IngredientView();

        this.scanner = new Scanner(System.in);

        showMenu();
        executeOption(scanner.nextInt());
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Przepisy.");
        System.out.println("1. Dodaj");
        System.out.println("2. Szukaj / oceniaj");
        System.out.println("3. Wróć do Menu głównego");
    }

    private void executeOption(Integer option) {
        switch (option) {
            case 1:
                addRecipe();
                break;
            case 2:
                searchAndRate();
                break;
            case 3:
                backToMainMenu();
                break;
        }
    }

    private void addRecipe() {
        System.out.println("Podaj nazwę: ");
        String name = scanner.next();

        System.out.println("Podaj autora: ");
        String author = scanner.next();

        System.out.println("Podaj sposób przygotowania: ");
        StringBuilder description = new StringBuilder();
        String word = "";
        while (!word.equals("END")) {
            word = scanner.next();
            description.append(word);
            description.append(" ");
        }

        Set<RecipeIngredient> recipeIngredientSet = addIngredients();

        recipeController.add(name, author, description.toString(), recipeIngredientSet);

        showMenu();
        executeOption(scanner.nextInt());
    }

    private Set<RecipeIngredient> addIngredients() {
        Set<RecipeIngredient> ingredients = new HashSet<>();

        boolean next = true;
        while (next) {
            System.out.println("Wybierz składnik z listy");
            showIngredients();
            System.out.println("(numer ilość jednostka): ");

            String[] input = new String[3];
            input[0] = scanner.next();
            input[1] = scanner.next();
            input[2] = scanner.next();

            ingredients.add(new RecipeIngredient(
                    ingredientController.getById(Long.valueOf(input[0])),
                    Double.valueOf(input[1]),
                    unitController.getByNameOrSymbol(input[2])
            ));

            System.out.println("Następny skłądnik? (1 - Tak / 2 - Nie");
            next = scanner.nextInt() == 1;
        }

        return ingredients;
    }

    private void showIngredients() {
        ingredientView.showIngredients();
    }

    private void searchAndRate() {
        showRecipes();
        System.out.println("Brak składniku na liscie? (-1. Dodaj nowy)");
        System.out.println("0. Wróć");
        System.out.println("Wybierz numer: ");

        int option = scanner.nextInt();

        Recipe selectedRecipe;
        if (option == -1) {
            ingredientView.addForm();
            searchAndRate();
        }
        else if (option == 0) {
            showMenu();
            executeOption(scanner.nextInt());
            return;
        }
        selectedRecipe = recipeController.getById((long) option);
        showFullDescription(selectedRecipe);

        System.out.println("1. Oceń");
        System.out.println("2. Wróc");

        if (scanner.nextInt() == 1) {
            System.out.println("Podaj ocene (1 - 5): ");
            Double value = Double.valueOf(scanner.next());
            rateController.add(selectedRecipe, value);
        }
        showMenu();
        executeOption(scanner.nextInt());
    }

    private void showRecipes() {
        recipeController.getAll()
                .forEach(System.out::println);
    }

    private void showFullDescription(Recipe recipe) {
        System.out.println();
        System.out.println("Nazwa: " + recipe.getName());
        System.out.println("Autor: " + recipe.getAuthor());
        System.out.println("List składników: ");
        recipe.getIngredients()
                .forEach(recipeIngredient ->
                        System.out.println(recipeIngredient.getIngredient().getName()));
        System.out.println(recipe.getDescription());
        System.out.println("Srednia ocena: " + rateController.getAverageRateForRecipe(recipe));
    }

    private void backToMainMenu() {
        new MainMenu();
    }
}
