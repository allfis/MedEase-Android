package com.example.practice.patient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PatientAboutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, android.os.Bundle b) {
        TextView tv = new TextView(getContext());
        tv.setText("MedEase Patient Panel");
        tv.setTextSize(18);
        tv.setPadding(24, 24, 24, 24);
        return tv;
    }
}
