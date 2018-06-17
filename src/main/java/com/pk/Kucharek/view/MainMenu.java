package com.pk.Kucharek.view;

import com.pk.Kucharek.model.database.DBManager;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);

        init();
    }

    private void init() {
        showMenu();

        switch (getInput()) {
            case 1:
                goToRecipes();
                break;
            case 2:
                goToIngredients();
                break;
            case 3:
                exit();
                break;
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("Menu główne.");
        System.out.println("1. Przepisy");
        System.out.println("2. Składniki");
        System.out.println("3. Wyjdź");
    }

    private int getInput() {
        System.out.println("Podaj opcję: ");

        return scanner.nextInt();
    }

    private void goToRecipes() {
        new RecipeView();
    }

    private void goToIngredients() {
        IngredientView ingredientView = new IngredientView();
        ingredientView.showIngredients();

        System.out.println("-1. Dodaj");
        System.out.println("0. Wróc");

        Integer option = scanner.nextInt();
        if (option == -1) {
            ingredientView.addForm();
            goToIngredients();
        }
        else if (option == 0) {
            init();
        }
    }

    private void exit() {
        DBManager.closeConnection();
        System.exit(0);
    }
}
