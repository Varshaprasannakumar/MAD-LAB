package com.example.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        String first_name = sharedPref.getString("fname", "Not Available");
        String last_name = sharedPref.getString("lname", "Not Available");
        String gender = sharedPref.getString("gender", "Not Available");
        String email = sharedPref.getString("email", "Not Available");
        String phone = sharedPref.getString("phone", "Not Available");
        String password = sharedPref.getString("password", "Not Available");
        String dob = sharedPref.getString("dob", "Not Available");

        TextView txtDetails = findViewById(R.id.d1);

        String details = "First Name: " + first_name + "\n\n" +
                "Last Name: " + last_name + "\n\n" +
                "Gender: " + gender + "\n\n" +
                "Email: " + email + "\n\n" +
                "Phone: " + phone + "\n\n" +
                "Password: " + password + "\n\n" +
                "Date of Birth: " + dob;

        txtDetails.setText(details);
    }

    public void back(View view) {
        Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show();
        Intent bintent=new Intent(MainActivity2.this, MainActivity.class);
        startActivity(bintent);
        finish();
    }
}