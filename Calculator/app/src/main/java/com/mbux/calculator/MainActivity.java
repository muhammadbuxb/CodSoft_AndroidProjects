package com.mbux.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button minus = findViewById(R.id.minus);
        Button plus = findViewById(R.id.plus);
        Button times = findViewById(R.id.times);
        Button div = findViewById(R.id.div);
        Button equal = findViewById(R.id.equal);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button point = findViewById(R.id.point);


        TextView screen = findViewById(R.id.screen);

        ac.setOnClickListener(view -> {
            firstNum = 0;
            screen.setText("0");
        });

        off.setOnClickListener(View->screen.setVisibility(View.GONE));
        on.setOnClickListener(View->{
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");

        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);


        for (Button b:nums){
            b.setOnClickListener(View->{
                if(!screen.getText().toString().equals("0")){
                    screen.setText(screen.getText().toString() + b.getText().toString());
                }else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> opers = new ArrayList<>();
        opers.add(div);
        opers.add(times);
        opers.add(minus);
        opers.add(plus);

        for (Button b: opers){
            b.setOnClickListener(View->{
                firstNum = Double.parseDouble(screen.getText().toString());
                operation = b.getText().toString();
                screen.setText("0");
            });
        }

        del.setOnClickListener(View->{
            String num = screen.getText().toString();
            if(num.length()>1){
                screen.setText(num.substring(0,num.length()-1));

            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            };

        });

        point.setOnClickListener(View-> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText (screen.getText().toString()+".");
            }
        });

        equal.setOnClickListener(view -> {
            double secondNum = Double.parseDouble (screen.getText().toString());
            double result;
            switch (operation) {
                case "/":
                    result = firstNum/secondNum;
                    break;
                case "X":
                    result = firstNum*secondNum;
                    break;
                case "+":
                    result = firstNum+secondNum;
                    break;
                case "-":
                    result = firstNum-secondNum;
                    break;
                default:
                    result = firstNum+secondNum;
            }
            screen.setText(String.valueOf(result));
            firstNum = result;
        });

    }
}