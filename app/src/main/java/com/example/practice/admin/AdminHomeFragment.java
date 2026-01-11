package com.example.practice.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AdminHomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        TextView tv = new TextView(getContext());
        tv.setText("Admin Dashboard");
        tv.setTextSize(20);
        tv.setPadding(24, 24, 24, 24);
        return tv;
    }
}
