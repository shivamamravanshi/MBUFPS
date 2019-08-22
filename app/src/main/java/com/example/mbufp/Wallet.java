package com.example.mbufp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class Wallet extends AppCompatActivity {
    Button addwallet;
    EditText aadhaar,balance;
    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;
    String adnumber1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        try {
            firebaseFirestore.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                    adnumber1 = documentSnapshot.getString("aadhaar");
                    // Toast.makeText(Wallet.this, "aadhaar is" + adnumber1, Toast.LENGTH_SHORT).show();

                }
            });
        }catch (NullPointerException e)
        {
            //Toast.makeText(this, "Exception Occur", Toast.LENGTH_SHORT).show();
        }
        aadhaar = (EditText) findViewById(R.id.aadhaar);
        balance = (EditText) findViewById(R.id.balance);
        addwallet = (Button) findViewById(R.id.addwallet);
        addwallet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addWallet();
            }
        });
    }
    private  void addWallet()
    {
        final String AADHHAR=adnumber1;
        final String BALANCE=balance.getText().toString().trim();
        Map<String,String> wallet_map =new HashMap<>();
        wallet_map.put("aadhaar",AADHHAR);
        wallet_map.put("balance",BALANCE);
        final String ad=aadhaar.getText().toString();
        firebaseFirestore.collection("wallet").document(mAuth.getCurrentUser().getUid()).set(wallet_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (AADHHAR.equals(ad)) {


                    if (task.isSuccessful()) {
                        mAuth.signOut();
                        Toast.makeText(Wallet.this, "Your Wallet added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Wallet.this, home.class);
                        startActivity(intent);

                    }
                } else {

                    Toast.makeText(Wallet.this, "Enter Valid Aadhar Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
