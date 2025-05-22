package com.javafx.nutrimaker.repository;

import com.google.gson.JsonParser;
import com.javafx.nutrimaker.database.DatabaseClient;
import com.google.gson.Gson;
import com.google.gson.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import com.javafx.nutrimaker.models.*;

public class DietRepository {
    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/diet/";
    private final Gson gson = new Gson();
    private final DatabaseClient dbClient = new DatabaseClient();

    public List<DietSummary> getDiets(int offset, int limit, int user_id) throws IOException {
        String getsDietsURL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/dietapi/diets?limit=10&offset=0&user_id=1";
        String urlWithParams = getsDietsURL + "?offset=" + offset + "&limit=" + limit + "&user_id=" + user_id;
        String json = dbClient.get(urlWithParams, null);

        Gson gson = new Gson();
        DietSummaryResponse response = gson.fromJson(json, DietSummaryResponse.class);

        // Formatear la fecha a solo AAAA-MM-DD
        for (DietSummary diet : response.getItems()) {
            String fullDate = diet.getCreationDate();
            if (fullDate != null && fullDate.contains("T")) {
                diet.setCreationDate(fullDate.split("T")[0]);
            }
        }

        return response.getItems();
    }

    public Diet getDietObjectById(int dietId) throws IOException, ParseException {
        String urlDietById = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/dietapi_access/diets/access?dietId=" + dietId;
        String json = dbClient.get(urlDietById, null);

        return buildDietFromFlatJson(json);
    }



    public int getTotalDietsCount() throws IOException {
        String url = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/dietapi_count/diets/count";
        String json = dbClient.get(url, null);

        // Parsear el JSON para obtener total_diets
        return JsonParser.parseString(json)
                .getAsJsonObject()
                .getAsJsonArray("items")
                .get(0)
                .getAsJsonObject()
                .get("total_diets")
                .getAsInt();
    }


    // Obtener dieta por id
    public String getDietById(int dietId) throws IOException {
        String url = BASE_URL + dietId;
        return dbClient.get(url, null);
    }

    // Crear Dieta
    public String createDiet(Map<String, Object> dietData) throws IOException {
        String json = gson.toJson(dietData);
        return dbClient.post(BASE_URL, json, defaultHeaders());
    }

    // Modificar dieta
    public String updateDiet(int dietId, Map<String, Object> dietData) throws IOException {
        String json = gson.toJson(dietData);
        String url = BASE_URL + dietId;
        return dbClient.put(url, json, defaultHeaders());
    }

    // Eliminar dieta por diet_id
    public String deleteDiet(int dietId) throws IOException {
        String url = BASE_URL + dietId;
        return dbClient.delete(url, defaultHeaders());
    }

    // Buscar dietas por user_id
    public String getDietsByUserId(int userId) throws IOException {
        String query = "{\"user_id\":" + userId + "}";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = BASE_URL + "?q=" + encodedQuery;
        return dbClient.get(url, null);
    }

    // Buscar dietas por patient_id
    public String getDietsByPatientId(int patientId) throws IOException {
        String query = "{\"patient_id\":" + patientId + "}";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = BASE_URL + "?q=" + encodedQuery;
        return dbClient.get(url, null);
    }

    private Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }



    public Diet buildDietFromFlatJson(String json) throws ParseException {
        JsonObject root = JsonParser.parseString(json).getAsJsonObject(); // ✅ parsea como objeto
        JsonArray rows = root.getAsJsonArray("items"); // ✅ accede al array de "items"

        Diet diet = new Diet();
        Map<Integer, Meal> mealsMap = new HashMap<>();

        for (JsonElement elem : rows) {
            JsonObject row = elem.getAsJsonObject();

            // Asignar datos generales de la dieta (sólo una vez)
            if (diet.getDietID() == 0) {
                diet.setDietID(row.get("diet_id").getAsInt());
                String fullDate = row.get("creation_date").getAsString();
                String dateOnly = fullDate.split("T")[0];

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOnly);
                diet.setCreationDate(date);
                diet.setCalories(row.get("calories").getAsDouble());
                diet.setProtein(row.get("protein").getAsDouble());
                diet.setCalcium(row.get("calcium").getAsDouble());
                diet.setSodium(row.get("sodium").getAsDouble());
                diet.setIron(row.get("iron").getAsDouble());
                diet.setFats(row.get("fat").getAsDouble());

                Patient patient = new Patient(row.get("patient_name").getAsString(), row.get("age").getAsInt(), row.get("weight").getAsDouble(), row.get("height").getAsDouble(), row.get("patient_id").getAsInt());
                diet.setPatient(patient);
            }

            // Obtener meal id y verificar si ya existe
            int mealBaseId = row.get("meal_base_id").getAsInt();
            Meal meal = mealsMap.get(mealBaseId);
            if (meal == null) {
                meal = new Meal();
                meal.setMealBaseId(mealBaseId);
                meal.setName(row.get("meal_name").getAsString());
                meal.setIngredients(new ArrayList<>());

                // Fecha del día de la comida
                String dayString = row.get("day").getAsString(); // Ej: "2025-05-21T00:00:00Z"
                Date mealDate = Date.from(Instant.parse(dayString));
                meal.setDay(mealDate);

                // Hora del día
                String timeString = row.get("time_of_day").getAsString(); // Ej: "2025-05-01T08:00:00Z"
                Date mealTime = Date.from(Instant.parse(timeString));
                meal.setTimeOfDay(mealTime);

                // Tipo, grupo, etc.
                meal.setMealType(row.get("meal_type").getAsString());
                meal.setMealGroup(row.get("meal_group").getAsString());

                // Nutrimentos
                meal.setCalories(row.get("meal_calories").getAsDouble());
                meal.setFat(row.get("meal_fat").getAsDouble());
                meal.setCholesterol(row.get("cholesterol").getAsDouble());
                meal.setSodium(row.get("meal_sodium").getAsDouble());
                meal.setCarbohydrates(row.get("carbohydrates").getAsDouble());
                meal.setProtein(row.get("meal_protein").getAsDouble());
                meal.setCalcium(row.get("meal_calcium").getAsDouble());
                meal.setIron(row.get("meal_iron").getAsDouble());

                mealsMap.put(mealBaseId, meal);
            }

            // Obtener ingrediente (si existe)
            if (row.has("ingredient_name") && !row.get("ingredient_name").isJsonNull()) {
                String ingredientName = row.get("ingredient_name").getAsString();

                boolean ingredientExists = meal.getIngredients().stream()
                        .anyMatch(i -> i.getName().equalsIgnoreCase(ingredientName));

                if (!ingredientExists) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(ingredientName);

                    if (row.has("ingredient_amount") && !row.get("ingredient_amount").isJsonNull()) {
                        ingredient.setAmount(row.get("ingredient_amount").getAsDouble());
                    }

                    // Agrega otros atributos si están disponibles
                    meal.getIngredients().add(ingredient);
                }
            }
        }

        // Finalmente setear la lista de meals en la dieta
        diet.setMeals(new ArrayList<>(mealsMap.values()));

        return diet;
    }

}



