package com.example.mycartapplication;

public class CartItem {
    public String name;
    public String imgURL;
    public float price;
    public String description;
    public boolean isAdded = false;
    public boolean isProcessing = false;

    public CartItem(String name, String imgURL, float price) {
        this.name = name;
        this.imgURL = imgURL;
        this.price = price;
    }


}
