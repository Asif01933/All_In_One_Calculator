package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Area extends AppCompatActivity {
    EditText edtText;
    RadioGroup Group;
    RadioButton m2f,f2m,k2m,m2k,h2a,a2h;
    Button submitbtn,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        edtText = findViewById(R.id.editText);
        Group = findViewById(R.id.radioGroup);
        m2f = findViewById(R.id.SquareMeterToSquareFeet);
        f2m = findViewById(R.id.SquareFeetToSquareMeter);
        k2m = findViewById(R.id.SquareKilometerToSquareMeter);
        m2k = findViewById(R.id.SquareMeterToSquareKilometer);
        h2a = findViewById(R.id.HectareToAcre);
        a2h = findViewById(R.id.AcreToHectare);

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
                    if (selected == R.id.SquareFeetToSquareMeter) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 / 10.763910417;
                        edtText.setText(value1+" sqr ft = "+""+value2+" sqr m");

                    }
                    if (selected == R.id.SquareMeterToSquareFeet) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 10.763910417;
                        edtText.setText(value1+" sqr m = "+""+value2+" sqr ft");

                    }
                    if (selected == R.id.SquareMeterToSquareKilometer) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 / 1000000;
                        edtText.setText(value1+" sqr m = "+""+value2+"sqr km");

                    }
                    if (selected == R.id.SquareKilometerToSquareMeter) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1000000;
                        edtText.setText(value1+" sqr Km = "+""+value2+" sqr m");

                    }
                    if (selected == R.id.HectareToAcre) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 2.4710538147;
                        edtText.setText(value1+" Hectare = "+""+value2+" Acre");

                    }
                    if (selected == R.id.AcreToHectare) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 / 2.4710538147;
                        edtText.setText(value1+" Acre = "+""+value2+" Hectre");

                    }

                }catch (Exception e){
                    Toast.makeText(Area.this, "Enter A Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
