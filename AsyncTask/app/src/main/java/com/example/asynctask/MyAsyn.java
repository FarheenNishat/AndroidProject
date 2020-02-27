package com.example.asynctask;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class MyAsyn extends AsyncTask<String,String, Bitmap> {

    public ProgressDialog p;
    Context context;
    private URL ImageUrl;
    Bitmap bmImg = null;
    ImageView imageView= null;
    InputStream is = null;

    public MyAsyn(Context c) {
        context=c;
        p = new ProgressDialog(context);
        imageView=(ImageView)((Activity)context).findViewById(R.id.myimage);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            ImageUrl = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            bmImg = BitmapFactory.decodeStream(is, null, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmImg;
        
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        p.setMessage("Please wait...It is downloading");
        p.setIndeterminate(false);
        p.setCancelable(false);
        p.show();
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (imageView != null) {
            p.hide();
            imageView.setImageBitmap(bitmap);
        } else {
            p.show();
        }
        System.out.println("onPostExecute");
        Intent intent = new Intent("com.example.asynctask.SOME_ACTION");
        context.sendBroadcast(intent);








    }
}
