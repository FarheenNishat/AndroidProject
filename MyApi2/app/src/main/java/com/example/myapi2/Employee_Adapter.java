package com.example.myapi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Employee_Adapter extends RecyclerView.Adapter<Employee_Adapter.ViewHolder> {
    ArrayList<Employee2> employees;

    public Employee_Adapter(Context context, @NonNull ArrayList<Employee2> emp) {
        this.employees = emp;

    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nm,sal,age,id;
        public ViewHolder(@NonNull final View itemView) {

            super(itemView);

            id=itemView.findViewById(R.id.emp_id);
            nm=itemView.findViewById(R.id.emp_name);
            sal=itemView.findViewById(R.id.emp_salary);
            age=itemView.findViewById(R.id.emp_age);


        }
    }


    @Override
    public Employee_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Employee_Adapter.ViewHolder holder, int position) {


        holder.itemView.setTag(employees.get(position));
        holder.id.setText(employees.get(position).getId());
        holder.nm.setText(employees.get(position).getEmployee_name());
        holder.sal.setText(employees.get(position).getEmployee_salary());
        holder.age.setText(employees.get(position).getEmployee_age());


    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}


