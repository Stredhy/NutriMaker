package com.javafx.nutrimaker.models;

import okhttp3.*;

import java.io.IOException;

public class Database {
    private final OkHttpClient client = new OkHttpClient();

    // URL base del endpoint ORDS
    private static final String USUARIOS_URL = "https://g123ac362d4a31c-appnutrimaker.adb.mx-queretaro-1.oraclecloudapps.com/ords/developer/usuario/";

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
                    String respuestaJson = response.body().string();
                    System.out.println("Respuesta JSON:\n" + respuestaJson);
                } else {
                    System.err.println("Error en la respuesta HTTP: " + response.code());
                }
            }
        });
    }
}

