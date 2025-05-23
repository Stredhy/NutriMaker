package com.javafx.nutrimaker.repository;

import com.javafx.nutrimaker.database.DatabaseClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Collections;

public class UserRepository {

    private static final String BASE_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/useraccount/";
    private final DatabaseClient dbClient;

    public UserRepository() {
        this.dbClient = new DatabaseClient();
    }

    // 1. Insertar nuevo usuario
    // Función para insertar usuario con contraseña hasheada
    public boolean insertUser(String email, String plainPassword) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password", hashedPassword);

        try {
            dbClient.post(BASE_URL, json.toString(), null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Obtener ID del usuario por correo
    public Integer getIdByEmail(String email) {
        String url = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/user_idcorreo/user/idcorreo?email=" + email;

        try {
            String response = dbClient.get(url, Collections.emptyMap());
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            JsonArray items = json.getAsJsonArray("items");
            if (items != null && items.size() > 0) {
                JsonObject user = items.get(0).getAsJsonObject();
                return user.get("user_id").getAsInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Obtener contraseña por correo
    // Función para verificar contraseña (envía correo y contraseña, compara con hash)
    public boolean verifyPasswordByEmail(String email, String plainPassword) {
        try {
            String url = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/user_password/user/password?email=";
            String response = dbClient.get(url + email, null);
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
            JsonArray items = jsonResponse.getAsJsonArray("items");

            if (items.size() > 0) {
                String hashedPassword = items.get(0).getAsJsonObject().get("password").getAsString();
                System.out.println("Hash recibido desde BD: " + hashedPassword);
                return BCrypt.checkpw(plainPassword, hashedPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
