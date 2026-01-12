package com.example.practice.patient;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.example.practice.data.MedEaseRepo;
import com.google.android.material.button.MaterialButton;

public class DoctorProfileActivity extends AppCompatActivity {

    private String doctorId;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        doctorId = getIntent().getStringExtra("doctorId");
        email = getIntent().getStringExtra("email");

        Doctor doctor = MedEaseRepo.get().findDoctorById(doctorId);

        MaterialButton btnFix = findViewById(R.id.btnFixAppointment);

        if (doctor == null) {
            Toast.makeText(this, "Doctor not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        boolean already = MedEaseRepo.get().hasAnyAppointmentForDoctor(email, doctorId);
        if (already) {
            btnFix.setEnabled(false);
            btnFix.setText("Appointment Fixed");
        } else {
            btnFix.setEnabled(true);
            btnFix.setText("Fix Appointment");
        }

        btnFix.setOnClickListener(v -> {
            String slot = "10:00 AM";

            boolean ok = MedEaseRepo.get().createAppointmentIfNotExists(email, doctor, slot);

            if (!ok) {
                btnFix.setEnabled(false);
                btnFix.setText("Appointment Fixed");
                Toast.makeText(this, "Already fixed with this doctor", Toast.LENGTH_SHORT).show();
                return;
            }

            btnFix.setEnabled(false);
            btnFix.setText("Appointment Fixed");
            Toast.makeText(this, "Appointment request sent", Toast.LENGTH_SHORT).show();
        });
    }
}
