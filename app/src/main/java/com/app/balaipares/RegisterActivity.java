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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ProgressBar pBar;

    EditText fullName,pNumber,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void loginhere(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void Register(View view) {
        pBar = findViewById(R.id.progress_bar);
        pBar.setVisibility(View.VISIBLE);
        fullName = findViewById(R.id.nameEt);
        username = findViewById(R.id.userNameEt);
        password = findViewById(R.id.passwordEt);
        pNumber = findViewById(R.id.pNumberEt);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("user");

        fAuth = FirebaseAuth.getInstance();

        String name = fullName.getText().toString();
        String uName = username.getText().toString();
        String phone = pNumber.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(name)){
            fullName.setError("Name is Required");
            pBar.setVisibility(View.GONE);
            return;
        }else if (TextUtils.isEmpty(uName)){
            username.setError("Username is Required");
            pBar.setVisibility(View.GONE);
            return;
        }else if (TextUtils.isEmpty(phone)){
            pNumber.setError("Phone is Required");
            pBar.setVisibility(View.GONE);
        } else if (pass.length() < 6){
            password.setError("Too short Password!");
            pBar.setVisibility(View.GONE);
            return;
        }else if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(uName) && !TextUtils.isEmpty(pass) &&!TextUtils.isEmpty(phone)){
            String email = uName+"@balaipares.com";
           

            fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   

                    if (task.isSuccessful()){
                        Toast.makeText( RegisterActivity.this,"Registration is Successful",Toast.LENGTH_SHORT).show();
                        String userId = fAuth.getCurrentUser().getUid();
                       //insert data in database
                        UserModel userModel = new UserModel(name,phone,userId);
                        reference.child(userId).setValue(userModel);


                        pBar.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText( RegisterActivity.this,"some error Encountered"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        pBar.setVisibility(View.GONE);
                        finish();

                    }

                }
            });

        }

    }
}