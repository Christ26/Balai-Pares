package com.app.balaipares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AdminPanel extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<FinalOrderModel> flist;
    private ArrayList<SalesModel> slist;

    private MainAdapter adapter;

    private FirebaseDatabase root;
    private DatabaseReference reference;
    Button sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        slist = new ArrayList<>();

        sales = findViewById(R.id.sales);
        sales.setClickable(false);

        root = FirebaseDatabase.getInstance();
        reference = root.getReference("admin/OrdersToDeliver");

        recyclerView = findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);


        flist = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (snapshot.hasChildren()){
                        FinalOrderModel finalOrderModel = new FinalOrderModel();

                        finalOrderModel.setFoodOrders(Objects.requireNonNull(dataSnapshot.child("foodOrders").getValue()).toString());
                        finalOrderModel.setAddOns(Objects.requireNonNull(dataSnapshot.child("addOns").getValue()).toString());
                        finalOrderModel.setFoodQuantity(Objects.requireNonNull(dataSnapshot.child("foodQuantity").getValue()).toString());
                        finalOrderModel.setDrinksOrders(Objects.requireNonNull(dataSnapshot.child("drinksOrders").getValue()).toString());
                        finalOrderModel.setDrinksQuantity(Objects.requireNonNull(dataSnapshot.child("drinksQuantity").getValue()).toString());
                        finalOrderModel.setTotalPayment(Objects.requireNonNull(dataSnapshot.child("totalPayment").getValue()).toString());
                        finalOrderModel.setNameOfReceiver(Objects.requireNonNull(dataSnapshot.child("nameOfReceiver").getValue()).toString());
                        finalOrderModel.setMobileNumber(Objects.requireNonNull(dataSnapshot.child("mobileNumber").getValue()).toString());
                        finalOrderModel.setAddress(Objects.requireNonNull(dataSnapshot.child("address").getValue()).toString());
                        finalOrderModel.setKey(Objects.requireNonNull(dataSnapshot.child("key").getValue()).toString());

                        flist.add(finalOrderModel);

                    }else{
                        Toast.makeText(AdminPanel.this, "No Orders", Toast.LENGTH_SHORT).show();
                    }

                }
                adapter = new MainAdapter(AdminPanel.this,flist);
                recyclerView.setAdapter(adapter);
                recyclerView.setAdapter(adapter);
                goToSales();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminPanel.this, "DB ERROR", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void goToSales() {
        reference = root.getReference("TotalSales");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (snapshot.hasChildren()){
                        SalesModel salesModel = new SalesModel();

                        salesModel.setTotalSales(Objects.requireNonNull(dataSnapshot.child("totalSales").getValue()).toString());
                        salesModel.setTotalOrder(Objects.requireNonNull(dataSnapshot.child("totalOrder").getValue()).toString());
                        slist.add(salesModel);

                    }else{
                        Toast.makeText(AdminPanel.this, "No Customer", Toast.LENGTH_SHORT).show();
                    }

                }
                if (slist.size() > 0)
                goToast(slist.get(0).getTotalSales(),slist.get(0).getTotalOrder());
                else
                    return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminPanel.this, "DB ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToast(String totalSales, String totalOrder) {
        Global.sales = totalSales;
        Global.orders = totalOrder;
        if (Global.sales != "")
        sales.setClickable(true);
        else
            return;

    }

    public void ExitAdmin(View view) {
        FirebaseAuth.getInstance().signOut();
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        super.onBackPressed();
    }

    public void Sales(View view) {
        Intent intent = new Intent(AdminPanel.this,Sales.class);
        startActivity(intent);
    }

    public void Customers(View view) {
        Intent intent = new Intent(AdminPanel.this,Customers.class);
        startActivity(intent);

    }

    public void Security(View view) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        EditText resetMail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Change Password");
        passwordResetDialog.setMessage("Enter Admin email");
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Reset", (dialog, which) -> {
            //extract emailand sent rest link
            String mail = resetMail.getText().toString();
            if (TextUtils.isEmpty(mail)){
                Toast.makeText(this, "Field is Empty", Toast.LENGTH_SHORT).show();
                return;

            }
            if ( mail.equals("balaipares26@gmail.com")){
                fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(aVoid -> Toast.makeText( AdminPanel.this, "Reset link has been sent.", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText( AdminPanel.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
             else {
                resetMail.setError("Not An Admin Account");
                Toast.makeText(this, "Not An Admin Account", Toast.LENGTH_SHORT).show();
            }

        });

        passwordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //close dialog
            }
        });


        passwordResetDialog.create().show();
    }

    @Override
    protected void onDestroy() {
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }
}