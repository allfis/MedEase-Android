package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Log.d("DB", "Users count = " + MedEaseRepo.get().countUsers());


        setContentView(R.layout.activity_main);

        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
            String password = etPassword.getText() == null ? "" : etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            String role = MedEaseRepo.get().authenticate(email, password);

            if (role == null) {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent;

            if (role.equals("ADMIN")) {
                intent = new Intent(this, AdminDashboardActivity.class);
            } else {
                intent = new Intent(this, PatientDashboardActivity.class);
            }

            intent.putExtra(EXTRA_EMAIL, email);
            startActivity(intent);
            finish();
        });
    }
}
