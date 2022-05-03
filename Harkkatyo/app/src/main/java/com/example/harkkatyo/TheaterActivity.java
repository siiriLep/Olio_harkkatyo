package com.example.harkkatyo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
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
    int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater);
        selectedIndex = 0;

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;
                movieChoice();
            }
        });

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
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theater.getMovieNames()){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };
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

    // TODO: maybe rename this function to something more descriptive
    public void movieChoice () {
        Intent intent = new Intent(TheaterActivity.this, MovieActivity.class);
        intent.putExtra("movie", theater.getMovie(selectedIndex));
        startActivity(intent);
    }

}