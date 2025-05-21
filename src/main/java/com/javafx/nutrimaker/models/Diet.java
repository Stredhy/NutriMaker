package com.javafx.nutrimaker.models;

import java.util.Date;

public class Diet {
    private Patient patient;
    private Meal[] meals;
    private Date creationDate;
    private Double calories;
    private Double protein;
    private Double calcium;
    private Double sodium;
    private Double iron;
    private Double fats;
    
    private void copyAll(Diet d){
        this.patient = d.patient;
        this.meals = d.meals;
        this.creationDate = d.creationDate;
        this.calories = d.calories;
        this.protein = d.protein;
        this.calcium = d.calcium;
        this.sodium = d.sodium;
        this.iron = d.iron;
        this.fats = d.fats;
    }
    
    public Diet(Diet d){
        copyAll(d);
    }
    
    public Diet(Patient pat, Meal[] meal, Date date, Double calo, Double prot, 
            Double calc, Double sodi, Double ironX, Double fat){
        this.patient = pat;
        this.meals = meal;
        this.creationDate = date;
        this.calories = calo;
        this.protein = prot;
        this.calcium = calc;
        this.sodium = sodi;
        this.iron = ironX;
        this.fats = fat;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMeals(Meal[] meals) {
        this.meals = meals;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
    

    public Patient getPatient() {
        return patient;
    }

    public Meal[] getMeals() {
        return meals;
    }

    public Date getCreationDate() {
        return creationDate;
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
