package com.tricky__tweaks.jugaad.activity.Main.UserOptions.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tricky__tweaks.jugaad.Model.CustomerOrders;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.adapter.OrderRecyclerAdapter;

import java.io.Serializable;
import java.util.ArrayList;


public class UserOrdersFragment extends Fragment implements Serializable {

   private TextView textViewOderNo;
   private TextView textViewTrackingNo;
   private TextView textViewDate;
   private TextView textViewQuantity;
   private TextView textViewDeliveryStatus;

   private ImageView orderImage;

   private RecyclerView orderRecyclerView;
    OrderRecyclerAdapter adapter;
    ArrayList<CustomerOrders> orderList;

    public UserOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_orders, container, false);


        orderList = new ArrayList<>();
        populateList();

        orderRecyclerView = view.findViewById(R.id.user_order_recycler_view);
        adapter = new OrderRecyclerAdapter(getActivity(), orderList);
        orderRecyclerView.setAdapter(adapter);

        return view;
    }

    private void populateList() {
        DatabaseReference s = FirebaseDatabase.getInstance().getReference("CustomerOrders/" + FirebaseAuth.getInstance().getUid());
        s.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    CustomerOrders orders = shot.getValue(CustomerOrders.class);
                    orderList.add(orders);
                    adapter.notifyDataSetChanged();

//                    String key = dataSnapshot.getKey();
//                    Log.e("Orders Fragment  ", key+"");
//                    Log.e("Orders Fragment  ", dataSnapshot.child(key)+"");
//                    if (key!= null) {
//                        orderList.add(dataSnapshot.child(key).getValue(CustomerOrders.class));
//                    }
//                    adapter.notifyDataSetChanged();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
