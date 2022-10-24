package com.example.cis183_hw3_piazza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etfn;
    EditText etln;
    EditText etun;
    EditText etpass;
    EditText etemail;
    EditText etage;
    ListView lvelist;

    Button btnadd;

    ArrayList<Employ> eList;
    ArrayList<String> names;

    ArrayAdapter<String> adapter;

    DatabaseHelper dbHelp;
    Intent updateIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etfn = findViewById(R.id.et_fn);
        etln = findViewById(R.id.et_ln);
        etun = findViewById(R.id.et_un);
        etpass = findViewById(R.id.et_pass);
        etemail = findViewById(R.id.et_email);
        etage = findViewById(R.id.et_age);
        lvelist = findViewById(R.id.lv_elist);
        btnadd = findViewById(R.id.btn_add);

        dbHelp = new DatabaseHelper(this);

        dbHelp.initializeDB();

        Log.d("Number of Records:", dbHelp.numberOfRowsInTable()+"");

        fillArrayList();

        copyNamestoList();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        lvelist.setAdapter(adapter);

        EmpFiredEvent();

        updateIntent = new Intent(MainActivity.this,ActUpdate.class);

        EmpChangeEvent();
        addNewEmployeeBtnEvent();

    }

    public void EmpFiredEvent(){
        lvelist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dbHelp.EmpFired(eList.get(i).getUname());
                eList.remove(i);
                names.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void EmpChangeEvent(){
        lvelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                updateIntent.putExtra("Employ", eList.get(i));
                startActivity(updateIntent);
            }
        });
    }

    public void fillArrayList(){
        eList = new ArrayList<Employ>();
        eList = dbHelp.getAllRows();
    }

    public void displayAllEmploy(){
        for (int i=0; i<eList.size();i++){
            Log.d("===", eList.get(i).getUname()+", "+eList.get(i).getFname()+", "+ eList.get(i).getLname());
        }
    }

    public void addNewEmploy(Employ u){
        dbHelp.addNewEmploy(u);
    }

    public void copyNamestoList(){
        names=new ArrayList<String>();
        for (int i=0; i<eList.size();i++){
            names.add(eList.get(i).getUname());
        }
    }
    public void addNewEmployeeBtnEvent(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f=etfn.getText().toString();
                String l=etln.getText().toString();
                String u=etun.getText().toString();
                String p=etpass.getText().toString();
                String e=etemail.getText().toString();
                String a=etage.getText().toString();

                Employ employ = new Employ(u, f, l, p, e, a);

                addNewEmploy(employ);

                eList.add(employ);

                names.add(u);

                adapter.notifyDataSetChanged();

                etfn.setText("");
                etln.setText("");
                etun.setText("");
                etpass.setText("");
                etemail.setText("");
                etage.setText("");

                displayAllEmploy();
            }
        });
    }
}