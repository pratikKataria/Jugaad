package com.tricky__tweaks.jugaad.activity.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.MainActivity;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewUserName;
    private MaterialButton loginBtn;

    private FirebaseAuth firebaseAuth;

    private void init_fields() {
        editTextEmail = findViewById(R.id.activity_login_et_email);
        editTextPassword = findViewById(R.id.activity_login_et_password);
        textViewUserName = findViewById(R.id.activity_login_tv_error_message);
        loginBtn = findViewById(R.id.activity_login_mb_login);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init_fields();

        loginBtn.setOnClickListener(new View.OnClickListener() {
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

                login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
            }
        });

    }

    private void login(final String email, final String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this , "signup successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    textViewUserName.setVisibility(View.VISIBLE);
                    textViewUserName.setText("invalid credentails check password");
                } else if (e instanceof FirebaseAuthInvalidUserException) {
                    textViewUserName.setVisibility(View.VISIBLE);
                    textViewUserName.setText("not regester user please sign up ");
                } else if (e instanceof FirebaseAuthUserCollisionException) {
                    textViewUserName.setVisibility(View.VISIBLE);
                    textViewUserName.setText("already have email");
                } else if (e instanceof FirebaseAuthWeakPasswordException) {
                    textViewUserName.setVisibility(View.VISIBLE);
                    textViewUserName.setText("password is to weak");
                }
            }
        });

    }
}
