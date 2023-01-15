package com.example.myapplication99;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class KayitActivity extends AppCompatActivity {
    EditText email,sifre;
    Button login3,register3;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        login3 = findViewById(R.id.buton_giris);
        register3 = findViewById(R.id.buton_kayit);
        email = findViewById(R.id.kayitEmail);
        sifre= findViewById(R.id.kayitSifre);
        fauth = FirebaseAuth.getInstance();
        register3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em,sif;
                em = email.getText().toString();
                sif= sifre.getText().toString();
                if(sif.isEmpty()||em.isEmpty()){

                    Toast.makeText(KayitActivity.this,"Lütfen alanları boş bırakmayınız!",Toast.LENGTH_SHORT).show();

                }

                fauth.createUserWithEmailAndPassword(em,sif).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){

                        startActivity(new Intent(KayitActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(KayitActivity.this, "Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}