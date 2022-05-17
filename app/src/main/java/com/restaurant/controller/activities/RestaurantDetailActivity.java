package com.restaurant.controller.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.restaurant.R;
import com.restaurant.controller.adapter.PopularAdapter;
import com.restaurant.controller.adapter.ViewPagerAdapter;
import com.restaurant.controller.fragment.MealsFragment;
import com.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Meals;
import com.restaurant.model.Restaurant;

import java.util.ArrayList;

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

        binding.imgBack.setOnClickListener(view -> onBackPressed());

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
        popular();
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

    private void popular() {
        ArrayList<Meals> list = new ArrayList<>();
        list.add(new Meals(1, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق", 4f, R.drawable.borger));
        list.add(new Meals(7, "اصابع دجاج", "لحمة ، خس ، دجاج ، صوص", "10",
                "5 دقائق", 4f, R.drawable.chicken_fingers));
        list.add(new Meals(11, "كوكا كولا", "زجاجة عصير صودا بطعم الكولا", "2",
                "5 دقائق", 4f, R.drawable.cola));
        list.add(new Meals(16, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق", 4f, R.drawable.crepe));
        PopularAdapter adapter = new PopularAdapter(this);
        adapter.setList(list);
        binding.recyclerView.setAdapter(adapter);
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