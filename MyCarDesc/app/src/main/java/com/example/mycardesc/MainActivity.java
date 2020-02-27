package com.example.mycardesc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CarAdapter.ItemClicked {
    ImageView caricon;
    TextView tvmodel,tvname,tvtel;
    Button btncar,btnowner;
    FragmentManager fragmentManager;
    Fragment listfrag,btnfrag,carfrag,ownerfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncar=findViewById(R.id.btncarinfo);
        btnowner=findViewById(R.id.btnowner);
         caricon=findViewById(R.id.iconcar);
         tvmodel=findViewById(R.id.tvmodel);
         tvname=findViewById(R.id.tvname);
         tvtel=findViewById(R.id.tvtel);
         fragmentManager=getSupportFragmentManager();
         listfrag=fragmentManager.findFragmentById(R.id.listfrag);
         btnfrag=fragmentManager.findFragmentById(R.id.ButtonFrag);
         carfrag=fragmentManager.findFragmentById(R.id.carfrag4);
         ownerfrag=fragmentManager.findFragmentById(R.id.ownerfrag);
         fragmentManager.beginTransaction().show(listfrag).show(btnfrag).show(carfrag).hide(ownerfrag).commit();
       btncar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               fragmentManager.beginTransaction().show(carfrag).hide(ownerfrag).commit();
           }
       });
       btnowner.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               fragmentManager.beginTransaction().show(ownerfrag).hide(carfrag).commit();

           }
       });

    }

    @Override
    public void onItemClick(int index) {
        tvname.setText(MyApplication.cars.get(index).getOwnername());
        tvmodel.setText(MyApplication.cars.get(index).getModel());
        tvtel.setText(MyApplication.cars.get(index).getOwnertel());
        if(MyApplication.cars.get(index).getMake()=="Volkswagen")
        {
           caricon.setImageResource(R.drawable.volkswagen);
        }
        else if(MyApplication.cars.get(index).getMake()=="Nissan")
        {
            caricon.setImageResource(R.drawable.nissan);
        }
        else
            caricon.setImageResource(R.drawable.mercedes);

    }
}
