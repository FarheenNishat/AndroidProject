package com.example.myapi2;

import java.util.ArrayList;
import java.util.HashMap;

public class Employee {
    private ArrayList<Employee2> data;
    private String status;

    public Employee(ArrayList<Employee2> data, String status) {
        this.data = data;
        this.status = status;
    }

    public ArrayList<Employee2> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
