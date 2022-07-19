package com.app.balaipares;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    Context context;
    ArrayList<FinalOrderModel> fList;

    FirebaseDatabase root;
    DatabaseReference ref;

    public MainAdapter(Context context, ArrayList<FinalOrderModel> fList) {
        this.context = context;
        this.fList = fList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_item, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FinalOrderModel finalOrderModel = fList.get(position);
        root = FirebaseDatabase.getInstance();
        holder.nameClick.setText(finalOrderModel.getNameOfReceiver());

        holder.nameClick.setOnClickListener(view -> {
            Intent intent = new Intent(context,OrderDetails.class);
            intent.putExtra("FOOD_ORDERS",finalOrderModel.getFoodOrders());
            intent.putExtra("FOOD_ADD_ONS",finalOrderModel.getAddOns());
            intent.putExtra("FOOD_QUANTITY",finalOrderModel.getFoodQuantity());
            intent.putExtra("DRINKS_ORDER",finalOrderModel.getDrinksOrders());
            intent.putExtra("DRINKS_QUANTITY",finalOrderModel.getDrinksQuantity());
            intent.putExtra("NAME_RECEIVER",finalOrderModel.getNameOfReceiver());
            intent.putExtra("RECEIVER_PHONE",finalOrderModel.getMobileNumber());
            intent.putExtra("RECEIVER_ADDRESS",finalOrderModel.getAddress());
            intent.putExtra("PAYMENT",finalOrderModel.getTotalPayment());
            intent.putExtra("KEY",finalOrderModel.getKey());


            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        });



    }

    @Override
    public int getItemCount() {
        return fList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameClick;
        ImageView indicator;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameClick = itemView.findViewById(R.id.nameClick);
            indicator = itemView.findViewById(R.id.greenDot);
        }
    }
}
