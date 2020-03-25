package com.tricky__tweaks.jugaad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;

import java.util.ArrayList;

public class EachCategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<EachItemDataModel> list;
    private LayoutInflater inflater;

    public EachCategoryRecyclerAdapter(Context context, ArrayList<EachItemDataModel> list) {
        this.list = list;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_each_items_rv, parent, false);
        holder = new ItemCardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemCardViewHolder productCardViewHolder = (ItemCardViewHolder) holder;

        productCardViewHolder.setCardView(list.get( position).getItemName(), list.get(position).getItemDepositPrice(), list.get(position).getItemImageDownloadUrl());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemCardViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName     ;
        TextView textViewItemPrice;
        ImageView imageViewItemImage;



        public ItemCardViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemName  = itemView.findViewById(R.id.rv_tv_item_name);
            textViewItemPrice = itemView.findViewById(R.id.rv_tv_item_price);

            imageViewItemImage = itemView.findViewById(R.id.rv_iv_item_image);
        }

        public void setCardView(String name, String price, String url) {
            textViewItemName.setText(name);
            textViewItemPrice.setText(price);

            Glide.with(context).load(url).into(imageViewItemImage);
        }
    }
}
