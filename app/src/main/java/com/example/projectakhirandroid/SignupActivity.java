package com.example.projectakhirandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.projectakhirandroid.databinding.ActivitySignupBinding;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if(email.isEmpty()) {
                    showAlert("Email masih kosong!");
                } else if(!isValidEmail(email)) {
                    showAlert("Email kamu tidak valid!");
                } else if(password.isEmpty()) {
                    showAlert("Password masih kosong");
                } else if(confirmPassword.isEmpty()) {
                    showAlert("Confirm password anda masih kosong!");
                } else if(password.length() < 8) {
                    showAlert("Password harus lebih dari 8 karakter ya!");
                } else if(!password.equals(confirmPassword)) {
                    showAlert("Passwords kamu salah!");
                } else {
                    Boolean checkUserEmail = databaseHelper.checkEmail(email);
                    if(checkUserEmail == false){
                        Boolean insert = databaseHelper.insertUserData(email, password);
                        if(insert == true){
                            showAlert("Signup Berhasil!");
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            showAlert("Signup Gagal!");
                        }
                    } else {
                        showAlert("Hmm email ini sudah terdaftar!");
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Oi Cugg!!")
                .setMessage(message)
                .setPositiveButton("AMAN AJA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something when OK button is clicked
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isValidEmail(String email) {
        // Ekspresi reguler untuk validasi email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
