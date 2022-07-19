package com.app.balaipares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Sales extends AppCompatActivity {
    Spinner spinnerMonth,spinnerYear;
    String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"} ;
    TextView totalSales,totalOrder;
    List<String> year;
    List<SalesModel> slist;
    private FirebaseDatabase root;
    private DatabaseReference reference;
    ArrayAdapter<String> adapter2;
    String yearPath = "";

    Button view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        year = new ArrayList<>();
        slist = new ArrayList<>();


        totalSales = findViewById(R.id.admin_total_sales);
        totalOrder = findViewById(R.id.admin_total_orders);
        view = findViewById(R.id.viewSales);

        spinnerMonth = findViewById(R.id.monthSpinner);
        spinnerYear = findViewById(R.id.yearSpinner);

        root = FirebaseDatabase.getInstance();
        reference = root.getReference("Years/yearList");



        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (snapshot.hasChildren()){

                        year.add(dataSnapshot.child("year").getValue().toString());
                       
                    }else{
                        Toast.makeText(Sales.this, "No Sales Yet", Toast.LENGTH_SHORT).show();
                    }

                }
                prepairYear(year);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Sales.this, "DB ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        int year = Calendar.getInstance().get(Calendar.YEAR);



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Sales.this, android.R.layout.simple_spinner_item,months);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMonth.setAdapter(adapter1);
       

        if (Global.sales != ""){
            totalSales.setText(Global.sales);
            totalOrder.setText(Global.orders);
        }



        view.setOnClickListener(v->{
            String monthPath = spinnerMonth.getSelectedItem().toString();
            yearPath = spinnerYear.getSelectedItem().toString();
            String path = monthPath+yearPath;
            goToSales(path);
        });

    }

    private void goToast(String totalSalesTv, String totalOrderTv) {
        totalSales.setText(totalSalesTv);
        totalOrder.setText(totalOrderTv);
    }

    private void prepairYear(List<String> year) {
        adapter2 = new ArrayAdapter<String>(Sales.this, android.R.layout.simple_spinner_item,year);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(adapter2);
    }


    private void goToSales(String path) {
        DatabaseReference sRef = FirebaseDatabase.getInstance().getReference("Sales/"+path);
        DatabaseReference sPRef = sRef.child("record");
        DatabaseReference pathRef = sPRef.child("totalSales");

        pathRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    totalSales.setText("No Sales Record");
                    goToOrderTotal(path);
                    return;
                }
                String salesT = dataSnapshot.getValue().toString();
                if (!TextUtils.isEmpty(salesT)){
                    totalSales.setText(salesT);
                }else{
                    totalSales.setText("No Sales Record");
                }
             //   Toast.makeText(Sales.this, salesT, Toast.LENGTH_SHORT).show();
                goToOrderTotal(path);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void goToOrderTotal(String path) {
        DatabaseReference sRef = FirebaseDatabase.getInstance().getReference("Sales/"+path);
        DatabaseReference sPRef = sRef.child("record");
        DatabaseReference pathRef = sPRef.child("totalOrder");
        pathRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    totalOrder.setText("No Order Record");
                    return;
                }
                String salesT = dataSnapshot.getValue().toString();
                if (!TextUtils.isEmpty(salesT)){
                    totalOrder.setText(salesT);
                }else{
                    totalOrder.setText("No Order Record");
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
    @Override
    protected void onDestroy() {
        FirebaseAuth.getInstance().signOut();
        super.onDestroy();
    }

    public void Backadmin(View view) {
        onBackPressed();
    }
}