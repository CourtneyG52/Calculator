package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String strToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.inputDisplay);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {

        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }

        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroButton(View view) {
        updateText(strToAdd = "0");
    }

    public void oneButton(View view) {
        updateText(strToAdd = "1");
    }

    public void twoButton(View view) {
        updateText(strToAdd = "2");
    }

    public void threeButton(View view) {
        updateText(strToAdd = "3");
    }

    public void fourButton(View view) {
        updateText(strToAdd = "4");
    }

    public void fiveButton(View view) {
        updateText(strToAdd = "5");
    }

    public void sixButton(View view) {
        updateText(strToAdd = "6");
    }

    public void sevenButton(View view) {
        updateText(strToAdd = "7");
    }

    public void eightButton(View view) {
        updateText(strToAdd = "8");
    }

    public void nineButton(View view) {
        updateText(strToAdd = "9");
    }

    public void clearButton(View view) {
        display.setText("");
    }

    public void exponentButton(View view) {
        updateText(strToAdd = "^");
    }

    public void parenthesisButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for(int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }

        if(openPar == closePar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");
        }

        else if(display.getText().toString().substring(textLen -1, textLen).equals("÷")) {
            updateText("(");
        }

        else if(display.getText().toString().substring(textLen - 1, textLen).equals("×")) {
            updateText("(");
        }

        else if(display.getText().toString().substring(textLen - 1, textLen).equals("+")) {
            updateText("(");
        }

        else if(display.getText().toString().substring(textLen - 1, textLen).equals("-")) {
            updateText("(");
        }

        else if(display.getText().toString().substring(textLen - 1, textLen).equals("^")) {
            updateText("(");
        }

        else if (closePar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");
        }

        display.setSelection(cursorPos + 1);
    }

    public void divideButton(View view) {
        updateText(strToAdd = "÷");
    }

    public void multiplyButton(View view) {
        updateText(strToAdd = "×");
    }

    public void subtractButton(View view) {
        updateText(strToAdd = "-");
    }

    public void additionButton(View view) {
        updateText(strToAdd = "+");
    }

    public void equalsButton(View view) {
        String userExpression = display.getText().toString();

        userExpression = userExpression.replaceAll("÷", "/");
        userExpression = userExpression.replaceAll("×", "*");

        Expression exp = new Expression(userExpression);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void decimalButton(View view) {
        updateText(strToAdd = ".");
    }

    public void backspaceButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos !=0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}