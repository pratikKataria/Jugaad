package com.tricky__tweaks.jugaad.activity.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.MainActivity;

public class SignUp extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUserName;
    private MaterialButton signupBtn;

    private FirebaseAuth firebaseAuth;

    private void init_fields() {
        editTextEmail = findViewById(R.id.activity_sign_up_et_email);
        editTextPassword = findViewById(R.id.activity_sign_up_et_password);
        editTextUserName = findViewById(R.id.activity_sign_up_et_username);
        signupBtn = findViewById(R.id.activity_sign_up_mb_signup);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init_fields();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextEmail.getText().toString().isEmpty()) {
                    editTextEmail.setError("should not empty");
                    editTextEmail.requestFocus();
                    return;
                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    editTextPassword.setError("should not empty");
                    editTextPassword.requestFocus();
                    return;
                }
                if (editTextPassword.getText().toString().length() < 6) {
                    editTextPassword.setError("should to 6 or greater");
                    editTextPassword.requestFocus();
                    return;
                }

                signup(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

    }

    private void signup(final String email, final String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this , "signup successfull", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "login successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

    }
}
