package com.example.practice.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Appointment;
import com.example.practice.data.MedEaseRepo;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AdminAppointmentAdapter extends RecyclerView.Adapter<AdminAppointmentAdapter.VH> {

    private final List<Appointment> data;

    public AdminAppointmentAdapter(List<Appointment> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_appointment, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Appointment a = data.get(position);

        h.tvDoctor.setText(a.doctorName);
        h.tvStatus.setText(a.status.name());

        h.btnApprove.setOnClickListener(v -> {
            MedEaseRepo.get().setAppointmentStatus(a.id, Appointment.Status.APPROVED);
            notifyItemChanged(position);
        });

        h.btnReject.setOnClickListener(v -> {
            MedEaseRepo.get().setAppointmentStatus(a.id, Appointment.Status.REJECTED);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDoctor, tvStatus;
        MaterialButton btnApprove, btnReject;

        VH(View v) {
            super(v);
            tvDoctor = v.findViewById(R.id.tvDoctor);
            tvStatus = v.findViewById(R.id.tvStatus);
            btnApprove = v.findViewById(R.id.btnApprove);
            btnReject = v.findViewById(R.id.btnReject);
        }
    }
}
