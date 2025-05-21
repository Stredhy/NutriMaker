package com.javafx.nutrimaker.database;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public class DatabaseClient {

    private final OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json");

    public interface ResponseCallback {
        void onSuccess(String responseBody);
        void onError(Exception e);
    }

    public DatabaseClient() {
        this.client = new OkHttpClient();
    }

    // GET
    public void get(String url, Map<String, String> headers, ResponseCallback callback) {
        Request.Builder builder = new Request.Builder().url(url).get();
        addHeaders(builder, headers);
        executeAsync(builder.build(), callback);
    }

    // POST
    public void post(String url, String jsonBody, Map<String, String> headers, ResponseCallback callback) {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        addHeaders(builder, headers);
        executeAsync(builder.build(), callback);
    }

    // PUT
    public void put(String url, String jsonBody, Map<String, String> headers, ResponseCallback callback) {
        RequestBody body = RequestBody.create(jsonBody, JSON);
        Request.Builder builder = new Request.Builder().url(url).put(body);
        addHeaders(builder, headers);
        executeAsync(builder.build(), callback);
    }

    // DELETE
    public void delete(String url, Map<String, String> headers, ResponseCallback callback) {
        Request.Builder builder = new Request.Builder().url(url).delete();
        addHeaders(builder, headers);
        executeAsync(builder.build(), callback);
    }

    // Utilidad: Agregar headers opcionales
    private void addHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(builder::addHeader);
        }
    }

    // Ejecutar de forma asíncrona
    private void executeAsync(Request request, ResponseCallback callback) {
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call,@NotNull IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(@NotNull Call call,@NotNull Response response) throws IOException {
                try (ResponseBody body = response.body()) {
                    if (!response.isSuccessful()) {
                        callback.onError(new IOException("Error HTTP: " + response.code()));
                        return;
                    }
                    callback.onSuccess(body != null ? body.string() : "");
                }
            }
        });
    }
}
