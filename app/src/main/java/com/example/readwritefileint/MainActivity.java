package com.example.readwritefileint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private Button saveB, readB, clearB;
    private EditText fileNameET;
    private EditText textET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveB = (Button) findViewById(R.id.saveB);
        readB = (Button) findViewById(R.id.readB);
        clearB = (Button) findViewById(R.id.clearB);
        fileNameET = (EditText) findViewById(R.id.fileNameET);
        textET = (EditText) findViewById(R.id.textDisplayET);
        textET.setMinLines(2);
        textET.setMaxLines(5);
        textET.setText("");
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { saveData(); }
        });
        readB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { readData(); }
        });
        clearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textET.setText(""); }
        });
    }

    private void saveData() {
        String fileName = fileNameET.getText().toString();
        String data = textET.getText().toString();
        try {
            FileOutputStream out =
                    this.openFileOutput(fileName, MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this, "File saved!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error:",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void readData() {
        try {
            String fileName = fileNameET.getText().toString();
            FileInputStream in = this.openFileInput(fileName);
            BufferedReader br= new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder sb= new StringBuilder();
            String s= null;
            while((s = br.readLine()) != null)  {
                sb.append(s).append("\n");
            }
            textET.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(this, "Error:",
                    Toast.LENGTH_SHORT).show();
        }
    }
}