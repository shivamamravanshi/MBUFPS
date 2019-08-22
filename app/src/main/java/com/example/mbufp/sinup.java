package com.example.mbufp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class sinup extends AppCompatActivity {

    Button submit;
    EditText name,address,email,contact_number,aadhaar,driving_licence,vehicle_number,password,re_enter_password;
    DatabaseReference databasesignup;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        databasesignup= FirebaseDatabase.getInstance().getReference("MBUFP3");

        name = (EditText)findViewById(R.id.name);
        address =(EditText) findViewById(R.id.address);
        email =(EditText) findViewById(R.id.email);
        contact_number =(EditText) findViewById(R.id.contact_number);
        aadhaar =(EditText) findViewById(R.id.aadhaar_number);
        driving_licence = (EditText)findViewById(R.id.driving_licence);
        vehicle_number = (EditText)findViewById(R.id.vehicle_number);
        password = (EditText)findViewById(R.id.password);
        re_enter_password = (EditText)findViewById(R.id.re_enter_password);

        submit = (Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(
                        name.getText().toString().isEmpty() ||
                                email.getText().toString().isEmpty() ||
                                driving_licence.getText().toString().isEmpty() ||
                                vehicle_number.getText().toString().isEmpty() ||
                                re_enter_password.getText().toString().isEmpty() ||
                                aadhaar.getText().toString().isEmpty() ||
                                contact_number.getText().toString().isEmpty() ||
                                password.getText().toString().isEmpty())
                {

                    Toast.makeText(sinup.this,"Please Enter All Field!",Toast.LENGTH_SHORT).show();
                }
                else {
                    addUser();


                }
            }
        });

    }
            private void addUser()
            {
                final String NAME = name.getText().toString();
                final String ADDRESS = address.getText().toString();
                final String EMAIL = email.getText().toString();
                final String CONTACT_NUMBER = contact_number.getText().toString();
                final String AADHAAR = aadhaar.getText().toString();
                final String DRIVING_LICENCE = driving_licence.getText().toString();
                final String VEHICLE_NUMBER = vehicle_number.getText().toString();
                String PASSWORD = password.getText().toString();
                String RE_ENTER_PASSWORD = re_enter_password.getText().toString();

               if(AADHAAR.length()==12) {
                   if (AADHAAR.matches("^[0-9]*$")) {
                       if (DRIVING_LICENCE.length() == 15) {
                           if (DRIVING_LICENCE.matches("^[A-Za-z0-9]*$")) {
                               if (VEHICLE_NUMBER.length() == 10) {
                                   if (VEHICLE_NUMBER.matches("^[A-Za-z0-9]*$")) {
                                       if (PASSWORD.equals(RE_ENTER_PASSWORD) == true) {
                                           if (!TextUtils.isEmpty(NAME)) {
                                               String id = databasesignup.push().getKey();
                                               SignUpData signUpData = new SignUpData(id, name, address, aadhaar, contact_number, email, driving_licence, vehicle_number, password);

                                               mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<AuthResult> task) {

                                                       if (task.isSuccessful()) {
                                                           Toast.makeText(sinup.this, "Account Succesfully created", Toast.LENGTH_SHORT).show();
                                                           Map<String, String> user_map = new HashMap<>();
                                                           user_map.put("name", NAME);
                                                           user_map.put("address", ADDRESS);
                                                           user_map.put("email", EMAIL);
                                                           user_map.put("contact_number", CONTACT_NUMBER);
                                                           user_map.put("aadhaar", AADHAAR);
                                                           user_map.put("driving_licence", DRIVING_LICENCE);
                                                           user_map.put("vehicle_number", VEHICLE_NUMBER);


                                                           firebaseFirestore.collection("User").document(mAuth.getCurrentUser().getUid()).set(user_map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                               @Override
                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                   if (task.isSuccessful()) {
                                                                       mAuth.signOut();
                                                                       Toast.makeText(sinup.this, "Registered Data", Toast.LENGTH_SHORT).show();
                                                                       Intent intent = new Intent(sinup.this, MainActivity.class);
                                                                       startActivity(intent);

                                                                   }
                                                               }
                                                           });


                                                       } else {
                                                           String errorMessage = task.getException().getMessage();
                                                           Toast.makeText(sinup.this, "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
                                                       }
                                                   }
                                               });
                                           } else {
                                               Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
                                           }
                                       } else {
                                           Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                                       }
                                   } else {
                                       Toast.makeText(this, "Enter Valid Vehicle Number", Toast.LENGTH_SHORT).show();
                                   }
                               } else {
                                   Toast.makeText(this, "Enter Valid Vehicle Number", Toast.LENGTH_SHORT).show();
                               }
                           } else {
                               Toast.makeText(this, "Enter Valid Driving Licence", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(this, "Enter Valid Driving Licence", Toast.LENGTH_SHORT).show();
                       }
                   }else {
                       Toast.makeText(this, "Enter Valid Aadhaar", Toast.LENGTH_SHORT).show();
                   }


               }else {
                   Toast.makeText(this, "Enter Valid Aadhaar", Toast.LENGTH_SHORT).show();
               }

            }


}
