package com.tricky__tweaks.jugaad;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Jugaad extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
