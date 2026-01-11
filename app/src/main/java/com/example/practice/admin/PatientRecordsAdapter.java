package com.example.practice.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.data.Appointment;

import java.util.List;

public class PatientRecordsAdapter extends RecyclerView.Adapter<PatientRecordsAdapter.VH> {

    private final List<Appointment> data;

    public PatientRecordsAdapter(List<Appointment> data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient_record, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int position) {
        Appointment a = data.get(position);
        h.email.setText(a.patientEmail);
        h.doctor.setText("Doctor: " + a.doctorName);
        h.slot.setText("Slot: " + a.slot);
        h.status.setText("Status: " + a.status.name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView email, doctor, slot, status;

        VH(View v) {
            super(v);
            email = v.findViewById(R.id.tvPatientEmail);
            doctor = v.findViewById(R.id.tvDoctor);
            slot = v.findViewById(R.id.tvSlot);
            status = v.findViewById(R.id.tvStatus);
        }
    }
}
