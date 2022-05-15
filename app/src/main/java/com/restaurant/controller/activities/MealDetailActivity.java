package com.restaurant.controller.activities;

import android.os.Bundle;

import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.databinding.ActivityMealDetailBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Cart;
import com.restaurant.model.Meals;

public class MealDetailActivity extends BaseActivity {

    ActivityMealDetailBinding binding;
    Meals model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        model = (Meals) getIntent().getSerializableExtra(Constants.TYPE_MODEL);

        binding.appbar.tvTool.setText("تفاصيل الوجبة");
        binding.appbar.imgBack.setOnClickListener(view -> onBackPressed());

        binding.title.setText(model.getName());
        binding.ingredients.setText(model.getIngredients());
        binding.ratingBar.setRating(model.getRate());
        binding.time.setText(model.getTime());
        binding.image.setImageResource(model.getImage());

        binding.btnAdd.setOnClickListener(view -> {
            DatabaseAccess db = DatabaseAccess.getInstance(this);
            db.open();

            Cart cart = new Cart();
            cart.setImage(model.getImage());
            cart.setName(model.getName());
            cart.setIngredients(model.getIngredients());
            cart.setPrice(model.getPrice());
            cart.setProductId(model.getId());
            cart.setQuantity(1);
            cart.setUserId(Hawk.get(Constants.USER_ID, -1));
            if (db.getCartItem(cart.getProductId()).getProductId() == 0
                    || db.getCartItem(cart.getProductId()).getUserId() != cart.getUserId()
            ) {
                db.insertCart(cart);
                Constants.showAlert(
                        this,
                        getString(R.string.successfully_add_cart),
                        R.color.successColor);
            } else {
                Constants.showAlert(
                        this,
                        getString(R.string.already_in_cart),
                        R.color.warningColor);
            }
            db.close();
            MainActivity.updateCarts();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}