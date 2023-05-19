package com.example.appqr.models;


public class Cart {
    private SkDetail skDetail;
    private int amount;

    public SkDetail getMealDetail() {
        return skDetail;
    }

    public void setMealDetail(SkDetail mealDetail) {
        this.skDetail = mealDetail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
