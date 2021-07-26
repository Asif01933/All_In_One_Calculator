package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Weight extends AppCompatActivity {
    EditText edtText;
    RadioGroup Group;
    RadioButton gm2kg,kg2gm,kg2tn,tn2kg,kg2pn,pn2kg;
    Button submitbtn,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        edtText = findViewById(R.id.editText);
        Group = findViewById(R.id.radioGroup);
        gm2kg = findViewById(R.id.gmTokg);
        kg2gm = findViewById(R.id.kgTogm);
        kg2tn = findViewById(R.id.kgToton);
        tn2kg = findViewById(R.id.tonTokg);
        kg2pn = findViewById(R.id.kgTopound);
        pn2kg = findViewById(R.id.poundTokg);

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
                    if (selected == R.id.gmTokg) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 / 1000;
                        edtText.setText(value1+" gram = "+""+value2+" Kilogram");

                    }
                    if (selected == R.id.kgTogm) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1000;
                        edtText.setText(value1+" Kilogram = "+""+value2+" Gram");

                    }
                    if (selected == R.id.kgToton) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 0.001;
                        edtText.setText(value1+" Kg = "+""+value2+" Metric Ton");

                    }
                    if (selected == R.id.tonTokg) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1000;
                        edtText.setText(value1+" Metric Ton = "+""+value2+" kg");

                    }
                    if (selected == R.id.poundTokg) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 0.453592;
                        edtText.setText(value1+" pound = "+""+value2+" kg");

                    }
                    if (selected == R.id.kgTopound) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 2.204624;
                        edtText.setText(value1+" kg = "+""+value2+" pound");

                    }

                }catch (Exception e){
                    Toast.makeText(Weight.this, "Enter A Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
