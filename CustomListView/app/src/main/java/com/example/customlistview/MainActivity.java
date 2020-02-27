package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listproducts;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        products=new ArrayList<Product>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listproducts=(ListView)findViewById(R.id.listProd);

        Product prod1=new Product("HP laptop 13C6","4 GB RAM,1TB Harddisk,Robust,good quality","Laptop",36500,true);
        Product prod2=new Product("DELL laptop 13C6","8 GB RAM,2TB Harddisk WITH Graphics Card,Robust,good quality","Laptop",56500,false);
        Product prod3=new Product("Sandisk Memory","32 GB,Robust,secure","Memory",1500,true);
        Product prod4=new Product("HDD Screen 76","High Definition,Scratch Proof,256 Colors","Screen",16500,false);
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);
        products.add(prod4);
        ProductAdapter myadapter=new ProductAdapter(this,products);
        listproducts.setAdapter(myadapter);


    }
}
