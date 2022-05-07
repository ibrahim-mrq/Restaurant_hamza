package com.restaurant.controller.interfaces;

import android.widget.ImageView;

import com.restaurant.model.Meals;

public interface MealsInterface {

    void onImageClick( ImageView imageView);

    void addTOCart(Meals model);
}
