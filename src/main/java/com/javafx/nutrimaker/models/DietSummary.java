package com.javafx.nutrimaker.models;


import com.google.gson.annotations.SerializedName;

public class DietSummary {
    @SerializedName("diet_id")
    private int dietId;

    @SerializedName("patient_name")
    private String patientName;

    private double weight;
    private double height;

    @SerializedName("creation_date")
    private String creationDate;

    // Getters y Setters
    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
