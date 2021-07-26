package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Volume extends AppCompatActivity {
    EditText edtText;
    RadioGroup Group;
    RadioButton ml2l,l2ml;
    Button submitbtn,cancelbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        edtText = findViewById(R.id.editText);
        Group = findViewById(R.id.radioGroup);
        ml2l = findViewById(R.id.mlTol);
        l2ml = findViewById(R.id.lToml);


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
                    if (selected == R.id.mlTol) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 / 1000;
                        edtText.setText(value1+" Milliliter = "+""+value2+" liter");

                    }
                    if (selected == R.id.lToml) {
                        String value1 = edtText.getText().toString();
                        Double value2 = Double.parseDouble(value1);
                        value2 = value2 * 1000;
                        edtText.setText(value1+" liter = "+""+value2+" milliliter");

                    }

                }catch (Exception e){
                    Toast.makeText(Volume.this, "Enter A Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
