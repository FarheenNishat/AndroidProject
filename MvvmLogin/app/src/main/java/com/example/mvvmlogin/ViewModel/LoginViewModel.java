package com.example.mvvmlogin.ViewModel;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmlogin.Model.LoginUser;
import com.example.mvvmlogin.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    private MutableLiveData<List<LoginUser>> userMutableLiveData;
    private UserRepository newUserObject;

    /*public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }*/
    public void init() {
        if (userMutableLiveData != null) {
            return;
        }
        newUserObject = UserRepository.getInstance();
        userMutableLiveData = newUserObject.getNicePlaces();
    }


    public void addNewValue(final LoginUser newuser) {
        /*mIsUpdating.setValue(true);


        List<LoginUser> currentPlaces = userMutableLiveData.getValue();
        currentPlaces.add(newuser);
        userMutableLiveData.postValue(currentPlaces);
        mIsUpdating.postValue(false);*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("getting data fake");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<LoginUser> users = new ArrayList<LoginUser>();
                users.add(newuser);
                userMutableLiveData.postValue(users);
            }
        }).start();

    }


    public LiveData<List<LoginUser>> getNicePlaces() {
        return userMutableLiveData;
    }


    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }
}
