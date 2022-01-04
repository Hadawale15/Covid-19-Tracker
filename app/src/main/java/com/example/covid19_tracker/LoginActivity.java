package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username_id);
        password=findViewById(R.id.password_id);
        firebaseAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.loginbutton_id);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String UserName=username.getText().toString();
                String Password=password.getText().toString();

                if (UserName.isEmpty())
                {
                    username.setError("Please enter valid Email Id");
                    username.requestFocus();
                    return;
                }
                if (Password.isEmpty())
                {
                    password.setError("Please enter valid password");
                    password.requestFocus();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(UserName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });



    }

    public void SignUpMethode(View view) {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        // means user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
          Intent intent=new Intent(LoginActivity.this,MainActivity.class);
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);
        }
    }*/
}