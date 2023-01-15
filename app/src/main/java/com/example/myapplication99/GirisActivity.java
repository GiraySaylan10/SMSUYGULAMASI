package com.example.myapplication99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity {
    EditText email,sifre;
    Button login2,register2;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
       login2 = findViewById(R.id.button_giris);
       register2 = findViewById(R.id.button_kayit);
       email = findViewById(R.id.girisEmail);
       sifre= findViewById(R.id.giris_Sifre);

        fauth = FirebaseAuth.getInstance();
       login2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String em,sif;
               em = email.getText().toString();
               sif= sifre.getText().toString();
               if(sif.isEmpty()||em.isEmpty()){

                   Toast.makeText(GirisActivity.this,"Lütfen alanları doldurunuz",Toast.LENGTH_SHORT).show();

               }

               else{

                   fauth.signInWithEmailAndPassword(em,sif).addOnCompleteListener(task -> {
                       if(task.isSuccessful()){
                           Toast.makeText(GirisActivity.this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(GirisActivity.this,MainActivity.class);
                           startActivity(i);
                       }
                       else{
                           Toast.makeText(GirisActivity.this, "Giriş Başarısız!", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }
       });

    }





}