package com.restaurant.controller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.databinding.CustomOrderBinding;
import com.restaurant.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context mContext;
    ArrayList<Order> list;

    public OrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Order> getList() {
        return list;
    }

    public void setList(ArrayList<Order> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order model = list.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        CustomOrderBinding binding;

        private OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomOrderBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Order model) {
            binding.tvDate.setText(model.getDate());
            binding.tvOrderNumber.setText("#OTB0005");
            binding.tvStats.setText("تمت بنجاح");
            binding.tvPrice.setText(model.getPrice());
        }
    }

}
