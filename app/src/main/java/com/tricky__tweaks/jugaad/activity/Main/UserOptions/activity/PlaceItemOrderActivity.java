package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;

public class PlaceItemOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_item_order);

        EachItemDataModel model = (EachItemDataModel) getIntent().getSerializableExtra("DATA_MODEL");

        TextView textViewItemName = findViewById(R.id.place_item_tv_name);
        TextView textViewItemCategory = findViewById(R.id.place_item_tv_category);
        TextView textViewItemRentPrice = findViewById(R.id.place_item_tv_rent_price);
        TextView textViewItemMainPrice = findViewById(R.id.place_item_tv_main_price);
        TextView textViewItemDepositPrice = findViewById(R.id.place_item_tv_deposit_price);

        ImageView imageViewItemImage = findViewById(R.id.place_item_iv_itemImage);

        MaterialButton placeOrder = findViewById(R.id.activity_post_new_item_mb_save);


        if (model != null) {
            textViewItemName.setText(model.getItemName());
            textViewItemCategory.setText(model.getItemCategory());
            textViewItemDepositPrice.setText(model.getItemDepositPrice()+"");
            textViewItemMainPrice.setText(model.getItemMainPrice()+"");
            textViewItemRentPrice.setText(model.getItemRentPrice()+"");

            Glide.with(this).load(model.getItemImageDownloadUrl()).into(imageViewItemImage);
        }

    }
}
