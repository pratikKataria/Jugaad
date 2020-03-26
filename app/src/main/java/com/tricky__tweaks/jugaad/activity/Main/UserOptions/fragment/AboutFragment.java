package com.tricky__tweaks.jugaad.activity.Main.UserOptions.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tricky__tweaks.jugaad.R;
import com.tricky__tweaks.jugaad.activity.Main.MainActivity;

import java.io.Serializable;

public class AboutFragment extends Fragment implements Serializable {

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ImageButton imageButton =  view.findViewById(R.id.about_include).findViewById(R.id.back_button);
        imageButton.setOnClickListener(n -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        });

        return view;
    }
}
