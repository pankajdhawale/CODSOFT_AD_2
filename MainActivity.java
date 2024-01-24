package com.example.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private String operator = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        int[] numberButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int buttonId : numberButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appendNumber(((Button) v).getText().toString());
                }
            });
        }

        int[] operatorButtonIds = {R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide};
        for (int buttonId : operatorButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleOperator(((Button) v).getText().toString());
                }
            });
        }

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendDecimal();
            }
        });

        findViewById(R.id.btnEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastDigit();
            }
        });
    }

    private void appendNumber(String number) {
        currentInput.append(number);
        display.setText(currentInput.toString());
    }

    private void appendDecimal() {
        if (!currentInput.toString().contains(".")) {
            currentInput.append(".");
            display.setText(currentInput.toString());
        }
    }

    private void handleOperator(String op) {
        if (!currentInput.toString().isEmpty()) {
            operator = op;
            result = currentInput.toString();
            currentInput.setLength(0); // Clear current input for the next number
            display.setText(result + operator);
        }
    }

    private void calculate() {
        if (!operator.isEmpty()) {
            try {
                double num1 = Double.parseDouble(result);
                double num2 = Double.parseDouble(currentInput.toString());
                double sum = num1 + num2;
                display.setText(String.valueOf(sum));
                currentInput.setLength(0); // Clear current input
                result = "";
                operator = "";
            } catch (Exception e) {
                display.setText("Error");
            }
        }
    }

    private void clearDisplay() {
        currentInput.setLength(0);
        operator = "";
        result = "";
        display.setText("");
    }

    private void removeLastDigit() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            display.setText(currentInput.toString());
        }
    }
}
