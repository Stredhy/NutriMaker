package com.javafx.nutrimaker.models;

public class Ingredient {
    private String name;
    private String group;
    private Double calories;
    private Double protein;
    private Double calcium;
    private Double sodium;
    private Double iron;
    private Double fats;
    
    private void copyAll(Ingredient i){
        this.name = i.name;
        this.group = i.group;
        this.calories = i.calories;
        this.protein = i.protein;
        this.calcium = i.calcium;
        this.sodium = i.sodium;
        this.iron = i.iron;
        this.fats = i.fats;
    }
    
    public Ingredient(Ingredient i){
        copyAll(i);
    }
    
    public Ingredient(String nameX, String groupX, Double calo, Double prot, 
            Double calc, Double sodi, Double ironX, Double fat){
        this.name = nameX;
        this.group = groupX;
        this.calories = calo;
        this.protein = prot;
        this.calcium = calc;
        this.sodium = sodi;
        this.iron = ironX;
        this.fats = fat;
    }
}
