package com.example.mbufp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Puc_list extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListOfPuc> listOfPucs;
    Button ViewMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puc_list);
        ViewMap = findViewById(R.id.view_on_map);
        ViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Puc_list.this,com.example.mbufp.Toll_tax.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listOfPucs = new ArrayList<>();
            ListOfPuc listOfPuc = new ListOfPuc("Madhya Pradesh Pollution Control Board","E-5, Link Rd Number 3, Ekant Park, Arera Colony, Bhopal, Madhya Pradesh 462016");
            listOfPucs.add(listOfPuc);
            ListOfPuc listOfPuc4 = new ListOfPuc("PUC Pollution Testing Centre","Near Vishal Mega Mart, Indira Press Complex, Zone-I, Maharana Pratap Nagar, Bhopal, Madhya Pradesh 462011");
            listOfPucs.add(listOfPuc4);
            ListOfPuc listOfPuc3 = new ListOfPuc("MAHAKAAL PUC CENTRE","LINK ROAD NO. 2 NEAR DURGA PETROL PUMP SHIVAJI NAGAR, Shivaji Nagar, Bhopal, Madhya Pradesh 462016");
            listOfPucs.add(listOfPuc3);
            ListOfPuc listOfPuc1 = new ListOfPuc("SUVIDHA POLLUTION TESTING CENTER","Opposite Subhash School, RBI Colony, Char Imli, Bhopal, Madhya Pradesh 462016");
            listOfPucs.add(listOfPuc1);
            ListOfPuc listOfPuc2 = new ListOfPuc("Maa Krapa PUC Center","Bhopal, Madhya Pradesh 462010");
            listOfPucs.add(listOfPuc2);


        /*for (int i =0;i<10;i++)
        {
            ListOfPuc listOfPuc = new ListOfPuc(
                    "PUC Pollution Testing Centre",
                    "Near Vishal Mega Mart, Indira Press Complex, Zone-I, Maharana Pratap Nagar, Bhopal, Madhya Pradesh 462011"
            );
            listOfPucs.add(listOfPuc);
        }*/

        adapter = new AdapterForPuc(listOfPucs,this);
        recyclerView.setAdapter(adapter);

    }
}
