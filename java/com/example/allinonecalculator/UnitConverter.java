package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

public class UnitConverter extends AppCompatActivity {
    private Button len_btn,area_btn,volume_btn,weight_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Unit Converter");
        len_btn = findViewById(R.id.LengthId);
        len_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UnitConverter.this,Length.class);
                startActivity(intent);
            }
        });
        area_btn = findViewById(R.id.AreaId);
        area_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UnitConverter.this,Area.class);
                startActivity(intent);
            }
        });
        volume_btn = findViewById(R.id.VolumeId);
        volume_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UnitConverter.this,Volume.class);
                startActivity(intent);
            }
        });
        weight_btn = findViewById(R.id.WeightId);
       weight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UnitConverter.this,Weight.class);
                startActivity(intent);
            }
        });
    }
}
