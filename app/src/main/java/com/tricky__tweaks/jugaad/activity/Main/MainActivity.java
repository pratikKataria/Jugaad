package com.tricky__tweaks.jugaad.activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Login.Login;
import com.tricky__tweaks.jugaad.activity.Login.SignUp;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity.OptionsMainActivity;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity.PostNewItemActivity;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.fragment.AboutFragment;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.fragment.ContactUsFragment;
import com.tricky__tweaks.jugaad.activity.Main.categories.ClothActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.FootwearActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.FurnitureActivity;
import com.tricky__tweaks.jugaad.activity.Main.categories.JewelleryActivity;
import com.tricky__tweaks.jugaad.adapter.MainScreenRecyclerAdapter;
import com.tricky__tweaks.jugaad.adapter.SpacesItemDecoration;
import com.tricky__tweaks.jugaad.Model.RentalProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private MainScreenRecyclerAdapter mainScreenRecyclerAdapter;
    private ArrayList<RentalProduct> datalist;

//    private Button button = findViewById(R.id.button);

    private void init_fields() {
        recyclerView = findViewById(R.id.recyclerView);
        datalist = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillCategoryItems();
        fillUserProfileLayout();

        init_fields();
        init_recyclerView();
        populateList();
    }

    public void fillUserProfileLayout() {
        View view = findViewById(R.id.user_profile_include);

        CardView cvUploadProduct = view.findViewById(R.id.user_option_upload_product_card);
        CardView cvLogout = view.findViewById(R.id.user_option_order_logout);
        CardView cvOrders = view.findViewById(R.id.user_option_orders);
        CardView cvAbout = view.findViewById(R.id.user_option_about);
        CardView cvContactus = view.findViewById(R.id.user_option_contact_us);
        CardView cvProfile = view.findViewById(R.id.user_option_profile);

        cvLogout.setOnClickListener(n -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        });

        cvUploadProduct.setOnClickListener(n -> {
            startActivity(new Intent(MainActivity.this, PostNewItemActivity.class));
        });

        cvAbout.setOnClickListener(n -> {
            startActivity(new Intent(MainActivity.this, OptionsMainActivity.class).putExtra("FRAGMENT", (Serializable) new AboutFragment()));

        });

        cvContactus.setOnClickListener(n -> {
            startActivity(new Intent(MainActivity.this, OptionsMainActivity.class).putExtra("FRAGMENT", (Serializable) new ContactUsFragment()));

        });

        cvOrders.setOnClickListener(n -> {

        });

        cvProfile.setOnClickListener(n -> {

        });
    }

    private void fillCategoryItems() {
        View v_cloth = findViewById(R.id.include_cloth);

        ImageView imageView = v_cloth.findViewById(R.id.rv_iv_item_image);
        imageView.setImageResource(R.drawable.shirt);
        TextView  textView = v_cloth.findViewById(R.id.rv_tv_item_name);
        textView.setText("Cloth");

        View v_jewll = findViewById(R.id.include_jewellery);
        imageView = v_jewll.findViewById(R.id.rv_iv_item_image);
        imageView.setImageResource(R.drawable.jewellery);
        textView = v_jewll.findViewById(R.id.rv_tv_item_name);
        textView.setText("Jewellery");


        View v_foot = findViewById(R.id.include_furniture);
        imageView = v_foot.findViewById(R.id.rv_iv_item_image);
        imageView.setImageResource(R.drawable.bed);
        textView = v_foot.findViewById(R.id.rv_tv_item_name);
        textView.setText("Furniture");


        View v_furni = findViewById(R.id.include_footwear);
        imageView = v_furni.findViewById(R.id.rv_iv_item_image);
        imageView.setImageResource(R.drawable.footware);
        textView = v_furni.findViewById(R.id.rv_tv_item_name);
        textView.setText("Footwear");

        View v_end = findViewById(R.id.include_end);
        imageView = v_end.findViewById(R.id.rv_iv_item_image);
        imageView.setImageDrawable(getDrawable(R.drawable.ic_right_arrow));
        textView = v_end.findViewById(R.id.rv_tv_item_name);
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
        }
    }


    private void init_recyclerView() {
        mainScreenRecyclerAdapter = new MainScreenRecyclerAdapter(this, datalist);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(23);
        recyclerView.addItemDecoration(spacesItemDecoration);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(mainScreenRecyclerAdapter);

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

        mainScreenRecyclerAdapter.notifyDataSetChanged();
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
