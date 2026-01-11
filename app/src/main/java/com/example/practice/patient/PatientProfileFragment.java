package com.example.practice.patient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PatientProfileFragment extends Fragment {

    private static final String ARG_EMAIL = "email";

    public static PatientProfileFragment newInstance(String email) {
        PatientProfileFragment f = new PatientProfileFragment();
        Bundle b = new Bundle();
        b.putString(ARG_EMAIL, email);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        String email = getArguments() == null ? "" : getArguments().getString(ARG_EMAIL, "");

        TextView tv = new TextView(getContext());
        tv.setText("Patient Profile\n\nEmail: " + email);
        tv.setTextSize(18);
        tv.setPadding(24, 24, 24, 24);
        return tv;
    }
}
