package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,pass1,pass2;
    Button signUp;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name_id);
        email=findViewById(R.id.email_id);
        pass1=findViewById(R.id.password1_id);
        pass2=findViewById(R.id.password2_id);
        signUp=findViewById(R.id.signUpButton_id);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserAccount");


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString() ;
                String Email=email.getText().toString();
                String Pass1=pass1.getText().toString();
                String Pass2=pass2.getText().toString();

                if (Name.isEmpty())
                {
                    name.setError("Please enter valid Name");
                    name.requestFocus();
                    return;
                }
                if (Email.isEmpty())
                {
                    email.setError("Please enter valid Email Id");
                    email.requestFocus();
                    return;
                }
                if (Pass1.isEmpty())
                {
                    pass1.setError("Please enter valid password");
                    pass1.requestFocus();
                    return;
                }
                if (!Pass2.equals(Pass1))
                {
                    pass2.setError("Password can't match");
                    pass2.requestFocus();
                    return;
                }

                String ID=databaseReference.push().getKey();
                Model1 model1=new Model1(Name,Email,Pass1,ID);

                         databaseReference.child(ID).setValue(model1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        firebaseAuth.createUserWithEmailAndPassword(Email,Pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(RegisterActivity.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                                    startActivity(intent);

                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                databaseReference.child(ID).removeValue();

                                            }
                                        });

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });









            }
        });

    }
}