package com.restaurant.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restaurant.R;
import com.restaurant.controller.activities.RestaurantDetailActivity;
import com.restaurant.databinding.CustomRestaurantBinding;
import com.restaurant.helpers.Constants;
import com.restaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements Filterable {

    Context mContext;
    ArrayList<Restaurant> list;
    private List<Restaurant> listFilter;

    public RestaurantAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Restaurant> getList() {
        return list;
    }

    public void setList(ArrayList<Restaurant> list) {
        this.list = list;
        listFilter = new ArrayList<>(list);
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
            binding.address.setText(model.getAddress());
            binding.ratingBar.setRating(model.getRate());
            binding.image.setImageResource(model.getImage());
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Restaurant> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Restaurant item : listFilter) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
