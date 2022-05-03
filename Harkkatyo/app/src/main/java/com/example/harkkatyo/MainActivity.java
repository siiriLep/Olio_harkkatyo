package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button button;
    TheaterManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spinner = findViewById(R.id.theaterNames);
        button = findViewById(R.id.button);
        manager = TheaterManager.getInstance();

        manager.fetchTheaters();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, manager.getTheaterNames());
        spinner.setAdapter(adapter);

    }
    // load theater activity with the selected theater
    public void loadActivity (View v) {
        Intent intent = new Intent(MainActivity.this, TheaterActivity.class);
        intent.putExtra("object", manager.getTheater(spinner.getSelectedItemPosition()));
        startActivity(intent);

    }

    public void loadRatingsView(View v) {
        Intent intent = new Intent(MainActivity.this, AllRatingsActivity.class);
        startActivity(intent);
    }

}