package com.javafx.nutrimaker.models;

public class Patient {
    private String name;
    private int age;
    private Double weight;
    private Double height;
    
    private void copyAll(Patient p){
        this.name = p.name;
        this.age = p.age;
        this.weight = p.weight;
        this.height = p.height;
    }
    
    public Patient(Patient p){
        copyAll(p);
    }
    
    public Patient(String nameX, int ageX, Double wh, Double hg){
        this.name = nameX;
        this.age = ageX;
        this.weight = wh;
        this.height = hg;
    }
}
