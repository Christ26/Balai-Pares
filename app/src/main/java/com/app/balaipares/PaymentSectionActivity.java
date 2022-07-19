package com.app.balaipares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PaymentSectionActivity extends AppCompatActivity {
    CheckBox cod;
    TextView order_food,order_drinks,fQuantity,dQuantity,total,addons;
    Button proceedBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_section);


        cod = findViewById(R.id.cashondelivery);
        order_food = findViewById(R.id.orders);
        order_drinks = findViewById(R.id.drinks);
        fQuantity = findViewById(R.id.quantity_orders);
        dQuantity = findViewById(R.id.quantity_drinks);
        proceedBtn = findViewById(R.id.proceedBtn);
        addons = findViewById(R.id.addons_summary);
        total = findViewById(R.id.total);

        if (!ParesOrder.Order.equals("") && !MamiOrder.Order.equals("")){
            String order = ParesOrder.Order+" and "+MamiOrder.Order;
            order_food.setText(order);
            Orders.Orders = order;
        }else if (!ParesOrder.Order.equals("")){
            order_food.setText(ParesOrder.Order);
            Orders.Orders = ParesOrder.Order;
        }else if (!MamiOrder.Order.equals("")){
            order_food.setText(MamiOrder.Order);
            Orders.Orders = MamiOrder.Order;
        }else {
            order_food.setText("No Orders");
            Orders.Orders = "No Foods";
        }

        if (!ParesOrder.AddOns.equals("") && !MamiOrder.AddOns.equals("")){
            String addOns = ParesOrder.AddOns+" \n "+MamiOrder.AddOns;
            addons.setText(addOns);
            Orders.AddOns = addOns;
        }else if (!ParesOrder.AddOns.equals("")){
            addons.setText(ParesOrder.AddOns);
            Orders.AddOns = ParesOrder.AddOns;
        }else if (!MamiOrder.AddOns.equals("")){
            addons.setText(MamiOrder.AddOns);
            Orders.AddOns = MamiOrder.AddOns;
        }else {
            addons.setText("No Add Ons");
            Orders.AddOns = "No Add Ons";
        }


        if (!ParesOrder.Quantity.equals("") && !MamiOrder.Quantity.equals("")){
            String quant = ParesOrder.Quantity+"\n"+MamiOrder.Quantity;
            fQuantity.setText(quant);
            Orders.fQuantity = quant;
        }else if (!ParesOrder.Quantity.equals("")){
            fQuantity.setText(ParesOrder.Quantity);
            Orders.fQuantity = ParesOrder.Quantity;
        }else if (!MamiOrder.Quantity.equals("")){
            fQuantity.setText(MamiOrder.Quantity);
            Orders.fQuantity = MamiOrder.Quantity;
        }else {
            fQuantity.setText("No Orders");
            Orders.fQuantity = "No orders";
        }

        if (!DrinksOrder.Drinks.equals("")){
            order_drinks.setText(DrinksOrder.Drinks);
            Orders.Drinks = DrinksOrder.Drinks;
        }else {
            order_drinks.setText("No Drinks");
            Orders.Drinks = "No Orders";
        }

        if (!DrinksOrder.Quantity.equals("")){
            dQuantity.setText(DrinksOrder.Quantity);
            Orders.dQuantity = DrinksOrder.Quantity;
        }else {
            dQuantity.setText("No Orders");
            Orders.dQuantity ="No Orders";
        }

        if(!ParesOrder.ParesTotalPay.equals("") && !MamiOrder.MamiTotalPay.equals("") && !DrinksOrder.payTotal.equals("")){
            int paresPay = Integer.parseInt(ParesOrder.ParesTotalPay);
            int mamiPay = Integer.parseInt(MamiOrder.MamiTotalPay);
            int drinksPay = Integer.parseInt(DrinksOrder.payTotal);

            int totalPayment = paresPay+mamiPay+drinksPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);


        }else if (!ParesOrder.ParesTotalPay.equals("") && !MamiOrder.MamiTotalPay.equals("")){
            int paresPay = Integer.parseInt(ParesOrder.ParesTotalPay);
            int mamiPay = Integer.parseInt(MamiOrder.MamiTotalPay);

            int totalPayment = paresPay+mamiPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);
        }else if (!ParesOrder.ParesTotalPay.equals("") && !DrinksOrder.payTotal.equals("")){
            int paresPay = Integer.parseInt(ParesOrder.ParesTotalPay);
            int drinksPay = Integer.parseInt(DrinksOrder.payTotal);
            int totalPayment = paresPay+drinksPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);
        }else if (!DrinksOrder.payTotal.equals("")){

            int drinksPay = Integer.parseInt(DrinksOrder.payTotal);
            int totalPayment = drinksPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);
        }else if (!ParesOrder.ParesTotalPay.equals("")){

            int paresPay = Integer.parseInt(ParesOrder.ParesTotalPay);
            int totalPayment = paresPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);
        }else if (!MamiOrder.MamiTotalPay.equals("")){

            int mamiPay = Integer.parseInt(MamiOrder.MamiTotalPay);
            int totalPayment = mamiPay;

            total.setText(String.valueOf(totalPayment));
            Orders.totalPayement = String.valueOf(totalPayment);
        }

        cod.setOnClickListener(view -> {
            if (cod.isChecked()){
                proceedBtn.setEnabled(true);
                proceedBtn.setBackgroundResource(R.drawable.yellow_bg);
            }else {
                proceedBtn.setEnabled(false);
                proceedBtn.setBackgroundResource(R.drawable.inactive_bg);
            }

        });

        proceedBtn.setOnClickListener(view -> {
            Intent intent = new Intent(PaymentSectionActivity.this, DeliverAddressActivity.class);

            startActivity(intent);
        });




    }
}