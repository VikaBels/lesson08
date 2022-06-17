package com.example.lesson08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    private TextView allDigits;
    private TextView currentDigit;

    private Button btnSave;
    private Button btnCalculator;

    public void findViewById() {
        allDigits = findViewById(R.id.txtAllDigit);
        currentDigit = findViewById(R.id.txtCurrentDigit);

        btnSave = findViewById(R.id.btnSave);
        btnCalculator = findViewById(R.id.btnCalculator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        findViewById();

        Intent goToInfo = new Intent(this , MainActivity.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToInfo);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        allDigits = null;
        currentDigit = null;

        btnSave = null;
        btnCalculator = null;
    }
}