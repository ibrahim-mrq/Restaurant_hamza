package com.restaurant.controller.activities;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.controller.fragment.CartFragment;
import com.restaurant.controller.fragment.RestaurantsFragment;
import com.restaurant.controller.fragment.OrderFragment;
import com.restaurant.controller.fragment.ProfileFragment;
import com.restaurant.databinding.ActivityMainBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Cart;
import com.restaurant.model.Order;

import java.util.ArrayList;

@SuppressLint("StaticFieldLeak,NonConstantResourceId")
public class MainActivity extends BaseActivity {

    static ActivityMainBinding binding;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        context = MainActivity.this;
        setSupportActionBar(binding.appbar.toolbar);
        bottomNavigation();
        updateCarts();
        updateOrders();
    }

    private void bottomNavigation() {
        replaceFragment(new RestaurantsFragment(), R.string.restaurants);
        binding.main.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.restaurants:
                    replaceFragment(new RestaurantsFragment(), R.string.restaurants);
                    return true;
                case R.id.cart:
                    replaceFragment(new CartFragment(), R.string.cart);
                    return true;
                case R.id.order:
                    replaceFragment(new OrderFragment(), R.string.order);
                    return true;
                case R.id.profile:
                    replaceFragment(new ProfileFragment(), R.string.profile);
                    return true;
            }
            return false;
        });
    }

    public void replaceFragment(Fragment fragment, @StringRes int title) {
        binding.appbar.tvTool.setText(getResources().getText(title));
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    public static void updateCarts() {
        BadgeDrawable cartBadge = binding.main.bottomNavigation.getOrCreateBadge(R.id.cart);
        DatabaseAccess db = DatabaseAccess.getInstance(context);
        db.open();
        ArrayList<Cart> list = new ArrayList<>(db.getAllCartByUserId(Hawk.get(Constants.USER_ID, -1)));
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserId() != Hawk.get(Constants.USER_ID, 1)) {
                    list.remove(list.get(i));
                }
            }
            cartBadge.setVisible(true);
            cartBadge.setNumber(list.size());
        } else {
            cartBadge.setVisible(false);
            cartBadge.setNumber(0);
        }
        db.close();
    }

    public static void updateOrders() {
        BadgeDrawable orderBadge = binding.main.bottomNavigation.getOrCreateBadge(R.id.order);
        DatabaseAccess db = DatabaseAccess.getInstance(context);
        db.open();
        ArrayList<Order> list = new ArrayList<>(db.getAllOrderByUserId(Hawk.get(Constants.USER_ID, -1)));
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUserId() != Hawk.get(Constants.USER_ID, 1)) {
                    list.remove(list.get(i));
                }
            }
            orderBadge.setVisible(true);
            orderBadge.setNumber(list.size());
        } else {
            orderBadge.setVisible(false);
            orderBadge.setNumber(0);
        }
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.appbar_logout) {
            Constants.logout(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Toast backToasty;
    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToasty.cancel();
                super.onBackPressed();
                return;
            } else {
                backToasty = Toast.makeText(this, getString(R.string.back_exit), Toast.LENGTH_SHORT);
                backToasty.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    }

}