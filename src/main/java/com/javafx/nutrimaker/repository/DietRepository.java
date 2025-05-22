package com.javafx.nutrimaker.repository;

import com.google.gson.JsonParser;
import com.javafx.nutrimaker.database.DatabaseClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.javafx.nutrimaker.models.DietSummary;
import com.javafx.nutrimaker.models.DietSummaryResponse;

import java.util.List;

public class DietRepository {
    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/diet/";
    private final Gson gson = new Gson();
    private final DatabaseClient dbClient = new DatabaseClient();

    public List<DietSummary> getDiets(int offset, int limit) throws IOException {
        String getsDietsURL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/dietapi/diets";
        String urlWithParams = getsDietsURL + "?offset=" + offset + "&limit=" + limit;
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
}



