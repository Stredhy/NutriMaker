package com.javafx.nutrimaker.database;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;

public class DatabaseClient {

    private final OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json");

    public DatabaseClient() {
        this.client = new OkHttpClient();
    }

    // GET sincrónico
    public String get(String url, Map<String, String> headers) throws IOException {
        Request.Builder builder = new Request.Builder().url(url).get();
        addHeaders(builder, headers);
        return executeSync(builder.build());
    }

    // POST sincrónico
    public String post(String url, String jsonBody, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        addHeaders(builder, headers);
        return executeSync(builder.build());
    }

    // PUT sincrónico
    public String put(String url, String jsonBody, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request.Builder builder = new Request.Builder().url(url).put(body);
        addHeaders(builder, headers);
        return executeSync(builder.build());
    }

    // DELETE sincrónico
    public String delete(String url, Map<String, String> headers) throws IOException {
        Request.Builder builder = new Request.Builder().url(url).delete();
        addHeaders(builder, headers);
        return executeSync(builder.build());
    }

    // Agregar headers si existen
    private void addHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
    }

    // Ejecuta la petición y devuelve el cuerpo en String o lanza IOException en error HTTP
    private String executeSync(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error HTTP: " + response.code());
            }
            ResponseBody body = response.body();
            return body != null ? body.string() : "";
        }
    }
}

