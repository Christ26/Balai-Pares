package com.app.balaipares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParesActivity extends AppCompatActivity {
    CheckBox pares, eggPares, garlicPares;
    CheckBox ricePares, eggRicePares, garlicRicePares;

    EditText paresQuantity, riceParesQuantity;
    Button addOrder, nextBtn,mainMenuBtn;

    LinearLayout addOn1, addOn2;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    boolean isHasPares = false;
    boolean isHasBeefRicePares = false;

    boolean isHasEgg = false;
    boolean isHasGarlicRice = false;


    private  static  int paresPrice = 45;
    private  static int riceParesPrice = 55;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pares);

        pares = findViewById(R.id.beef_pares);
        eggPares = findViewById(R.id.egg_pares);
        garlicPares = findViewById(R.id.garlic_rice_pares);

        ricePares = findViewById(R.id.beef_with_rice);
        eggRicePares = findViewById(R.id.egg_beef_pares);
        garlicRicePares = findViewById(R.id.garlic_rice_beef_pares);

        paresQuantity = findViewById(R.id.quantityEtPares);
        riceParesQuantity = findViewById(R.id.quantityEtBeefpares);

        addOrder = findViewById(R.id.addOrder_pares);
        nextBtn = findViewById(R.id.nextBtn_pares);
        mainMenuBtn = findViewById(R.id.backtomenu_pares);

        addOn1 = findViewById(R.id.pares_add_ons);
        addOn2 = findViewById(R.id.beef_pares_add_ons);
        
        rootNode = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        
        String userId = firebaseAuth.getCurrentUser().getUid();
        reference = rootNode.getReference(userId);


        addOrder.setEnabled(false);
        nextBtn.setEnabled(false);
        addOrder.setBackgroundResource(R.drawable.inactive_bg);
        nextBtn.setBackgroundResource(R.drawable.inactive_bg);


        pares.setOnClickListener(view -> {
            if (pares.isChecked()){
                addOrder.setClickable(true);
                addOrder.setEnabled(true);
                addOrder.setBackgroundResource(R.drawable.green_bg);
                addOn1.setVisibility(View.VISIBLE);
            }else{
                addOn1.setVisibility(View.GONE);
                addOrder.setClickable(false);
                addOrder.setEnabled(false);
            }
        });
        ricePares.setOnClickListener(view -> {
            if (ricePares.isChecked()){
                addOrder.setClickable(true);
                addOrder.setEnabled(true);
                addOrder.setBackgroundResource(R.drawable.green_bg);
                addOn2.setVisibility(View.VISIBLE);
            }else{
                addOn2.setVisibility(View.GONE);
                addOrder.setClickable(false);
                addOrder.setEnabled(false);
            }
        });


        addOrder.setOnClickListener(view -> {

            if (pares.isChecked() && ricePares.isChecked()){
                ParesOrder.Order = "Beef Pares and Beef Rice Pares";
                isHasPares = true;
                isHasBeefRicePares = true;

            }else if(pares.isChecked()){
                ParesOrder.Order = "Beef Pares";
                isHasPares = true;

            } else if (ricePares.isChecked()){
                ParesOrder.Order = "Beef Rice Pares";
                isHasBeefRicePares = true;
            }

            if ((eggPares.isChecked()&& garlicPares.isChecked()) || (ricePares.isChecked()&& garlicRicePares.isChecked())){
                ParesOrder.AddOns = "Egg and Garlic Rice";
                isHasEgg = true;
                isHasGarlicRice = true;
            }else if (eggPares.isChecked() || eggRicePares.isChecked()){
                ParesOrder.AddOns = "Egg";
                isHasEgg = true;
            }else if (garlicPares.isChecked() || garlicRicePares.isChecked()){
                ParesOrder.AddOns = "Garlic rice";
                isHasGarlicRice = true;
            }else {
                ParesOrder.AddOns = "";
            }

            String paresQuant = paresQuantity.getText().toString();
            String riceParesQuant = riceParesQuantity.getText().toString();
            
            if (isHasPares && isHasBeefRicePares){
                if (TextUtils.isEmpty(paresQuant)){
                    paresQuantity.setError("Please Specify");
                    return;
                }
                if (TextUtils.isEmpty(riceParesQuant)){
                    riceParesQuantity.setError("Please Specify");
                    return;
                }
                if (!TextUtils.isEmpty(paresQuant) && !TextUtils.isEmpty(riceParesQuant)){
                   if (isHasEgg && isHasGarlicRice){
                       paresPrice = 65;
                       riceParesPrice = 75;
                       String quantity = paresQuant+" Beef Pares and "+riceParesQuant+" Beef Pares w/ rice";
                       int pQuant = Integer.parseInt(paresQuant);
                       int pRiceQuant = Integer.parseInt(riceParesQuant);
                       int paresPay = paresPrice * pQuant;
                       int riceParesPay = paresPrice * pRiceQuant;
                       int totalPay = paresPay + riceParesPay;

                       goToPreview(totalPay,quantity);

                      }else if (isHasEgg){
                       paresPrice = 55;
                       riceParesPrice = 65;
                       String quantity = paresQuant+" Beef Pares and "+riceParesQuant+" Beef Pares w/ rice";
                       int pQuant = Integer.parseInt(paresQuant);
                       int pRiceQuant = Integer.parseInt(riceParesQuant);
                       int paresPay = paresPrice * pQuant;
                       int riceParesPay = paresPrice * pRiceQuant;
                       int totalPay = paresPay + riceParesPay;

                       goToPreview(totalPay,quantity);
                   }else if (isHasGarlicRice){
                       paresPrice = 55;
                       riceParesPrice = 65;
                       String quantity = paresQuant+" Beef Pares and "+riceParesQuant+" Beef Pares w/ rice";
                       int pQuant = Integer.parseInt(paresQuant);
                       int pRiceQuant = Integer.parseInt(riceParesQuant);
                       int paresPay = paresPrice * pQuant;
                       int riceParesPay = paresPrice * pRiceQuant;
                       int totalPay = paresPay + riceParesPay;

                       goToPreview(totalPay,quantity);
                   }else{
                       paresPrice = 45;
                       riceParesPrice = 55;
                       String quantity = paresQuant+" Beef Pares and "+riceParesQuant+" Beef Pares w/ rice";
                       int pQuant = Integer.parseInt(paresQuant);
                       int pRiceQuant = Integer.parseInt(riceParesQuant);
                       int paresPay = paresPrice * pQuant;
                       int riceParesPay = paresPrice * pRiceQuant;
                       int totalPay = paresPay + riceParesPay;

                       goToPreview(totalPay,quantity);
                   }


                }

            }else if (isHasPares){
                if (TextUtils.isEmpty(paresQuant)){
                    paresQuantity.setError("Please Specify");
                }else {
                    if (isHasEgg && isHasGarlicRice){
                        paresPrice = 65;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(paresQuant);
                        int paresPay = paresPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);

                    }else if (isHasEgg){
                        paresPrice = 55;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(paresQuant);
                        int paresPay = paresPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }else if (isHasGarlicRice){
                        paresPrice = 55;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(paresQuant);
                        int paresPay = paresPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }else{
                        paresPrice = 45;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(paresQuant);
                        int paresPay = paresPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }
                }
            }else if (isHasBeefRicePares){
                if (TextUtils.isEmpty(riceParesQuant)){
                    riceParesQuantity.setError("Please Specify");
                }else {
                    if (isHasEgg && isHasGarlicRice){
                        riceParesPrice = 75;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(riceParesQuant);
                        int paresPay = riceParesPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);

                    }else if (isHasEgg){
                        riceParesPrice = 65;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(riceParesQuant);
                        int paresPay = riceParesPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }else if (isHasGarlicRice){
                        riceParesPrice = 65;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(riceParesQuant);
                        int paresPay = riceParesPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }else{
                        riceParesPrice = 55;
                        String quantity = paresQuant+" Beef Pares";
                        int pQuant = Integer.parseInt(riceParesQuant);
                        int paresPay = riceParesPrice * pQuant;
                        int totalPay = paresPay;

                        goToPreview(totalPay,quantity);
                    }
                }
            }
        });

        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ParesActivity.this,PaymentSectionActivity.class);
            startActivity(intent);

        });

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ParesActivity.this,MainMenuActivity.class);
            startActivity(intent);
            finishAffinity();

        });



    }

    private void goToPreview(int totalPay, String quantity) {
        String payTotal = String.valueOf(totalPay);
        ParesOrder.ParesTotalPay = payTotal;
        ParesOrder.Quantity = quantity;
        nextBtn.setEnabled(true);
        nextBtn.setClickable(true);
        nextBtn.setBackgroundResource(R.drawable.yellow_bg);
       /* PreOrderModel preOrderModel = new PreOrderModel(ParesOrder.Order,ParesOrder.AddOns,ParesOrder.Quantity,ParesOrder.ParesTotalPay);
        reference.child("pre-order").push().setValue(preOrderModel);*/
        Toast.makeText(ParesActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

    }
}