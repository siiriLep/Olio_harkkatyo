package com.example.harkkatyo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;



public class AllRatingsActivity extends AppCompatActivity {

    ListView list;
    TextView noRatings;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allratings);

        RatingManager rm = new RatingManager();
        ArrayList<Rating> ratings = rm.getAllRatings(AllRatingsActivity.this);
        list = (ListView) findViewById(R.id.listView);
        noRatings = (TextView) findViewById(R.id.noRatings);

        ArrayList<String> parsedRatings = new ArrayList<>();
        for(Rating rating : ratings) {
            String parsed = rating.getStars() + "/5\t" + rating.getTitle();
            parsedRatings.add(parsed);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,parsedRatings);
        System.out.println(ratings.size());
        if (parsedRatings.size() < 1) {
            //noRatings.setText("Ei vielä arvosteluja!\nJätä arvostelu jollekkin elokuvalle niin ne näkyvät täällä");
        } else {
            list.setAdapter(adapter);
        }


    }
}
