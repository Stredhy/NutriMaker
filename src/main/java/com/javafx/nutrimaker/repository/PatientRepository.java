package com.javafx.nutrimaker.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.javafx.nutrimaker.database.DatabaseClient;
import com.javafx.nutrimaker.models.Patient;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PatientRepository {

    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/patient/";
    private final DatabaseClient databaseClient;
    private final Gson gson;

    public PatientRepository() {
        this.databaseClient = new DatabaseClient();
        this.gson = new Gson();
    }

    public int getMostRecentPatientId() throws IOException {
        String url = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/api/patient/recent";
        String json = databaseClient.get(url, null);

        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonArray items = root.getAsJsonArray("items");

        int patientId = -1; // Valor por defecto
        if (items != null && items.size() > 0) {
            JsonObject firstItem = items.get(0).getAsJsonObject();
            patientId = firstItem.get("patient_id").getAsInt();
        }

        return patientId;
    }

    //Obtener todos lo pacientes
    public String getAllPatients() throws IOException {
        return databaseClient.get(BASE_URL, null);
    }
    //Obtener paciente mediante ID
    public String getPatientById(int id) throws IOException {
        String url = BASE_URL + id;
        return databaseClient.get(url, null);
    }

    public boolean createPatient(String name, int age, Double weight, Double height) {
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("age", age);
        json.addProperty("weight", weight);
        json.addProperty("height", height);

        try {
            databaseClient.post(BASE_URL, json.toString(), null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String updatePatient(int id, String name, int age, Double weight, Double height) throws IOException {
        String url = BASE_URL + id;

        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("age", age);
        json.addProperty("weight", weight);
        json.addProperty("height", height);

        return databaseClient.put(url, json.toString(), null);
    }

    public String deletePatient(int id) throws IOException {
        String url = BASE_URL + id;
        return databaseClient.delete(url, null);
    }
}
