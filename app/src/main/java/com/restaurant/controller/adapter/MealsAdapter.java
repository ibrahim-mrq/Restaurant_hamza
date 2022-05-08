package com.restaurant.controller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.controller.interfaces.MealsInterface;
import com.restaurant.databinding.CustomMealsBinding;
import com.restaurant.model.Meals;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {

    Context mContext;
    ArrayList<Meals> list;
    MealsInterface mealsInterface;

    public MealsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Meals> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setMealsInterface(MealsInterface mealsInterface) {
        this.mealsInterface = mealsInterface;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_meals, parent, false);
        return new MealsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        Meals model = list.get(position);
        holder.bind(model);

        holder.binding.image.setOnClickListener(view -> {
            mealsInterface.onImageClick(holder.binding.image);
        });

        holder.binding.cart.setOnClickListener(view -> {
            mealsInterface.addTOCart(model);
        });
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

     class MealsViewHolder extends RecyclerView.ViewHolder {

        CustomMealsBinding binding;

        private MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomMealsBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Meals model) {
            binding.name.setText(model.getName());
            binding.ingredients.setText(model.getIngredients());
            binding.price.setText(model.getPrice()+ " " + mContext.getString(R.string.coin_shekel));
            binding.image.setImageResource(model.getImage());
        }
    }

}
