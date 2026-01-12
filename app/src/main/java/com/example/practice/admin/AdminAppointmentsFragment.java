package com.example.practice.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.MedEaseRepo;

public class AdminAppointmentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_admin_appointments, c, false);

        RecyclerView rv = v.findViewById(R.id.rvAppointments);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(
                new AdminAppointmentAdapter(
                        MedEaseRepo.get().getPendingAppointments()
                )
        );

        return v;
    }
}
