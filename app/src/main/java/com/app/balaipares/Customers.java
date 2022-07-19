package com.app.balaipares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Customers extends AppCompatActivity {

    FirebaseDatabase root;
    DatabaseReference ref;
    ArrayList<UserModel> list;
    RecyclerView recyclerView;
    CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);


        root = FirebaseDatabase.getInstance();
        ref = root.getReference().child("user");
        recyclerView = findViewById(R.id.rec);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();




        recyclerView.setAdapter(customerAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        UserModel userModel = dataSnapshot.getValue(UserModel.class);
                        list.add(userModel);

                }

                customerAdapter = new CustomerAdapter(Customers.this,list);
                recyclerView.setAdapter(customerAdapter);

                customerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void Backadmin(View view) {
        onBackPressed();
    }

}