package com.tricky__tweaks.jugaad.activity.Main.UserOptions.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.NavigationHost;
/*
check up test 27 april
* */
public class OptionsMainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_main);
        changeStatusBarColor();

        //default fragment set
//        getSupportFragmentManager().beginTransaction().replace(R.id.options_main_fragment_holder, new UserProfileFragment()).commit();
        ((NavigationHost)this).navigateTo((Fragment)getIntent().getSerializableExtra("FRAGMENT"), false );

    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.options_main_fragment_holder, fragment);

            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
    }

    private void changeStatusBarColor() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));

    }
}
