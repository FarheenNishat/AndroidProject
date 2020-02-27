package com.example.myrtlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn_ok,btn_ty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.disp);
        btn_ok=(Button)findViewById(R.id.btnok);
        btn_ty=(Button)findViewById(R.id.btn_tyou);


        String hello = getResources().getString(R.string.hello_world);
        String ok=getResources().getString(R.string.ok);
        String tt=getResources().getString(R.string.thanks);
        btn_ok.setText(R.string.ok);
        btn_ty.setText(R.string.thanks);


        tv.setText(R.string.hello_world);
    }
}
