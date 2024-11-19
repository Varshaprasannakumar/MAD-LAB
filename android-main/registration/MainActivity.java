package com.example.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText first_name1;
    private EditText last_name1;
    private EditText email1;
    private EditText phone1;
    private EditText pass1;
    private EditText dob1;
    private RadioGroup gender1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        first_name1=findViewById(R.id.fname);
        last_name1 =findViewById(R.id.lname);
        gender1=findViewById(R.id.radioGroup3);
        email1=findViewById(R.id.email);
        phone1=findViewById(R.id.phn);
        pass1=findViewById(R.id.pass);
        dob1=findViewById(R.id.dob);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void next(View view) {
        String first_name=first_name1.getText().toString();
        String last_name= last_name1.getText().toString();
        String email=email1.getText().toString();
        String phone=phone1.getText().toString();
        String password=pass1.getText().toString();
        String dob=dob1.getText().toString();
        int selectedId = gender1.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String gender = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not Specified";
        if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || dob.isEmpty()){
            Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else if(!isValidName(first_name) || !isValidName(last_name)){
            Toast.makeText(this, "Invalid format for name", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPhoneNumber(phone)) {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }
        else if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidDateFormat(dob, "dd/mm/yyyy")){
            Toast.makeText(this, "Invalid date format. Please use dd/mm/yyyy", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
            SharedPreferences sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("fname", first_name);
            editor.putString("lname", last_name);
            editor.putString("gender", gender);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.putString("dob", dob);
            editor.apply();
            Toast.makeText(this, "Registration Success.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
    private boolean isValidName(String name) {
        return name.matches("[A-Za-z][A-Za-z ]*");
    }
    private boolean isValidPhoneNumber(CharSequence phone) {
        String phoneString = phone.toString();
        String cleanedPhone = phoneString.replaceAll("\\D", "");
        return cleanedPhone.length() >= 10;
    }
    private boolean isValidDateFormat(String dob, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        sdf.setLenient(false); // Set lenient to false to ensure strict parsing
        try {
            sdf.parse(dob);
            return true; // Date is in correct format
        } catch (ParseException e) {
            return false; // Date is not in correct format
        }
    }
}