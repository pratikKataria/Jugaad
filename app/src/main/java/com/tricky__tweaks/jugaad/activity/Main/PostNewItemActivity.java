package com.tricky__tweaks.jugaad.activity.Main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tricky__tweaks.jugaad.R;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PostNewItemActivity extends AppCompatActivity {

    ImageView itemImage;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_new_item);

        EditText editTextName = findViewById(R.id.activity_post_new_item_name);
        EditText editTextMainPrice = findViewById(R.id.activity_post_new_item_et_main_price);
        EditText editTextRentPrice = findViewById(R.id.activity_post_new_item_et_rent_price);

        itemImage = findViewById(R.id.activity_post_new_item_iv_item_image);

        Spinner catSpinner = findViewById(R.id.activity_post_new_item_spinner_category);
        ProgressBar progressBar = findViewById(R.id.activity_post_new_item_progress_bar);
        MaterialButton materialButton = findViewById(R.id.activity_post_new_item_mb_save);

        itemImage.setOnClickListener(v -> {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(PostNewItemActivity.this);
        });

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

            StorageReference storageReference = FirebaseStorage.getInstance().getReference("ProductImage").child(sCategory[0]).child(key+".jpeg");
            if (imageUri != null) {
                storageReference.putFile(imageUri).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful() && task1.getResult() != null) {

                        Task<Uri> fileUri = task1.getResult().getMetadata().getReference().getDownloadUrl();
                        fileUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String downloadUrl = uri.toString();

                                map.put( "/"+ sCategory[0] + "/"+ key + "/itemImageDownloadUrl", downloadUrl);

                                ref.updateChildren(map).addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        Toast.makeText(PostNewItemActivity.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(e -> {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(PostNewItemActivity.this, "failed to upload task: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });

                            }
                        });
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                itemImage.setImageURI(imageUri);
                Toast.makeText(this, "result uri " + imageUri.toString(), Toast.LENGTH_SHORT).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                if (error != null)
                    Toast.makeText(this, "error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
