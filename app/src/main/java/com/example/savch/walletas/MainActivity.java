package com.example.savch.walletas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.view.Gravity;


public class MainActivity extends AppCompatActivity {
    static private double controlSum = 0.0d;
    static private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewInfo = (TextView) findViewById(R.id.amount_view);
        textViewInfo.setText(String.format( "%.2f", controlSum));
    }

    public void strCheck(TextView txt, String x, String y) {
        double delAmount;
        double addAmount;
        if (!x.isEmpty() && y.isEmpty()) {
            addAmount = Double.parseDouble(x);
            controlSum += addAmount;
            txt.setText(String.format( "%.2f", controlSum));
        }else if(x.isEmpty() && !y.isEmpty()){
            delAmount = Double.parseDouble(y);
            if (controlSum < delAmount){
                toast = Toast.makeText(getApplicationContext(), "Персик ты попытался удалить слишком много",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }else{
                controlSum -= delAmount;
                txt.setText(String.format( "%.2f", controlSum));
            }
        }else if(!x.isEmpty() && !y.isEmpty()){
            toast = Toast.makeText(getApplicationContext(), "Йоу персик, можнотолько одно поле в один момент",
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else{
            txt.setText(String.format( "%.2f", controlSum));
        }
    }

    public void onClick(View view) {
        TextView textViewInfo = (TextView) findViewById(R.id.amount_view);
        switch (view.getId()){
            case R.id.countButton:
                String tmpAdd;
                String tmpDel;

                EditText addEditText = (EditText) findViewById(R.id.addEditText);
                EditText delEditText = (EditText) findViewById(R.id.delEditText);

                tmpAdd = addEditText.getText().toString();
                tmpDel = delEditText.getText().toString();

                strCheck(textViewInfo, tmpAdd, tmpDel);
                addEditText.setText("");
                delEditText.setText("");
                break;
            case R.id.nullButton:
                controlSum = 0.0d;
                textViewInfo.setText(String.format( "%.2f", controlSum));
                break;
            case R.id.aboutButton:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
