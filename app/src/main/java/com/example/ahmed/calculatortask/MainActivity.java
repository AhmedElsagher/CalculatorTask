package com.example.ahmed.calculatortask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends Activity implements View.OnClickListener {
    private Stack<Double> numbers;
    private Stack<Character> operators;
    private double firstNum;
    private double secondNum;
    private double resultNum;
    private double ans = 0;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number0 = (Button) findViewById(R.id.zero);
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
        operators = new Stack<Character>();
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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.zero:
                resultTV.append("0");
                if (input.length() != 0) {
                    // what if u want to add 0+22
                    input.append("0");
                }
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

                stackNumInput = Double.parseDouble(input.toString());
                numbers.push(stackNumInput);
                input.delete(0, input.capacity());
                if (operators.size() > 0 && (operators.peek().equals('*') || operators.peek().equals('/')))
                    minmize();
                //u can't put * & / over +
                //meaning u cant execute them after + or minus
                //u have to have number can't press any opeartor before pressing number
                if (!numbers.empty()) {
                    operators.push('+');
                    resultTV.append("+");

                }
                break;
            case R.id.subOp:
                stackNumInput = Double.parseDouble(input.toString());
                numbers.push(stackNumInput);
                input.delete(0, input.capacity());
                if (operators.size() > 0 && (operators.peek().equals('*') || operators.peek().equals('/')))
                    minmize();
                if (!numbers.empty()) {
                    operators.push('-');
                    resultTV.append("-");
                }
                break;
            case R.id.mulOp:

                stackNumInput = Double.parseDouble(input.toString());
                numbers.push(stackNumInput);
                input.delete(0, input.capacity());
                if (operators.size() > 0 && (operators.peek().equals('*') || operators.peek().equals('/')))
                    minmize();
                if (!numbers.empty()) {
                    operators.push('*');
                    resultTV.append("*");
                }
                break;
            case R.id.divOp:
                stackNumInput = Double.parseDouble(input.toString());
                numbers.push(stackNumInput);
                input.delete(0, input.capacity());
                if (operators.size() > 0 && (operators.peek().equals('*') || operators.peek().equals('/')))
                    minmize();
                if (!numbers.empty()) {
                    operators.push('/');
                    resultTV.append("/");
                }
                break;
            case R.id.equal:
                stackNumInput = Double.parseDouble(input.toString());
                numbers.push(stackNumInput);
                calculate();
                resultTV.setText(String.valueOf(resultNum));
                break;
            case R.id.erase:
                String nowString = resultTV.getText().toString();
                if (nowString.length() > 0) {
                    String x = String.valueOf((nowString.charAt(nowString.length() - 1)));
                    if (Character.isDigit(x.charAt(0)))
                        //how to remove operators after numbers
                        input.deleteCharAt(input.length() - 1);
                    else {
                        //remove numbers after operator
                        operators.pop();
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
                operators.clear();
                resultTV.setText("");
                break;
            case R.id.dot:
                resultTV.append(".");
                input.append(".");
                break;

        }
    }

    private void minmize() {
        secondNum = numbers.pop();
        firstNum = numbers.pop();
        switch (operators.pop()) {
            case '*':

                resultNum = firstNum * secondNum;
                numbers.push(resultNum);
                break;
            case '/':
                resultNum = firstNum / secondNum;
                numbers.push(resultNum);
                break;

        }

    }

    private void calculate() {
        while (!operators.empty() && numbers.size() > 1) {//make with the second + or - make the first cal
            secondNum = numbers.pop();
            firstNum = numbers.pop();
            switch (operators.pop()) {

                case '+':
                    if (operators.size() > 0 && operators.peek().equals('-'))
                        resultNum = -(-firstNum + secondNum);
                    else
                        resultNum = firstNum + secondNum;
                    numbers.push(resultNum);
                    break;
                case '-':
                    if (operators.size() > 0 && operators.peek().equals('-'))
                        resultNum = (firstNum + secondNum);
                    else
                        resultNum = firstNum - secondNum;
                    numbers.push(resultNum);
                    break;
                case '*':

                    resultNum = firstNum * secondNum;
                    numbers.push(resultNum);
                    break;
                case '/':
                    resultNum = firstNum / secondNum;
                    numbers.push(resultNum);
                    break;
            }
        }
    }
}
