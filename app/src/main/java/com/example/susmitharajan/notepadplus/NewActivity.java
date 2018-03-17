package com.example.susmitharajan.notepadplus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by susmitharajan on 17/03/18.
 */

public class NewActivity extends Activity {
    SharedPreferences sf;
    EditText name;
    public static final String preferences = "pref1";
    public static String saveIt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity);
        int num = getIntent().getIntExtra("key", 0);
        saveIt = String.valueOf(num);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        TextView date = (TextView) findViewById(R.id.date);
        date.setText(formattedDate);
        name = (EditText) findViewById(R.id.edit_story);
        sf = getSharedPreferences(preferences, Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        String store = name.getText().toString();
        if(!store.isEmpty()){
            SharedPreferences.Editor editor = sf.edit();
            editor.putString(saveIt, store);
            editor.commit();
        }
        finish();
    }
}
