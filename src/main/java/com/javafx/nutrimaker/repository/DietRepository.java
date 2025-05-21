package com.javafx.nutrimaker.repository;

import com.javafx.nutrimaker.database.DatabaseClient;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class DietRepository {
    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/diet/";
    private final Gson gson = new Gson();

    //Obtener todas las dietas
    public void getAllDiets(DatabaseClient.ResponseCallback callback) {
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(BASE_URL, null, callback);
    }
    //Obtener dieta por id
    public void getDietById(int dietId, DatabaseClient.ResponseCallback callback) {
        String url = BASE_URL + dietId;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }
    //Crear Dieta
    public void createDiet(Map<String, Object> dietData, DatabaseClient.ResponseCallback callback) {
        // diet_id y creation_date no deben incluirse en dietData
        String json = gson.toJson(dietData);
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.post(BASE_URL, json, defaultHeaders(), callback);
    }
    //Modificar dieta
    public void updateDiet(int dietId, Map<String, Object> dietData, DatabaseClient.ResponseCallback callback) {
        String json = gson.toJson(dietData);
        String url = BASE_URL + dietId;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.put(url, json, defaultHeaders(), callback);
    }
    // Eliminar dieta por diet_id
    public void deleteDiet(int dietId, DatabaseClient.ResponseCallback callback) {
        String url = BASE_URL + dietId;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.delete(url, defaultHeaders(), callback);
    }

    // Buscar dietas por user_id
    public void getDietsByUserId(int userId, DatabaseClient.ResponseCallback callback) {
        String query = "{\"user_id\":" + userId + "}";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = BASE_URL + "?q=" + encodedQuery;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }

    // Buscar dietas por patient_id
    public void getDietsByPatientId(int patientId, DatabaseClient.ResponseCallback callback) {
        String query = "{\"patient_id\":" + patientId + "}";
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = BASE_URL + "?q=" + encodedQuery;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }

    private Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
