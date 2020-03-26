package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.NavigationHost;
import com.tricky__tweaks.jugaad.activity.Main.UserOptions.UserProfileFragment;

public class OptionsMainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_main);

        //default fragment set
        getSupportFragmentManager().beginTransaction().replace(R.id.options_main_fragment_holder, new UserProfileFragment()).commit();

    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.options_main_fragment_holder, fragment);

            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
    }
}
