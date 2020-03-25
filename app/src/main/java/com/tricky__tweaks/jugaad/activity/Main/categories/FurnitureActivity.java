package com.tricky__tweaks.jugaad.activity.Main.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.adapter.EachCategoryRecyclerAdapter;

import java.util.ArrayList;

public class FurnitureActivity extends AppCompatActivity {

    RecyclerView furnitureRv;
    ArrayList<EachItemDataModel> furnitureList;
    EachCategoryRecyclerAdapter furnitureRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        furnitureList = new ArrayList<>();
        populateList();
        init_recyclerView();
    }

    private void init_recyclerView() {
        furnitureRv = findViewById(R.id.footwear_recyclerView);
        LinearLayoutManager manger = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        furnitureRecyclerAdapter = new EachCategoryRecyclerAdapter(this, furnitureList);
        furnitureRv.setLayoutManager(manger);
        furnitureRv.setAdapter(furnitureRecyclerAdapter);
    }

    private void populateList() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products/footwear");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren()) {
                    if (m.exists()) {
                        EachItemDataModel model = m.getValue(EachItemDataModel.class);
                        furnitureList.add(model);
                        furnitureRecyclerAdapter.notifyDataSetChanged();
                    }
                }
                furnitureRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
