package com.ghores.calculater;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txt_history;
    TextView txt_result;
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_plus;
    Button btn_minus;
    Button btn_multiply;
    Button btn_division;
    Button btn_sign;
    Button btn_clear;
    Button btn_clearEntity;
    Button btn_back;
    Button btn_dot;
    Button btn_equal;

    boolean mustReset = false;
    float currentResult = 0;
    String operand = "";

    public void toggleSign() {
        String resultValue = txt_result.getText().toString();
        if (resultValue.contains("-")) {
            resultValue = resultValue.replace("-", "");
        } else {
            resultValue = "-" + resultValue;
        }

        txt_result.setText(resultValue);
    }

    public void compute(String nextOperand) {
        String resultValue = txt_result.getText().toString();
        float resultNumber = Float.parseFloat(resultValue);

        if (operand.equals("+")) {
            currentResult += resultNumber;
        } else if (operand.equals("-")) {
            currentResult -= resultNumber;
        } else if (operand.equals("*")) {
            currentResult *= resultNumber;
        } else if (operand.equals("/")) {
            currentResult /= resultNumber;
        } else if (operand.equals("")) {
            currentResult = resultNumber;
        }

        String oldHistory = txt_history.getText().toString();
        txt_history.setText(oldHistory + " " + resultNumber + " " + nextOperand);
        txt_result.setText("" + currentResult);
        operand = nextOperand;
        mustReset = true;
    }

    public void processEqual() {
        compute("");
        txt_history.setText("");
    }

    public void doOperand(String nextOperand) {
        compute(nextOperand);
    }

    public void backOneLetter() {
        String value = txt_result.getText().toString();
        String newValue = value.substring(0, value.length() - 1);
        txt_result.setText(newValue);
    }

    public void addPoint() {
        if (mustReset) {
            txt_result.setText("0");
            mustReset = false;
        }

        String oldValue = txt_result.getText().toString();
        if (oldValue.contains(".")) {
            return;
        }

        if (oldValue.length() > 9) {
            return;
        }

        txt_result.setText(oldValue + ".");
    }

    public void appendNumber(int num) {
        if (mustReset) {
            txt_result.setText("");
            mustReset = false;
        }

        String oldValue = txt_result.getText().toString();

        if (oldValue.length() > 9) {
            return;
        }

        if (oldValue.equals("0")) {
            if (num == 0) {
                return;
            } else {
                oldValue = "";
            }
        }

        txt_result.setText(oldValue + num);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_history = findViewById(R.id.txt_history);
        txt_result = findViewById(R.id.txt_result);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_division = findViewById(R.id.btn_division);
        btn_sign = findViewById(R.id.btn_sign);
        btn_clear = findViewById(R.id.btn_clear);
        btn_clearEntity = findViewById(R.id.btn_clearEntity);
        btn_back = findViewById(R.id.btn_back);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_result.setText("0");
                txt_history.setText("");
                currentResult = 0;
                mustReset = false;
            }
        });

        btn_clearEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_result.setText("0");
            }
        });

        View.OnClickListener numbersListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = Integer.parseInt(view.getTag().toString());
                appendNumber(index);
            }
        };

        btn_0.setOnClickListener(numbersListener);
        btn_1.setOnClickListener(numbersListener);
        btn_2.setOnClickListener(numbersListener);
        btn_3.setOnClickListener(numbersListener);
        btn_4.setOnClickListener(numbersListener);
        btn_5.setOnClickListener(numbersListener);
        btn_6.setOnClickListener(numbersListener);
        btn_7.setOnClickListener(numbersListener);
        btn_8.setOnClickListener(numbersListener);
        btn_9.setOnClickListener(numbersListener);

        View.OnClickListener operandListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOperand(view.getTag().toString());
            }
        };

        btn_plus.setOnClickListener(operandListener);
        btn_minus.setOnClickListener(operandListener);
        btn_multiply.setOnClickListener(operandListener);
        btn_division.setOnClickListener(operandListener);

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processEqual();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backOneLetter();
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPoint();
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSign();
            }
        });
    }
}
