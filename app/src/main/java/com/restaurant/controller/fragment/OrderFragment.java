package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.controller.adapter.OrderAdapter;
import com.restaurant.databinding.FragmentOrderBinding;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Order;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    FragmentOrderBinding binding;
    ArrayList<Order> list = new ArrayList<>();
    OrderAdapter adapter;
    DatabaseAccess db;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        db = DatabaseAccess.getInstance(getActivity());

        adapter = new OrderAdapter(requireActivity());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerView.setAdapter(adapter);
        initOrder();
    }

    private void initOrder() {
        binding.statefulLayout.showLoading();
        db.open();
        list = db.getAllOrderByUserId(Hawk.get(Constants.USER_ID, 1));
        db.close();
        if (list.isEmpty()) {
            Log.e("response", "initOrder: " + list.size());
            binding.statefulLayout.showEmpty();
        } else {
            binding.statefulLayout.showContent();
            adapter.setList(list);
            Log.e("response", "initOrder: else " + list.size());
        }
    }
}