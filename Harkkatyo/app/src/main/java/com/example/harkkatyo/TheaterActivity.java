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


        updateMovieList(date);
        updateDate(date);
    }


    private void updateDate(LocalDate date){
        String strDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        currentDateText.setText(strDate);
    }


    public void oneDayForward(View v){
        date = date.plusDays(1);
        updateDate(date);
        updateMovieList(date);
    }

    public void oneDayBack(View v){
        date = date.minusDays(1);
        updateDate(date);
        updateMovieList(date);
    }

    private void updateMovieList(LocalDate date){
        theater.fetchMovies(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), null, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theater.getMovieNames());
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}