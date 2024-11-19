package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText n1,n2;
double number1,number2;
EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        n1 = findViewById(R.id.num1);
        n2 = findViewById(R.id.num2);
        t = findViewById(R.id.res);
    }

    public void add(View view) {
        number1 = Integer.parseInt(n1.getText().toString());
        number2 = Integer.parseInt(n2.getText().toString());
        double sum = number1 + number2;
        t.setText(String.valueOf(sum));
    }

    public void sub(View view) {
        number1 = Integer.parseInt(n1.getText().toString());
        number2 = Integer.parseInt(n2.getText().toString());
        double diff = number1 - number2;
        t.setText(String.valueOf(diff));
    }

    public void div(View view) {
        number1 = Integer.parseInt(n1.getText().toString());
        number2 = Integer.parseInt(n2.getText().toString());
        if (number2 != 0) {
            double result = number1 / number2;
            t.setText(String.valueOf(result));
        } else {
            // Handle division by zero error
            t.setText("Error: Division by zero");
        }
    }

    public void mul(View view) {
        number1 = Integer.parseInt(n1.getText().toString());
        number2 = Integer.parseInt(n2.getText().toString());
        double product = number1 * number2;
        t.setText(String.valueOf(product));
    }
}