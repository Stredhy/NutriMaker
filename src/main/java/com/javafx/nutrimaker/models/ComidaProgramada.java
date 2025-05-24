package com.javafx.nutrimaker.models;

import java.util.ArrayList;
import java.util.List;

public class ComidaProgramada {
    private String tipo;  // BREAKFAST, LUNCH, DINNER, SNACK
    private double caloriasAsignadas;
    private List<Meal> opciones;

    public ComidaProgramada(String tipo, double caloriasAsignadas) {
        this.tipo = tipo;
        this.caloriasAsignadas = caloriasAsignadas;
        this.opciones = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCaloriasAsignadas() {
        return caloriasAsignadas;
    }

    public void setCaloriasAsignadas(double caloriasAsignadas) {
        this.caloriasAsignadas = caloriasAsignadas;
    }

    public List<Meal> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Meal> opciones) {
        this.opciones = opciones;
    }

    public void agregarOpcion(Meal mealBase) {
        this.opciones.add(mealBase);
    }
}
