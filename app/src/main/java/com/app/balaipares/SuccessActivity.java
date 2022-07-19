package com.app.balaipares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SuccessActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }

    public void BackToMenu(View view) {
        Intent intent = new Intent(SuccessActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void Logout(View view) {

       FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SuccessActivity.this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}