package com.example.mbufp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterForTollList extends RecyclerView.Adapter<AdapterForTollList.ViewHolder> {

    List<ListOfToll> listOfTolls;
    private Context context;

    public AdapterForTollList(List<ListOfToll> listOfTolls, Context context) {
        this.listOfTolls = listOfTolls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.toll_list,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListOfToll listOfToll = listOfTolls.get(i);
        viewHolder.tollname.setText(listOfToll.getNameoftoll());
        viewHolder.tollprice.setText(listOfToll.getPriceoftoll());
    }

    @Override
    public int getItemCount() {
        return listOfTolls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tollname;
        public  TextView tollprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tollname = itemView.findViewById(R.id.toll_name);
            tollprice = itemView.findViewById(R.id.toll_price);

        }
    }
}
