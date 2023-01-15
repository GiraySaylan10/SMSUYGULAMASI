package com.example.myapplication99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class IlkActivity extends AppCompatActivity {

Button register,login;
FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk);
        Button register = findViewById(R.id.btn_kayit);
        Button login = findViewById(R.id.btn_giris);

   register.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(IlkActivity.this, KayitActivity.class);
           startActivity(i);
       }
   });

   login.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent i = new Intent(IlkActivity.this, GirisActivity.class);
           startActivity(i);
       }
   });
    }




}