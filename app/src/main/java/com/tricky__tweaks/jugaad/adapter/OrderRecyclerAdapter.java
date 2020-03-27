package com.tricky__tweaks.jugaad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tricky__tweaks.jugaad.Model.CustomerOrders;
import com.tricky__tweaks.jugaad.Model.EachItemDataModel;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.Utility.Utility;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<CustomerOrders> list;
    OnItemClickListener mListener;

    private static final int CARD_VIEW = 1;
    private static final int EMPTY_VIEW = 0;

    public   interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener  = listener;
    }

    public OrderRecyclerAdapter(Context context, ArrayList<CustomerOrders> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        if (viewType == CARD_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_orders, parent, false);
            holder = new CustomerOrdersHolder(view, mListener, context);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_loading_layout, parent, false);
            holder = new EmptyViewLoader(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CustomerOrdersHolder) {
            CustomerOrdersHolder ordersHolder = (CustomerOrdersHolder) holder;

            ordersHolder.setCardView(list.get(position).getOrderNumber()+"", list.get(position).getTrackingNumber(), list.get(position).getOrderedDate());
            ordersHolder.setOrderImage(list.get(position).getItemId(), list.get(position).getItemCategory());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() > 0) {
            return CARD_VIEW;

        } else {
            return EMPTY_VIEW;
        }
    }

    @Override
    public int getItemCount() {

        if (list.size() == 0) {
            return 1;
        } else {
            return list.size();
        }
    }

    public static class CustomerOrdersHolder extends RecyclerView.ViewHolder {

        private TextView textViewOderNo;
        private TextView textViewTrackingNo;
        private TextView textViewDate;
        private TextView textViewQuantity;
        private TextView textViewDeliveryStatus;
        private TextView textViewName;
        private TextView textViewDeposit;

        private ImageView orderImage;

        Context context;

        public CustomerOrdersHolder(@NonNull View itemView, OnItemClickListener listener, Context context) {
            super(itemView);

            textViewOderNo     = itemView.findViewById(R.id.cv_rv_order_no);
            textViewTrackingNo = itemView.findViewById(R.id.cv_rv_tracking_no);
            textViewDate       = itemView.findViewById(R.id.cv_rv_date);
            textViewQuantity   = itemView.findViewById(R.id.cv_rv_quantity);
            textViewDeliveryStatus = itemView.findViewById(R.id.cv_rv_delivery_status);
            textViewDeposit = itemView.findViewById(R.id.cv_rv_deposit);
            textViewName = itemView.findViewById(R.id.cv_rv_name);

            orderImage = itemView.findViewById(R.id.cv_rv_order_image);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            this.context = context;
        }

        public void setCardView(String orderNo, String trackingNo, String orderedDate) {
            textViewOderNo.setText("Order NO: "+orderNo);
            textViewTrackingNo.setText("Tracking No: "+trackingNo);
            textViewDate.setText("Date: "+orderedDate.substring(0, 10));
            textViewQuantity.setText("Quantity: " +1+"");
            textViewDeliveryStatus.setText("Delivery status: "+ "ordered");
        }

        public void setOrderImage(String orderId, String orderCategory) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Products/"+orderCategory+"/"+orderId);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        EachItemDataModel data = dataSnapshot.getValue(EachItemDataModel.class);
                        Glide.with(context).load(data.getItemImageDownloadUrl()).into(orderImage);
                        textViewDeposit.setText("Deposit amount: \u20b9"+data.getItemDepositPrice()+"");
                        textViewName.setText(data.getItemName());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private static class EmptyViewLoader extends RecyclerView.ViewHolder {
        public EmptyViewLoader(View view) {
            super(view);
        }
    }
}

