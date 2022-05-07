package com.restaurant.controller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.controller.interfaces.CartInterface;
import com.restaurant.databinding.CustomCartBinding;
import com.restaurant.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context mContext;
    ArrayList<Cart> list;
    CartInterface cartInterface;

    public CartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setCartInterface(CartInterface cartInterface) {
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart model = list.get(position);
        holder.bind(model);

        holder.binding.imgPlus.setOnClickListener(v -> cartInterface.plus(position));
        holder.binding.imgMinus.setOnClickListener(v -> cartInterface.minus(position));

        holder.binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                cartInterface.remove(model, position);
                holder.binding.checkBox.setChecked(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        CustomCartBinding binding;

        private CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCartBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void bind(Cart model) {
            binding.tvTitle.setSelected(true);
            binding.tvTitle.setText(model.getName());
            binding.ingredients.setText(model.getIngredients());
            binding.tvCount.setText(model.getQuantity() + "");
            binding.tvPrice.setText(model.getPrice() + " " + mContext.getString(R.string.coin_shekel));
            binding.img.setImageResource(model.getImage());
        }
    }

}