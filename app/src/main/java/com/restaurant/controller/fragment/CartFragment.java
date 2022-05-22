package com.restaurant.controller.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.controller.activities.MainActivity;
import com.restaurant.controller.adapter.CartAdapter;
import com.restaurant.controller.interfaces.CartInterface;
import com.restaurant.databinding.FragmentCartBinding;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Cart;
import com.restaurant.model.Order;
import com.restaurant.model.User;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }

    FragmentCartBinding binding;
    ArrayList<Cart> list = new ArrayList<>();
    CartAdapter adapter;
    DatabaseAccess db;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        db = DatabaseAccess.getInstance(getActivity());

        adapter = new CartAdapter(requireActivity());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerView.setAdapter(adapter);

        adapter.setCartInterface(new CartInterface() {
            @Override
            public void remove(Cart model, int position) {
                db.open();
                db.deleteCart(model);
                db.open();
                list.remove(position);
                adapter.notifyDataSetChanged();
                update(list);
            }

            @Override
            public void plus(int position) {
                int quantity = list.get(position).getQuantity();
                ++quantity;
                list.get(position).setQuantity(quantity);
                update(list);
                update(list.get(position));
            }

            @Override
            public void minus(int position) {
                int quantity = list.get(position).getQuantity();
                if (quantity > 1) {
                    --quantity;
                    list.get(position).setQuantity(quantity);
                    update(list);
                    update(list.get(position));
                }
            }
        });

        initCart();

        binding.btnSend.setOnClickListener(view -> {
            User user = Hawk.get(Constants.USER);
            Order order = new Order();
            order.setCarts(list);
            order.setPrice(binding.tvTotal.getText().toString().trim());
            order.setQuantity(Integer.parseInt(binding.tvQuantity.getText().toString().trim()));

            order.setUserEmail(user.getEmail());
            order.setUserId(user.getId());
            order.setUserName(user.getFirstName() + " " + user.getLastName());
            order.setUserPhone(user.getPhone());
            order.setDate(Constants.getDate());

            db.open();
            db.insertOrder(order);
            for (int i = 0; i < list.size(); i++) {
                db.deleteCart(list.get(i));
                list.remove(list.get(i));
            }
            db.close();
            update(list);
            MainActivity.updateOrders();
            MainActivity.updateCarts();
            if (list.isEmpty()) {
                MainActivity.replaceFragment(new RestaurantsFragment(), R.string.restaurants);
            }
        });
    }

    private void initCart() {
        binding.statefulLayout.showLoading();
        db.open();
        list = db.getAllCartByUserId(Hawk.get(Constants.USER_ID, 1));
        db.close();
        update(list);
        if (list.isEmpty()) {
            binding.statefulLayout.showEmpty();
        } else {
            binding.statefulLayout.showContent();
            adapter.setList(list);
        }
    }

    @SuppressLint("SetTextI18n")
    private void update(Cart model) {
        binding.statefulLayout.showLoading();
        if (list.isEmpty()) {
            binding.statefulLayout.showEmpty();
        } else {
            db.open();
            db.updateCart(model);
            db.open();
            binding.statefulLayout.showContent();
        }
        double total = 0;
        int quantity = 0;
        for (Cart product : list) {
            total += (product.getQuantity() * Double.parseDouble(product.getPrice()));
            quantity += product.getQuantity();
        }
        DecimalFormat df = new DecimalFormat(".##");
        binding.tvQuantity.setText(quantity + "");
        binding.tvTotal.setText(df.format(total) + " " + getString(R.string.coin_shekel));
        adapter.notifyDataSetChanged();
        MainActivity.updateCarts();
    }

    @SuppressLint("SetTextI18n")
    private void update(ArrayList<Cart> list) {
        binding.statefulLayout.showLoading();
        if (list.isEmpty()) {
            binding.statefulLayout.showEmpty();
        } else {
            binding.statefulLayout.showContent();
        }
        double total = 0;
        int quantity = 0;
        for (Cart product : list) {
            total += (product.getQuantity() * Double.parseDouble(product.getPrice()));
            quantity += product.getQuantity();
        }
        DecimalFormat df = new DecimalFormat(".##");
        binding.tvQuantity.setText(quantity + "");
        binding.tvTotal.setText(df.format(total) + " " + getString(R.string.coin_shekel));
        adapter.notifyDataSetChanged();
        MainActivity.updateCarts();
    }
}