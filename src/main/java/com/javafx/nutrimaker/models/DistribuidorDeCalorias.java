package com.javafx.nutrimaker.models;

import java.time.LocalTime;
import java.util.*;

public class DistribuidorDeCalorias {

    private static final Map<Integer, String[]> tiposComida = Map.of(
            6, new String[]{"BREAKFAST", "SNACK", "LUNCH", "SNACK", "DINNER", "SNACK"},
            5, new String[]{"BREAKFAST", "SNACK", "LUNCH", "SNACK", "DINNER"},
            4, new String[]{"BREAKFAST", "LUNCH", "SNACK", "DINNER"},
            3, new String[]{"BREAKFAST", "LUNCH", "DINNER"},
            2, new String[]{"BREAKFAST", "LUNCH"},
            1, new String[]{"BREAKFAST"}
    );

    private static final Map<Integer, Integer[]> distribucionPorcentual = Map.of(
            6, new Integer[]{25, 10, 22, 10, 22, 11},
            5, new Integer[]{30, 10, 25, 10, 25},
            4, new Integer[]{25, 30, 10, 35},
            3, new Integer[]{30, 40, 30},
            2, new Integer[]{50, 50},
            1, new Integer[]{100}
    );

    public static List<ComidaProgramada> distribuir(double caloriasTotales, int comidasPorDia) {

        String[] tipos = tiposComida.get(comidasPorDia);
        Integer[] porcentajes = distribucionPorcentual.get(comidasPorDia);

        List<ComidaProgramada> resultado = new ArrayList<>();
        for (int i = 0; i < comidasPorDia; i++) {
            double caloriasAsignadas = caloriasTotales * porcentajes[i] / 100.0;
            resultado.add(new ComidaProgramada(tipos[i], caloriasAsignadas));
        }

        return resultado;
    }

    public static List<LocalTime> obtenerHoras(int comidasPorDia) {
        if (comidasPorDia < 1 || comidasPorDia > 6) {
            throw new IllegalArgumentException("El número de comidas por día debe estar entre 1 y 6.");
        }

        LocalTime inicio = LocalTime.of(8, 0);   // 8:00 AM
        LocalTime fin = LocalTime.of(20, 0);     // 8:00 PM

        int minutosTotales = (fin.toSecondOfDay() - inicio.toSecondOfDay()) / 60; // total minutos entre 8AM y 8PM
        int intervalo = minutosTotales / (comidasPorDia - 1 > 0 ? comidasPorDia - 1 : 1);

        List<LocalTime> horarios = new ArrayList<>();
        for (int i = 0; i < comidasPorDia; i++) {
            LocalTime horaComida = inicio.plusMinutes(intervalo * i);
            horarios.add(horaComida);
        }
        return horarios;
    }
}

