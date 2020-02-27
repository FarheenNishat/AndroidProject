package com.example.myimagedownload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static final String PROGRESS_UPDATE = "progress_update";
    ImageView myimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimage=(ImageView)findViewById(R.id.myimage);
    }

    public void onClick(View v)
    {
        String url="https://www.tutorialspoint.com/images/tp-logo-diamond.png";
        Intent myintent=new Intent(this,ServiceDownload.class);
        myintent.putExtra(ServiceDownload.Extra_message,getResources().getString(R.string.button_response));
        myintent.putExtra("url",url);
        myintent.putExtra("filename","myimagenn.jpg");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }


        startService(myintent);
       registerReceiver();
    }
    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PROGRESS_UPDATE);
        bManager.registerReceiver(mBroadcastReceiver, intentFilter);

    }
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(PROGRESS_UPDATE)) {
                boolean downloadComplete = intent.getBooleanExtra("downloadComplete", false);
                //Log.d("API123", download.getProgress() + " current progress");

                if (downloadComplete) {


                    Toast.makeText(getApplicationContext(), "File download completed", Toast.LENGTH_SHORT).show();

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "myimage.jpg");

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    myimage.setBackgroundColor(getColor(R.color.colorPrimary));
            }*/
                    try {
                        Picasso.get().load(file).fit().into(myimage);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }


            }
        }
    };
}
