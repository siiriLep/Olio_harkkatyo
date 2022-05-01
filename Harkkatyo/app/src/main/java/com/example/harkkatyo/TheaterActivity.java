package com.example.harkkatyo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class TheaterActivity extends AppCompatActivity {

    Theater theater;
    ListView list;
    LocalDate date;
    TextView currentDateText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);


        theater = (Theater) getIntent().getSerializableExtra("object");
        list = findViewById(R.id.list);
        date = LocalDate.now();
        currentDateText = findViewById(R.id.textView2);


        theater.fetchMovies(date.format(DateTimeFormatter.ofPattern("ddMMyyyy")), null, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theater.getMovieNames());
        list.setAdapter(adapter);
        updateDate(date);
    }


    private void updateDate(LocalDate date){
        String strDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        currentDateText.setText(strDate);
    }


    public void oneDayForward(View v){
        date.plusDays(1);
        updateDate(date);
    }

    public void oneDayBack(View v){
        date.minusDays(1);
        updateDate(date);
    }
}