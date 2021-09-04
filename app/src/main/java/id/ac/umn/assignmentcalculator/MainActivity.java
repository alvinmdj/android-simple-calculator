package id.ac.umn.assignmentcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private String oldNumber = "";
    private String operator = "";
    private boolean isNewOperator = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        inputField.setShowSoftInputOnFocus(false);
    }

    private void updateNumber(String str) {
        if(isNewOperator) {
            inputField.setText(R.string.default_number);
        }
        isNewOperator = false;
        String oldStr = inputField.getText().toString();
        int cursorPosition = inputField.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPosition);
        String rightStr = oldStr.substring(cursorPosition);
        if (getString(R.string.default_number).equals(inputField.getText().toString())) {
            inputField.setText(str);
        } else {
            inputField.setText(String.format("%s%s%s", leftStr, str, rightStr));
        }
        inputField.setSelection(cursorPosition + 1);
    }

    public void operatorEvent (String str) {
        isNewOperator = true;
        oldNumber = inputField.getText().toString().replace(',', '.');
        operator = str;
    }

    public void calculate() {
        String newNumber = inputField.getText().toString();
        double result = 0.0;
        switch (operator) {
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        inputField.setText(String.valueOf(result));
        inputField.setSelection(inputField.getText().length());
    }

    public void btnZero(View view) {
        updateNumber("0");
    }

    public void btnOne(View view) {
        updateNumber("1");
    }

    public void btnTwo(View view) {
        updateNumber("2");
    }

    public void btnThree(View view) {
        updateNumber("3");
    }

    public void btnFour(View view) {
        updateNumber("4");
    }

    public void btnFive(View view) {
        updateNumber("5");
    }

    public void btnSix(View view) {
        updateNumber("6");
    }

    public void btnSeven(View view) {
        updateNumber("7");
    }

    public void btnEight(View view) {
        updateNumber("8");
    }

    public void btnNine(View view) {
        updateNumber("9");
    }

    public void btnCE(View view) {
        inputField.setText(R.string.default_number);
    }

    public void btnC(View view) {
        oldNumber = "0";
        inputField.setText(R.string.default_number);
    }

    public void btnBack(View view) {
        int cursorPosition = inputField.getSelectionStart();
        int textLen = inputField.getText().length();

        if(cursorPosition != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) inputField.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            inputField.setText(selection);
            inputField.setSelection(cursorPosition - 1);
            if(textLen == 1) {
                inputField.setText(R.string.default_number);
            }
        }
    }

    public void btnAdd(View view) {
        operatorEvent("+");
    }

    public void btnSubtractor(View view) {
        operatorEvent("-");
    }

    public void btnMultiplier(View view) {
        operatorEvent("*");
    }

    public void btnDivider(View view) {
        operatorEvent("/");
    }

    public void btnPlusMinus(View view) {
        String str = inputField.getText().toString();
        String minus = "-" + str;
        if(str.charAt(0) != '-') {
            inputField.setText(minus);
        } else {
            inputField.setText(str.substring(1));
        }
        inputField.setSelection(inputField.getText().length());
    }

    public void btnComma(View view) {
        if(!inputField.getText().toString().contains(",") && !inputField.getText().toString().contains(".")) {
            updateNumber(",");
        }
    }

    public void btnEqual(View view) {
        calculate();
    }
}