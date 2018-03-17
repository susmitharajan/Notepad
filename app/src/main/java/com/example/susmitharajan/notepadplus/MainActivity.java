package com.example.susmitharajan.notepadplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String preferences = "pref1";
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    public static String saveIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewActivity.class);
                i.putExtra("key", arrayList.size());
                startActivity(i);
            }
        });
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        ListView listView = (ListView)findViewById(R.id.listview);
        Map<String,?> keys = sharedPreferences.getAll();
        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());
            arrayList.add("key: "+String.valueOf(entry.getKey())+"value: "+entry.getValue().toString());
        }
        if(arrayList != null){
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(),editActivity.class);
                    intent.putExtra("key",i);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
        ListView listView = (ListView) findViewById(R.id.listview);
        adapter.clear();
        adapter.notifyDataSetChanged();
        Map<String, ?> keys = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " +
                    entry.getValue().toString());
            arrayList.add("key: " + String.valueOf(entry.getKey()) + "value: " + entry.getValue().toString());
        }
        if (arrayList != null) {
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, arrayList);
            listView.setAdapter(adapter);
        }
    }
}
