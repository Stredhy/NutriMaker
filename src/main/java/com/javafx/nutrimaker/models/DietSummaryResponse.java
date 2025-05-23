package com.javafx.nutrimaker.models;

import java.util.List;

public class DietSummaryResponse {
    private List<DietSummary> items;

    public List<DietSummary> getItems() {
        return items;
    }

    public void setItems(List<DietSummary> items) {
        this.items = items;
    }
}