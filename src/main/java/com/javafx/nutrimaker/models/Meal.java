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

    public void setIngredients(ObservableList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public ObservableList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getType() {
        return type;
    }

    public String getHour() {
        return hour;
    }
    
    
}
