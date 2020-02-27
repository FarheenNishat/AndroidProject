package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Mybroadcast conn2;
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    BroadcastReceiver br;
    IntentFilter myfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.download);
        imageView = findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyn asyncTask = new MyAsyn(MainActivity.this);
                asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
            }

        });
        conn2=new Mybroadcast(this);
        myfilter=new IntentFilter("com.example.asynctask.SOME_ACTION");
        registerReceiver(conn2, myfilter);



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(conn2);
    }




}
class Mybroadcast extends BroadcastReceiver{
    Context context;
    public Mybroadcast(Context c) {
        context=c;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("com.example.asynctask.SOME_ACTION"))

            Toast.makeText(context,"Download Completed",Toast.LENGTH_SHORT).show();

    }
}



