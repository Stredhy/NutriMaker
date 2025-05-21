package com.javafx.nutrimaker.repository;

import com.javafx.nutrimaker.database.DatabaseClient;
import com.javafx.nutrimaker.database.DatabaseClient.ResponseCallback;
import com.google.gson.JsonObject;

import java.util.Map;

public class UserRepository {

    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/usuario/";

    // Obtener todos los usuarios
    public void getAllUsers(ResponseCallback callback) {
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(BASE_URL, null, callback);
    }

    // Obtener usuario por ID
    public void getUserById(int id, ResponseCallback callback) {
        String url = BASE_URL + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }

    // Insertar un nuevo usuario
    public void createUser(String correo, String password, ResponseCallback callback) {
        JsonObject json = new JsonObject();
        json.addProperty("correo", correo);
        json.addProperty("password", password);
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.post(BASE_URL, json.toString(), null, callback);
    }

    // Actualizar usuario existente por ID
    public void updateUser(int id, String correo, String password, ResponseCallback callback) {
        JsonObject json = new JsonObject();
        json.addProperty("correo", correo);
        json.addProperty("password", password);
        String url = BASE_URL + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.put(url, json.toString(), null, callback);
    }

    // Eliminar usuario por ID
    public void deleteUser(int id, ResponseCallback callback) {
        String url = BASE_URL + id;
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.delete(url, null, callback);
    }

    // Buscar usuario por correo
    public void getUserByEmail(String correo, ResponseCallback callback) {
        String url = BASE_URL + "?q={\"correo\":\"" + correo + "\"}";
        DatabaseClient dbClient = new DatabaseClient();
        dbClient.get(url, null, callback);
    }
}
