package com.restaurant.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Meals implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String price;
//    private String prepare;
    private String time;
    private float rate;
    private int image;

    public Meals() {
    }

//    public Meals(int id, String name, String ingredients, String price, int image) {
//        this.id = id;
//        this.name = name;
//        this.ingredients = ingredients;
//        this.price = price;
//        this.image = image;
//    }


    public Meals(int id, String name, String ingredients, String price,
//                 String prepare,
                 String time, float rate, int image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
//        this.prepare = prepare;
        this.time = time;
        this.rate = rate;
        this.image = image;
    }

    @NotNull
    @Override
    public String toString() {
        return "Meals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price='" + price + '\'' +
//                ", prepare='" + prepare + '\'' +
                ", time='" + time + '\'' +
                ", rate=" + rate +
                ", image=" + image +
                '}';
    }

//    public String getPrepare() {
//        return prepare;
//    }
//
//    public void setPrepare(String prepare) {
//        this.prepare = prepare;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
