package com.example.harkkatyo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

        // Parse the rating data gotten from rating manager
        ArrayList<String> parsedRatings = new ArrayList<>();
        for(Rating rating : ratings) {
            String parsed = "Elokuva: " + rating.getTitle() + "\n\nKommenttisi:  " + rating.getStars() + "/5" + "\n" + rating.getComment();
            parsedRatings.add(parsed);
        }


        // List view font colour change borrowed from https://stackoverflow.com/a/4533488/8057177
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, parsedRatings){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);

                return view;
            }
        };

        // Check if there is ratings, if yes then show them, if no then show a text advising the user to rate a movie
        if (parsedRatings.size() < 1) {
            noRatings.setText("Ei vielä arvosteluja!\nJätä arvostelu jollekkin elokuvalle niin ne näkyvät täällä");
        } else {
            list.setAdapter(adapter);
        }


    }

}
