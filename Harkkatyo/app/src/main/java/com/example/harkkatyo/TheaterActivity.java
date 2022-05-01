package com.example.harkkatyo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

public class TheaterActivity extends AppCompatActivity {

    Theater theater;
    ListView list;
    LocalDate date;
    TextView currentDateText;
    LocalTime filterStart, filterEnd;
    Button timePickerStart, timePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);


        theater = (Theater) getIntent().getSerializableExtra("object");
        list = findViewById(R.id.list);
        date = LocalDate.now();
        currentDateText = findViewById(R.id.textView2);
        timePickerStart = findViewById(R.id.StartTimePicker);
        timePickerEnd = findViewById(R.id.EndTimePicker);

        filterStart = LocalTime.MIN;
        filterEnd = LocalTime.MAX;

        updateMovieList(date, filterStart, filterEnd);
        updateDate(date);
    }


    private void updateDate(LocalDate date){
        String strDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        currentDateText.setText(strDate);
    }


    public void oneDayForward(View v){
        date = date.plusDays(1);
        updateDate(date);
        updateMovieList(date, filterStart, filterEnd);
    }

    public void oneDayBack(View v){
        date = date.minusDays(1);
        updateDate(date);
        updateMovieList(date, filterStart, filterEnd);
    }

    private void updateMovieList(LocalDate date, LocalTime filterTimePeriodStart, LocalTime filterTimePeriodEnd){
        theater.fetchMovies(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), filterTimePeriodStart, filterTimePeriodEnd);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theater.getMovieNames());
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void openStartTimePicker(View V){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)).isBefore(filterEnd)) {
                    filterStart = LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    timePickerStart.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    updateMovieList(date, filterStart, filterEnd);
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,00,00,true);
        timePickerDialog.setTitle("Select start time");
        timePickerDialog.show();
    }

    public void openEndTimePicker(View V){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                if(LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)).isAfter(filterStart)) {
                    filterEnd = LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    timePickerEnd.setText(String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute));
                    updateMovieList(date, filterStart, filterEnd);
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,00,00,true);
        timePickerDialog.setTitle("Select end time");
        timePickerDialog.show();
    }
}