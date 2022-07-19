package com.app.balaipares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MamiActivity extends AppCompatActivity {

    CheckBox mami, eggMami, garlicMami;
    CheckBox beefMami, eggBeefMami, garlicRiceMami;

    EditText mamiQuantity, beefMamiQuantity;
    Button addOrder, nextBtn, mainMenuBtn;

    LinearLayout addOn1, addOn2;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    boolean isHasMami = false;
    boolean isHasBeefMami = false;

    boolean isHasEgg = false;
    boolean isHasGarlicRice = false;


    private  static  int mamiPrice = 25;
    private  static int beefMamiPrice = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mami);

        mami = findViewById(R.id.mami);
        eggMami = findViewById(R.id.egg_mami);
        garlicMami = findViewById(R.id.garlic_rice_mami);

        beefMami = findViewById(R.id.beef_mami);
        eggBeefMami = findViewById(R.id.egg_beef_mami);
        garlicRiceMami = findViewById(R.id.garlic_rice_beef_mami);

        mamiQuantity = findViewById(R.id.quantityEtMami);
        beefMamiQuantity = findViewById(R.id.quantityEtMamibeef);

        addOrder = findViewById(R.id.addOrder_mami);
        nextBtn = findViewById(R.id.nextBtn_mami);
        mainMenuBtn = findViewById(R.id.backtomenu_mami);

        addOn1 = findViewById(R.id.mami_add_ons);
        addOn2 = findViewById(R.id.beef_mami_add_ons);

        rootNode = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        String userId = firebaseAuth.getCurrentUser().getUid();
        reference = rootNode.getReference(userId);


        addOrder.setEnabled(false);
        nextBtn.setEnabled(false);
        addOrder.setBackgroundResource(R.drawable.inactive_bg);
        nextBtn.setBackgroundResource(R.drawable.inactive_bg);


        mami.setOnClickListener(view -> {
            if (mami.isChecked()){
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
        beefMami.setOnClickListener(view -> {
            if (beefMami.isChecked()){
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

            if (mami.isChecked() && beefMami.isChecked()){
                MamiOrder.Order = "Beef Mami and Beef Mami";
                isHasMami = true;
                isHasBeefMami = true;

            }else if(mami.isChecked()){
                MamiOrder.Order = "Beef Mami";
                isHasMami = true;

            } else if (beefMami.isChecked()){
                MamiOrder.Order = "Beef Mami";
                isHasBeefMami = true;

            }

            if ((eggMami.isChecked()&& garlicMami.isChecked()) || (beefMami.isChecked()&& garlicRiceMami.isChecked())){
                MamiOrder.AddOns = "Egg and Garlic Rice";
                isHasEgg = true;
                isHasGarlicRice = true;
            }else if (eggMami.isChecked() || eggBeefMami.isChecked()){
                MamiOrder.AddOns = "Egg";
                isHasEgg = true;
            }else if (garlicMami.isChecked() || garlicRiceMami.isChecked()){
                MamiOrder.AddOns = "Garlic pares";
                isHasGarlicRice = true;
            }else {
                MamiOrder.AddOns = "";
            }
            String mamiQuant = mamiQuantity.getText().toString();
            String beefMamiQuant = beefMamiQuantity.getText().toString();

            if (isHasMami && isHasBeefMami){
                if (TextUtils.isEmpty(mamiQuant)){
                    mamiQuantity.setError("Please Specify");
                }
                if (TextUtils.isEmpty(beefMamiQuant)){
                    beefMamiQuantity.setError("Please Specify");
                }
                if (!TextUtils.isEmpty(mamiQuant) && !TextUtils.isEmpty(beefMamiQuant)){
                    if (isHasEgg && isHasGarlicRice){
                        mamiPrice = 45;
                        beefMamiPrice = 55;
                        String quantity = mamiQuant+" Beef Mami and "+beefMamiQuant+" Beef Mami w/ rice";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mBeefMamiQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int beefMamiPay = mamiPrice * mBeefMamiQuant;
                        int totalPay = mamiPay + beefMamiPay;

                        goToPreview(totalPay,quantity);

                    }else if (isHasEgg){
                        mamiPrice = 35;
                        beefMamiPrice = 45;
                        String quantity = mamiQuant+" Beef Mami and "+beefMamiQuant+" Beef Mami w/ rice";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mBeefMamiQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int beefMamiPay = mamiPrice * mBeefMamiQuant;
                        int totalPay = mamiPay + beefMamiPay;

                        goToPreview(totalPay,quantity);
                    }else if (isHasGarlicRice){
                        mamiPrice = 35;
                        beefMamiPrice = 45;
                        String quantity = mamiQuant+" Beef Mami and "+beefMamiQuant+" Beef Mami w/ rice";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mBeefMamiQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int beefMamiPay = mamiPrice * mBeefMamiQuant;
                        int totalPay = mamiPay + beefMamiPay;

                        goToPreview(totalPay,quantity);
                    }else{
                        mamiPrice = 25;
                        beefMamiPrice = 35;
                        String quantity = mamiQuant+" Beef Mami and "+beefMamiQuant+" Beef Mami w/ rice";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mBeefMamiQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int beefMamiPay = mamiPrice * mBeefMamiQuant;
                        int totalPay = mamiPay + beefMamiPay;

                        goToPreview(totalPay,quantity);
                    }


                }

            }else if (isHasMami){
                if (TextUtils.isEmpty(mamiQuant)){
                    mamiQuantity.setError("Please Specify");
                }else {
                    if (isHasEgg && isHasGarlicRice){
                        mamiPrice = 45;
                        String quantity = mamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);

                    }else if (isHasEgg){
                        mamiPrice = 35;
                        String quantity = mamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }else if (isHasGarlicRice){
                        mamiPrice = 35;
                        String quantity = mamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }else{
                        mamiPrice = 25;
                        String quantity = mamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(mamiQuant);
                        int mamiPay = mamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }
                }
            }else if (isHasBeefMami){
                if (TextUtils.isEmpty(beefMamiQuant)){
                    beefMamiQuantity.setError("Please Specify");
                }else {
                    if (isHasEgg && isHasGarlicRice){
                        beefMamiPrice = 35;
                        String quantity = beefMamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = beefMamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);

                    }else if (isHasEgg){
                        beefMamiPrice = 45;
                        String quantity = beefMamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = beefMamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }else if (isHasGarlicRice){
                        beefMamiPrice = 45;
                        String quantity = beefMamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = beefMamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }else{
                        beefMamiPrice = 35;
                        String quantity = beefMamiQuant+" Beef Mami";
                        int mQuant = Integer.parseInt(beefMamiQuant);
                        int mamiPay = beefMamiPrice * mQuant;
                        int totalPay = mamiPay;

                        goToPreview(totalPay,quantity);
                    }
                }
            }
        });

        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MamiActivity.this,PaymentSectionActivity.class);
            startActivity(intent);

        });

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MamiActivity.this,MainMenuActivity.class);
            startActivity(intent);
            finishAffinity();

        });


    }

    private void goToPreview(int totalPay, String quantity) {
        String payTotal = String.valueOf(totalPay);
        MamiOrder.MamiTotalPay = payTotal;
        MamiOrder.Quantity = quantity;
        nextBtn.setEnabled(true);
        nextBtn.setClickable(true);
        nextBtn.setBackgroundResource(R.drawable.yellow_bg);
        /*PreOrderModel preOrderModel = new PreOrderModel(MamiOrder.Order,MamiOrder.AddOns,MamiOrder.Quantity,MamiOrder.MamiTotalPay);
        reference.child("pre-order").push().setValue(preOrderModel);*/
        Toast.makeText(MamiActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

    }
}