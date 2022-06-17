package com.example.lesson08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnDivide;
    private Button btnMultiply;
    private Button btnEqual;
    private Button btnClear;
    private Button btnOk;

    private TextView txtViewResult;
    private TextView txtViewLine;

    private final StringBuilder line = new StringBuilder();
    private final StringBuilder number = new StringBuilder();

    private int num1 = 0;
    private int num2 = 0;
    private String operand;
    private boolean error;


    public void findViewById() {
        btnZero = findViewById(R.id.btnZero);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);

        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        btnOk = findViewById(R.id.btnOk);

        txtViewResult = findViewById(R.id.txtResult);
        txtViewLine = findViewById(R.id.txtLine);
    }

    public void setOnClickListener(View.OnClickListener allButton) {
        btnZero.setOnClickListener(allButton);
        btnOne.setOnClickListener(allButton);
        btnTwo.setOnClickListener(allButton);
        btnThree.setOnClickListener(allButton);
        btnFour.setOnClickListener(allButton);
        btnFive.setOnClickListener(allButton);
        btnSix.setOnClickListener(allButton);
        btnSeven.setOnClickListener(allButton);
        btnEight.setOnClickListener(allButton);
        btnNine.setOnClickListener(allButton);

        btnPlus.setOnClickListener(allButton);
        btnMinus.setOnClickListener(allButton);
        btnDivide.setOnClickListener(allButton);
        btnMultiply.setOnClickListener(allButton);

        btnEqual.setOnClickListener(allButton);
        btnClear.setOnClickListener(allButton);
        btnOk.setOnClickListener(allButton);
    }

    public void selectedButton(Button selectedButton) {
        number.append(selectedButton.getText().toString().trim());
        txtViewResult.setText(number);

        line.append(selectedButton.getText().toString().trim());
    }

    public void workWithOperand(String selectedOperand, Button selectedButton) {
        if (num1 == 0) {
            num1 = Integer.parseInt(number.toString());
            operand = selectedOperand;
        } else if (num2 == 0) {
            try {
                num2 = Integer.parseInt(number.toString());
            } catch (Exception e) {
                return;
            }

            switch (operand) {
                case "+":
                    num1 = num1 + num2;
                    break;
                case "-":
                    num1 = num1 - num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        error = true;
                    } else {
                        num1 = num1 / num2;
                    }
                    break;
                case "*":
                    num1 = num1 * num2;
                    break;
            }

            operand = selectedOperand;
            num2 = 0;
        }

        if (selectedOperand.equals("=")) {
            txtViewResult.setText(error ? "ERROR" : String.valueOf(num1));
            number.setLength(0);
            num1 = 0;

        } else {
            //txtViewResult.setText("");
            number.setLength(0);

            line.append(selectedButton.getText().toString().trim());
        }
        txtViewLine.setText(line);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();

        Intent goToInfo = new Intent(this, InfoActivity.class);

        View.OnClickListener allButton = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnZero:
                        selectedButton(btnZero);
                        break;

                    case R.id.btnOne:
                        selectedButton(btnOne);
                        break;

                    case R.id.btnTwo:
                        selectedButton(btnTwo);
                        break;

                    case R.id.btnThree:
                        selectedButton(btnThree);
                        break;

                    case R.id.btnFour:
                        selectedButton(btnFour);
                        break;

                    case R.id.btnFive:
                        selectedButton(btnFive);
                        break;

                    case R.id.btnSix:
                        selectedButton(btnSix);
                        break;

                    case R.id.btnSeven:
                        selectedButton(btnSeven);
                        break;

                    case R.id.btnEight:
                        selectedButton(btnEight);
                        break;

                    case R.id.btnNine:
                        selectedButton(btnNine);
                        break;
                    //////////////////////////////////////
                    case R.id.btnPlus:
                        workWithOperand("+", btnPlus);
                        break;

                    case R.id.btnMinus:
                        workWithOperand("-", btnMinus);
                        break;

                    case R.id.btnDivide:
                        workWithOperand("/", btnDivide);
                        break;

                    case R.id.btnMultiply:
                        workWithOperand("*", btnMultiply);
                        break;

                    case R.id.btnClear:
                        txtViewLine.setText("");
                        txtViewResult.setText("0");
                        line.setLength(0);
                        number.setLength(0);
                        break;
                    case R.id.btnOk:
                        startActivity(goToInfo);
                        System.out.println("OK");
                        break;

                    case R.id.btnEqual:
                        workWithOperand("=", btnEqual);
                        break;
                }
            }
        };

        setOnClickListener(allButton);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        btnZero = null;
        btnOne = null;
        btnTwo = null;
        btnThree = null;
        btnFour = null;
        btnFive = null;
        btnSix = null;
        btnSeven = null;
        btnEight = null;
        btnNine = null;
        btnPlus = null;
        btnMinus = null;
        btnDivide = null;
        btnMultiply = null;
        btnEqual = null;
        btnClear = null;
        btnOk = null;

        txtViewResult = null;
        txtViewLine = null;
    }
}