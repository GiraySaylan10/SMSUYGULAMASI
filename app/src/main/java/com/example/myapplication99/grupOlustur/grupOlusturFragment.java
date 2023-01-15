package com.example.myapplication99.grupOlustur;

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


public class grupOlusturFragment extends Fragment {
       EditText isim,aciklama;
       Button grupOlustur;
       FirebaseAuth fauth;
        FirebaseFirestore ffstore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grup_olustur2, container, false);

        isim = view.findViewById(R.id.grupAdi);
        aciklama = view.findViewById(R.id.aciklama);
        grupOlustur= view.findViewById(R.id.grupOlusturButton);
        fauth = FirebaseAuth.getInstance();
        ffstore = FirebaseFirestore.getInstance();
            grupOlustur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nm,ack;
                    nm = isim.getText().toString();
                    ack = aciklama.getText().toString();

                    if(nm.isEmpty()){
                        Toast.makeText(getContext(),"Grup adı boş olamaz!",Toast.LENGTH_SHORT).show();

                    }
                   else if(ack.isEmpty()){
                        Toast.makeText(getContext(),"Grup açıklaması boş olamaz!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(),"Grup oluşturma başarılı!", Toast.LENGTH_SHORT).show();
                        grupOlustur(nm,ack);
                    }
                }
            });
        return view;
    }

    private void grupOlustur(String ad, String aciklama){
        String userId = fauth.getCurrentUser().getUid();

        ffstore.collection("/users/" + userId + "/groups").add(new HashMap<String,String>() {{
                    put("groupName", ad);
                    put("groupExplanation", aciklama);
                }})
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Grup oluşturuldu!", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Grup oluşturulamadı!", Toast.LENGTH_SHORT).show();
                });

    }
}