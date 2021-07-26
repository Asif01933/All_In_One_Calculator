package com.example.allinonecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.text.DecimalFormat;

public class Calculator extends AppCompatActivity {
     TextView calculation,answer;
     String sCalculation="",sAnswer="",number_one="",number_two="",current_operator="",prev_ans="";
     String RorD="RAD",sin_inv,cos_inv,tan_inv,function;
     Double Result=0.0,numberOne=0.0,numberTwo=0.0,temp=0.0;
     Boolean dot_present=false,number_allow=true,power_present=false,root_present = false,invert_allow=true;
     Boolean factorial_present=false,constant_present=false,function_present=false,value_inverted=false;
     NumberFormat format,longformat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculation=findViewById(R.id.calculation);
        calculation.setMovementMethod(new ScrollingMovementMethod());
        answer=findViewById(R.id.answer);
        format = new DecimalFormat("#.####");
        longformat = new DecimalFormat("0.#E0");
        sin_inv = String.valueOf(Html.fromHtml("sin<sup><small>-1</small></sup>"));
        cos_inv = String.valueOf(Html.fromHtml("cos<sup><small>-1</small></sup>"));
        tan_inv = String.valueOf(Html.fromHtml("tan<sup><small>-1</small></sup>"));
       final Button btn_RorD=findViewById(R.id.btn_RorD);
        btn_RorD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RorD = btn_RorD.getText().toString();
                RorD = RorD.equals("RAD") ? "DEG" : "RAD";
                btn_RorD.setText(RorD);
            }
        });

    }
    public void onClickNumber(View v){
        if (number_allow) {
            Button bn = (Button) v;
            sCalculation += bn.getText();
            number_one += bn.getText();
            numberOne = Double.parseDouble(number_one);
            if(function_present){
                calculateFunction(function);
                return;
            }
            if(root_present){
                numberOne=Math.sqrt(numberOne);
            }
            switch (current_operator) {
                case "":
                    if (power_present) {
                        temp = Result + Math.pow(numberTwo, numberOne);
                    } else {
                        temp = Result + numberOne;
                    }
                    break;

                case "+":
                    if (power_present) {
                        temp = Result + Math.pow(numberTwo, numberOne);
                    } else {
                        temp = Result + numberOne;
                    }
                    break;

                case "-":
                    if (power_present) {
                        temp = Result - Math.pow(numberTwo, numberOne);
                    } else {
                        temp = Result - numberOne;
                    }
                    break;

                case "x":
                    if (power_present) {
                        temp = Result * Math.pow(numberTwo, numberOne);
                    } else {
                        temp = Result * numberOne;
                    }
                    break;

                case "/":
                    try {

                        if (power_present) {
                            temp = Result / Math.pow(numberTwo, numberOne);
                        } else {
                            temp = Result / numberOne;
                        }
                    } catch (Exception e) {
                        sAnswer = e.getMessage();
                    }
                    break;

            }
            sAnswer = format.format(temp).toString();
            updateCalculation();
        }
    }
    public void onClickOperator(View v){
        Button ob=(Button)v;
        if (sAnswer!=""){

            if (current_operator!=""){
                char c=getcharfromlast(sCalculation, 2);
                if(c=='+' || c=='-' || c=='x' || c=='/'){
                    sCalculation=sCalculation.substring(0,sCalculation.length()-3);
                }
            }
            sCalculation+="\n"+ob.getText();
            number_one="";

            Result=temp;


            current_operator=ob.getText().toString();
            updateCalculation();
            number_two="";
            numberTwo=0.0;
            dot_present=false;
            number_allow=true;
            root_present=false;
            invert_allow=true;
            power_present=false;
            factorial_present=false;
            constant_present=false;
            function_present=false;
            value_inverted = false;
        }

    }
    private char getcharfromlast(String s,int i){
        char c=s.charAt(s.length()-i);
        return c;
    }
    public void onClickClear(View v){
       cleardata();
    }
    public void cleardata() {
        sCalculation = "";
        sAnswer = "";
        current_operator = "";
        number_one = "";
        number_two = "";
        prev_ans = "";
        Result = 0.0;
        numberOne = 0.0;
        numberTwo = 0.0;
        temp = 0.0;
        updateCalculation();
        dot_present = false;
        number_allow = true;
        root_present = false;
        invert_allow = true;
        power_present = false;
        factorial_present = false;
        function_present = false;
        constant_present = false;
        value_inverted = false;
    }
    public void updateCalculation(){
        calculation.setText(sCalculation);
        answer.setText(sAnswer);
    }
    public void onDotClick(View view){
        if(!dot_present){
           if(number_one.length()==0){
               number_one="0.";
               sCalculation += "0.";
               sAnswer = "0.";
               dot_present=true;
               updateCalculation();
           }else {
               number_one+=".";
               sCalculation+=".";
               sAnswer+=".";
               dot_present=true;
               updateCalculation();
           }
        }
    }
    public void onClickEqual(View view){
       showresult();
    }
    public void showresult(){
        if(sAnswer!="" && sAnswer!=prev_ans){
            sCalculation+="\n= "+sAnswer+"\n--------------\n"+sAnswer+" ";
            number_one="";
            number_two="";
            numberOne=0.0;
            numberTwo=0.0;
            Result=temp;

            prev_ans=sAnswer;
            updateCalculation();
            dot_present=true;
            power_present=false;
            number_allow=false;
            factorial_present=false;
            constant_present=false;
            function_present=false;
            value_inverted = false;
        }
    }
    public void onModuloClick(View view){
        if (sAnswer != "" && getcharfromlast(sCalculation, 1)!= ' '){
            sCalculation +="& ";
            switch (current_operator) {
                case "":
                    temp = temp / 100;
                    break;
                case "+":
                    temp = Result + ((Result * numberOne) / 100);
                    break;
                case "-":
                    temp = Result - ((Result * numberOne) / 100);
                    break;
                case "x":
                    temp = Result * (numberOne / 100);
                    break;
                case "/":
                    try {
                        temp = Result / (numberOne / 100);
                    } catch (Exception e) {
                        sAnswer = e.getMessage();
                    }
                    break;
            }
            sAnswer = format.format(temp).toString();
            if (sAnswer.length() > 9) {
                sAnswer = longformat.format(temp).toString();
            }
            Result=temp;
           showresult();
        }
    }
    public void onPorMClick(View view){
        if (invert_allow) {
            if (sAnswer != "" && getcharfromlast(sCalculation, 1) != ' ') {
                numberOne = numberOne * (-1);
                number_one = format.format(numberOne).toString();
                switch (current_operator) {
                    case "":
                        temp = numberOne;
                        sCalculation = number_one;
                        break;
                    case "+":
                        temp = Result + numberOne;

                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "-":
                        temp = Result - numberOne;

                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "x":
                        temp = Result * numberOne;

                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "/":
                        try {
                            temp = Result / numberOne;

                            removeuntilchar(sCalculation, ' ');
                            sCalculation += number_one;
                        } catch (Exception e) {
                            sAnswer = e.getMessage();
                        }
                        break;
                }
                sAnswer = format.format(temp).toString();
                updateCalculation();
            }
        }

    }
    public void  removeuntilchar(String str,char chr){
        char c = getcharfromlast(str, 1);
        if (c != chr) {
            //remove last char
            str = removechar(str, 1);
            sCalculation = str;
            updateCalculation();
            removeuntilchar(str, chr);
        }
    }
    public String removechar(String str, int i) {
        char c = str.charAt(str.length() - i);
        //we need to check if dot is removed or not
        if (c == '.' && !dot_present) {
            dot_present = false;
        }
        if (c == '^') {
            power_present = false;
        }
        if (c == ' ') {
            return str.substring(0, str.length() - (i - 1));
        }
        return str.substring(0, str.length() - i);
    }
    public void onRootClick(View view){
        Button root=(Button)view;
        if(sAnswer=="" && Result==0 && !root_present  && !function_present){
            sCalculation=root.getText().toString();
            root_present = true;
            invert_allow=false;
            updateCalculation();
        }else if(getcharfromlast(sCalculation, 1)==' ' && current_operator !="" && !root_present){
            sCalculation=root.getText().toString();
            root_present = true;
            invert_allow=false;
            updateCalculation();
        }
    }
    public void onPowerClick(View view){
        Button power=(Button) view;
        if (sCalculation!="" && !root_present && !power_present  && !function_present){
            if (getcharfromlast(sCalculation, 1)!=' '){
                sCalculation+=power.getText().toString();
                number_two=number_one;
                numberTwo=numberOne;
                number_one="";
                power_present=true;
                updateCalculation();
            }
        }
    }
    public void onSquareClick(View view){
        if (sCalculation != "" && sAnswer != "") {
            if (!root_present  && !power_present && getcharfromlast(sCalculation, 1) != ' ' && getcharfromlast(sCalculation, 1) != ' ') {
                numberOne = numberOne * numberOne;
                number_one = format.format(numberOne).toString();
                if (current_operator == "") {
                    if (number_one.length() > 9) {
                        number_one = longformat.format(numberOne);
                    }
                    sCalculation = number_one;
                    temp = numberOne;
                } else {
                    switch (current_operator) {
                        case "+":
                            temp = Result + numberOne;
                            break;
                        case "-":
                            temp = Result - numberOne;
                            break;
                        case "x":
                            temp = Result * numberOne;
                            break;
                        case "/":
                            try {
                                temp = Result / numberOne;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;
                    }
                    removeuntilchar(sCalculation, ' ');
                    if (number_one.length() > 9) {
                        number_one = longformat.format(numberOne);
                    }
                    sCalculation += number_one;
                }
                sAnswer = format.format(temp);
                if (sAnswer.length() > 9) {
                    sAnswer = longformat.format(temp);
                }
                updateCalculation();
            }
        }
    }
    public void onClickFactorial(View view){
        if (!sAnswer.equals("") && !factorial_present && !root_present && !dot_present && !power_present  && !function_present) {
            if (getcharfromlast(sCalculation, 1) != ' ') {
                for (int i = 1; i < Integer.parseInt(number_one); i++) {
                    numberOne *= i;
                }
                if (numberOne.equals(0.0)) {
                    numberOne = 1.0;
                }
                number_one = format.format(numberOne).toString();
                switch (current_operator) {
                    case "":
                        Result = numberOne;
                        break;
                    case "+":
                        Result += numberOne;
                        break;
                    case "-":
                        Result -= numberOne;
                        break;
                    case "x":
                        Result *= numberOne;
                        break;
                    case "/":
                        try {
                            Result /= numberOne;
                        } catch (Exception e) {
                            sAnswer = e.getMessage();
                        }

                        break;
                }
                sAnswer = Result.toString();
                temp = Result;
                sCalculation += "! ";
                factorial_present = true;
                number_allow = false;
                updateCalculation();
            }
        }
    }
    public void onClickInverse(View view){
        if (!sAnswer.equals("") && !factorial_present && !root_present && !dot_present && !power_present  && !function_present) {
            if (getcharfromlast(sCalculation, 1) != ' ') {
                numberOne = Math.pow(numberOne, -1);
                number_one = format.format(numberOne).toString();
                switch (current_operator) {
                    case "":
                        temp = numberOne;
                        sCalculation = number_one;
                        break;
                    case "+":
                        temp = Result + numberOne;
                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "-":
                        temp = Result - numberOne;
                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "x":
                        temp = Result * numberOne;
                        removeuntilchar(sCalculation, ' ');
                        sCalculation += number_one;
                        break;
                    case "/":
                        try {
                            temp = Result / numberOne;
                            removeuntilchar(sCalculation, ' ');
                            sCalculation += number_one;
                        } catch (Exception e) {
                            sAnswer = e.getMessage();
                        }

                        break;
                }
                sAnswer = format.format(temp).toString();
                updateCalculation();
            }
        }
    }
    public void onClickPiorE(View view){
        Button btn_PIorE = (Button) view;
        number_allow = false;
        if (!root_present && !dot_present && !power_present && !factorial_present && !constant_present && !function_present ) {
            String str_PIorE = btn_PIorE.getText().toString() + " ";
            if (!str_PIorE.equals("e ")) {
                str_PIorE = "\u03A0" + " ";
            }
            if (sCalculation == "") {
                number_one = str_PIorE;
                if (str_PIorE.equals("e ")) {
                    numberOne = Math.E;
                } else {
                    numberOne = Math.PI;
                }
                temp = numberOne;
            } else {
                if (str_PIorE.equals("e ")) {
                    //use ternary operation
                    numberOne =getcharfromlast(sCalculation, 1) == ' ' ? Math.E : Double.parseDouble(number_one) * Math.E;
                } else {
                    numberOne = getcharfromlast(sCalculation, 1) == ' ' ? Math.PI : Double.parseDouble(number_one) * Math.PI;
                }
                switch (current_operator) {

                    case "":
                        temp = Result + numberOne;
                        break;

                    case "+":
                        temp = Result + numberOne;
                        break;

                    case "-":
                        temp = Result - numberOne;
                        break;

                    case "x"://we use x instedof * so change it in another function if you not.
                        temp = Result * numberOne;
                        break;

                    case "/":
                        try {
                            temp = Result / numberOne;
                        } catch (Exception e) {
                            sAnswer = e.getMessage();
                        }
                        break;
                }
            }
            sCalculation += str_PIorE;
            sAnswer = format.format(temp).toString();
            updateCalculation();
            constant_present = true;
        }
    }
    public void onClickFunction(View view){
        Button func = (Button) view;
        function = func.getHint().toString();
        if (!function_present && !root_present && !power_present && !factorial_present && !dot_present) {
            calculateFunction(function);

        }
    }
    public void calculateFunction(String function) {
        function_present = true;
        if (current_operator != "" && getcharfromlast(sCalculation, 1) == ' ') {
            switch (function) {
                case "sin_inv":
                    sCalculation += sin_inv + "(";
                    break;
                case "cos_inv":
                    sCalculation += cos_inv + "(";
                    break;
                case "tan_inv":
                    sCalculation += tan_inv + "(";
                    break;
                default:
                    sCalculation += function + "(";
                    break;
            }
            updateCalculation();
        } else {
            switch (current_operator) {
                case "":
                    if (sCalculation.equals("")) {
                        switch (function) {
                            case "sin_inv":
                                sCalculation += sin_inv + "( ";
                                break;
                            case "cos_inv":
                                sCalculation += cos_inv + "( ";
                                break;
                            case "tan_inv":
                                sCalculation += tan_inv + "( ";
                                break;
                            default:
                                sCalculation += function + "( ";
                                break;
                        }
                    } else {
                        switch (function) {
                            case "log":
                                temp = Result + Math.log10(numberOne);
                                sCalculation = "log( " + number_one;
                                break;

                            case "ln":
                                temp = Result + Math.log(numberOne);
                                sCalculation = "ln( " + number_one;
                                break;

                            case "sin":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);

                                }
                                temp = Result + Math.sin(numberOne);
                                sCalculation = "sin( " + number_one;


                                break;

                            case "sin_inv":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result + Math.asin(numberOne);
                                sCalculation = sin_inv + "( " + number_one;
                                break;

                            case "cos":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result + Math.cos(numberOne);
                                sCalculation = "cos( " + number_one;
                                break;

                            case "cos_inv":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result + Math.acos(numberOne);
                                sCalculation = cos_inv + "( " + number_one;
                                break;

                            case "tan":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result + Math.tan(numberOne);
                                sCalculation = "tan( " + number_one;
                                break;

                            case "tan_inv":
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result + Math.atan(numberOne);
                                sCalculation = tan_inv + "( " + number_one;
                                break;
                        }
                    }
                    sAnswer = temp.toString();
                    updateCalculation();
                    break;

                case "+":
                    removeuntilchar(sCalculation, ' ');
                    switch (function) {
                        case "log":
                            temp = Result + Math.log10(numberOne);
                            sCalculation += "log(" + number_one;
                            break;

                        case "ln":
                            temp = Result + Math.log(numberOne);
                            sCalculation += "ln(" + number_one;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result+ Math.sin(numberOne);
                            sCalculation += "sin(" + number_one;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result + Math.asin(numberOne);
                            sCalculation += sin_inv + "(" + number_one;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result + Math.cos(numberOne);
                            sCalculation += "cos(" + number_one;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result + Math.acos(numberOne);
                            sCalculation += cos_inv + "(" + number_one;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result + Math.tan(numberOne);
                            sCalculation += "tan(" + number_one;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result + Math.atan(numberOne);
                            sCalculation += tan_inv + "(" + number_one;
                            break;
                    }
                    sAnswer = temp.toString();
                    updateCalculation();
                    break;

                case "-":
                    removeuntilchar(sCalculation, ' ');
                    switch (function) {
                        case "log":
                            temp = Result - Math.log10(numberOne);
                            sCalculation += "log(" + number_one;
                            break;

                        case "ln":
                            temp = Result - Math.log(numberOne);
                            sCalculation += "ln(" + number_one;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.sin(numberOne);
                            sCalculation += "sin(" + number_one;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.asin(numberOne);
                            sCalculation += sin_inv + "(" + number_one;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.cos(numberOne);
                            sCalculation += "cos(" + number_one;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.acos(numberOne);
                            sCalculation += cos_inv + "(" + number_one;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.tan(numberOne);
                            sCalculation += "tan(" + number_one;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result - Math.atan(numberOne);
                            sCalculation += tan_inv + "(" + number_one;
                            break;
                    }
                    sAnswer = temp.toString();
                    updateCalculation();
                    break;

                case "x":
                    removeuntilchar(sCalculation, ' ');
                    switch (function) {
                        case "log":
                            temp = Result * Math.log10(numberOne);
                            sCalculation += "log(" + number_one;
                            break;

                        case "ln":
                            temp = Result * Math.log(numberOne);
                            sCalculation += "ln(" + number_one;
                            break;

                        case "sin":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.sin(numberOne);
                            sCalculation += "sin(" + number_one;
                            break;

                        case "sin_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.asin(numberOne);
                            sCalculation += sin_inv + "(" + number_one;
                            break;

                        case "cos":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.cos(numberOne);
                            sCalculation += "cos(" + number_one;
                            break;

                        case "cos_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.acos(numberOne);
                            sCalculation += cos_inv + "(" + number_one;
                            break;

                        case "tan":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.tan(numberOne);
                            sCalculation += "tan(" + number_one;
                            break;

                        case "tan_inv":
                            if (RorD.equals("DEG")) {
                                numberOne = Math.toDegrees(numberOne);
                            }
                            temp = Result * Math.atan(numberOne);
                            sCalculation += tan_inv + "(" + number_one;
                            break;
                    }
                    sAnswer = temp.toString();
                    updateCalculation();
                    break;

                case "/":
                    removeuntilchar(sCalculation, ' ');
                    switch (function) {
                        case "log":
                            try {
                                temp = Result / Math.log10(numberOne);
                                sCalculation += "log(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "ln":
                            try {
                                temp = Result / Math.log(numberOne);
                                sCalculation += "ln(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "sin":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.sin(numberOne);
                                sCalculation += "sin(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "sin_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.asin(numberOne);
                                sCalculation += sin_inv + "(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "cos":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.cos(numberOne);
                                sCalculation += "cos(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "cos_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.acos(numberOne);
                                sCalculation += cos_inv + "(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "tan":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.tan(numberOne);
                                sCalculation += "tan(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;

                        case "tan_inv":
                            try {
                                if (RorD.equals("DEG")) {
                                    numberOne = Math.toDegrees(numberOne);
                                }
                                temp = Result / Math.atan(numberOne);
                                sCalculation += tan_inv + "(" + number_one;
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            break;
                    }
                    sAnswer = temp.toString();
                    updateCalculation();
                    break;
            }
        }
    }
    public void onClickDelete(View view) {
        if (function_present) {
            DeleteFunction();
            return;
        }
        if (root_present) {
            removeRoot();
            return;
        }
        if (power_present) {
            removePower();
            return;
        }
        if (sAnswer != "") {
            if (getcharfromlast(sCalculation, 1) != ' ') {
                if (number_one.length() < 2 && current_operator != "") {
                    number_one = "";
                    temp = Result;
                    sAnswer = format.format(Result).toString();
                    sCalculation = removechar(sCalculation, 1);
                    updateCalculation();
                } else {
                    switch (current_operator) {
                        case "":
                            if (value_inverted) {
                                sAnswer = sAnswer.substring(1, sAnswer.length());
                                sCalculation = sCalculation.substring(1, sAnswer.length());
                                updateCalculation();
                            }
                            if (sCalculation.length() < 2) {
                                cleardata();
                            } else {
                                if (getcharfromlast(sCalculation, 1) == '.') {
                                    dot_present = false;
                                }
                                number_one = removechar(number_one, 1);
                                numberOne = Double.parseDouble(number_one);
                                temp = numberOne;
                                sCalculation = number_one;
                                sAnswer = number_one;
                                updateCalculation();
                            }
                            break;

                        case "+":
                            if (value_inverted) {
                                numberOne = numberOne * (-1);
                                number_one = format.format(numberOne).toString();
                                temp = Result + numberOne;
                                sAnswer = format.format(temp).toString();
                                removeuntilchar(sCalculation, ' ');
                                sCalculation += number_one;
                                updateCalculation();
                                value_inverted = value_inverted ? false : true;
                            }
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            temp = Result + numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "-":
                            if (value_inverted) {
                                numberOne = numberOne * (-1);
                                number_one = format.format(numberOne).toString();
                                temp = Result - numberOne;
                                sAnswer = format.format(temp).toString();
                                removeuntilchar(sCalculation, ' ');
                                sCalculation += number_one;
                                updateCalculation();
                                value_inverted = value_inverted ? false : true;
                            }
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            temp = Result - numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "x":
                            if (value_inverted) {
                                numberOne = numberOne * (-1);
                                number_one = format.format(numberOne).toString();
                                temp = Result * numberOne;
                                sAnswer = format.format(temp).toString();
                                removeuntilchar(sCalculation, ' ');
                                sCalculation += number_one;
                                updateCalculation();
                                value_inverted = value_inverted ? false : true;
                            }
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            temp = Result * numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "/":
                            try {
                                if (value_inverted) {
                                    numberOne = numberOne * (-1);
                                    number_one = format.format(numberOne).toString();
                                    temp = Result / numberOne;
                                    sAnswer = format.format(temp).toString();
                                    removeuntilchar(sCalculation, ' ');
                                    sCalculation += number_one;
                                    updateCalculation();
                                    value_inverted = value_inverted ? false : true;
                                }
                                if (getcharfromlast(sCalculation, 1) == '.') {
                                    dot_present = false;
                                }
                                number_one = removechar(number_one, 1);
                                if (number_one.length() == 1 && number_one == ".") {
                                    numberOne = Double.parseDouble(number_one);
                                }
                                numberOne = Double.parseDouble(number_one);
                                temp = Result / numberOne;
                                sAnswer = format.format(temp).toString();
                                sCalculation = removechar(sCalculation, 1);
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            updateCalculation();
                            break;
                    }
                }
            }
        }
    }
    public void removePower() {
        if (sAnswer != "" && sCalculation != "") {
            switch (current_operator) {
                case "":
                    if (getcharfromlast(sCalculation, 1) == '^') {
                        sCalculation = removechar(sCalculation, 1);
                        number_one = number_two;
                        numberOne = Double.parseDouble(number_one);
                        number_two = "";
                        numberTwo = 0.0;
                        updateCalculation();
                    } else if (getcharfromlast(sCalculation, 2) == '^') {
                        number_one = "";
                        numberOne = 0.0;
                        temp = numberTwo;
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    } else {
                        if (getcharfromlast(sCalculation, 1) == '.') {
                            dot_present = false;
                        }
                        number_one = removechar(number_one, 1);
                        numberOne = Double.parseDouble(number_one);
                        temp = Math.pow(numberTwo, numberOne);
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    }
                    break;

                case "+":
                    if (getcharfromlast(sCalculation, 1) == '^') {
                        sCalculation = removechar(sCalculation, 1);
                        number_one = number_two;
                        numberOne = Double.parseDouble(number_one);
                        number_two = "";
                        numberTwo = 0.0;
                        updateCalculation();
                    } else if (getcharfromlast(sCalculation, 2) == '^') {
                        number_one = "";
                        numberOne = 0.0;
                        temp = Result + numberTwo;
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    } else {
                        if (getcharfromlast(sCalculation, 1) == '.') {
                            dot_present = false;
                        }
                        number_one = removechar(number_one, 1);
                        numberOne = Double.parseDouble(number_one);
                        temp = Result + Math.pow(numberTwo, numberOne);
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    }
                    break;

                case "-":
                    if (getcharfromlast(sCalculation, 1) == '^') {
                        sCalculation = removechar(sCalculation, 1);
                        number_one = number_two;
                        numberOne = Double.parseDouble(number_one);
                        number_two = "";
                        numberTwo = 0.0;
                        updateCalculation();
                    } else if (getcharfromlast(sCalculation, 2) == '^') {
                        number_one = "";
                        numberOne = 0.0;
                        temp = Result - numberTwo;
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    } else {
                        if (getcharfromlast(sCalculation, 1) == '.') {
                            dot_present = false;
                        }
                        number_one = removechar(number_one, 1);
                        numberOne = Double.parseDouble(number_one);
                        temp = Result - Math.pow(numberTwo, numberOne);
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    }
                    break;

                case "x":
                    if (getcharfromlast(sCalculation, 1) == '^') {
                        sCalculation = removechar(sCalculation, 1);
                        number_one = number_two;
                        numberOne = Double.parseDouble(number_one);
                        number_two = "";
                        numberTwo = 0.0;
                        updateCalculation();
                    } else if (getcharfromlast(sCalculation, 2) == '^') {
                        number_one = "";
                        numberOne = 0.0;
                        temp = Result * numberTwo;
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    } else {
                        if (getcharfromlast(sCalculation, 1) == '.') {
                            dot_present = false;
                        }
                        number_one = removechar(number_one, 1);
                        numberOne = Double.parseDouble(number_one);
                        temp = Result * Math.pow(numberTwo, numberOne);
                        sAnswer = format.format(temp).toString();
                        sCalculation = removechar(sCalculation, 1);
                        updateCalculation();
                    }
                    break;

                case "/":
                    try {
                        if (getcharfromlast(sCalculation, 1) == '^') {
                            sCalculation = removechar(sCalculation, 1);
                            number_one = number_two;
                            numberOne = Double.parseDouble(number_one);
                            number_two = "";
                            numberTwo = 0.0;
                            updateCalculation();
                        } else if (getcharfromlast(sCalculation, 2) == '^') {
                            number_one = "";
                            numberOne = 0.0;
                            temp = Result / numberTwo;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                        } else {
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            numberOne = Double.parseDouble(number_one);
                            temp = Result / Math.pow(numberTwo, numberOne);
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                        }
                    } catch (Exception e) {
                        sAnswer = e.getMessage();
                    }
                    updateCalculation();
                    break;
            }
        }
    }

    public void removeRoot() {
        if (getcharfromlast(sCalculation, 1) != ' ') {
            if (String.valueOf(getcharfromlast(sCalculation, 1)).equals("\u221A")) {
                sCalculation = removechar(sCalculation, 1);
                root_present = false;
                invert_allow = true;
                updateCalculation();
            }
            if (sAnswer != "") {
                if (number_one.length() < 2 && current_operator != "") {
                    number_one = "";
                    numberOne = Result;
                    temp = Result;
                    sAnswer = format.format(Result).toString();
                    sCalculation = removechar(sCalculation, 1);
                    updateCalculation();
                } else {
                    switch (current_operator) {
                        case "":
                            if (sCalculation.length() <= 2) {
                                cleardata();
                            } else {
                                if (getcharfromlast(sCalculation, 1) == '.') {
                                    dot_present = false;
                                }
                                number_one = removechar(number_one, 1);
                                numberOne = Double.parseDouble(number_one);
                                numberOne = Math.sqrt(numberOne);
                                temp = numberOne;
                                sAnswer = format.format(temp).toString();
                                sCalculation = "\u221A" + number_one;
                                updateCalculation();
                            }
                            break;

                        case "+":
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            numberOne = Math.sqrt(numberOne);
                            temp = Result + numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "-":
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            numberOne = Math.sqrt(numberOne);
                            temp = Result - numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "x":
                            if (getcharfromlast(sCalculation, 1) == '.') {
                                dot_present = false;
                            }
                            number_one = removechar(number_one, 1);
                            if (number_one.length() == 1 && number_one == ".") {
                                numberOne = Double.parseDouble(number_one);
                            }
                            numberOne = Double.parseDouble(number_one);
                            numberOne = Math.sqrt(numberOne);
                            temp = Result * numberOne;
                            sAnswer = format.format(temp).toString();
                            sCalculation = removechar(sCalculation, 1);
                            updateCalculation();
                            break;

                        case "/":
                            try {
                                if (getcharfromlast(sCalculation, 1) == '.') {
                                    dot_present = false;
                                }
                                number_one = removechar(number_one, 1);
                                if (number_one.length() == 1 && number_one == ".") {
                                    numberOne = Double.parseDouble(number_one);
                                }
                                numberOne = Double.parseDouble(number_one);
                                numberOne = Math.sqrt(numberOne);
                                temp = Result + numberOne;
                                sAnswer = format.format(temp).toString();
                                sCalculation = removechar(sCalculation, 1);
                            } catch (Exception e) {
                                sAnswer = e.getMessage();
                            }
                            updateCalculation();
                            break;
                    }
                }
            }
        }
    }

    public void DeleteFunction() {
        if (current_operator == "") {
            if (getcharfromlast(sCalculation, 1) == ' ') {
                cleardata();
            } else if (getcharfromlast(sCalculation, 2) == ' ') {
                cleardata();
            } else {
                sCalculation = removechar(sCalculation, 1);
                number_one = removechar(number_one, 1);
                numberOne = Double.parseDouble(number_one);
                calculateFunction(function);
            }
            updateCalculation();
        } else {
            if (getcharfromlast(sCalculation, 1) == '(') {
                removeuntilchar(sCalculation, ' ');
                function_present = false;
            } else if (getcharfromlast(sCalculation, 2) == '(') {
                sCalculation = removechar(sCalculation, 1);
                number_one = "";
                temp = Result;
                sAnswer = format.format(Result).toString();
            } else {
                sCalculation = removechar(sCalculation, 1);
                number_one = removechar(number_one, 1);
                numberOne = Double.parseDouble(number_one);
                calculateFunction(function);
            }
            updateCalculation();
        }
    }


}
