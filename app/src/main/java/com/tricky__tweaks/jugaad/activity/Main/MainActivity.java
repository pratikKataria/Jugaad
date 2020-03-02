package com.tricky__tweaks.jugaad.activity.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Login.Login;
import com.tricky__tweaks.jugaad.activity.Login.SignUp;
import com.tricky__tweaks.jugaad.activity.Splash.SplashActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, Login.class));
            finish();
        }
        super.onStart();
    }
}
