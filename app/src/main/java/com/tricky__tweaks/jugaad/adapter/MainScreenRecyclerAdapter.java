package com.tricky__tweaks.jugaad.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.model.RentalProduct;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainScreenRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<RentalProduct> list;
    private LayoutInflater inflater;

    public MainScreenRecyclerAdapter(Context context, ArrayList<RentalProduct> list) {
        this.list = list;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rental_card_view, parent, false);
        holder = new ProductCardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductCardViewHolder productCardViewHolder = (ProductCardViewHolder) holder;

        productCardViewHolder.productName.setText(list.get(position).getProductName());
        productCardViewHolder.productImage.setImageResource(list.get(position).getImageDrawable());
        productCardViewHolder.productPrice.setText(list.get(position).getProductPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
