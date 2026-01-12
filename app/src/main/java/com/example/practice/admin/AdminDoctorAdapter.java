package com.example.practice.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Doctor;

import java.util.List;

public class AdminDoctorAdapter extends RecyclerView.Adapter<AdminDoctorAdapter.VH> {

    private final List<Doctor> data;

    public AdminDoctorAdapter(List<Doctor> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_doctor, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Doctor d = data.get(position);
        h.name.setText(d.name);
        h.specialist.setText("Specialist: " + d.specialist);
        h.hospital.setText("Hospital: " + d.hospital);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, specialist, hospital;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tvName);
            specialist = v.findViewById(R.id.tvSpecialist);
            hospital = v.findViewById(R.id.tvHospital);
        }
    }
}
