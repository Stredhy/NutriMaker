package com.javafx.nutrimaker.models;

import javafx.collections.ObservableList;

public class Meal {
    private ObservableList<Ingredient> ingredients;
    private String type;
    private String hour;
    
    private void copyAll(Meal m){
        this.ingredients = m.ingredients;
        this.type = m.type;
        this.hour = m.hour;
    }
    
    public Meal(Meal m){
        copyAll(m);
    }
    
    public Meal(ObservableList<Ingredient> obIngredients, String typeX, String hourX){
        this.ingredients = obIngredients;
        this.type = typeX;
        this.hour = hourX;
    }
}
