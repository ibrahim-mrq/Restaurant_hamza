package com.restaurant.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Meals implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String price;
    private int image;

    public Meals() {
    }

    public Meals(int id, String name, String ingredients, String price, int image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.image = image;
    }


    @NotNull
    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price='" + price + '\'' +
                ", image=" + image +
                '}';
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
