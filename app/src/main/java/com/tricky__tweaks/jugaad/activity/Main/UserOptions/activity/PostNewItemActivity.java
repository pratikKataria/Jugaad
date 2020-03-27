package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import id.zelory.compressor.Compressor;

public class PostNewItemActivity extends AppCompatActivity {

    ImageView itemImage;
    Uri imageUri;
    Bitmap compressedImageFile;

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

            String itemName      = editTextName.getText().toString();

            if (itemName.isEmpty()) {
                editTextName.setError("should not be empty");
                editTextName.requestFocus();
                return;
            }

            if (editTextMainPrice.getText().toString().isEmpty()) {
                editTextMainPrice.setError("should not be empty");
                editTextMainPrice.requestFocus();
                return;
            }

            if (editTextRentPrice.getText().toString().isEmpty()) {
                editTextRentPrice.setError("should not be empty");
                editTextRentPrice.requestFocus();
                return;
            }

            String itemCategory  = sCategory[0];

            int itemMainPrice = Integer.parseInt(editTextMainPrice.getText().toString());
            int itemRentPrice = Integer.parseInt(editTextRentPrice.getText().toString());
            int itemDepositPrice;

            if (!(itemRentPrice < itemMainPrice)) {
                Toast.makeText(this, "item rent price should be lower then main price", Toast.LENGTH_SHORT).show();
                return;
            } else {
                itemDepositPrice = itemMainPrice - itemRentPrice;
            }

            if (imageUri == null) {
                Toast.makeText(this, "uri is null", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> map = new HashMap<>();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Products");

            String key = ref.push().getKey();

            map.put( "/"+ sCategory[0] + "/"+ key + "/itemId", key);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemName", itemName);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemCategory", itemCategory);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemMainPrice", itemMainPrice);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemRentPrice", itemRentPrice);
            map.put( "/"+ sCategory[0] + "/"+ key + "/itemDepositPrice", itemDepositPrice);
            map.put("/" + sCategory[0] + "/" + key + "/item_priority", (int)System.nanoTime());

            File newImageFile = new File(imageUri.getPath());

            try {
                compressedImageFile = new Compressor(PostNewItemActivity.this)
                        .setQuality(5)
                        .compressToBitmap(newImageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            progressBar.setVisibility(View.VISIBLE);
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("ProductImage").child(sCategory[0]);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] thumbdata = baos.toByteArray();

            UploadTask uploadTask = storageReference.child(key + ".jpeg").putBytes(thumbdata);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getReference() != null) {
                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                    result.addOnSuccessListener(uri -> {
                        String downloadUrl = uri.toString();
                        map.put("/" + sCategory[0] + "/" + key + "/itemImageDownloadUrl", downloadUrl);

                        ref.updateChildren(map).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(PostNewItemActivity.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(e -> {

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(PostNewItemActivity.this, "failed to upload task: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }).addOnFailureListener(e1 -> {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(PostNewItemActivity.this, "error: " + e1.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(PostNewItemActivity.this, "error: ", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PostNewItemActivity.this, "error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });

//            if (imageUri != null) {
//                progressBar.setVisibility(View.VISIBLE);
//                storageReference.putFile(imageUri).addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful() && task1.getResult() != null) {
//
//                        Task<Uri> fileUri = task1.getResult().getMetadata().getReference().getDownloadUrl();
//                        fileUri.addOnSuccessListener(uri -> {
//
//                            String downloadUrl = uri.toString();
//
//                            map.put( "/"+ sCategory[0] + "/"+ key + "/itemImageDownloadUrl", downloadUrl);
//
//                            ref.updateChildren(map).addOnCompleteListener(task -> {
//                                if (task.isSuccessful()) {
//
//                                    progressBar.setVisibility(View.GONE);
//
//                                    Toast.makeText(PostNewItemActivity.this, "uploaded successfully", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                            }).addOnFailureListener(e -> {
//
//                                progressBar.setVisibility(View.GONE);
//
//                                Toast.makeText(PostNewItemActivity.this, "failed to upload task: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            });
//
//                        }).addOnFailureListener(e -> {
//                            progressBar.setVisibility(View.GONE);
//
//                            Toast.makeText(this, "error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        });
//                    }
//                });
//            }
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
