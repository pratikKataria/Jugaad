package com.tricky__tweaks.jugaad.activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
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
    private EditText editTextResetPassword;
    private MaterialButton sendPasswordBtn;
    private TextView textViewUserName;
    private TextView textViewLoginBtn;
    private TextView textViewForgetPassword;
    private MaterialButton loginBtn;
    private LinearLayout linearLayout;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private void init_fields() {
        editTextEmail = findViewById(R.id.activity_login_et_email);
        editTextPassword = findViewById(R.id.activity_login_et_password);
        editTextResetPassword = findViewById(R.id.activity_login_et_reset_password);
        textViewUserName = findViewById(R.id.activity_login_tv_error_message);
        textViewLoginBtn = findViewById(R.id.activity_login_tv_signup_btn);
        textViewForgetPassword = findViewById(R.id.activity_login_tv_forget_password);
        loginBtn = findViewById(R.id.activity_login_mb_login);
        sendPasswordBtn = findViewById(R.id.activity_login_mb_send_password);
        linearLayout = findViewById(R.id.linearLayout);

        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init_fields();

        loginBtn.setOnClickListener(v -> {
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
        });

        textViewLoginBtn.setOnClickListener(v -> startActivity(new Intent(Login.this, SignUp.class)));

        textViewForgetPassword.setOnClickListener(v -> {
            linearLayout.setVisibility(View.VISIBLE);

            sendPasswordBtn.setOnClickListener(v1 -> {
                if (editTextResetPassword.getText().toString().isEmpty()) {
                    editTextResetPassword.setError("should not be empty");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().sendPasswordResetEmail(editTextResetPassword.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);
                                editTextResetPassword.setText(" ");
                                Toast.makeText(Login.this, "password reset link send", Toast.LENGTH_SHORT).show();
                            }
                        });
            });
        });
    }

    private void login(final String email, final String password) {

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Login.this, "signup successfull", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Login.this, MainActivity.class));
            }
        }).addOnFailureListener(e -> {
            progressBar.setVisibility(View.GONE);
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
        });

    }
}
