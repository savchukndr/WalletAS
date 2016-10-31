package com.example.savch.walletas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    static private double controlSum = 0.0d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static boolean isAdigit(String userNameString){
        Pattern p = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
/*
    public boolean isAdigit(String str){
        for(char ch: str.toCharArray()){
            if (Character.isDigit(ch)) {
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
*/
    public void strCheck(TextView txt, String x, String y){
        double delAmount;
        double addAmount;
        if (!x.isEmpty() && y.isEmpty()) {
            if (!isAdigit(x)) {
                txt.setText("Неверный формат ввода числа!");
            } else {
                addAmount = Double.parseDouble(x);
                controlSum += addAmount;
                txt.setText(String.format( "%.2f", controlSum));
            }
        } else if(x.isEmpty() && !y.isEmpty()){
            if (!isAdigit(y)) {
                txt.setText("Неверный формат ввода числа!");
            } else {
                delAmount = Double.parseDouble(y);
                if (controlSum < delAmount){
                    txt.setText("Йоу персик, меньше уже нельзя =(");
                }else{
                    controlSum -= delAmount;
                    txt.setText(String.format( "%.2f", controlSum));
                }
            }
        } else if(!x.isEmpty() && !y.isEmpty()){
            if(!isAdigit(x) && !isAdigit(y)) {
                txt.setText(x  + " и " + y + " - Не является числом...");
            }else if(!isAdigit(y)){
                txt.setText(y + " - Не является числом...");
            }else if(!isAdigit(x)){
                txt.setText(x + " - Не является числом...");
            }else {
                txt.setText("Йоу персик, можнотолько одно поле в один момент");
            }
        } else {
            txt.setText(String.format( "%.2f", controlSum));
        }
    }

    public void onClick(View view) {
        String tmpAdd;
        String tmpDel;

        EditText addEditText = (EditText) findViewById(R.id.addEditText);
        EditText delEditText = (EditText) findViewById(R.id.delEditText);

        tmpAdd = addEditText.getText().toString();
        tmpDel = delEditText.getText().toString();
        TextView textViewInfo = (TextView) findViewById(R.id.amount_view);

        strCheck(textViewInfo, tmpAdd, tmpDel);
    }
}
