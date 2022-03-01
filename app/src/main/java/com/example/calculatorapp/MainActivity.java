package com.example.calculatorapp;

import static java.lang.String.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Constant values
    private static final String EMPTY_STRING = "";
    private static final String DEFAULT_ZERO_VALUES = "0";
    private static final String VALIDATE_INPUT = "Validate the entered data";
    private static final String ZERO_DIVISION = "It is not possible to divide by 0";

    // Private [EditText] type view properties
    private EditText txtResult;
    private EditText txtNumber1;
    private EditText txtNumber2;

    // Integer global values to inputs [txtNumber1] and [txtNumber2]
    private int number1;
    private int number2;


    /**
     * OnCreate method (Main execute)
     *
     * @param savedInstanceState Bundle saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set view controls to global variables

        txtResult = findViewById(R.id.txtResult);
        txtNumber1 = findViewById(R.id.txtNumber1);
        txtNumber2 = findViewById(R.id.txtNumber2);

        // Set view labels messages with strings.xml resource file
        TextView lblNumber1 = findViewById(R.id.lblNumber1);
        lblNumber1.setText(getString(R.string.lbl_number_one));
        TextView lblNumber2 = findViewById(R.id.lblNumber2);
        lblNumber2.setText(getString(R.string.lbl_number_two));
        TextView lblResult = findViewById(R.id.lblResult);
        lblResult.setText(getString(R.string.lbl_result));

        // View types controls
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        ImageButton btnSubtract = findViewById(R.id.btnSubtract);
        ImageButton btnMultiply = findViewById(R.id.btnMultiply);
        ImageButton btnDivide = findViewById(R.id.btnDivide);
        ImageButton btnErase = findViewById(R.id.btnErase);

        // Set listener on add button click
        btnAdd.setOnClickListener(view -> {
            boolean isValid = validate(txtNumber1.getText().toString(), txtNumber2.getText().toString(), basicOperations.add);
            if (isValid)
                txtResult.setText(format("%s%s", add(number1, number2), EMPTY_STRING));
        });

        // Set listener on subtract button click
        btnSubtract.setOnClickListener(view -> {
            boolean isValid = validate(txtNumber1.getText().toString(), txtNumber2.getText().toString(), basicOperations.subtract);
            if (isValid)
                txtResult.setText(format("%s%s", subtract(number1, number2), EMPTY_STRING));
        });

        // Set listener on multiply button click
        btnMultiply.setOnClickListener(view -> {
            boolean isValid = validate(txtNumber1.getText().toString(), txtNumber2.getText().toString(), basicOperations.multiply);
            if (isValid)
                txtResult.setText(format("%s%s", multiply(number1, number2), EMPTY_STRING));
        });

        // Set listener on divide button click
        btnDivide.setOnClickListener(view -> {
            boolean isValid = validate(txtNumber1.getText().toString(), txtNumber2.getText().toString(), basicOperations.divide);
            if (isValid)
                txtResult.setText(format("%s%s", divide(number1, number2), EMPTY_STRING));
        });

        // Set listener on clear button click
        btnErase.setOnClickListener(view -> clearInputs());
    }

    /**
     * Method to add two integers (+)
     *
     * @param number1 integer data number one
     * @param number2 integer data number two
     * @return Returns an integer value as the result of the sum of two integers
     */
    private int add(int number1, int number2) {
        return number1 + number2;
    }

    /**
     * Method to subtract two integers (-)
     *
     * @param number1 integer data number one
     * @param number2 integer data number two
     * @return Returns an integer value as the result of the subtraction of two integers
     */
    private int subtract(int number1, int number2) {
        return number1 - number2;
    }

    /**
     * Method to multiply two integers (*)
     *
     * @param number1 integer data number one
     * @param number2 integer data number two
     * @return Returns an integer value as the result of the multiplication of two integers
     */
    private int multiply(int number1, int number2) {
        return number1 * number2;
    }

    /**
     * Method to divide two integers (÷)
     *
     * @param number1 integer data number one
     * @param number2 integer data number two
     * @return Returns a float value as the result of dividing two integers
     */
    private float divide(int number1, int number2) {
        return (float) number1 / number2;
    }

    /**
     * Method to validate data entered in input fields and zero division
     *
     * @param number1   String data number one
     * @param number2   String data number two
     * @param operation Enumerator operation Type (+,-,*,÷)
     * @return Boolean value determine if input data is valid
     */
    private boolean validate(String number1, String number2, basicOperations operation) {
        try {
            this.number1 = Integer.parseInt(number1);
            this.number2 = Integer.parseInt(number2);
            if (operation.equals(basicOperations.divide) && this.number2 == 0) {
                txtResult.setText(ZERO_DIVISION);
                return false;
            }
            return true;
        } catch (Exception ex) {
            txtResult.setText(VALIDATE_INPUT);
            return false;
        }
    }

    /**
     * Method to clear view input fields
     */
    private void clearInputs() {
        txtResult.setText(EMPTY_STRING);
        txtNumber1.setText(DEFAULT_ZERO_VALUES);
        txtNumber2.setText(DEFAULT_ZERO_VALUES);
    }

    /**
     * Enumerator for basic arithmetic operations
     */
    private enum basicOperations {
        add,
        subtract,
        multiply,
        divide
    }
}