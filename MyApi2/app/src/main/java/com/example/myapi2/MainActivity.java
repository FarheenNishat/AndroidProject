package com.example.myapi2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    final String URL_GET_DATA = "http://dummy.restapiexample.com/api/v1/employees";
    RecyclerView recyclerView;
    Employee_Adapter adapter;
    ArrayList<Employee2> empList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        empList = new ArrayList<Employee2>();

        getEmployees();
    }
    private void getEmployees() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        MyApi api = retrofit.create(MyApi.class);
        Call<Employee> call = api.getEmployees();

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
              ArrayList<Employee2> emp = response.body().getData();

               for(int i=0;i<emp.size();i++)
               {

                   String id=emp.get(i).getId();
                   String nm=emp.get(i).getEmployee_name();
                   String sal=emp.get(i).getEmployee_salary();
                   String age=emp.get(i).getEmployee_age();
                   Employee2 temp=new Employee2(id,nm,sal,age);
                   empList.add(temp);

               }

                adapter = new Employee_Adapter(getApplicationContext(),empList);



                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
