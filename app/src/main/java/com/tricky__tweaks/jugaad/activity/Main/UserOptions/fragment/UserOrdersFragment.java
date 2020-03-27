package com.tricky__tweaks.jugaad.activity.Main.UserOptions.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tricky__tweaks.jugaad.R;


public class UserOrdersFragment extends Fragment {

   private TextView textViewOderNo;
   private TextView textViewTrackingNo;
   private TextView textViewDate;
   private TextView textViewQuantity;
   private TextView textViewDeliveryStatus;

   private ImageView orderImage;

   private RecyclerView orderRecyclerView;

    public UserOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_orders, container, false);

        orderRecyclerView = view.findViewById(R.id.user_order_recycler_view);

        textViewOderNo = view.findViewById(R.id.cv_rv_order_no);
        textViewTrackingNo = view.findViewById(R.id.cv_rv_tracking_no);
        textViewDate = view.findViewById(R.id.cv_rv_date);
        textViewQuantity = view.findViewById(R.id.cv_rv_quantity);
        textViewDeliveryStatus = view.findViewById(R.id.cv_rv_delivery_status);

        orderImage = view.findViewById(R.id.cv_rv_order_image);

        return view;
    }


}
