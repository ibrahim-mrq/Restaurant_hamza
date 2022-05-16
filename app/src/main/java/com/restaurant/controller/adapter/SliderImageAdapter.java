package com.restaurant.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.restaurant.R;
import com.restaurant.databinding.CustomImageSliderBinding;

import java.util.ArrayList;

public class SliderImageAdapter extends RecyclerView.Adapter<SliderImageAdapter.SliderImageViewHolder> {

    Context mContext;
    ArrayList<Integer> list;

    public SliderImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SliderImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_slider, parent, false);
        return new SliderImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderImageViewHolder holder, int position) {
        int model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public static class SliderImageViewHolder extends RecyclerView.ViewHolder {

        CustomImageSliderBinding binding;

        private SliderImageViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomImageSliderBinding.bind(itemView);
        }

        private void bind(int model) {
            binding.image.setImageResource(model);
        }
    }

}
