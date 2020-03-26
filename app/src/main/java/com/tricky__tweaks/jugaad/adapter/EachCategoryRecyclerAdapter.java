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
import com.tricky__tweaks.jugaad.Utility.Utility;

import java.util.ArrayList;

public class EachCategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<EachItemDataModel> list;
    OnItemClickListener mListener;

    private static final int CARD_VIEW = 1;
    private static final int EMPTY_VIEW = 0;

    public   interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener  = listener;
    }

    public EachCategoryRecyclerAdapter(Context context, ArrayList<EachItemDataModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        if (viewType == CARD_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_each_items_rv, parent, false);
            holder = new ItemCardViewHolder(view, mListener, context);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_loading_layout, parent, false);
            holder = new EmptyViewLoader(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemCardViewHolder) {
            ItemCardViewHolder productCardViewHolder = (ItemCardViewHolder) holder;

            productCardViewHolder.setCardView(list.get( position).getItemName(), list.get(position).getItemDepositPrice()+"", list.get(position).getItemImageDownloadUrl());

        } else {
            EmptyViewLoader emptyViewLoader = (EmptyViewLoader) holder;
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

    public static class ItemCardViewHolder extends RecyclerView.ViewHolder {

        TextView textViewItemName;
        TextView textViewItemPrice;
        ImageView imageViewItemImage;

        Context context;

        public ItemCardViewHolder(@NonNull View itemView, OnItemClickListener listener, Context context) {
            super(itemView);

            textViewItemName  = itemView.findViewById(R.id.rv_tv_item_name);
            textViewItemPrice = itemView.findViewById(R.id.rv_tv_item_price);

            imageViewItemImage = itemView.findViewById(R.id.rv_iv_item_image);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            this.context = context;
        }

        public void setCardView(String name, String price, String url) {
            textViewItemName.setText(Utility.toTitleCase(name));
            textViewItemPrice.setText(price);

            Glide.with(context).load(url).into(imageViewItemImage);
        }
    }

    private static class EmptyViewLoader extends RecyclerView.ViewHolder {
        public EmptyViewLoader(View view) {
            super(view);
        }
    }
}
