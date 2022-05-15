package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.restaurant.controller.adapter.MealsAdapter;
import com.restaurant.databinding.FragmentMealsBinding;
import com.restaurant.helpers.BaseFragment;
import com.restaurant.model.Restaurant;

import org.jetbrains.annotations.NotNull;

public class MealsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_PARAM_MODEL = "param_model";
    private static final String ARG_PARAM_TYPE = "param_type";
    FragmentMealsBinding binding;
    MealsAdapter adapter;
    Restaurant model;
    int type = 1;

    public MealsFragment() {
        // Required empty public constructor
    }

    public static MealsFragment newInstance(Restaurant model, int type) {
        MealsFragment fragment = new MealsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_MODEL, model);
        args.putInt(ARG_PARAM_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = (Restaurant) getArguments().getSerializable(ARG_PARAM_MODEL);
            type = getArguments().getInt(ARG_PARAM_TYPE);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMealsBinding.inflate(getLayoutInflater());
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

        ImagePopup imagePopup = new ImagePopup(getActivity());
        imagePopup.setFullScreen(false);

        adapter = new MealsAdapter(getActivity());
        binding.include.recyclerView.setAdapter(adapter);
        binding.include.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.include.recyclerView.setHasFixedSize(true);

        adapter.setMealsInterface(imageView -> {
            imagePopup.initiatePopup(imageView.getDrawable());
            imagePopup.viewPopup();
        });

        switch (type) {
            case 1:
                adapter.setList(model.getMeals());
                break;
            case 2:
                adapter.setList(model.getAppetizers());
                break;
            case 3:
                adapter.setList(model.getDrinks());
                break;
            case 4:
                adapter.setList(model.getCandy());
                break;
        }

    }

    @Override
    public void onRefresh() {
        binding.include.swipeToRefresh.setRefreshing(false);
    }
}