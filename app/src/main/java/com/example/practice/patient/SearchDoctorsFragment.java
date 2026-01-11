package com.example.practice.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.example.practice.data.MedEaseRepo;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorsFragment extends Fragment {

    ArrayList<Doctor> list = new ArrayList<>();
    DoctorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle b) {
        View v = i.inflate(R.layout.fragment_search_doctors, c, false);

        TextInputEditText et = v.findViewById(R.id.etQuery);
        RecyclerView rv = v.findViewById(R.id.rvDoctors);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DoctorAdapter(list, d -> {
            Intent it = new Intent(getContext(), DoctorProfileActivity.class);
            it.putExtra("doctorId", d.id);
            it.putExtra("email", getActivity().getIntent().getStringExtra(MainActivity.EXTRA_EMAIL));
            startActivity(it);
        });
        rv.setAdapter(adapter);

        load(MedEaseRepo.get().allDoctors());

        et.addTextChangedListener(new SimpleTextWatcher(s ->
                load(MedEaseRepo.get().searchDoctors(s, s, s))
        ));

        return v;
    }

    void load(List<Doctor> d) {
        list.clear();
        list.addAll(d);
        adapter.notifyDataSetChanged();
    }
}
