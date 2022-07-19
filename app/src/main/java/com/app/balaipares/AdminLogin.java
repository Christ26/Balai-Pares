package com.app.balaipares;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText username, password;
    ProgressBar pBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void Adminlogin(View view) {
        pBar = findViewById(R.id.progress_bar);
        pBar.setVisibility(View.VISIBLE);
        username = findViewById(R.id.admin_userNameEt);
        password = findViewById(R.id.admin_passwordEt);
        fAuth = FirebaseAuth.getInstance();

        String userLogin = username.getText().toString();
        String passLogin = password.getText().toString();

        if (TextUtils.isEmpty(userLogin)) {
            username.setError("username is Required");
            pBar.setVisibility(View.GONE);
            return;
        } else if (TextUtils.isEmpty(passLogin)) {
            password.setError("Password is Required");
            pBar.setVisibility(View.GONE);
            return;
        } else if(!TextUtils.isEmpty(userLogin) && !TextUtils.isEmpty(passLogin)) {
            String emailLogin = userLogin+"@gmail.com";
            fAuth.signInWithEmailAndPassword(emailLogin,passLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText( AdminLogin.this, "Admin Panel", Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText( AdminLogin.this, "Logged In Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);

                    }

                }
            });

        }
    }

    public void Back(View view) {
        super.onBackPressed();
    }
}