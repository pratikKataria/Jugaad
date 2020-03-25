package com.tricky__tweaks.jugaad.activity.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tricky__tweaks.jugaad.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostNewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_new_item);

        EditText editTextName = findViewById(R.id.activity_post_new_item_name);
        EditText editTextMainPrice = findViewById(R.id.activity_post_new_item_et_main_price);
        EditText editTextRentPrice = findViewById(R.id.activity_post_new_item_et_rent_price);

        ImageView imageView = findViewById(R.id.activity_post_new_item_iv_item_image);

        Spinner catSpinner = findViewById(R.id.activity_post_new_item_spinner_category);
        ProgressBar progressBar = findViewById(R.id.activity_post_new_item_progress_bar);
        MaterialButton materialButton = findViewById(R.id.activity_post_new_item_mb_save);


        final String[] sCategory = {"cloth"};
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.category,
                android.R.layout.simple_spinner_dropdown_item
        );

        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        catSpinner.setAdapter(arrayAdapter);
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sCategory[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        materialButton.setOnClickListener(n -> {

            String itemName = editTextName.getText().toString();
            String itemCategory = sCategory[0];
            String itemMainPrice = editTextMainPrice.getText().toString();
            String itemRentPrice = editTextRentPrice.getText().toString();
            String itemDepositPrice = Integer.parseInt(itemMainPrice) - Integer.parseInt(itemRentPrice) + "";


            Map<String, Object> map = new HashMap<>();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Products");
            String key = ref.push().getKey();

            map.put( "/"+ sCategory[0] + "/"+ key + "/itemID", key);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemName", itemName);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemCategory", itemCategory);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemMainPrice", itemMainPrice);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemRentPrice", itemRentPrice);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemDepositPrice", itemDepositPrice);

            ref.updateChildren(map).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {

                    Toast.makeText(PostNewItemActivity.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> Toast.makeText(this, "failed to upload task: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
