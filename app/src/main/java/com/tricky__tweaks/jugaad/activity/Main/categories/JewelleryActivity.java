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

public class JewelleryActivity extends AppCompatActivity {

    RecyclerView jewelleryRv;
    ArrayList<EachItemDataModel> jewelleryList;
    EachCategoryRecyclerAdapter jewelleryRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewellery);

        jewelleryList = new ArrayList<>();
        populateList();
        init_recyclerView();
    }

    private void init_recyclerView() {
        jewelleryRv = findViewById(R.id.jewellery_recyclerView);
        LinearLayoutManager manger = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        jewelleryRecyclerAdapter = new EachCategoryRecyclerAdapter(this, jewelleryList);
        jewelleryRv.setLayoutManager(manger);
        jewelleryRv.setAdapter(jewelleryRecyclerAdapter);
    }

    private void populateList() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products/jewellery");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren()) {
                    if (m.exists()) {
                        EachItemDataModel model = m.getValue(EachItemDataModel.class);
                        jewelleryList.add(model);
                        jewelleryRecyclerAdapter.notifyDataSetChanged();
                    }
                }
                jewelleryRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
