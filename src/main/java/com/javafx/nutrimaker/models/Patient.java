package com.javafx.nutrimaker.models;

import com.google.gson.annotations.SerializedName;

public class Patient {
    private String name;
    private int age;
    private Double weight;
    private Double height;
    private int patientId;


    private void copyAll(Patient p){
        this.name = p.name;
        this.age = p.age;
        this.weight = p.weight;
        this.height = p.height;
        this.patientId = p.patientId;
    }
    
    public Patient(Patient p){
        copyAll(p);
    }
    
    public Patient(String nameX, int ageX, Double wh, Double hg, int id){
        this.name = nameX;
        this.age = ageX;
        this.weight = wh;
        this.height = hg;
        this.patientId = id;
    }

    public Patient(String name, int age, Double weight, Double height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }
    
}
