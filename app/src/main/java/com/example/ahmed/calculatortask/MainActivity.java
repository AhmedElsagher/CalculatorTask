package com.example.ahmed.calculatortask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

public class MainActivity extends Activity implements View.OnClickListener {
    private Stack<Double> numbers;
    private Stack<Character> operations;
    private double firstNum;
    private double secondNum;
    private double resultNum;
    private StringBuilder input = new StringBuilder();
    private Double stackNumInput;
    private Button number0;
    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;
    private Button number5;
    private Button number6;
    private Button number7;
    private Button number8;
    private Button number9;
    private Button dot;
    private Button addButton;
    private Button subButton;
    private Button mulButton;
    private Button divideButton;
    private Button equalButton;
    private Button erseButton;
    private Button clearButton;
    private TextView resultTV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number0 = (Button) findViewById(R.id.num0);
        number1 = (Button) findViewById(R.id.num1);
        number2 = (Button) findViewById(R.id.num2);
        number3 = (Button) findViewById(R.id.num3);
        number4 = (Button) findViewById(R.id.num4);
        number5 = (Button) findViewById(R.id.num5);
        number6 = (Button) findViewById(R.id.num6);
        number7 = (Button) findViewById(R.id.num7);
        number8 = (Button) findViewById(R.id.num8);
        number9 = (Button) findViewById(R.id.num9);
        addButton = (Button) findViewById(R.id.addOp);
        subButton = (Button) findViewById(R.id.subOp);
        mulButton = (Button) findViewById(R.id.mulOp);
        divideButton = (Button) findViewById(R.id.divOp);
        equalButton = (Button) findViewById(R.id.equal);
        erseButton = (Button) findViewById(R.id.erase);
        clearButton = (Button) findViewById(R.id.clear);
        resultTV = (TextView) findViewById(R.id.textID);
        numbers = new Stack<Double>();
        operations = new Stack<Character>();
        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        erseButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);
    }

    //handle events
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.num0:
                resultTV.append("0");
                input.append("0");
                break;
            case R.id.num1:
                input.append("1");
                resultTV.append("1");
                break;
            case R.id.num2:
                input.append("2");
                resultTV.append("2");
                break;
            case R.id.num3:
                resultTV.append("3");
                input.append("3");
                break;
            case R.id.num4:
                resultTV.append("4");
                input.append("4");
                break;
            case R.id.num5:
                resultTV.append("5");
                input.append("5");
                break;
            case R.id.num6:
                resultTV.append("6");
                input.append("6");
                break;
            case R.id.num7:
                resultTV.append("7");
                input.append("7");
                break;
            case R.id.num8:
                resultTV.append("8");
                input.append("8");
                break;
            case R.id.num9:
                resultTV.append("9");
                input.append("9");
                break;
            case R.id.addOp:
                try {
                    checkForInput(input);
                    minmize();
                    operations.push('+');
                    resultTV.append("+");


                } catch (DoubleOperationException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                } catch (NoOperatorException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                }


                break;
            case R.id.subOp:
                try {
                    checkForInput(input);
                    minmize();
                    operations.push('-');
                    resultTV.append("-");

                } catch (DoubleOperationException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                } catch (NoOperatorException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                }

                break;
            case R.id.mulOp:
                try {
                    checkForInput(input);
                    minmize();
                    operations.push('*');
                    resultTV.append("*");

                } catch (DoubleOperationException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                } catch (NoOperatorException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                }

                break;
            case R.id.divOp:
                try {
                    checkForInput(input);
                    minmize();
                    operations.push('/');
                    resultTV.append("/");

                } catch (DoubleOperationException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                } catch (NoOperatorException e) {
                    onClick(clearButton);
                    resultTV.setText(e.getMessage());
                }

                break;
            case R.id.equal:
                //handle click equal without having two operations and operation
                if (numbers.size() > 0 && operations.size() > 0) {
                    stackNumInput = Double.parseDouble(input.toString());
                    numbers.push(stackNumInput);
                    minmize();
                    calculate();
                    resultTV.setText(String.valueOf(resultNum));
                } else
                    resultTV.setText(String.valueOf("u have to enter operaters and operation"));
                break;
            case R.id.erase:
                //TODO:difficult process hndled badly try toexecute it better
                String nowString = resultTV.getText().toString();
                if (nowString.length() > 0) {
                    String x = String.valueOf((nowString.charAt(nowString.length() - 1)));
                    if (Character.isDigit(x.charAt(0)))
                        //how to remove operations after numbers
                        input.deleteCharAt(input.length() - 1);
                    else if (operations.size() > 0 && numbers.size() > 0) {
                        //remove numbers after operator

                        operations.pop();
                        input = new StringBuilder(String.valueOf(numbers.pop()));
                    }
                    String newText = resultTV.getText().toString().substring(0, resultTV.getText().toString().length() - 1);
                    resultTV.setText(newText);
                }
                break;
            case R.id.clear:
                input.delete(0, input.capacity());
                resultNum = 0;
                numbers.clear();
                operations.clear();
                resultTV.setText("");
                break;
            case R.id.dot:
                resultTV.append(".");
                input.append(".");
                break;

        }
    }

    //check the input if it's two operations after another
    //or u entered single operation without entering number before
    private void checkForInput(StringBuilder input)
            throws DoubleOperationException, NoOperatorException {
        if (input.length() != 0) {

            stackNumInput = Double.parseDouble(input.toString());
            numbers.push(stackNumInput);
            input.delete(0, input.capacity());
        } else {
            if (numbers.size() > 0) {
                throw new DoubleOperationException();
            } else
                throw new NoOperatorException();

            //TODO:throw Exception of no operations
            //TODO: throw exception here for 2 operators
        }


    }

    //elminate any *&&/ operation between +&- operation
    private void minmize() {
        if (operations.size() > 0 && (operations.peek().equals('*') || operations.peek().equals('/'))) {
            secondNum = numbers.pop();
            firstNum = numbers.pop();
            switch (operations.pop()) {
                case '*':

                    resultNum = firstNum * secondNum;
                    break;
                case '/':
                    resultNum = firstNum / secondNum;
                    break;

            }
            numbers.push(resultNum);
        }
    }

    //calculate any +|- operation
    private void calculate() {
        while (!operations.empty() && numbers.size() > 1) {
            secondNum = numbers.pop();
            firstNum = numbers.pop();
            switch (operations.pop()) {

                case '+':
                    if (operations.size() > 0 && operations.peek().equals('-'))
                        resultNum = -(-firstNum + secondNum);
                    else
                        resultNum = firstNum + secondNum;
                    break;
                case '-':
                    if (operations.size() > 0 && operations.peek().equals('-'))
                        resultNum = (firstNum + secondNum);
                    else
                        resultNum = firstNum - secondNum;

                    break;

            }
            numbers.push(resultNum);
        }
    }
}
