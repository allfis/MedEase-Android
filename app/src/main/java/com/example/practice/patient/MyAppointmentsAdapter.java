package com.example.practice.patient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Appointment;

import java.util.List;

public class MyAppointmentsAdapter extends RecyclerView.Adapter<MyAppointmentsAdapter.VH> {

    List<Appointment> data;

    public MyAppointmentsAdapter(List<Appointment> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext())
                .inflate(R.layout.row_my_appointment, p, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        Appointment a = data.get(i);
        h.tvDoctor.setText(a.doctorName);
        h.tvStatus.setText(a.status.name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDoctor, tvStatus;

        VH(View v) {
            super(v);
            tvDoctor = v.findViewById(R.id.tvDoctor);
            tvStatus = v.findViewById(R.id.tvStatus);
        }
    }
}
