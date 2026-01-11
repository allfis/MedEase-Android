package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.admin.AdminDashboardActivity;
import com.example.practice.data.MedEaseRepo;
import com.example.practice.patient.PatientDashboardActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MedEaseRepo.init(this);

        setContentView(R.layout.activity_main);

        RadioGroup rgRole = findViewById(R.id.rgRole);
        TextInputEditText etEmail = findViewById(R.id.etEmail);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        findViewById(R.id.rbPatient).performClick();

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isAdmin = rgRole.getCheckedRadioButtonId() == R.id.rbAdmin;

            Intent i = new Intent(this, isAdmin ? AdminDashboardActivity.class : PatientDashboardActivity.class);
            i.putExtra(EXTRA_EMAIL, email);
            startActivity(i);
        });
    }
}
