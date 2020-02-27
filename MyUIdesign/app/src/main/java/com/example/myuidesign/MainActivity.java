package com.example.myuidesign;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.myuidesign.R.id.password2;

public class MainActivity extends AppCompatActivity  {
    TextInputLayout tuser;
    TextInputLayout tpass;
    TextInputEditText tname;
    TextInputEditText tpasswrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tuser = (TextInputLayout) findViewById(R.id.user);
        tpass = (TextInputLayout) findViewById(R.id.password);
        tname = (TextInputEditText) findViewById(R.id.name2);
        tpasswrd = (TextInputEditText) findViewById(password2);
        tname.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    tuser.setErrorEnabled(false);


            }
        });


    }





    public void btnSubmit(View v) {
        if (tname.getText().toString().equals("")) {
            tuser.setErrorEnabled(true);
            tuser.setError("Please Enter Username");

        } else if (tname.getText().toString().matches(".*\\d.*")) {
            tuser.setErrorEnabled(true);
            tuser.setError("Username must contain only Characters");


        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("You have successfully Submitted, ThankYou").setTitle("Registered");
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    //tuser.setError(null);
                   // Toast.makeText(MainActivity.this, "focus", Toast.LENGTH_SHORT).show();
                    showToast(tname.getText().toString(), tpasswrd.getText().toString());

                }
            });
            dialog.show();
        }
    }

   /* @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId()==R.id.user && hasFocus) {
            tuser.setError(null);
            Toast.makeText(MainActivity.this, "focus", Toast.LENGTH_SHORT).show();
        }}*/




    public void showToast(String msg,String pwd)
    {
        View toastview=getLayoutInflater().inflate(R.layout.customtoast,(ViewGroup)findViewById(R.id.linlay));
        TextView tvname=(TextView)toastview.findViewById(R.id.tvuser);
        TextView tvpass=(TextView)toastview.findViewById(R.id.tvpass);
        tvname.setText(msg);
        tvpass.setText(pwd);
        Toast mytoast=new Toast(MainActivity.this);
        mytoast.setDuration(Toast.LENGTH_LONG);
        mytoast.setView(toastview);
        mytoast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
        mytoast.show();
    }
}
