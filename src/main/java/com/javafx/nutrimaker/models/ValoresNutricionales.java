package com.javafx.nutrimaker.models;

public class ValoresNutricionales {
    private double calories;
    private double fat;
    private double cholesterol;
    private double sodium;
    private double carbohydrates;
    private double protein;
    private double calcium;
    private double iron;

    public ValoresNutricionales() {
        this.calories = 0;
        this.fat = 0;
        this.cholesterol = 0;
        this.sodium = 0;
        this.carbohydrates = 0;
        this.protein = 0;
        this.calcium = 0;
        this.iron = 0;
    }

    // Método para agregar valores de un MealBase
    public void agregar(Meal meal) {
        if (meal != null) {
            this.calories += meal.getCalories();
            this.fat += meal.getFat();
            this.cholesterol += meal.getCholesterol();
            this.sodium += meal.getSodium();
            this.carbohydrates += meal.getCarbohydrates();
            this.protein += meal.getProtein();
            this.calcium += meal.getCalcium();
            this.iron += meal.getIron();
        }
    }

    // Getters y Setters
    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }
}
