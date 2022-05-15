package com.restaurant.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {

    private String id;
    private String name;
    private String address;
    private String phone;
    private float rate;
    private int image;
    private ArrayList<Meals> meals;        // وجبات
    private ArrayList<Meals> appetizers;  // مقبلات
    private ArrayList<Meals> drinks;     // مشروبات
    private ArrayList<Meals> candy;  // حلويات

    public Restaurant() {
    }

//        public Restaurant(String id, String name, int image,
//                      ArrayList<Meals> meals, ArrayList<Meals> appetizers,
//                      ArrayList<Meals> drinks, ArrayList<Meals> candy
//    ) {
//        this.id = id;
//        this.name = name;
//        this.image = image;
//        this.meals = meals;
//        this.appetizers = appetizers;
//        this.drinks = drinks;
//        this.candy = candy;
//    }

    public Restaurant(String id, String name, String address, String phone, float rate, int image,
                      ArrayList<Meals> meals,
                      ArrayList<Meals> appetizers,
                      ArrayList<Meals> drinks,
                      ArrayList<Meals> candy
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rate = rate;
        this.image = image;
        this.meals = meals;
        this.appetizers = appetizers;
        this.drinks = drinks;
        this.candy = candy;
    }

    @NotNull
    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", rate=" + rate +
                ", image=" + image +
                ", meals=" + meals +
                ", appetizers=" + appetizers +
                ", drinks=" + drinks +
                ", candy=" + candy +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Meals> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meals> meals) {
        this.meals = meals;
    }

    public ArrayList<Meals> getAppetizers() {
        return appetizers;
    }

    public void setAppetizers(ArrayList<Meals> appetizers) {
        this.appetizers = appetizers;
    }

    public ArrayList<Meals> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Meals> drinks) {
        this.drinks = drinks;
    }

    public ArrayList<Meals> getCandy() {
        return candy;
    }

    public void setCandy(ArrayList<Meals> candy) {
        this.candy = candy;
    }
}
