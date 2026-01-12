package com.example.practice.admin;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import com.example.practice.R;
import com.example.practice.data.*;

import java.util.List;

public class AdminDoctorsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_admin_doctors, c, false);

        RecyclerView rv = v.findViewById(R.id.rvDoctors);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Doctor> doctors = MedEaseRepo.get().allDoctors();
        rv.setAdapter(new AdminDoctorAdapter(doctors));

        return v;
    }
}
