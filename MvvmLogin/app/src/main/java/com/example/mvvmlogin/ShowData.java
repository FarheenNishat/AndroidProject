package com.example.mvvmlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mvvmlogin.Model.LoginUser;
import com.example.mvvmlogin.ViewModel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {
    TextView usr,pwd;
    LoginViewModel getviewdata;
    private MutableLiveData<List<LoginUser>> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        usr=(TextView)findViewById(R.id.showusr);
        pwd=(TextView)findViewById(R.id.showpwd);
        getviewdata= ViewModelProviders.of(this).get(LoginViewModel.class);
        userData= (MutableLiveData<List<LoginUser>>) getviewdata.getNicePlaces();
        ArrayList<LoginUser> users= (ArrayList<LoginUser>) userData.getValue();
        usr.setText(users.get(0).getStrEmailAddress());
        pwd.setText(users.get(0).getStrPassword());



    }
}
