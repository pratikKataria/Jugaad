package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;

import java.security.MessageDigest;

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
            String titleText = toTitleCase(model.getItemName());
            textViewItemName.setText(titleText);
            textViewItemCategory.setText(model.getItemCategory());
            textViewItemDepositPrice.setText(model.getItemDepositPrice() + "");
            textViewItemMainPrice.setText(model.getItemMainPrice() + "");
            textViewItemRentPrice.setText("\u20b9"+model.getItemRentPrice() + "");

            Glide.with(this).load(model.getItemImageDownloadUrl()).into(imageViewItemImage);

            placeOrder.setOnClickListener(n -> {

                if (model.getItemId() != null && model.getItemId().isEmpty()) {
                    return;
                }

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Orders");
                ref.child(FirebaseAuth.getInstance().getUid());

            });
        }
    }

    public static String toTitleCase(String str) {

        if (str == null) {
            return null;
        }

        boolean space = true;
        StringBuilder builder = new StringBuilder(str);
        final int len = builder.length();

        for (int i = 0; i < len; ++i) {
            char c = builder.charAt(i);
            if (space) {
                if (!Character.isWhitespace(c)) {
                    // Convert to title case and switch out of whitespace mode.
                    builder.setCharAt(i, Character.toTitleCase(c));
                    space = false;
                }
            } else if (Character.isWhitespace(c)) {
                space = true;
            } else {
                builder.setCharAt(i, Character.toLowerCase(c));
            }
        }

        return builder.toString();
    }
}