package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurant.R;
import com.restaurant.controller.adapter.RestaurantAdapter;
import com.restaurant.controller.adapter.SliderImageAdapter;
import com.restaurant.databinding.FragmentRestaurantsBinding;
import com.restaurant.helpers.BaseFragment;
import com.restaurant.model.Meals;
import com.restaurant.model.Restaurant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RestaurantsFragment extends BaseFragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    FragmentRestaurantsBinding binding;
    RestaurantAdapter adapter;

    SliderImageAdapter sliderImageAdapter;
    ArrayList<Integer> sliderList = new ArrayList<>();
    Handler handler = new Handler();

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

        adapter = new RestaurantAdapter(getActivity());
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setHasFixedSize(true);
        addData();
        viewPager();
    }

    private void viewPager() {
        sliderList.clear();
        sliderList.add(R.drawable.borger);
        sliderList.add(R.drawable.coffee);
        sliderList.add(R.drawable.nescafe);
        sliderList.add(R.drawable.pancake);

        sliderImageAdapter = new SliderImageAdapter(requireActivity());
        sliderImageAdapter.setList(sliderList);
        binding.viewpager.setAdapter(sliderImageAdapter);
        binding.viewpager.setClipToPadding(false);
        binding.viewpager.setClipChildren(false);
        binding.viewpager.setOffscreenPageLimit(3);
//        binding.indicator.setViewPager(binding.viewpager);
        binding.viewpager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(30));
        transformer.addTransformer((page, position) -> {
            float v = 1 - Math.abs(position);
            page.setScaleY(0.75f + v * 0.15f);
        });
        binding.viewpager.setPageTransformer(transformer);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (binding.viewpager.getCurrentItem() == sliderImageAdapter.getItemCount() - 1) {
                binding.viewpager.setCurrentItem(0, true);
            } else {
                binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1, true);
            }
        }
    };

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

        // TODO : meals
        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(1, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق",
                4f,
                R.drawable.borger));
        meals.add(new Meals(2, "بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.bistro));
        meals.add(new Meals(3, "شاورما بشاميل", "دجاج ، بطاطا ، صوص", "20",
                "5 دقائق",
                4f,
                R.drawable.shawarma));
        meals.add(new Meals(4, "سناك بوكس", "لحمة ، خس ، خيار ، صوص ، سناك", "25",
                "5 دقائق",
                4f,
                R.drawable.snack));
        meals.add(new Meals(5, "شيش طاووك", "سدر دجاج ، بهارات", "22",
                "5 دقائق",
                4f,
                R.drawable.shish_tawook));

        // TODO : appetizers
        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(6, "اصابع جبنة", "جبنة ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.cheese_fingers));
        appetizers.add(new Meals(7, "اصابع دجاج", "لحمة ، خس ، دجاج ، صوص", "10",
                "5 دقائق",
                4f,
                R.drawable.chicken_fingers));
        appetizers.add(new Meals(8, "حلقات بصل", "بصل ، بندورة ، بعض التوابل ، بهارات", "20",
                "5 دقائق",
                4f,
                R.drawable.onion));
        appetizers.add(new Meals(9, "صوص رانش", " خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.ranch));
        appetizers.add(new Meals(10, "سلطة بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.authority));

        // TODO : drinks
        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(11, "كوكا كولا", "زجاجة عصير صودا بطعم الكولا", "2",
                "5 دقائق",
                4f,
                R.drawable.cola));
        drinks.add(new Meals(12, "سبرايت", "زجاجة عصير صودا بطعم الليمون", "2",
                "5 دقائق",
                4f,
                R.drawable.sprite));
        drinks.add(new Meals(13, "شاي", "ماء ، شاي ، سكر", "4",
                "5 دقائق",
                4f,
                R.drawable.tea));
        drinks.add(new Meals(14, "قهوة", "ماء ، بن", "4",
                "5 دقائق",
                4f,
                R.drawable.coffee));
        drinks.add(new Meals(15, "نسكافيه", "ماء ، حليب مبيض ، نسكافيه ، سكر", "7",
                "5 دقائق",
                4f,
                R.drawable.nescafe));

        // TODO : candy
        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(16, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق",
                4f,
                R.drawable.crepe));
        candy.add(new Meals(17, "بان كيك", "كيك ، ماء ، سكر", "12",
                "5 دقائق",
                4f,
                R.drawable.pancake));
        candy.add(new Meals(18, "كنافة بالنوتيلا", "كنافة ، نوتيلا ، سكر ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.konafa_nutella));
        candy.add(new Meals(19, "سلطة فواكه", "فواكه ، فراولة ، موز ، تفاح ، اناناس", "15",
                "5 دقائق",
                4f,
                R.drawable.fruit_salad));
        candy.add(new Meals(20, "لقيمات", "نوتيلا ، صوص الحلبي", "7",
                "5 دقائق",
                4f,
                R.drawable.lokimat));

        return new Restaurant("1", "مطعم بيسترو عرفات",
                "غزة - شارع النصر - مقابل مخبز العائلات",
                "0593221010",
                2f,
                R.drawable.arafat,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Alhalabi() {

        // TODO : meals
        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(21, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق",
                4f,
                R.drawable.borger));
        meals.add(new Meals(22, "بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.bistro));
        meals.add(new Meals(23, "شاورما بشاميل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.shawarma));
        meals.add(new Meals(24, "سناك بوكس", "لحمة ، خس ، خيار ، صوص ، بندورة", "25",
                "5 دقائق",
                4f,
                R.drawable.snack));
        meals.add(new Meals(25, "شيش طاووك", "لحمة ، خس ، خيار ، صوص ، بندورة", "22",
                "5 دقائق",
                4f,
                R.drawable.shish_tawook));

        // TODO : appetizers
        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(26, "اصابع جبنة", "لحمة ، خس ، خيار ، صوص ، بندورة", "8",
                "5 دقائق",
                4f,
                R.drawable.cheese_fingers));
        appetizers.add(new Meals(27, "اصابع دجاج", "لحمة ، خس ، خيار ، صوص ، بندورة", "10",
                "5 دقائق",
                4f,
                R.drawable.chicken_fingers));
        appetizers.add(new Meals(28, "حلقات بصل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.onion));
        appetizers.add(new Meals(29, "صوص رانش", "لحمة ، خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.ranch));
        appetizers.add(new Meals(30, "سلطة بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.authority));

        // TODO : drinks
        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(31, "كوكا كولا", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.cola));
        drinks.add(new Meals(32, "سبرايت", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.sprite));
        drinks.add(new Meals(33, "شاي", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.tea));
        drinks.add(new Meals(34, "قهوة", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.coffee));
        drinks.add(new Meals(35, "نسكافيه", "لحمة ، خس ، خيار ، صوص ، بندورة", "7",
                "5 دقائق",
                4f,
                R.drawable.nescafe));

        // TODO : candy
        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(36, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق",
                4f,
                R.drawable.crepe));
        candy.add(new Meals(37, "بان كيك", "كيك ، ماء ، سكر", "12",
                "5 دقائق",
                4f,
                R.drawable.pancake));
        candy.add(new Meals(38, "كنافة بالنوتيلا", "كنافة ، نوتيلا ، سكر ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.konafa_nutella));
        candy.add(new Meals(39, "سلطة فواكه", "فواكه ، فراولة ، موز ، تفاح ، اناناس", "15",
                "5 دقائق",
                4f,
                R.drawable.fruit_salad));
        candy.add(new Meals(40, "لقيمات", "نوتيلا ، صوص الحلبي", "7",
                "5 دقائق",
                4f,
                R.drawable.lokimat));

        return new Restaurant("2", "مطعم الحلبي",
                "غزة السرايا شارع شركة جوال باتجاه الغرب 50 متر (شارع احمد عبد العزيز)",
                "0593111190",
                3f,
                R.drawable.alhalabi,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Eyad() {

        // TODO : meals
        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(41, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق",
                4f,
                R.drawable.borger));
        meals.add(new Meals(42, "بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.bistro));
        meals.add(new Meals(43, "شاورما بشاميل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.shawarma));
        meals.add(new Meals(44, "سناك بوكس", "لحمة ، خس ، خيار ، صوص ، بندورة", "25",
                "5 دقائق",
                4f,
                R.drawable.snack));
        meals.add(new Meals(45, "شيش طاووك", "لحمة ، خس ، خيار ، صوص ، بندورة", "22",
                "5 دقائق",
                4f,
                R.drawable.shish_tawook));

        // TODO : appetizers
        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(46, "اصابع جبنة", "لحمة ، خس ، خيار ، صوص ، بندورة", "8",
                "5 دقائق",
                4f,
                R.drawable.cheese_fingers));
        appetizers.add(new Meals(47, "اصابع دجاج", "لحمة ، خس ، خيار ، صوص ، بندورة", "10",
                "5 دقائق",
                4f,
                R.drawable.chicken_fingers));
        appetizers.add(new Meals(48, "حلقات بصل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.onion));
        appetizers.add(new Meals(49, "صوص رانش", "لحمة ، خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.ranch));
        appetizers.add(new Meals(50, "سلطة بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.authority));

        // TODO : drinks
        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(51, "كوكا كولا", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.cola));
        drinks.add(new Meals(52, "سبرايت", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.sprite));
        drinks.add(new Meals(53, "شاي", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.tea));
        drinks.add(new Meals(54, "قهوة", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.coffee));
        drinks.add(new Meals(55, "نسكافيه", "لحمة ، خس ، خيار ، صوص ، بندورة", "7",
                "5 دقائق",
                4f,
                R.drawable.nescafe));

        // TODO : candy
        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(56, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق",
                4f,
                R.drawable.crepe));
        candy.add(new Meals(57, "بان كيك", "كيك ، ماء ، سكر", "12",
                "5 دقائق",
                4f,
                R.drawable.pancake));
        candy.add(new Meals(58, "كنافة بالنوتيلا", "كنافة ، نوتيلا ، سكر ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.konafa_nutella));
        candy.add(new Meals(59, "سلطة فواكه", "فواكه ، فراولة ، موز ، تفاح ، اناناس", "15",
                "5 دقائق",
                4f,
                R.drawable.fruit_salad));
        candy.add(new Meals(60, "لقيمات", "نوتيلا ، صوص الحلبي", "7",
                "5 دقائق",
                4f,
                R.drawable.lokimat));

        return new Restaurant("3", "مطعم اياد",
                "غزة - غرب مفترق الاتصالات - بجوار بيت الصحافة .",
                "0599569844",
                4f,
                R.drawable.eyad,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Fahad() {

        // TODO : meals
        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(61, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق",
                4f,
                R.drawable.borger));
        meals.add(new Meals(62, "بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.bistro));
        meals.add(new Meals(63, "شاورما بشاميل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.shawarma));
        meals.add(new Meals(64, "سناك بوكس", "لحمة ، خس ، خيار ، صوص ، بندورة", "25",
                "5 دقائق",
                4f,
                R.drawable.snack));
        meals.add(new Meals(65, "شيش طاووك", "لحمة ، خس ، خيار ، صوص ، بندورة", "22",
                "5 دقائق",
                4f,
                R.drawable.shish_tawook));

        // TODO : appetizers
        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(66, "اصابع جبنة", "لحمة ، خس ، خيار ، صوص ، بندورة", "8",
                "5 دقائق",
                4f,
                R.drawable.cheese_fingers));
        appetizers.add(new Meals(67, "اصابع دجاج", "لحمة ، خس ، خيار ، صوص ، بندورة", "10",
                "5 دقائق",
                4f,
                R.drawable.chicken_fingers));
        appetizers.add(new Meals(68, "حلقات بصل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.onion));
        appetizers.add(new Meals(69, "صوص رانش", "لحمة ، خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.ranch));
        appetizers.add(new Meals(70, "سلطة بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.authority));

        // TODO : drinks
        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(71, "كوكا كولا", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.cola));
        drinks.add(new Meals(72, "سبرايت", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.sprite));
        drinks.add(new Meals(73, "شاي", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.tea));
        drinks.add(new Meals(74, "قهوة", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.coffee));
        drinks.add(new Meals(75, "نسكافيه", "لحمة ، خس ، خيار ، صوص ، بندورة", "7",
                "5 دقائق",
                4f,
                R.drawable.nescafe));

        // TODO : candy
        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(76, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق",
                4f,
                R.drawable.crepe));
        candy.add(new Meals(77, "بان كيك", "كيك ، ماء ، سكر", "12",
                "5 دقائق",
                4f,
                R.drawable.pancake));
        candy.add(new Meals(78, "كنافة بالنوتيلا", "كنافة ، نوتيلا ، سكر ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.konafa_nutella));
        candy.add(new Meals(79, "سلطة فواكه", "فواكه ، فراولة ، موز ، تفاح ، اناناس", "15",
                "5 دقائق",
                4f,
                R.drawable.fruit_salad));
        candy.add(new Meals(80, "لقيمات", "نوتيلا ، صوص الحلبي", "7",
                "5 دقائق",
                4f,
                R.drawable.lokimat));

        return new Restaurant("4", "مطعم فهد",
                "غزة - شارع الشهداء - خلف الاتصالات - بجوار الفدان الأخضر",
                "0595678080",
                5f,
                R.drawable.fahad,
                meals, appetizers, drinks, candy);
    }

    private Restaurant Moaaz() {

        // TODO : meals
        ArrayList<Meals> meals = new ArrayList<>();
        meals.add(new Meals(81, "كلاسك برجر", "لحمة ، خس ، خيار ، صوص ، بندورة", "14",
                "5 دقائق",
                4f,
                R.drawable.borger));
        meals.add(new Meals(82, "بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.bistro));
        meals.add(new Meals(83, "شاورما بشاميل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.shawarma));
        meals.add(new Meals(84, "سناك بوكس", "لحمة ، خس ، خيار ، صوص ، بندورة", "25",
                "5 دقائق",
                4f,
                R.drawable.snack));
        meals.add(new Meals(85, "شيش طاووك", "لحمة ، خس ، خيار ، صوص ، بندورة", "22",
                "5 دقائق",
                4f,
                R.drawable.shish_tawook));

        // TODO : appetizers
        ArrayList<Meals> appetizers = new ArrayList<>();
        appetizers.add(new Meals(98, "اصابع جبنة", "لحمة ، خس ، خيار ، صوص ، بندورة", "8",
                "5 دقائق",
                4f,
                R.drawable.cheese_fingers));
        appetizers.add(new Meals(86, "اصابع دجاج", "لحمة ، خس ، خيار ، صوص ، بندورة", "10",
                "5 دقائق",
                4f,
                R.drawable.chicken_fingers));
        appetizers.add(new Meals(87, "حلقات بصل", "لحمة ، خس ، خيار ، صوص ، بندورة", "20",
                "5 دقائق",
                4f,
                R.drawable.onion));
        appetizers.add(new Meals(88, "صوص رانش", "لحمة ، خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.ranch));
        appetizers.add(new Meals(89, "سلطة بيسترو", "لحمة ، خس ، خيار ، صوص ، بندورة", "15",
                "5 دقائق",
                4f,
                R.drawable.authority));

        // TODO : drinks
        ArrayList<Meals> drinks = new ArrayList<>();
        drinks.add(new Meals(90, "كوكا كولا", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.cola));
        drinks.add(new Meals(91, "سبرايت", "لحمة ، خس ، خيار ، صوص ، بندورة", "2",
                "5 دقائق",
                4f,
                R.drawable.sprite));
        drinks.add(new Meals(92, "شاي", "لحمة ، خس ، خيار ، صوص ، بندورة", "4",
                "5 دقائق",
                4f,
                R.drawable.tea));
        drinks.add(new Meals(93, "قهوة", "لحمة ، خس ، خيار ، صوص ، بندورة", "5",
                "5 دقائق",
                4f,
                R.drawable.coffee));
        drinks.add(new Meals(94, "نسكافيه", "لحمة ، خس ، خيار ، صوص ، بندورة", "7",
                "5 دقائق",
                4f,
                R.drawable.nescafe));

        // TODO : candy
        ArrayList<Meals> candy = new ArrayList<>();
        candy.add(new Meals(95, "كريب نوتيلا", "نوتيلا ، كريب", "10",
                "5 دقائق",
                4f,
                R.drawable.crepe));
        candy.add(new Meals(96, "بان كيك", "كيك ، ماء ، سكر", "12",
                "5 دقائق",
                4f,
                R.drawable.pancake));
        candy.add(new Meals(97, "كنافة بالنوتيلا", "كنافة ، نوتيلا ، سكر ، صوص", "8",
                "5 دقائق",
                4f,
                R.drawable.konafa_nutella));
        candy.add(new Meals(99, "سلطة فواكه", "فواكه ، فراولة ، موز ، تفاح ، اناناس", "15",
                "5 دقائق",
                4f,
                R.drawable.fruit_salad));
        candy.add(new Meals(100, "لقيمات", "نوتيلا ، صوص الحلبي", "7",
                "5 دقائق",
                4f,
                R.drawable.lokimat));

        return new Restaurant("5", "مطعم معاذ",
                "غزة جنوب دوار انصار",
                "0592121031",
                3f,
                R.drawable.moaaz,
                meals, appetizers, drinks, candy);
    }

}