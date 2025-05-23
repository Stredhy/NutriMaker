package com.javafx.nutrimaker.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("ingredient_name")
    private String name;
    @SerializedName("ingredient_amount")
    private double amount; // en gramos o mililitros

    public Ingredient(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public Ingredient() {

    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
