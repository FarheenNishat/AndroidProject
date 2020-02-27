package com.example.mycardesc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;






public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    ArrayList<Car> list;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClick(int index);
    }


    public CarAdapter(Context c,ArrayList<Car> item) {
        activity=(ItemClicked)c;
        list=item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView icon;
        TextView tvmake,tvowner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            tvmake=itemView.findViewById(R.id.tvmake);
            tvowner=itemView.findViewById(R.id.tvowner);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClick(list.indexOf((Car)v.getTag()));
                }
            });

        }
    }
    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(list.get(position));
        holder.tvmake.setText(list.get(position).getModel());
        holder.tvowner.setText(list.get(position).getOwnername());
        if(list.get(position).getMake()=="Volkswagen")
        {
            holder.icon.setImageResource(R.drawable.volkswagen);
        }
        else if(list.get(position).getMake()=="Nissan")
        {
            holder.icon.setImageResource(R.drawable.nissan);
        }
        else
            holder.icon.setImageResource(R.drawable.mercedes);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
