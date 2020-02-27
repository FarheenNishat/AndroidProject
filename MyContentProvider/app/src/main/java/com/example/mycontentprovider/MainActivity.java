package com.example.mycontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnsubmit=(Button)findViewById(R.id.btnsubmit);
        tvshow=(TextView)findViewById(R.id.tvshow);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(ContactProvider.key_name,
                        ((EditText) findViewById(R.id.name)).getText().toString());

                values.put(ContactProvider.key_cell,
                        ((EditText) findViewById(R.id.phone)).getText().toString());

                Uri uri = getContentResolver().insert(
                        ContactProvider.CONTENT_URI, values);

                Toast.makeText(getBaseContext(),
                        uri.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public void onClickAddName(View view) {


    }


    public void onClickRetrieveStudents(View view) {

        String URL = "content://com.example.mycontentprovider.ContactProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "null");

        if (c.moveToFirst()) {
            do{
                /*Toast.makeText(this,
                        c.getString(c.getColumnIndex(ContactProvider.row_id)) +
                                ", " +  c.getString(c.getColumnIndex( ContactProvider.key_name)) +
                                ", " + c.getString(c.getColumnIndex( ContactProvider.key_cell)),
                        Toast.LENGTH_SHORT).show();*/
                tvshow.setText( c.getString(c.getColumnIndex(ContactProvider.row_id)) +
                        ", " +  c.getString(c.getColumnIndex( ContactProvider.key_name)) +
                        ", " + c.getString(c.getColumnIndex( ContactProvider.key_cell))+"\n");

            } while (c.moveToNext());
        }
    }
}
