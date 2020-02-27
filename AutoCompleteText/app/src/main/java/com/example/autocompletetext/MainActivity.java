package com.example.autocompletetext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView fname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname=findViewById(R.id.firstName);
      fname=(AutoCompleteTextView)findViewById(R.id.firstName);
      String[] names={"james","jinny","jennifer","john","jack","johnny","jagjeet"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.customview,names);
        fname.setThreshold(1);
        fname.setAdapter(adapter);

    }
}
