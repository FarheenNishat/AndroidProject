package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final ArrayList<Product> values;

    public ProductAdapter(Context context1,  ArrayList<Product> list) {
        super(context1, R.layout.row_design);
        this.context = context1;
        values=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.row_design,parent,false);
        TextView tvproduct= (TextView)view.findViewById(R.id.tvname);
        TextView tvprice= (TextView)view.findViewById(R.id.tvprice);
        TextView tvdesc= (TextView)view.findViewById(R.id.tvDescription);
        ImageView ivprod= (ImageView)view.findViewById(R.id.ivprod);
        ImageView ivsale= (ImageView)view.findViewById(R.id.ivsale);

        tvproduct.setText(values.get(position).getTitle());
        tvprice.setText("R"+ values.get(position).getPrice());
        tvdesc.setText(values.get(position).getDescription());

        if(values.get(position).isSale()) {
            ivsale.setImageResource(R.mipmap.sale);
        }
        else {
            ivsale.setImageResource(R.mipmap.best_price);
        }

        if(values.get(position).getType().equals("Laptop")){
            ivprod.setImageResource(R.mipmap.laptop);}
        else if(values.get(position).getType().equals("Memory")){
            ivprod.setImageResource(R.mipmap.memory);}
        else if(values.get(position).getType().equals("Screen")){
            ivprod.setImageResource(R.mipmap.screen);}
        else{
            ivprod.setImageResource(R.mipmap.hdd);}




        return view;
    }
}
