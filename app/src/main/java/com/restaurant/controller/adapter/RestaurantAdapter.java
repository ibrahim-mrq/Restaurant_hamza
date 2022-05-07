package com.restaurant.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.controller.activities.RestaurantDetailActivity;
import com.restaurant.databinding.CustomRestaurantBinding;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    Context mContext;
    ArrayList<Restaurant> list;

    public RestaurantAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Restaurant> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_restaurant, parent, false);
        return new RestaurantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant model = list.get(position);
        holder.bind(model);

        holder.itemView.setOnClickListener(view -> {
            mContext.startActivity(new Intent(mContext, RestaurantDetailActivity.class)
                    .putExtra(Constants.TYPE_MODEL, model)
            );
        });
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        CustomRestaurantBinding binding;

        private RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomRestaurantBinding.bind(itemView);
        }

        private void bind(Restaurant model) {
            binding.name.setText(model.getName());
            binding.image.setImageResource(model.getImage());
        }
    }

}
