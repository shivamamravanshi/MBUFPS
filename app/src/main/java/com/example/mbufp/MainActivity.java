package com.example.mbufp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //FirebaseDatabase.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startlogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sinup.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void startlogin()
{
    final String email=username.getText().toString();
    String PASSWORD=password.getText().toString();
    if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(PASSWORD)) {
        Toast.makeText(MainActivity.this, "Fields Are Empty!!", Toast.LENGTH_LONG).show();
    }else
    {
        firebaseAuth.signInWithEmailAndPassword(email, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    String error = task.getException().getMessage();
                    Toast.makeText(MainActivity.this, "ERROR :" + error, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, home.class);
                    //intent.putExtra("email",username.getText().toString().trim());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }





}
}
