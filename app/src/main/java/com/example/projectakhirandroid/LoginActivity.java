package com.example.projectakhirandroid; // Deklarasi paket untuk kelas LoginActivity

import androidx.appcompat.app.AlertDialog; // Impor kelas AlertDialog dari pustaka Android
import androidx.appcompat.app.AppCompatActivity; // Impor kelas AppCompatActivity dari pustaka Android
import android.content.DialogInterface; // Impor kelas DialogInterface dari pustaka Android
import android.content.Intent; // Impor kelas Intent dari pustaka Android
import android.os.Bundle; // Impor kelas Bundle dari pustaka Android
import android.view.View; // Impor kelas View dari pustaka Android
import com.example.projectakhirandroid.databinding.ActivityLoginBinding; // Impor kelas ActivityLoginBinding yang dibuat oleh ViewBinding

public class LoginActivity extends AppCompatActivity { // Deklarasi kelas LoginActivity yang merupakan subkelas dari AppCompatActivity

    ActivityLoginBinding binding; // Deklarasi variabel binding bertipe ActivityLoginBinding
    DatabaseHelper databaseHelper; // Deklarasi variabel databaseHelper bertipe DatabaseHelper

    @Override // Anotasi @Override menunjukkan bahwa metode ini menggantikan metode di kelas induknya
    protected void onCreate(Bundle savedInstanceState) { // Deklarasi metode onCreate yang dipanggil saat aktivitas dibuat
        super.onCreate(savedInstanceState); // Memanggil metode onCreate dari kelas induknya
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // Menginisialisasi binding dengan hasil inflate dari layout activity_login.xml
        setContentView(binding.getRoot()); // Mengatur tata letak aktivitas menggunakan root View yang diambil dari binding

        databaseHelper = new DatabaseHelper(this); // Inisialisasi objek databaseHelper dengan konteks saat ini

        binding.loginButton.setOnClickListener(new View.OnClickListener() { // Menetapkan OnClickListener untuk tombol loginButton
            @Override // Anotasi @Override menunjukkan bahwa metode ini menggantikan metode di kelas induknya
            public void onClick(View view) { // Deklarasi metode onClick yang dipanggil saat tombol diklik
                String email = binding.loginEmail.getText().toString(); // Mengambil email dari EditText loginEmail
                String password = binding.loginPassword.getText().toString(); // Mengambil password dari EditText loginPassword

                if(email.isEmpty()) { // Memeriksa apakah email kosong
                    showAlert("Email masih kosong!"); // Menampilkan dialog peringatan dengan pesan "Email masih kosong!"
                } else if(!isValidEmail(email)) { // Memeriksa apakah email valid
                    showAlert("Email kamu salah!"); // Menampilkan dialog peringatan dengan pesan "Email kamu salah!"
                } else if(password.isEmpty()) { // Memeriksa apakah password kosong
                    showAlert("Password masih kosong!"); // Menampilkan dialog peringatan dengan pesan "Password masih kosong!"
                } else if(password.length() < 8) { // Memeriksa apakah password memiliki panjang kurang dari 8 karakter
                    showAlert("Password harus lebih dari 8 karakter!"); // Menampilkan dialog peringatan dengan pesan "Password harus lebih dari 8 karakter!"
                } else { // Jika tidak ada kesalahan validasi
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password); // Memeriksa kredensial pengguna di database

                    if(checkCredentials) { // Jika kredensial benar
                        showSuccessAlert(); // Menampilkan dialog sukses untuk login berhasil
                    } else { // Jika kredensial salah
                        showAlert("User tidak terdaftar"); // Menampilkan dialog peringatan dengan pesan "User tidak terdaftar"
                    }
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() { // Menetapkan OnClickListener untuk teks signupRedirectText
            @Override // Anotasi @Override menunjukkan bahwa metode ini menggantikan metode di kelas induknya
            public void onClick(View view) { // Deklarasi metode onClick yang dipanggil saat teks diklik
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class); // Membuat objek Intent untuk mengarahkan ke SignupActivity
                startActivity(intent); // Memulai aktivitas SignupActivity
            }
        });
    }

    private void showAlert(String message) { // Deklarasi metode showAlert untuk menampilkan dialog peringatan
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); // Membuat objek AlertDialog.Builder dengan konteks LoginActivity
        builder.setTitle("Oi cug!!!") // Mengatur judul dialog menjadi "Oi cug!!!"
                .setMessage(message) // Mengatur pesan dialog sesuai parameter yang diberikan
                .setPositiveButton("AMAN AJA", new DialogInterface.OnClickListener() { // Menambahkan tombol positif dengan teks "AMAN AJA"
                    public void onClick(DialogInterface dialog, int id) { // Mengatur tindakan saat tombol positif diklik
                        // Do something when OK button is clicked
                    }
                });
        AlertDialog alert = builder.create(); // Membuat objek AlertDialog dari AlertDialog.Builder
        alert.show(); // Menampilkan dialog peringatan
    }

    // Method untuk menampilkan alert ketika login berhasil
    private void showSuccessAlert() { // Deklarasi metode showSuccessAlert untuk menampilkan dialog sukses
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); // Membuat objek AlertDialog.Builder dengan konteks LoginActivity
        builder.setTitle("Yeay!") // Mengatur judul dialog menjadi "Yeay!"
                .setMessage("Login berhasil!") // Mengatur pesan dialog menjadi "Login berhasil!"
                .setPositiveButton("OK", new DialogInterface.OnClickListener() { // Menambahkan tombol positif dengan teks "OK"
                    public void onClick(DialogInterface dialog, int id) { // Mengatur tindakan saat tombol positif diklik
                        // Redirect ke DashboardActivity atau halaman lain jika perlu
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class); // Membuat objek Intent untuk mengarahkan ke DashboardActivity
                        startActivity(intent); // Memulai aktivitas DashboardActivity
                        finish(); // Menutup LoginActivity agar tidak dapat kembali dengan menekan tombol back
                    }
                });
        AlertDialog alert = builder.create(); // Membuat objek AlertDialog dari AlertDialog.Builder
        alert.show(); // Menampilkan dialog sukses
    }

    private boolean isValidEmail(CharSequence target) { // Deklarasi metode isValidEmail untuk memeriksa apakah email valid
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches(); // Menggunakan regular expression untuk memeriksa kevalidan email
    }
}
