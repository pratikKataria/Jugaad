package com.tricky__tweaks.jugaad.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.Model.RentalProduct;

import java.util.ArrayList;

public class MainScreenRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<EachItemDataModel> list;
    private LayoutInflater inflater;

    private static final int CARD_VIEW = 1;
    private static final int EMPTY_VIEW = 0;

    public MainScreenRecyclerAdapter(Context context, ArrayList<EachItemDataModel> list) {
        this.list = list;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        if (viewType == CARD_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_card_view, parent, false);
            holder = new ProductCardViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_loading_layout, parent, false);
            holder = new EmptyViewLoader(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProductCardViewHolder) {

            ProductCardViewHolder productCardViewHolder = (ProductCardViewHolder) holder;

            productCardViewHolder.productName.setText(list.get(position).getItemName());

            Glide.with(context).load(list.get(position).getItemImageDownloadUrl()).into(productCardViewHolder.productImage);
            productCardViewHolder.productPrice.setText(list.get(position).getItemRentPrice()+"");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0) {
            return CARD_VIEW;

        } else {
            return EMPTY_VIEW;
        }
    }

    @Override
    public int getItemCount() {

        if (list.size() == 0) {
            return 1;
        } else {
            return list.size();
        }
    }

    public class ProductCardViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private TextView productPrice;
        private ImageView productImage;


        public ProductCardViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.cardViewImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }

    private static class EmptyViewLoader extends RecyclerView.ViewHolder {
        public EmptyViewLoader(View view) {
            super(view);
        }
    }
}
