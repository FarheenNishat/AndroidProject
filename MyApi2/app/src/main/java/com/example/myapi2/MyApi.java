package com.example.myapi2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {
    String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    @GET("employees")//marvel is the API name
    Call<Employee> getEmployees();


}
