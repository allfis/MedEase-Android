package com.example.practice.admin;
import com.example.practice.admin.AdminAppointmentAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Appointment;
import com.example.practice.data.MedEaseRepo;


import java.util.ArrayList;

public class AdminAppointmentsFragment extends Fragment {

    ArrayList<Appointment> list = new ArrayList<>();
    AdminAppointmentAdapter adapter;

    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_admin_appointments, c, false);
        RecyclerView rv = v.findViewById(R.id.rvAppointments);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdminAppointmentAdapter(list);
        rv.setAdapter(adapter);
        return v;
    }

    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(MedEaseRepo.get().getAllAppointments());
        adapter.notifyDataSetChanged();
    }
}
