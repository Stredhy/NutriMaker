package com.javafx.nutrimaker.repository;

import com.javafx.nutrimaker.database.DatabaseClient;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;


public class DietRepository {
    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/usuario/";
    private final Gson gson = new Gson();


    public void getAllDiets(DatabaseClient.ResponseCallback callback) {
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(BASE_URL, null, callback);
    }

    public void getDietById(int id, DatabaseClient.ResponseCallback callback) {
        String url = BASE_URL + "/" + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }

    public void createDiet(Map<String, Object> dietData, DatabaseClient.ResponseCallback callback) {
        String json = gson.toJson(dietData);
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.post(BASE_URL, json, defaultHeaders(), callback);
    }

    public void updateDiet(int id, Map<String, Object> dietData, DatabaseClient.ResponseCallback callback) {
        String json = gson.toJson(dietData);
        String url = BASE_URL + "/" + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.put(url, json, defaultHeaders(), callback);
    }

    public void deleteDiet(int id, DatabaseClient.ResponseCallback callback) {
        String url = BASE_URL + "/" + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.delete(url, defaultHeaders(), callback);
    }
}
