package com.example.mbufp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Button toll_tax,puc,wallet;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    String USEREMAIL;
    String USERNAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toll_tax = findViewById(R.id.toll_tax);
        puc = findViewById(R.id.PUC);
        wallet = findViewById(R.id.wallet);
            mAuth = FirebaseAuth.getInstance();
            firebaseFirestore = FirebaseFirestore.getInstance();

            firebaseFirestore.collection("User").document(mAuth.getCurrentUser().getUid()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                String USERNAME = documentSnapshot.getString("name");
                                String USEREMAIL = documentSnapshot.getString("email");
                                NavigationView navigationView = findViewById(R.id.nav_view);
                                View headerView = navigationView.getHeaderView(0);
                                TextView useremailView = headerView.findViewById(R.id.header_mail);
                                TextView usernameView = headerView.findViewById(R.id.header_name);
                                useremailView.setText(USEREMAIL);
                                usernameView.setText(USERNAME);

                            }else {
                                Toast.makeText(home.this, "Something Went Wrong!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            /*firebaseFirestore.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                    USEREMAIL = documentSnapshot.getString("email");
                    USERNAME = documentSnapshot.getString("name");
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    View headerView = navigationView.getHeaderView(0);
                    TextView useremailView = headerView.findViewById(R.id.header_mail);
                    TextView usernameView = headerView.findViewById(R.id.header_name);
                    useremailView.setText(USEREMAIL);
                    usernameView.setText(USERNAME);
                }
            });*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,com.example.mbufp.Wallet.class);
                startActivity(intent);
            }
        });

        puc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,com.example.mbufp.Puc_list.class);
                startActivity(intent);
            }
        });

        toll_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this,com.example.mbufp.TollPayment.class);
                startActivity(intent);

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.logout:
                Intent intent = new Intent(home.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.your_profile:

                break;
            case R.id.help:
                Toast.makeText(this, "Send Us Email and We will Reach to You: Email:- amravanshishivam@gmail.com", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
        }

}
