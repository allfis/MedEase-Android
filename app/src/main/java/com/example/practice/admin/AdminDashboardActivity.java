package com.example.practice.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.practice.MainActivity;
import com.example.practice.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawer = findViewById(R.id.drawer);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> drawer.openDrawer(GravityCompat.START));

        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_about) {
                open(new AdminAboutFragment());
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

            if (id == R.id.nav_dashboard) open(new AdminHomeFragment());
            else if (id == R.id.nav_doctors) open(new AdminDoctorsFragment());
            else if (id == R.id.nav_appointments) open(new AdminAppointmentsFragment());
            else if (id == R.id.nav_patients) open(new AdminPatientsFragment());

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        open(new AdminHomeFragment());
    }

    private void open(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }
}
