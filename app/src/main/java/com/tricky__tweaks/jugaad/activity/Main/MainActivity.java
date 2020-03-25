package com.tricky__tweaks.jugaad.activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Login.SignUp;
import com.tricky__tweaks.jugaad.activity.Main.categories.ClothActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.FootwearActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.FurnitureActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.JewelleryActivity;
import com.tricky__tweaks.jugaad.adapter.RecyclerViewAdapter;
import com.tricky__tweaks.jugaad.adapter.SpacesItemDecoration;
import com.tricky__tweaks.jugaad.model.RentalProduct;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<RentalProduct> datalist;
    private FloatingActionButton fabPostNewItem;

//    private Button button = findViewById(R.id.button);

    private void init_fields() {
        recyclerView = findViewById(R.id.recyclerView);
        datalist = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabPostNewItem = findViewById(R.id.floating_action_button);
        fabPostNewItem.setOnClickListener(this);

        fillCategoryItems();

        init_fields();
        init_recyclerView();
        populateList();
    }

    public void fillCategoryItems() {
        View v_cloth = findViewById(R.id.include_cloth);

        ImageView imageView = v_cloth.findViewById(R.id.cat_layout_image_view);
        imageView.setImageResource(R.drawable.shirt);
        TextView  textView = v_cloth.findViewById(R.id.cat_layout_text_view);
        textView.setText("Cloth");

        View v_jewll = findViewById(R.id.include_jewellery);
        imageView = v_jewll.findViewById(R.id.cat_layout_image_view);
        imageView.setImageResource(R.drawable.jewellery);
        textView = v_jewll.findViewById(R.id.cat_layout_text_view);
        textView.setText("Jewellery");


        View v_foot = findViewById(R.id.include_furniture);
        imageView = v_foot.findViewById(R.id.cat_layout_image_view);
        imageView.setImageResource(R.drawable.bed);
        textView = v_foot.findViewById(R.id.cat_layout_text_view);
        textView.setText("Furniture");


        View v_furni = findViewById(R.id.include_footwear);
        imageView = v_furni.findViewById(R.id.cat_layout_image_view);
        imageView.setImageResource(R.drawable.footware);
        textView = v_furni.findViewById(R.id.cat_layout_text_view);
        textView.setText("Footwear");

        View v_end = findViewById(R.id.include_end);
        imageView = v_end.findViewById(R.id.cat_layout_image_view);
        imageView.setImageDrawable(getDrawable(R.drawable.ic_right_arrow));
        textView = v_end.findViewById(R.id.cat_layout_text_view);
        textView.setText("More");

        v_cloth.setOnClickListener(this::onClick);
        v_foot.setOnClickListener(this::onClick);
        v_furni.setOnClickListener(this::onClick);
        v_jewll.setOnClickListener(this::onClick);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_cloth:
                startActivity(new Intent(MainActivity.this, ClothActivity.class));
                break;
            case R.id.include_footwear:
                startActivity(new Intent(MainActivity.this, FootwearActivity.class));
                break;
            case R.id.include_furniture:
                startActivity(new Intent(MainActivity.this, FurnitureActivity.class));
                break;
            case R.id.include_jewellery:
                startActivity(new Intent(MainActivity.this, JewelleryActivity.class));
                break;
            case R.id.include_end:
                Toast.makeText(this, "comming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.floating_action_button:
                startActivity(new Intent(MainActivity.this, PostNewItemActivity.class));
                break;
        }
    }


    private void init_recyclerView() {
        recyclerViewAdapter = new RecyclerViewAdapter(this, datalist);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(23);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    private void populateList() {
        RentalProduct r = new RentalProduct("buds", "rs 254", R.drawable.buds);
        datalist.add(r);
        r = new RentalProduct("lg tv", "12,000", R.drawable.lg_tv);
        datalist.add(r);
        r = new RentalProduct("realme buds ", "25sdfas42", R.drawable.buds2);
        datalist.add(r);
        r = new RentalProduct("washing machine", "15,000", R.drawable.washing_machine);
        datalist.add(r);
        r = new RentalProduct("refrigerator", "10,000", R.drawable.refrigerator);
        datalist.add(r);
        r = new RentalProduct("phone", "2,000", R.drawable.phone);
        datalist.add(r);

        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, SignUp.class));
            finish();
        }
        super.onStart();
    }
}
