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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DrinksActivity extends AppCompatActivity {
    CheckBox iceT,pJuice,sDrinks,lJuice;
    EditText iceTET,pJuiceEt,sDrinksEt,lJuiceEt;

    Button addOrder, nextBtn,mainMenuBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    private  static  int iTPrice = 10;
    private  static int pJPrice = 10;
    private  static  int sDPrice = 15;
    private  static int lJPrice = 10;

    LinearLayout iTquant,pJquant,sDquant,lJquant;
    boolean hasTea = false, hasApple = false, hasSoftdrinks = false, haslemonJuice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        iceT = findViewById(R.id.ice_tea);
        pJuice = findViewById(R.id.pineapple);
        sDrinks = findViewById(R.id.softdrinks);
        lJuice = findViewById(R.id.lemon_juice);

        addOrder = findViewById(R.id.addOrder_drinks);
        nextBtn = findViewById(R.id.nextBtn_drinks);
        mainMenuBtn = findViewById(R.id.backtomenu_drinks);

        iTquant = findViewById(R.id.iceTeaquant);
        pJquant = findViewById(R.id.pApplequant);
        sDquant = findViewById(R.id.sDrinkquant);
        lJquant = findViewById(R.id.lJuicequant);

        iceTET = findViewById(R.id.quantityEtIceTea);
        pJuiceEt = findViewById(R.id.quantityEtpApple);
        sDrinksEt = findViewById(R.id.quantityEtsDrinks);
        lJuiceEt = findViewById(R.id.quantityEtslemon);

        firebaseAuth = FirebaseAuth.getInstance();

        rootNode = FirebaseDatabase.getInstance();
        String userId = firebaseAuth.getCurrentUser().getUid();
        reference = rootNode.getReference(userId);




        addOrder.setEnabled(false);
        addOrder.setBackgroundResource(R.drawable.inactive_bg);
        nextBtn.setEnabled(false);
        nextBtn.setBackgroundResource(R.drawable.inactive_bg);

        iceT.setOnClickListener(view -> {
            if (iceT.isChecked() ){
                addOrder.setClickable(true);
                addOrder.setEnabled(true);
                addOrder.setBackgroundResource(R.drawable.green_bg);
                iTquant.setVisibility(View.VISIBLE);
                hasTea = true;
            }else{
                addOrder.setClickable(false);
                addOrder.setEnabled(false);
                addOrder.setBackgroundResource(R.drawable.inactive_bg);
                iTquant.setVisibility(View.GONE);
            }

        });

        pJuice.setOnClickListener(view -> {
            if (pJuice.isChecked() ){
                addOrder.setClickable(true);
                addOrder.setEnabled(true);
                addOrder.setBackgroundResource(R.drawable.green_bg);
                pJquant.setVisibility(View.VISIBLE);
                hasApple = true;
            }else{
                addOrder.setClickable(false);
                addOrder.setEnabled(false);
                addOrder.setBackgroundResource(R.drawable.inactive_bg);
                pJquant.setVisibility(View.GONE);
            }
        });

        sDrinks.setOnClickListener(view -> {
            if (sDrinks.isChecked() ){
                addOrder.setClickable(true);
                addOrder.setEnabled(true);
                addOrder.setBackgroundResource(R.drawable.green_bg);
                sDquant.setVisibility(View.VISIBLE);
                hasSoftdrinks = true;
            }else {
                addOrder.setClickable(false);
                addOrder.setEnabled(false);
                addOrder.setBackgroundResource(R.drawable.inactive_bg);
                sDquant.setVisibility(View.GONE);
            }
        });

        lJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lJuice.isChecked() ){
                    addOrder.setClickable(true);
                    addOrder.setEnabled(true);
                    addOrder.setBackgroundResource(R.drawable.green_bg);
                    lJquant.setVisibility(View.VISIBLE);
                    haslemonJuice = true;
                }else{
                    addOrder.setClickable(false);
                    addOrder.setEnabled(false);
                    addOrder.setBackgroundResource(R.drawable.inactive_bg);
                    lJquant.setVisibility(View.GONE);
                }
            }
        });


        addOrder.setOnClickListener(view -> {
            String iT = iceTET.getText().toString();
            String pJ = pJuiceEt.getText().toString();
            String sD = sDrinksEt.getText().toString();
            String lJ = lJuiceEt.getText().toString();

            if (iceT.isChecked()){
                DrinksOrder.Drinks1 = "Iced Tea";
                hasTea = true;
                if (TextUtils.isEmpty(iT)){
                    iceTET.setError("Required");
                    return;
                }
            }
            if (pJuice.isChecked()){
                DrinksOrder.Drinks2 = "Pineapple Juice";
                hasApple = true;
                if (TextUtils.isEmpty(pJ)){
                    pJuiceEt.setError("Required");
                    return;
                }
            }
            if (sDrinks.isChecked()){
                DrinksOrder.Drinks3 = "Softdrinks";
                hasSoftdrinks = true;
                if (TextUtils.isEmpty(sD)){
                    sDrinksEt.setError("Required");
                    return;
                }
            }
            if (lJuice.isChecked()){
                DrinksOrder.Drinks4 = "Lemon Juice";
                haslemonJuice = true;
                if (TextUtils.isEmpty(lJ)){
                    lJuiceEt.setError("Required");
                    return;
                }
            }

            if (hasTea && haslemonJuice && hasSoftdrinks && hasApple){
                DrinksOrder.Drinks = "Ice Tea, Lemon Juice, Softdrinks, Pineapple Juice";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(lJ) && !TextUtils.isEmpty(sD)){
                    String quantity = iT+" Iced Tea,"+pJ+" Pineapple Juice,"+lJ+" Lemon Juice,"+sD+" Softdrinks";
                    int iTQuant = Integer.parseInt(iT);
                    int pJQuant = Integer.parseInt(pJ);
                    int lJQuant = Integer.parseInt(lJ);
                    int sDQuant = Integer.parseInt(sD);

                    int iTPay = iTPrice * iTQuant;
                    int pJPay = pJPrice * pJQuant;
                    int lJPay = iTPrice * lJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = iTPay + pJPay + lJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasTea && haslemonJuice && hasSoftdrinks){
                DrinksOrder.Drinks = "Ice Tea, Lemon Juice, Softdrinks";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(lJ) && !TextUtils.isEmpty(sD)){
                    String quantity = iT+" Iced Tea,"+lJ+" Lemon Juice,"+sD+" Softdrinks";
                    int iTQuant = Integer.parseInt(iT);
                    int lJQuant = Integer.parseInt(lJ);
                    int sDQuant = Integer.parseInt(sD);

                    int iTPay = iTPrice * iTQuant;
                    int lJPay = iTPrice * lJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = iTPay + lJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }

            }else if (hasTea && haslemonJuice && hasApple){
                DrinksOrder.Drinks = "Ice Tea, Lemon Juice, Pineapple Juice";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(lJ)){
                    String quantity = iT+" Iced Tea,"+pJ+" Pineapple Juice,"+lJ+" Lemon Juice,";
                    int iTQuant = Integer.parseInt(iT);
                    int pJQuant = Integer.parseInt(pJ);
                    int lJQuant = Integer.parseInt(lJ);


                    int iTPay = iTPrice * iTQuant;
                    int pJPay = pJPrice * pJQuant;
                    int lJPay = iTPrice * lJQuant;

                    int totalPay = iTPay + pJPay + lJPay;

                    goToPreview(totalPay,quantity);
                }

            }else if (hasTea && hasSoftdrinks && hasApple){
                DrinksOrder.Drinks = "Ice Tea, Softdrinks, Pineapple Juice";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(sD)){
                    String quantity = iT+" Iced Tea,"+pJ+" Pineapple Juice,"+sD+" Softdrinks";
                    int iTQuant = Integer.parseInt(iT);
                    int pJQuant = Integer.parseInt(pJ);
                    int sDQuant = Integer.parseInt(sD);

                    int iTPay = iTPrice * iTQuant;
                    int pJPay = pJPrice * pJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = iTPay + pJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasTea && haslemonJuice){
                DrinksOrder.Drinks = "Iced Tea, Pineapple Juice";
                if (!TextUtils.isEmpty(iT)  && !TextUtils.isEmpty(lJ) ){
                    String quantity = iT+" Iced Tea,"+pJ+" Pineapple Juice,";
                    int iTQuant = Integer.parseInt(iT);
                    int lJQuant = Integer.parseInt(lJ);


                    int iTPay = iTPrice * iTQuant;
                    int lJPay = iTPrice * lJQuant;

                    int totalPay = iTPay + lJPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasTea && hasSoftdrinks){
                DrinksOrder.Drinks = "Iced Tea, Softdrinks";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(sD)){
                    String quantity = iT+" Iced Tea,"+sD+" Softdrinks";
                    int iTQuant = Integer.parseInt(iT);
                    int sDQuant = Integer.parseInt(sD);

                    int iTPay = iTPrice * iTQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = iTPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasTea && hasApple){
                DrinksOrder.Drinks = "Iced Tea, Pineapple Juice";
                if (!TextUtils.isEmpty(iT) && !TextUtils.isEmpty(pJ)){
                    String quantity = iT+" Iced Tea,"+pJ+" Pineapple Juice,";
                    int iTQuant = Integer.parseInt(iT);
                    int pJQuant = Integer.parseInt(pJ);

                    int iTPay = iTPrice * iTQuant;
                    int pJPay = pJPrice * pJQuant;

                    int totalPay = iTPay + pJPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (haslemonJuice && hasSoftdrinks && hasApple){
                DrinksOrder.Drinks = "Lemon Juice, Softdrinks, Pineapple Juice";
                if (!TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(lJ) && !TextUtils.isEmpty(sD)){
                    String quantity = pJ+" Pineapple Juice,"+lJ+" Lemon Juice,"+sD+" Softdrinks";
                    int pJQuant = Integer.parseInt(pJ);
                    int lJQuant = Integer.parseInt(lJ);
                    int sDQuant = Integer.parseInt(sD);


                    int pJPay = pJPrice * pJQuant;
                    int lJPay = iTPrice * lJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay =  pJPay + lJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (haslemonJuice && hasSoftdrinks){
                DrinksOrder.Drinks = "Lemon Juice, Softdrinks";
                if (!TextUtils.isEmpty(lJ) && !TextUtils.isEmpty(sD)){
                    String quantity = lJ+" Lemon Juice,"+sD+" Softdrinks";

                    int lJQuant = Integer.parseInt(lJ);
                    int sDQuant = Integer.parseInt(sD);

                    int lJPay = iTPrice * lJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = lJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (haslemonJuice && hasApple){
                DrinksOrder.Drinks = "Lemon Juice, Pineapple Juice";
                if (!TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(lJ) ){
                    String quantity = pJ+" Pineapple Juice,"+lJ+" Lemon Juice,";

                    int pJQuant = Integer.parseInt(pJ);
                    int lJQuant = Integer.parseInt(lJ);



                    int pJPay = pJPrice * pJQuant;
                    int lJPay = iTPrice * lJQuant;


                    int totalPay = pJPay + lJPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasSoftdrinks && hasApple){
                DrinksOrder.Drinks = "Softdrinks, Pineapple Juice";
                if ( !TextUtils.isEmpty(pJ) && !TextUtils.isEmpty(sD)){
                    String quantity = pJ+" Pineapple Juice,"+sD+" Softdrinks";
                    int pJQuant = Integer.parseInt(pJ);
                    int sDQuant = Integer.parseInt(sD);


                    int pJPay = pJPrice * pJQuant;
                    int sDPay = sDPrice * sDQuant;

                    int totalPay = pJPay + sDPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasApple){
                DrinksOrder.Drinks = "Pineapple Juice";
                if (!TextUtils.isEmpty(pJ)){
                    String quantity = pJ+" Pineapple Juice";

                    int pJQuant = Integer.parseInt(pJ);


                    int pJPay = pJPrice * pJQuant;


                    int totalPay = pJPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasTea){
                DrinksOrder.Drinks = "Iced Tea";
                if (!TextUtils.isEmpty(iT)){
                    String quantity = iT+" Iced Tea";
                    int iTQuant = Integer.parseInt(iT);


                    int iTPay = iTPrice * iTQuant;


                    int totalPay = iTPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (haslemonJuice){
                DrinksOrder.Drinks = "Lemon Juice";
                if (!TextUtils.isEmpty(lJ)){
                    String quantity = lJ+" Lemon Juice";

                    int lJQuant = Integer.parseInt(lJ);



                    int lJPay = iTPrice * lJQuant;


                    int totalPay = lJPay;

                    goToPreview(totalPay,quantity);
                }
            }else if (hasSoftdrinks){
                DrinksOrder.Drinks = "Softdrinks";
                if (!TextUtils.isEmpty(sD)){
                    String quantity = sD+" Softdrinks";

                    int sDQuant = Integer.parseInt(sD);

                    int sDPay = sDPrice * sDQuant;

                    int totalPay = sDPay;

                    goToPreview(totalPay,quantity);
                }
            }








        });

        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DrinksActivity.this,PaymentSectionActivity.class);
            startActivity(intent);

        });

        mainMenuBtn.setOnClickListener(view -> {
            Intent intent = new Intent(DrinksActivity.this,MainMenuActivity.class);
            startActivity(intent);
            finishAffinity();

        });
    }

    private void goToPreview(int totalPay, String quantity) {
        String payTotal = String.valueOf(totalPay);
        DrinksOrder.payTotal = payTotal;
        DrinksOrder.Quantity = quantity;
        nextBtn.setEnabled(true);
        nextBtn.setClickable(true);
        nextBtn.setBackgroundResource(R.drawable.yellow_bg);
      /*  PreOrderModel preOrderModel = new PreOrderModel(DrinksOrder.Drinks,null,DrinksOrder.Quantity,DrinksOrder.payTotal);
        reference.child("pre-order").push().setValue(preOrderModel);*/
        Toast.makeText(DrinksActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

    }
}