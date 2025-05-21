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

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public Double getCalories() {
        return calories;
    }

    public Double getProtein() {
        return protein;
    }

    public Double getCalcium() {
        return calcium;
    }

    public Double getSodium() {
        return sodium;
    }

    public Double getIron() {
        return iron;
    }

    public Double getFats() {
        return fats;
    }
    
    
}
