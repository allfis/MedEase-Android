package com.example.twoactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tvName, tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.tvName);
        tvId = findViewById(R.id.tvId);

        String name = getIntent().getStringExtra("name");
        String id = getIntent().getStringExtra("id");

        tvName.setText("Name: " + name);
        tvId.setText("ID: " + id);
    }
}
