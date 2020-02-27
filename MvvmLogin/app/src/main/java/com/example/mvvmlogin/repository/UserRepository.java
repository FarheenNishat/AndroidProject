package com.example.mvvmlogin.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmlogin.Model.LoginUser;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    private static UserRepository instance;
    private ArrayList<LoginUser> dataSet = new ArrayList<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public MutableLiveData<List<LoginUser>> getNicePlaces() {
        setNicePlaces();
        MutableLiveData<List<LoginUser>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces() {
        dataSet.add(
                new LoginUser("Farheen",
                        "123345")
        );

    }
}
