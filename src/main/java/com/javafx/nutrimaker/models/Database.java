package com.javafx.nutrimaker.models;

import okhttp3.*;
import com.google.gson.*;
import java.io.IOException;

public class Database {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private static final String USUARIOS_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/usuario/";

    // 1. LISTAR USUARIOS
    public void listarUsuarios() {
        Request request = new Request.Builder()
                .url(USUARIOS_URL)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("Error al hacer la solicitud: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    System.out.println("Usuarios encontrados:\n" + json);
                } else {
                    System.err.println("Error en la respuesta HTTP: " + response.code());
                }
            }
        });
    }

    // 2. INSERTAR USUARIO
    public void insertarUsuario(String nombre, String email) {
        JsonObject json = new JsonObject();
        json.addProperty("nombre", nombre);
        json.addProperty("email", email);

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(USUARIOS_URL)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                System.err.println("Error al insertar: " + e.getMessage());
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Respuesta al insertar: " + response.code());
                System.out.println(response.body().string());
            }
        });
    }

    // 3. MODIFICAR USUARIO POR ID
    public void modificarUsuario(int id, String nuevoNombre, String nuevoEmail) {
        JsonObject json = new JsonObject();
        json.addProperty("nombre", nuevoNombre);
        json.addProperty("email", nuevoEmail);

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(USUARIOS_URL + id)
                .put(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                System.err.println("Error al modificar: " + e.getMessage());
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Respuesta al modificar: " + response.code());
                System.out.println(response.body().string());
            }
        });
    }

    // 4. ELIMINAR USUARIO POR CORREO (requiere buscar primero el ID)
    public void eliminarUsuarioPorCorreo(String correo) {
        Request request = new Request.Builder()
                .url(USUARIOS_URL)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                System.err.println("Error al buscar usuarios: " + e.getMessage());
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.err.println("Error al consultar usuarios: " + response.code());
                    return;
                }

                String json = response.body().string();
                JsonObject root = JsonParser.parseString(json).getAsJsonObject();
                JsonArray items = root.getAsJsonArray("items");

                for (JsonElement elem : items) {
                    JsonObject usuario = elem.getAsJsonObject();
                    String email = usuario.get("email").getAsString();
                    if (email.equalsIgnoreCase(correo)) {
                        int id = usuario.get("id").getAsInt();
                        eliminarUsuarioPorId(id);
                        return;
                    }
                }

                System.out.println("No se encontró el usuario con correo: " + correo);
            }
        });
    }

    private void eliminarUsuarioPorId(int id) {
        Request request = new Request.Builder()
                .url(USUARIOS_URL + id)
                .delete()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                System.err.println("Error al eliminar usuario: " + e.getMessage());
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Respuesta al eliminar: " + response.code());
                System.out.println(response.body().string());
            }
        });
    }
}
