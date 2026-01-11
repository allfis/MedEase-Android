package com.example.practice.patient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.VH> {

    interface Click { void onClick(Doctor d); }

    List<Doctor> data;
    Click click;

    DoctorAdapter(List<Doctor> d, Click c) {
        data = d;
        click = c;
    }

    public VH onCreateViewHolder(ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext()).inflate(R.layout.row_doctor, p, false);
        return new VH(view);
    }

    public void onBindViewHolder(VH h, int i) {
        Doctor d = data.get(i);
        h.name.setText(d.name);
        h.info.setText(d.specialist + " • " + d.hospital);
        h.btn.setOnClickListener(v -> click.onClick(d));
    }

    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, info;
        MaterialButton btn;
        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tvName);
            info = v.findViewById(R.id.tvInfo);
            btn = v.findViewById(R.id.btnView);
        }
    }
}
