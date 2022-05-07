package com.restaurant.controller.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.restaurant.R;
import com.restaurant.controller.adapter.ViewPagerAdapter;
import com.restaurant.controller.fragment.MealsFragment;
import com.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Restaurant;

public class RestaurantDetailActivity extends BaseActivity {

    ActivityRestaurantDetailBinding binding;
    Restaurant model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        model = (Restaurant) getIntent().getSerializableExtra(Constants.TYPE_MODEL);

        binding.appbar.imgBack.setOnClickListener(view -> onBackPressed());
        binding.appbar.tvTool.setText(model.getName());
        binding.image.setImageResource(model.getImage());
        initPager();
    }

    private void initPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MealsFragment.newInstance(model, 1), getString(R.string.meals));
        adapter.addFragment(MealsFragment.newInstance(model, 2), getString(R.string.appetizers));
        adapter.addFragment(MealsFragment.newInstance(model, 3), getString(R.string.drinks));
        adapter.addFragment(MealsFragment.newInstance(model, 4), getString(R.string.candy));

        binding.viewpager.setAdapter(adapter);
        binding.tab.setupWithViewPager(binding.viewpager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}