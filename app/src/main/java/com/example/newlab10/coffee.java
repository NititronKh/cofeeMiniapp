package com.example.newlab10;

public class coffee {
    private int imageResId;
    private String name;
    private String price;

    public coffee(int imageResId, String name, String price) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    // เพิ่มเมธอด setPrice เพื่ออัปเดตราคา
    public void setPrice(String price) {
        this.price = price;
    }
}
