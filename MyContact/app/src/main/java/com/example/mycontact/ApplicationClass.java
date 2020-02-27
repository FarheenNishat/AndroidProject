package com.example.mycontact;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static ArrayList<Person> people;
    @Override
    public void onCreate() {
        super.onCreate();
        people=new ArrayList<>();
        people.add(new Person("Farheen","890978657"));
        people.add(new Person("Rehan","890978657"));
        people.add(new Person("shaheen","998767657"));
        people.add(new Person("Farheen","890978657"));
        people.add(new Person("Rehan","890978657"));
        people.add(new Person("shaheen","998767657"));
    }
}
