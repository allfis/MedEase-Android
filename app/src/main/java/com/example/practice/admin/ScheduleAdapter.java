package com.example.practice.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.example.practice.data.MedEaseRepo;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.VH> {

    private final List<Doctor> data;

    public ScheduleAdapter(List<Doctor> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_schedule, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Doctor d = data.get(position);

        h.name.setText(d.name);
        h.info.setText(d.specialist + " • " + d.hospital);

        boolean available = MedEaseRepo.get().isDoctorAvailable(d.id);

        h.sw.setOnCheckedChangeListener(null);
        h.sw.setChecked(available);

        h.sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            MedEaseRepo.get().setDoctorAvailability(d.id, isChecked);
            h.itemView.animate().cancel();
            h.itemView.setAlpha(0.85f);
            h.itemView.animate().alpha(1f).setDuration(180).start();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, info;
        SwitchMaterial sw;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tvDoctorName);
            info = v.findViewById(R.id.tvDoctorInfo);
            sw = v.findViewById(R.id.swAvailable);
        }
    }
}
