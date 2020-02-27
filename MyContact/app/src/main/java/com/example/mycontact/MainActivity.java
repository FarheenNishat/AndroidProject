package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {
   EditText etname,etphone;
   TextView tvname,tvphone;
   Button btn;
   FragmentManager fragmgr;
   ListFragment listfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname=findViewById(R.id.etname);
        etphone=findViewById(R.id.etphone);
        tvname=findViewById(R.id.tvname);
        tvphone=findViewById(R.id.tvphone);
        btn=findViewById(R.id.btnAdd);
        fragmgr=this.getSupportFragmentManager();
        listfrag=(ListFragment) fragmgr.findFragmentById(R.id.listfrag);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().isEmpty()|| etphone.getText().toString().isEmpty())
                  Toast.makeText(MainActivity.this,"please eneter all feilds",Toast.LENGTH_SHORT).show();
                else
                {
                    ApplicationClass.people.add(new Person(etname.getText().toString().trim(),etphone.getText().toString().trim()));
                    etname.setText(null);
                    etphone.setText(null);
                    listfrag.notify_custom();
                    View toastview=getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.linlay));
                    TextView tvtoast=(TextView)toastview.findViewById(R.id.mytoast);
                    tvtoast.setText("Successfully Added");
                    Toast mytoast=new Toast(MainActivity.this);
                    mytoast.setDuration(Toast.LENGTH_LONG);
                    mytoast.setView(toastview);
                    mytoast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
                    mytoast.show();

                }

            }
        });
    }

    @Override
    public void onItemClick(int index) {
        tvname.setText(ApplicationClass.people.get(index).getName());
        tvphone.setText(ApplicationClass.people.get(index).getTelno());

    }
}
