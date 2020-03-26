package com.tricky__tweaks.jugaad.activity.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextUserName;
    private ProgressBar progressBar;
    private TextView textViewErrorMessage;
    private TextView textViewLoginBtn;
    private MaterialButton signupBtn;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private void init_fields() {
        editTextEmail = findViewById(R.id.activity_sign_up_et_email);
        editTextPassword = findViewById(R.id.activity_sign_up_et_password);
        editTextUserName = findViewById(R.id.activity_sign_up_et_username);
        signupBtn = findViewById(R.id.activity_sign_up_mb_signup);
        progressBar = findViewById(R.id.progressBar);

        textViewErrorMessage = findViewById(R.id.activity_sign_up_tv_error_message);
        textViewLoginBtn = findViewById(R.id.activity_signup_tv_login_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init_fields();

        signupBtn.setOnClickListener(v -> {
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
        });

        textViewLoginBtn.setOnClickListener(v -> startActivity(new Intent(SignUp.this, Login.class)));
    }

    private void signup(final String email, final String password) {

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(SignUp.this , "signup successfull", Toast.LENGTH_SHORT).show();
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUp.this , "user doc created", Toast.LENGTH_SHORT).show();
                        createUserDoc();
                    }
                });
            }
        }).addOnFailureListener(e -> {
            progressBar.setVisibility(View.GONE);
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorMessage.setText("invalid credentails check password");
            } else if (e instanceof FirebaseAuthInvalidUserException) {
                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorMessage.setText("not regester user please sign up ");
            } else if (e instanceof FirebaseAuthUserCollisionException) {
                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorMessage.setText("already have email");
            } else if (e instanceof FirebaseAuthWeakPasswordException) {
                textViewErrorMessage.setVisibility(View.VISIBLE);
                textViewErrorMessage.setText("password is to weak");
            }
        });;

    }

    public void createUserDoc() {
        if (firebaseAuth.getUid() != null) {

            Map<String, Object> userDocuments = new HashMap<>();
            userDocuments.put("user_id", firebaseAuth.getUid());
            userDocuments.put("user_name", editTextUserName.getText().toString());
            userDocuments.put("email_address", editTextEmail.getText().toString());
            userDocuments.put("password", editTextPassword.getText().toString());

            DatabaseReference documentReference = FirebaseDatabase.getInstance().getReference("User");
            documentReference.child(FirebaseAuth.getInstance().getUid())
                    .updateChildren(userDocuments).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "login successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                        } else {
                            Toast.makeText(SignUp.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
