package com.pk.Kucharek.view;

import com.pk.Kucharek.controller.IngredientController;

import java.util.Scanner;

class IngredientView {

    private final IngredientController ingredientController;
    private final Scanner scanner;

    IngredientView() {
        this.ingredientController = new IngredientController();
        this.scanner = new Scanner(System.in);
    }

    void showIngredients() {
        System.out.println();
        System.out.println("Składniki: ");
        ingredientController.getAll()
                .forEach(System.out::println);
    }

    void addForm() {
        System.out.println();

        System.out.println("Podaj nazwę: ");
        String name = scanner.next();

        ingredientController.add(name);
    }
}
