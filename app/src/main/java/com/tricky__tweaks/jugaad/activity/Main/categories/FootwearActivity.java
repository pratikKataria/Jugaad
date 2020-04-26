package com.tricky__tweaks.jugaad.activity.Main.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity.PlaceItemOrderActivity;
import com.tricky__tweaks.jugaad.adapter.EachCategoryRecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class FootwearActivity extends AppCompatActivity {

    RecyclerView footwearRv;
    ArrayList<EachItemDataModel> footwearList;
    EachCategoryRecyclerAdapter footwearAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footwear);

        footwearList = new ArrayList<>();
        populateList();
        init_recyclerView();
    }

    private void init_recyclerView() {
        footwearRv = findViewById(R.id.footwear_recyclerView);
        GridLayoutManager manger = new GridLayoutManager(this, 2);
        footwearAdapter = new EachCategoryRecyclerAdapter(this, footwearList);
        footwearRv.setLayoutManager(manger);
        footwearRv.setAdapter(footwearAdapter);
        footwearAdapter.setOnItemClickListener(position -> {
            EachItemDataModel eidm = footwearList.get(position);
            startActivity(new Intent(FootwearActivity.this, PlaceItemOrderActivity.class).putExtra("DATA_MODEL", (Serializable) eidm));
            Toast.makeText(FootwearActivity.this, "posittion" + position, Toast.LENGTH_SHORT).show();
        });
    }

        private void populateList() {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products/footwear");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot m : dataSnapshot.getChildren()) {
                        if (m.exists()) {
                            EachItemDataModel model = m.getValue(EachItemDataModel.class);
                            footwearList.add(model);
                            footwearAdapter.notifyDataSetChanged();
                        }
                    }
                    footwearAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

}
