package com.example.appqr.models;


public class Cart {
    private SkDetail skDetail;
    private int amount;

    public SkDetail getSkDetail() {
        return skDetail;
    }

    public void setSkDetail(SkDetail Detail) {
        this.skDetail = Detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
