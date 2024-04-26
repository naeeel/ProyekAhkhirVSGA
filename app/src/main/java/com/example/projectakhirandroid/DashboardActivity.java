package com.example.projectakhirandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // Mendapatkan referensi TextView "Profil"
        TextView textViewProfile = findViewById(R.id.textInputData);

        // Menambahkan click listener ke TextView "Profil"
        textViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        }); // Mendapatkan referensi Button "Informasi"
        Button buttonInformasi = findViewById(R.id.textInformasi);

        // Menambahkan click listener ke Button "Informasi"
        buttonInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InformasiActivity.class);
                startActivity(intent);
            }
        });

    }

    }

