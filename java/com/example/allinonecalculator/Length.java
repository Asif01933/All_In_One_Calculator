package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Length extends AppCompatActivity {
    EditText edtText;
    RadioGroup Group;
    RadioButton m2c,c2m,k2m,m2k,f2i,i2f,f2cm;
    Button submitbtn,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        edtText = findViewById(R.id.editText);
        Group = findViewById(R.id.radioGroup);
        m2c = findViewById(R.id.MeterToCentimeter);
        c2m = findViewById(R.id.CentimeterToMeter);
        k2m = findViewById(R.id.KilometerToMeter);
        m2k = findViewById(R.id.MeterToKilometer);
        f2i = findViewById(R.id.FeetToInch);
        i2f = findViewById(R.id.InchToFeet);
        f2cm= findViewById(R.id.feetTocm);

        submitbtn = findViewById(R.id.Button);
        cancelbtn = findViewById(R.id.CancelButton);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.CancelButton){
                    edtText.setText("");;
                }
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int selected = Group.getCheckedRadioButtonId();
                    submitbtn = (RadioButton) findViewById(selected);
                    String value = submitbtn.getText().toString();
                    if (selected == R.id.Button) {
                        edtText.setText("");
                    }
                    if (selected == R.id.MeterToCentimeter) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 100;
                        edtText.setText(value1+" meter = "+""+value2+" centimeter");

                    }
                    if (selected == R.id.CentimeterToMeter) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * .01;
                        edtText.setText(value1+" centimeter = "+""+value2+" meter");

                    }
                    if (selected == R.id.KilometerToMeter) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1000;
                        edtText.setText(value1+" kilometer = "+""+value2+" meter");

                    }
                    if (selected == R.id.MeterToKilometer) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * .001;
                        edtText.setText(value1+" meter = "+""+value2+" kilometer");

                    }
                    if (selected == R.id.MileToKilometer) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1.60935;
                        edtText.setText(value1+" mile = "+""+value2+" kilometer");

                    }
                    if (selected == R.id.KilometerToMile) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 0.6213688756;
                        edtText.setText(value1+" kilometer = "+""+value2+" mile");

                    }
                    if (selected == R.id.InchToFeet) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 0.083333333333;
                        edtText.setText(value1+" inch = "+""+value2+" feet");

                    }
                    if (selected == R.id.feetTocm) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 30.48;
                        edtText.setText(value1+" Feet = "+""+value2+" Cm");

                    }
                    if (selected == R.id.FeetToInch) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 12;
                        edtText.setText(value1+" feet = "+""+value2+" inch");

                    }
                }catch (Exception e){
                    Toast.makeText(Length.this, "Enter A Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
