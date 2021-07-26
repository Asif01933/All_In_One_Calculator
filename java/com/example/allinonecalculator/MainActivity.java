package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All In One Calculator");
        button1 = findViewById(R.id.calculatorId);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Calculator.class);
                startActivity(intent);
            }
        });
        button2 = findViewById(R.id.converterId);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, UnitConverter.class);
                startActivity(intent);
            }
        });
        button3 = findViewById(R.id.BMI);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, BMICalculator.class);
                startActivity(intent);
            }
        });
        button4 = findViewById(R.id.about);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });


    }
}
