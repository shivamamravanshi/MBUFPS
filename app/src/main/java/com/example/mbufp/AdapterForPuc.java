package com.example.mbufp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterForPuc extends RecyclerView.Adapter<AdapterForPuc.ViewHolder> {

    private List<ListOfPuc> listOfPucs;
    private Context context;

    public AdapterForPuc(List<ListOfPuc> puc_lists, Context context) {
        this.listOfPucs = puc_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_puc,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListOfPuc puclist = listOfPucs.get(i);
        viewHolder.puc_name.setText(puclist.getPuc_name());
        viewHolder.puc_description.setText(puclist.getPuc_description());
    }

    @Override
    public int getItemCount() {
        return listOfPucs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView puc_name;
        public TextView puc_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            puc_name = (TextView) itemView.findViewById(R.id.puc_name);
            puc_description = (TextView) itemView.findViewById(R.id.puc_description);
        }
    }
}
