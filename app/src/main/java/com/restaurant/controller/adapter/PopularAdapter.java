package com.restaurant.controller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.databinding.CustomPopularBinding;
import com.restaurant.model.Meals;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    Context mContext;
    ArrayList<Meals> list;

    public PopularAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Meals> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_popular, parent, false);
        return new PopularViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Meals model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {

        CustomPopularBinding binding;

        private PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomPopularBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Meals model) {
            binding.name.setText(model.getName());
            binding.ratingBar.setRating(model.getRate());
            binding.price.setText(model.getPrice() + " " + mContext.getString(R.string.coin_shekel));
            binding.image.setImageResource(model.getImage());
        }
    }

}
