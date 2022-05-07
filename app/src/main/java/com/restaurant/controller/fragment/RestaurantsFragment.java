package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurant.R;
import com.restaurant.controller.adapter.RestaurantAdapter;
import com.restaurant.databinding.FragmentRestaurantsBinding;
import com.restaurant.helpers.BaseFragment;
import com.restaurant.model.Meals;
import com.restaurant.model.Restaurant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestaurantsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    FragmentRestaurantsBinding binding;
    RestaurantAdapter adapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        binding.include.swipeToRefresh.setOnRefreshListener(this);
        binding.include.swipeToRefresh.setRefreshing(false);

        adapter = new RestaurantAdapter(getActivity());
        binding.include.recyclerView.setAdapter(adapter);
        binding.include.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.include.recyclerView.setHasFixedSize(true);
        addData();
    }

    private void addData() {
        ArrayList<Restaurant> list = new ArrayList<>();
        list.add(Arafat());
        list.add(Alhalabi());
        list.add(Eyad());
        list.add(Fahad());
        list.add(Moaaz());
        adapter.setList(list);
    }

    private Restaurant Arafat() {

        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(1, "كلاسك برجر"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "14", R.drawable.arafat));
        meals.add(new Meals(2, "بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        meals.add(new Meals(3, "شاورما بشاميل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        meals.add(new Meals(4, "سناك بوكس"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "25", R.drawable.arafat));
        meals.add(new Meals(5, "شيش طاووك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "22", R.drawable.arafat));

        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(6, "اصابع جبنة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        appetizers.add(new Meals(7, "اصابع دجاج"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        appetizers.add(new Meals(8, "حلقات بصل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        appetizers.add(new Meals(9, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        appetizers.add(new Meals(10, "سلطة بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));

        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(11, "كوكا كولا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(12, "سبرايت"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(13, "شاي"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(14, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(15, "نسكافيه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(16, "كريب نوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        candy.add(new Meals(17, "بان كيك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "12", R.drawable.arafat));
        candy.add(new Meals(18, "كنافة بالنوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        candy.add(new Meals(19, "سلطة فواكه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        candy.add(new Meals(20, "لقيمات"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        return new Restaurant("1"  , "مطعم بيسترو عرفات", R.drawable.arafat,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Alhalabi() {

        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(21, "كلاسك برجر"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "14", R.drawable.arafat));
        meals.add(new Meals(22, "بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        meals.add(new Meals(23, "شاورما بشاميل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        meals.add(new Meals(24, "سناك بوكس"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "25", R.drawable.arafat));
        meals.add(new Meals(25, "شيش طاووك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "22", R.drawable.arafat));

        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(26, "اصابع جبنة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        appetizers.add(new Meals(27, "اصابع دجاج"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        appetizers.add(new Meals(28, "حلقات بصل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        appetizers.add(new Meals(29, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        appetizers.add(new Meals(30, "سلطة بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));

        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(31, "كوكا كولا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(32, "سبرايت"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(33, "شاي"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(34, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(35, "نسكافيه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(36, "كريب نوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        candy.add(new Meals(37, "بان كيك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "12", R.drawable.arafat));
        candy.add(new Meals(38, "كنافة بالنوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        candy.add(new Meals(39, "سلطة فواكه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        candy.add(new Meals(40, "لقيمات"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        return new Restaurant("2"  , "مطعم الحلبي", R.drawable.alhalabi,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Eyad() {

        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(41, "كلاسك برجر"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "14", R.drawable.arafat));
        meals.add(new Meals(42, "بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        meals.add(new Meals(43, "شاورما بشاميل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        meals.add(new Meals(44, "سناك بوكس"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "25", R.drawable.arafat));
        meals.add(new Meals(45, "شيش طاووك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "22", R.drawable.arafat));

        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(46, "اصابع جبنة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        appetizers.add(new Meals(47, "اصابع دجاج"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        appetizers.add(new Meals(48, "حلقات بصل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        appetizers.add(new Meals(49, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        appetizers.add(new Meals(50, "سلطة بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));

        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(51, "كوكا كولا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(52, "سبرايت"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(53, "شاي"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(54, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(55, "نسكافيه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(56, "كريب نوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        candy.add(new Meals(57, "بان كيك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "12", R.drawable.arafat));
        candy.add(new Meals(58, "كنافة بالنوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        candy.add(new Meals(59, "سلطة فواكه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        candy.add(new Meals(60, "لقيمات"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        return new Restaurant("3" , "مطعم اياد", R.drawable.eyad,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Fahad() {

        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(61, "كلاسك برجر"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "14", R.drawable.arafat));
        meals.add(new Meals(62, "بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        meals.add(new Meals(63, "شاورما بشاميل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        meals.add(new Meals(64, "سناك بوكس"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "25", R.drawable.arafat));
        meals.add(new Meals(65, "شيش طاووك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "22", R.drawable.arafat));

        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(66, "اصابع جبنة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        appetizers.add(new Meals(67, "اصابع دجاج"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        appetizers.add(new Meals(68, "حلقات بصل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        appetizers.add(new Meals(69, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        appetizers.add(new Meals(70, "سلطة بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));

        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(71, "كوكا كولا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(72, "سبرايت"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(73, "شاي"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(74, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(75, "نسكافيه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(76, "كريب نوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        candy.add(new Meals(77, "بان كيك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "12", R.drawable.arafat));
        candy.add(new Meals(78, "كنافة بالنوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        candy.add(new Meals(79, "سلطة فواكه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        candy.add(new Meals(80, "لقيمات"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        return new Restaurant("4","مطعم فهد", R.drawable.fahad,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Moaaz() {

        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(81, "كلاسك برجر"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "14", R.drawable.arafat));
        meals.add(new Meals(82, "بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        meals.add(new Meals(83, "شاورما بشاميل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        meals.add(new Meals(84, "سناك بوكس"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "25", R.drawable.arafat));
        meals.add(new Meals(85, "شيش طاووك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "22", R.drawable.arafat));

        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(98, "اصابع جبنة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        appetizers.add(new Meals(86, "اصابع دجاج"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        appetizers.add(new Meals(87, "حلقات بصل"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "20", R.drawable.arafat));
        appetizers.add(new Meals(88, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        appetizers.add(new Meals(89, "سلطة بيسترو"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));

        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(90, "كوكا كولا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(91, "سبرايت"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "2", R.drawable.arafat));
        drinks.add(new Meals(92, "شاي"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "4", R.drawable.arafat));
        drinks.add(new Meals(93, "قهوة"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "5", R.drawable.arafat));
        drinks.add(new Meals(94, "نسكافيه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(95, "كريب نوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "10", R.drawable.arafat));
        candy.add(new Meals(96, "بان كيك"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "12", R.drawable.arafat));
        candy.add(new Meals(97, "كنافة بالنوتيلا"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "8", R.drawable.arafat));
        candy.add(new Meals(99, "سلطة فواكه"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "15", R.drawable.arafat));
        candy.add(new Meals(100, "لقيمات"  ,"لحمة ، خس ، خيار ، صوص ، بندورة" , "7", R.drawable.arafat));

        return new Restaurant("5","مطعم معاذ", R.drawable.moaaz,
                meals, appetizers, drinks, candy);
    }

    @Override
    public void onRefresh() {
        binding.include.swipeToRefresh.setRefreshing(false);
    }

}