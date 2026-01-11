package com.example.practice.patient;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;
import com.example.practice.data.Doctor;
import com.example.practice.data.MedEaseRepo;
import com.google.android.material.button.MaterialButton;

public class DoctorProfileActivity extends AppCompatActivity {

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_doctor_profile);

        Doctor d = MedEaseRepo.get().findDoctorById(getIntent().getStringExtra("doctorId"));

        ((TextView)findViewById(R.id.tvName)).setText(d.name);
        ((TextView)findViewById(R.id.tvInfo)).setText(d.specialist + " • " + d.hospital);

        StringBuilder sb = new StringBuilder();
        for(String r : d.reviews) sb.append("• ").append(r).append("\n");
        ((TextView)findViewById(R.id.tvReviews)).setText(sb.toString());

        MaterialButton btn = findViewById(R.id.btnFix);
        btn.setOnClickListener(v ->
                MedEaseRepo.get().createAppointment(
                        getIntent().getStringExtra("email"),
                        d,
                        "Next Slot"
                )
        );
    }
}
