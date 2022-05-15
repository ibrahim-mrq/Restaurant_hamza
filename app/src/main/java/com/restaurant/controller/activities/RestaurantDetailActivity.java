package com.restaurant.controller.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.restaurant.R;
import com.restaurant.controller.adapter.ViewPagerAdapter;
import com.restaurant.controller.fragment.MealsFragment;
import com.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Restaurant;

import java.util.Objects;

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

        binding.appbar.addOnOffsetChangedListener((appBarLayout, offset) -> {
            if (Math.abs(offset) == appBarLayout.getTotalScrollRange()) {
                // Collapsed
//                binding.collapsing.setTitle(model.getName());
                binding.collapsing.setTitle("تفاصيل المطعم");
            } else if (offset == 0) {
                // Expanded
                binding.collapsing.setTitle(" ");
            } else {
                // Somewhere in between
            }
        });
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.collapsing.setCollapsedTitleGravity(Gravity.START);
        binding.collapsing.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        binding.collapsing.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimary));

        binding.title.setText(model.getName());
        binding.phone.setText(model.getPhone());
        binding.ratingBar.setRating(model.getRate());
        binding.address.setText(model.getAddress());
        binding.image.setImageResource(model.getImage());

        binding.phone.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + model.getPhone()));
            startActivity(intent);
        });

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