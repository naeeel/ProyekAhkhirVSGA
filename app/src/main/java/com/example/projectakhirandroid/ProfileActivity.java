package com.example.projectakhirandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    EditText nomorEditText, namaEditText, tanggalLahirEditText, jenisKelaminEditText, alamatEditText;
    Button saveButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nomorEditText = findViewById(R.id.nomor);
        namaEditText = findViewById(R.id.nama);
        tanggalLahirEditText = findViewById(R.id.tanggal_lahir);
        jenisKelaminEditText = findViewById(R.id.gender);
        alamatEditText = findViewById(R.id.alamat);
        saveButton = findViewById(R.id.saveButton);
        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Periksa apakah ada data yang kosong sebelum menyimpan
                if (isDataEmpty()) {
                    showAlertDialog("Alert", "Semua kolom harus diisi!");
                } else {
                    saveData();
                    // Navigate to ListActivity
                    Intent intent = new Intent(ProfileActivity.this, ListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isDataEmpty() {
        String nomor = nomorEditText.getText().toString();
        String nama = namaEditText.getText().toString();
        String tanggalLahir = tanggalLahirEditText.getText().toString();
        String jenisKelamin = jenisKelaminEditText.getText().toString();
        String alamat = alamatEditText.getText().toString();

        // Periksa apakah ada kolom yang kosong
        return nomor.isEmpty() || nama.isEmpty() || tanggalLahir.isEmpty() || jenisKelamin.isEmpty() || alamat.isEmpty();
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Kosongkan dialog dan biarkan pengguna kembali mengisi
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void saveData() {
        String nomor = nomorEditText.getText().toString();
        String nama = namaEditText.getText().toString();
        String tanggalLahir = tanggalLahirEditText.getText().toString();
        String jenisKelamin = jenisKelaminEditText.getText().toString();
        String alamat = alamatEditText.getText().toString();

        try {
            // Simpan data ke database
            boolean checkInsertData = databaseHelper.insertProfileData(nomor, nama, tanggalLahir, jenisKelamin, alamat);
            if (checkInsertData) {
                showToast("Data berhasil ditambahkan");
            } else {
                showToast("Gagal menambahkan data");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast("Gagal menambahkan data: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
