package com.example.newdatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText name,surname,mark;
    TextView DataV;
    MyDatabase database=new MyDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        mark=findViewById(R.id.mark);
        DataV = findViewById(R.id.t1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void saveData(View view) {
        String Name=name.getText().toString();
        String Surname=surname.getText().toString();
        Integer Marks=Integer.parseInt(mark.getText().toString());
        Boolean result=database.insertdata(Name,Surname,Marks);
        if(result){
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data insertion failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void read(View view) {
        Cursor res=database.getAllData();
        StringBuffer stringBuffer=new StringBuffer();
        if(res!=null && res.getCount() > 0){
            while (res.moveToNext()){
                stringBuffer.append("Id: " + res.getString(0)+"\n");
                stringBuffer.append("Name: " + res.getString(1)+"\n");
                stringBuffer.append("Surname: " + res.getString(2)+"\n");
                stringBuffer.append("Marks: " + res.getString(3)+"\n");
            }
            DataV.setText(stringBuffer.toString());
        }
        else {
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
    }
}