package com.tricky__tweaks.jugaad.activity.Main.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.PlaceItemOrderActivity;
import com.tricky__tweaks.jugaad.adapter.EachCategoryRecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClothActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EachCategoryRecyclerAdapter recyclerAdapter;
    ArrayList<EachItemDataModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);

        list = new ArrayList<>();

        populateListView();
        init_recycler_view();

    }

    private void init_recycler_view() {
        recyclerView = findViewById(R.id.cloth_recyclerView);
        LinearLayoutManager llMan = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(llMan);
        recyclerAdapter = new EachCategoryRecyclerAdapter(this, list);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(position -> {
            EachItemDataModel eidm = list.get(position);
            startActivity(new Intent(ClothActivity.this, PlaceItemOrderActivity.class).putExtra("DATA_MODEL", (Serializable) eidm));
            Toast.makeText(ClothActivity.this, "posittion" + position, Toast.LENGTH_SHORT).show();
        });
    }

    private void populateListView() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Products/cloth");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Log.e("CLOTH ACT", s+"");
                    if (s.exists()) {
                        EachItemDataModel model = s.getValue(EachItemDataModel.class);
                        list.add(model);
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
