package com.restaurant.controller.interfaces;

import com.restaurant.model.Cart;

public interface CartInterface {
    void remove(Cart model, int position);

    void plus(int position);

    void minus(int position);
}
