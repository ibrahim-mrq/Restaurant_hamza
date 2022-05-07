package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.controller.activities.MainActivity;
import com.restaurant.controller.adapter.MealsAdapter;
import com.restaurant.controller.interfaces.MealsInterface;
import com.restaurant.databinding.FragmentMealsBinding;
import com.restaurant.helpers.BaseFragment;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Cart;
import com.restaurant.model.Meals;
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

        adapter.setMealsInterface(new MealsInterface() {
            @Override
            public void onImageClick(ImageView imageView) {
                imagePopup.initiatePopup(imageView.getDrawable());
                imagePopup.viewPopup();
            }

            @Override
            public void addTOCart(Meals model) {
                DatabaseAccess db = DatabaseAccess.getInstance(requireActivity());
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
                            requireActivity(),
                            getString(R.string.successfully_add_cart),
                            R.color.successColor);
                } else {
                    Constants.showAlert(
                            requireActivity(),
                            getString(R.string.already_in_cart),
                            R.color.warningColor);
                }
                db.close();
                MainActivity.updateCarts();
            }
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