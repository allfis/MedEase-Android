package com.example.practice.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class PatientDashboardActivity extends AppCompatActivity {

    public String email;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        email = getIntent().getStringExtra(MainActivity.EXTRA_EMAIL);

        drawer = findViewById(R.id.drawer);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> drawer.openDrawer(GravityCompat.START));

        TextView tvPatient = findViewById(R.id.tvPatient);
        tvPatient.setText(email);

        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_about) {
                open(new PatientAboutFragment());
                return true;
            } else if (id == R.id.action_logout) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;
            } else if (id == R.id.action_exit) {
                finishAffinity();
                return true;
            }
            return false;
        });

        NavigationView nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_search) {
                open(new SearchDoctorsFragment());
            } else if (id == R.id.nav_appointments) {
                open(MyAppointmentsFragment.newInstance(email));
            } else if (id == R.id.nav_profile) {
                open(PatientProfileFragment.newInstance(email));
            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        open(new SearchDoctorsFragment());
    }

    private void open(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }
}
