package com.app.balaipares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void ParesClicked(View view) {
        Intent intent = new Intent(MainMenuActivity.this,ParesActivity.class);
        startActivity(intent);
    }

    public void MamiClicked(View view) {
        Intent intent = new Intent(MainMenuActivity.this,MamiActivity.class);
        startActivity(intent);
    }

    public void DrinksClicked(View view) {
        Intent intent = new Intent(MainMenuActivity.this,DrinksActivity.class);
        startActivity(intent);
    }

    public void Exit(View view) {
        super.onBackPressed();
    }
}