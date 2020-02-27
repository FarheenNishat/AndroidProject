package com.example.mycardesc;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Car> cars;

    @Override
    public void onCreate() {
        super.onCreate();
        cars=new ArrayList<Car>();
        cars.add(new Car("Volkswagen","Polo","FarheenNishat","789867564"));
        cars.add(new Car("Nissan","ED200","Charu Gupta","9090897867"));
        cars.add(new Car("Mercedse","E180","Shahida Khatoon","987867564"));
        cars.add(new Car("Volkswagen","Polo","FarheenNishat","789867564"));
        cars.add(new Car("Nissan","ED200","Charu Gupta","9090897867"));
        cars.add(new Car("Mercedse","E180","Shahida Khatoon","987867564"));
        cars.add(new Car("Volkswagen","Polo","FarheenNishat","789867564"));
        cars.add(new Car("Nissan","ED200","Charu Gupta","9090897867"));
        cars.add(new Car("Mercedse","E180","Shahida Khatoon","987867564"));



    }
}
