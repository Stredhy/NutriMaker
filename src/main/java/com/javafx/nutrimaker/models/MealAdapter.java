package com.javafx.nutrimaker.models;

import com.google.gson.*;
import java.util.HashMap;
import java.util.Map;

public class MealAdapter {
    public static Meal adaptAndDeserialize(String originalJson) {
        Gson gson = new Gson();

        // Parse original JSON
        JsonObject original = JsonParser.parseString(originalJson).getAsJsonObject();
        JsonObject adapted = new JsonObject();

        // Mapeo manual de campos
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put("meal_base_id", "meal_base_id");
        keyMap.put("name", "meal_name");
        keyMap.put("meal_type", "base_meal_type");
        keyMap.put("meal_group", "meal_group");
        keyMap.put("calories", "meal_calories");
        keyMap.put("fat", "meal_fat");
        keyMap.put("cholesterol", "meal_cholesterol");
        keyMap.put("sodium", "meal_sodium");
        keyMap.put("carbohydrates", "meal_carbohydrates");
        keyMap.put("protein", "meal_protein");
        keyMap.put("calcium", "meal_calcium");
        keyMap.put("iron", "meal_iron");

        // Aplicar el mapeo
        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            if (original.has(entry.getKey())) {
                adapted.add(entry.getValue(), original.get(entry.getKey()));
            }
        }

        // Deserializar con las claves correctas
        return gson.fromJson(adapted, Meal.class);
    }
}
