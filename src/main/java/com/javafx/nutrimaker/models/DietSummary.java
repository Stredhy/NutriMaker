package com.javafx.nutrimaker.models;


import com.google.gson.annotations.SerializedName;

public class DietSummary {
    private int numDiet;
    
    @SerializedName("diet_id")
    private int dietId;

    @SerializedName("patient_name")
    private String patientName;

    private double weight;
    private double height;

    @SerializedName("creation_date")
    private String creationDate;

    
    private void copyAll(DietSummary ds){
        this.numDiet = ds.numDiet;
        this.dietId = ds.dietId;
        this.patientName = ds.patientName;
        this.weight = ds.weight;
        this.height = ds.height;
        this.creationDate = ds.creationDate;
    }
    
    public DietSummary(DietSummary ds){
        copyAll(ds);
    }
    
    public DietSummary(int num,int dietId, String patientName, double weight, double height, String creationDate) {
        this.numDiet = num;
        this.dietId = dietId;
        this.patientName = patientName;
        this.weight = weight;
        this.height = height;
        this.creationDate = creationDate;
    }
    
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

    public void setNumDiet(int numDiet) {
        this.numDiet = numDiet;
    }

    public int getNumDiet() {
        return numDiet;
    }
}
