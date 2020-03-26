package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tricky__tweaks.jugaad.Model.CustomerOrders;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import java.util.Date;


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

        ProgressBar progressBar = findViewById(R.id.place_item_progress_bar);

        Spinner monthSpinner = findViewById(R.id.place_item_spinner_rental_item);
        String rentTime [] = {"1 month"};

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.rental_time,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rentTime[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView imageViewItemImage = findViewById(R.id.place_item_iv_itemImage);

        MaterialButton placeOrder = findViewById(R.id.place_item_mb_place_order);

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

                progressBar.setVisibility(View.VISIBLE);

                //0 = pending // 1 = ordered // 2 =delivered

                String rentDuration = rentTime[0];
                int    quantity = 1;
                int    orderStatus = 0;
                int    itemPriority = (int) System.nanoTime();
                String date = new Date().toString();
                String orderId = model.getItemOrderId();
                String customerId = FirebaseAuth.getInstance().getUid();
                String returnDate = "";
                String deliveryCoordinates = "";
                boolean isPaid = false;

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CustomerOrders");
                ref.child(FirebaseAuth.getInstance().getUid()+"/"+ model.getItemOrderId()).setValue(
                        new CustomerOrders(
                                rentDuration,
                                quantity,
                                orderStatus,
                                itemPriority,
                                date,
                                orderId,
                                customerId,
                                returnDate,
                                deliveryCoordinates,
                                isPaid
                        )
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PlaceItemOrderActivity.this, "added ordered successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(PlaceItemOrderActivity.this, "task is failed to ordered" + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

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