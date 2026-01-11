package com.example.practice.patient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Appointment;
import com.example.practice.data.MedEaseRepo;

import java.util.ArrayList;
import java.util.List;

public class MyAppointmentsFragment extends Fragment {

    private static final String ARG_EMAIL = "email";
    private String email;

    public static MyAppointmentsFragment newInstance(String email) {
        MyAppointmentsFragment f = new MyAppointmentsFragment();
        Bundle b = new Bundle();
        b.putString(ARG_EMAIL, email);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_my_appointments, c, false);

        if (getArguments() != null) {
            email = getArguments().getString(ARG_EMAIL);
        }

        RecyclerView rv = v.findViewById(R.id.rvMyAppointments);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Appointment> list =
                MedEaseRepo.get().getAppointmentsForPatient(email);

        rv.setAdapter(new MyAppointmentsAdapter(list));

        return v;
    }
}
