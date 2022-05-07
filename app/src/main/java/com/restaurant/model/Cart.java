package com.restaurant.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Cart implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String price;
    private int image;
    private int quantity;
    private int userId;
    private int productId;

    public Cart() {
    }

    public Cart(int id, String name,  String ingredients, String price, int image, int quantity, int userId, int productId) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    @NotNull
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price='" + price + '\'' +
                ", image=" + image +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
