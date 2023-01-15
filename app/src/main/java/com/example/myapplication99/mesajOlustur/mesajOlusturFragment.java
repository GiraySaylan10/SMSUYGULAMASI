package com.example.myapplication99.mesajOlustur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication99.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class mesajOlusturFragment extends Fragment {
       EditText message,messageName;
       Button mesajButton;
       FirebaseAuth fauth;
       FirebaseFirestore ffstore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mesaj_olustur, container, false);
        message= view.findViewById(R.id.mesaj);
        messageName= view.findViewById(R.id.mesajAdi);
        mesajButton = view.findViewById(R.id.mesajOlusturButton);
        fauth = FirebaseAuth.getInstance();
        ffstore = FirebaseFirestore.getInstance();


        mesajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg,msgAd;
                msg = message.getText().toString();
                msgAd= messageName.getText().toString();

                if (msg.isEmpty() || msgAd.isEmpty()){
                    Toast.makeText(getContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();
                }

                else{
                    mesajOlustur(msgAd,msg);
                }
            }
        });

        return view;
    }

  private void   mesajOlustur(String messageName, String message){
      String userId = fauth.getCurrentUser().getUid();

      ffstore.collection("/users/" + userId + "/messages").add(new HashMap<String,String>() {{
                  put("messageName", messageName);
                  put("message", message);
              }})
              .addOnSuccessListener(documentReference -> {
                  Toast.makeText(getContext(), "Mesaj  oluşturuldu!", Toast.LENGTH_SHORT).show();

              })
              .addOnFailureListener(e -> {
                  Toast.makeText(getContext(), "Mesaj oluşturulamadı!", Toast.LENGTH_SHORT).show();
              });
  }
}