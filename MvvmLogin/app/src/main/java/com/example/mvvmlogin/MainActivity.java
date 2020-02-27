package com.example.mvvmlogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvmlogin.Model.LoginUser;
import com.example.mvvmlogin.ViewModel.LoginViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel myviewModel;
    EditText usr,pass;
    Button btn;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myviewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        myviewModel.init();
        usr=(EditText)findViewById(R.id.edusr);
        pass=(EditText)findViewById(R.id.edpass);
        btn=(Button)findViewById(R.id.login);
        show=(TextView)findViewById(R.id.showdata);
        myviewModel.getNicePlaces().observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(@Nullable List<LoginUser> nicePlaces) {
                show.setText(nicePlaces.get(0).getStrEmailAddress()+nicePlaces.get(0).getStrPassword());

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=usr.getText().toString();
                String pwd=pass.getText().toString();
                LoginUser newuser=new LoginUser(user,pwd);
                myviewModel.addNewValue(newuser);
               //  myintent=new Intent(MainActivity.this,ShowData.class);
               // startActivity(myintent);

            }
        });


    }
}
