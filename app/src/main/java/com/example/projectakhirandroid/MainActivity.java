// MainActivity.java
package com.example.projectakhirandroid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pindah ke LoginActivity saat aplikasi pertama kali dibuka
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Menutup MainActivity agar tidak dapat kembali dengan menekan tombol back
    }
}
