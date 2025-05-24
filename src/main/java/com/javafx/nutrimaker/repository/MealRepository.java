package com.javafx.nutrimaker.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.javafx.nutrimaker.models.*;
import java.io.IOException;
import java.time.*;
import java.util.*;

import com.javafx.nutrimaker.database.DatabaseClient;

public class MealRepository{
    private final DatabaseClient dbClient = new DatabaseClient();
    private final String BASEURL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/mealbase/";
    Gson gson = new Gson();

    public Meal getMealById(int mealBaseId) throws IOException {
        String url = BASEURL + mealBaseId;
        String json = dbClient.get(url, null);

        // Adaptar y deserializar usando el MealAdapter
        return MealAdapter.adaptAndDeserialize(json);
    }

    public boolean createNewDiet(double caloriasTotales, int comidasPorDia, String diaDescanso, int userId, int patientId, String note) {
        try {
            // Paso 1: Distribuir calorías en comidas usando DistribuidorDeCalorias
            List<ComidaProgramada> planComidas = DistribuidorDeCalorias.distribuir(caloriasTotales, comidasPorDia);

            // Paso 2: Para cada comida programada, agregar opciones de alimentos hasta llenar las calorías asignadas
            for (ComidaProgramada comida : planComidas) {
                int caloriasRestantes = (int) comida.getCaloriasAsignadas();
                while (caloriasRestantes > 0) {
                    Meal alimento = getMealsByTypeAndCalories(caloriasRestantes, comida.getTipo());
                    if (alimento == null) break;
                    comida.agregarOpcion(alimento);
                    caloriasRestantes -= alimento.getCalories();
                    if (alimento.getCalories() > caloriasRestantes) break;
                }
            }

            // Paso 3: Sumar valores nutricionales totales de todas las opciones
            ValoresNutricionales totales = new ValoresNutricionales();
            for (ComidaProgramada comida : planComidas) {
                for (Meal alimento : comida.getOpciones()) {
                    totales.agregar(alimento);
                }
            }

            // Paso 4: Insertar nueva dieta en la base de datos
            boolean confirm = sendDiet(userId, patientId, totales, diaDescanso, comidasPorDia, note);
            if (!confirm) return false;

            // Paso 5: Obtener el diet_id recién insertado
            String urlId = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/orden/by";
            String jsonId = dbClient.get(urlId, null);
            JsonObject root = JsonParser.parseString(jsonId).getAsJsonObject();
            JsonArray items = root.getAsJsonArray("items");

            int firstDietId = -1;
            if (items != null && items.size() > 0) {
                JsonObject firstItem = items.get(0).getAsJsonObject();
                firstDietId = firstItem.get("diet_id").getAsInt();
            }

            // Paso 6: Registrar diet_meal por día (excepto día de descanso)
            List<LocalTime> horasDistribuidas = DistribuidorDeCalorias.obtenerHoras(comidasPorDia);
            LocalDate fechaInicio = LocalDate.now();

            for (int i = 0; i < 7; i++) {
                LocalDate diaActual = fechaInicio.plusDays(i);
                String nombreDia = diaActual.getDayOfWeek().toString();
                if (nombreDia.equalsIgnoreCase(diaDescanso)) continue;

                for (int j = 0; j < planComidas.size(); j++) {
                    ComidaProgramada comida = planComidas.get(j);
                    LocalTime hora = horasDistribuidas.get(j);

                    for (Meal alimento : comida.getOpciones()) {
                        Map<String, Object> jsonMap = new HashMap<>();
                        jsonMap.put("diet_id", firstDietId);
                        jsonMap.put("meal_base_id", alimento.getMealBaseId());

                        OffsetDateTime dayUtc = diaActual.atStartOfDay().atOffset(ZoneOffset.UTC);
                        OffsetDateTime timeOfDayUtc = diaActual.atTime(hora).atOffset(ZoneOffset.UTC);

                        jsonMap.put("day", dayUtc.toString());
                        jsonMap.put("time_of_day", timeOfDayUtc.toString());
                        jsonMap.put("meal_type", comida.getTipo());

                        String jsonBody = new Gson().toJson(jsonMap);
                        String endpoint = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/diet_meal/";

                        boolean success = Boolean.parseBoolean(dbClient.post(endpoint, jsonBody, null));
                        if (!success) {
                            System.err.println("Error al insertar diet_meal: " + jsonBody);
                        }
                    }
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean sendDiet(
                                     int userId,
                                     int patientId,
                                     ValoresNutricionales valores,
                                     String restDay, // Puede ser null
                                     int mealsPerDay,
                                     String note) throws IOException {

        // Pasa targetGender tal cual, puede ser null
        DietRequest dieta = new DietRequest(userId, patientId, valores, restDay, mealsPerDay, note);

        // Serializa. Si targetGender es null, no se incluirá en el JSON (por defecto Gson lo omite)
        String json = gson.toJson(dieta);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String url = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/diet/";
        String response = dbClient.post(url, json, headers);

        return response != null && !response.isEmpty();
    }


    public Meal getMealsByTypeAndCalories(int calories, String mealType) throws IOException {
        String baseUrl = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/random/base";
        String url = baseUrl + "?calories=" + calories + "&meal_type=" + mealType;

        Map<String, String> headers = new HashMap<>();

        // Obtener JSON en String
        String responseJson = dbClient.get(url, headers);

        // Parsear con Gson
        JsonObject rootObj = JsonParser.parseString(responseJson).getAsJsonObject();
        JsonArray itemsArray = rootObj.getAsJsonArray("items");

        if (itemsArray == null || itemsArray.size() == 0) {
            throw new IOException("No se encontraron items o el formato es incorrecto");
        }

        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < itemsArray.size(); i++) {
            JsonObject item = itemsArray.get(i).getAsJsonObject();
            if (item.has("meal_base_id")) {
                ids.add(item.get("meal_base_id").getAsInt());
            }
        }


        if (ids.isEmpty()) {
            throw new IOException("No se encontraron meal_base_id válidos");
        }

        // Elegir uno al azar
        Random random = new Random();
        int randomIndex = random.nextInt(ids.size());
        int mealElected = ids.get(randomIndex);

        return getMealById(mealElected);
    }

}
