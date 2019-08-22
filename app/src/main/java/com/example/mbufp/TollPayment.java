package com.example.mbufp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TollPayment extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> a;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListOfToll> listOfTolls;
    Button viewtollonmap;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    Button paytoll;
    String place;
    String amount;
    String Aadhar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll_payment);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        try {
            firebaseFirestore.collection("wallet").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                    amount = documentSnapshot.getString("balance");
                    Aadhar= documentSnapshot.getString("aadhaar");
                    //Toast.makeText(TollPayment.this, "balance & aadhaar" + amount+Aadhar, Toast.LENGTH_SHORT).show();

                }
            });
        }catch (NullPointerException e)
        {
            // Toast.makeText(this, "Exception Occur", Toast.LENGTH_SHORT).show();
        }

        paytoll=(Button)findViewById(R.id.paytoll);
        paytoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWallet();
                //Toast.makeText(TollPayment.this, "jk"+place, Toast.LENGTH_SHORT).show();




            }
        });
        viewtollonmap = findViewById(R.id.view_toll_on_map);
        viewtollonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TollPayment.this,Toll_tax.class);
                startActivity(intent);
            }
        });

        spinner = findViewById(R.id.spinner);
        a = ArrayAdapter.createFromResource(this,R.array.destinations ,android.R.layout.simple_spinner_item);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place = (String)parent.getItemAtPosition(position);
                if(place.equals("Jabalpur"))
                {
                    showJabalpur();
                }
                if(place.equals("Delhi"))
                {
                    showDelhi();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(TollPayment.this, "Select your Destination", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void showJabalpur()
    {
        recyclerView = findViewById(R.id.recycler_view_toll);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfTolls = new ArrayList<>();
        ListOfToll listOfToll = new ListOfToll("Damoh-Jabalpur(B)","30Rs");
        listOfTolls.add(listOfToll);
        ListOfToll listOfToll1 = new ListOfToll("Bhopal-Vidisha Toll","20Rs");
        listOfTolls.add(listOfToll1);
        adapter = new AdapterForTollList(listOfTolls,this);
        recyclerView.setAdapter(adapter);
    }
    public void showDelhi()
    {
        recyclerView = findViewById(R.id.recycler_view_toll);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfTolls = new ArrayList<>();
        ListOfToll listOfToll = new ListOfToll("Pagara Toll Booth","95Rs");
        listOfTolls.add(listOfToll);
        ListOfToll listOfToll1 = new ListOfToll("Gunna Bypass Toll Booth","15Rs");
        listOfTolls.add(listOfToll1);
        ListOfToll listOfToll2 = new ListOfToll("Umari-Phoop-Pratapgrah Toll Booth","25Rs");
        listOfTolls.add(listOfToll2);
        ListOfToll listOfToll3 = new ListOfToll("Yamuna ExpressWay","105Rs");
        listOfTolls.add(listOfToll3);
        ListOfToll listOfToll4 = new ListOfToll("Ramnagar Toll Booth","75Rs");
        listOfTolls.add(listOfToll4);
        ListOfToll listOfToll5 = new ListOfToll("Choundha Toll Booth","65Rs");
        listOfTolls.add(listOfToll5);
        ListOfToll listOfToll6 = new ListOfToll("Jajau (Old Baretha) Toll Booth","70Rs");
        listOfTolls.add(listOfToll6);
        adapter = new AdapterForTollList(listOfTolls,this);
        recyclerView.setAdapter(adapter);
    }
    public void getWallet()
    {
        String BALANCE=amount;
        final String AADHAAR=Aadhar;
        if(place.equals("Delhi"))
        {
            int amount1=450;
            int a=Integer.parseInt(BALANCE);
            amount1=a-amount1;
            BALANCE=(Integer.toString(amount1));

        }else
        {
            int amount1=50;
            final int a=Integer.parseInt(BALANCE);
            amount1=a-amount1;
            BALANCE=(Integer.toString(amount1));
        }
        Map<String,String> wallet_map =new HashMap<>();
        wallet_map.put("aadhaar",AADHAAR);
        wallet_map.put("balance",BALANCE);

        firebaseFirestore.collection("wallet").document(mAuth.getCurrentUser().getUid()).set(wallet_map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if (task.isSuccessful()) {
                    mAuth.signOut();
                    Toast.makeText(TollPayment.this, "Paid successfully", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(.this, home.class);
                    // startActivity(intent);

                }
                else {

                    Toast.makeText(TollPayment.this, "sorry can't be paid at this moment", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
