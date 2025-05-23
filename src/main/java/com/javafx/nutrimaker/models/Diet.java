package com.javafx.nutrimaker.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Diet {
    private int dietID;
    private Patient patient;
    private List<Meal> meals;
    @SerializedName("creation_date")
    private Date creationDate;
    private Double calories;
    private Double protein;
    private Double calcium;
    private Double sodium;
    private Double iron;
    @SerializedName("fat")
    private Double fats;
    private String comments;
    
    public Diet() {

    }

    private void copyAll(Diet d){
        this.dietID = d.dietID;
        this.patient = d.patient;
        this.meals = d.meals;
        this.creationDate = d.creationDate;
        this.calories = d.calories;
        this.protein = d.protein;
        this.calcium = d.calcium;
        this.sodium = d.sodium;
        this.iron = d.iron;
        this.fats = d.fats;
        this.comments = d.comments;
    }
    
    public Diet(Diet d){
        copyAll(d);
    }

    public Diet(int dietID,Patient pat, List<Meal> meals, Date date, Double calo, Double prot,
                Double calc, Double sodi, Double ironX, Double fat,String comment){
        this.dietID = dietID;
        this.patient = pat;
        this.meals = meals;
        this.creationDate = date;
        this.calories = calo;
        this.protein = prot;
        this.calcium = calc;
        this.sodium = sodi;
        this.iron = ironX;
        this.fats = fat;
        this.comments = comment;
    }

    public void setDietID(int dietID) {
        this.dietID = dietID;
    }


    public int getDietID() {
        return dietID;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }
    
}
