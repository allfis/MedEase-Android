package com.example.practice.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.example.practice.data.MedEaseRepo;

import java.util.List;

public class AdminScheduleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_admin_schedule, c, false);

        RecyclerView rv = v.findViewById(R.id.rvSchedule);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Doctor> doctors = MedEaseRepo.get().allDoctors();
        rv.setAdapter(new ScheduleAdapter(doctors));

        return v;
    }
}
