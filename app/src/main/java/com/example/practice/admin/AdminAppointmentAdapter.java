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
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_admin_appointment, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Appointment a = data.get(position);

        h.tvDoctor.setText(a.doctorName);
        h.tvPatient.setText(a.patientEmail);
        h.tvSlot.setText(a.slot);

        h.btnApprove.setOnClickListener(v -> {
            MedEaseRepo.get().setAppointmentStatus(a.id, Appointment.Status.APPROVED);
            removeItem(position);
        });

        h.btnReject.setOnClickListener(v -> {
            MedEaseRepo.get().setAppointmentStatus(a.id, Appointment.Status.REJECTED);
            removeItem(position);
        });
    }

    private void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvDoctor, tvPatient, tvSlot;
        MaterialButton btnApprove, btnReject;

        VH(View v) {
            super(v);
            tvDoctor = v.findViewById(R.id.tvDoctor);
            tvPatient = v.findViewById(R.id.tvPatient);
            tvSlot = v.findViewById(R.id.tvSlot);
            btnApprove = v.findViewById(R.id.btnApprove);
            btnReject = v.findViewById(R.id.btnReject);
        }
    }
}
