package com.bradonlodwick.rate_urant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewRatings extends AppCompatActivity {
    TextView txt_low_ratings;
    TextView txt_high_ratings;
    TextView txt_low_ratings_title;
    TextView txt_high_ratings_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ratings);

        init();

        // Take the intent and get from it the array of restaurant names and ratings
        Intent given_intent = this.getIntent();
        String[] str_restaurants = given_intent.getStringArrayExtra(
                MainActivity.EXTRA_RESTAURANT_NAMES);
        int[] int_ratings = given_intent.getIntArrayExtra(MainActivity.EXTRA_RATINGS_GIVEN);

        // Sort through the ratings and display them as high or low
        ArrayList<ArrayList<Object>> str_low_ratings = new ArrayList<>();
        ArrayList<ArrayList<Object>> str_high_ratings = new ArrayList<>();
        // Loop through the ratings
        for (int i = 0; i < int_ratings.length; i++) {
            ArrayList<Object> restaurant_to_add = new ArrayList<>();
            restaurant_to_add.add(str_restaurants[i]);
            restaurant_to_add.add(int_ratings[i]);
            if (int_ratings[i] > 3) {
                str_high_ratings.add(restaurant_to_add);
            }
            else {
                str_low_ratings.add(restaurant_to_add);
            }
        }
        // Show the ratings of the restaurants in their respective columns
        txt_low_ratings_title.append(" - " + Integer.toString(str_low_ratings.size()));
        for (int i = 0; i < str_low_ratings.size(); i++) {
            String txt_to_add =
                    str_low_ratings.get(i).get(0) + " " + str_low_ratings.get(i).get(1) + "\n";
            txt_low_ratings.append(txt_to_add);
        }
        txt_high_ratings_title.append(" - " + Integer.toString(str_high_ratings.size()));
        for (int i = 0; i < str_high_ratings.size(); i++) {
            String txt_to_add =
                    str_high_ratings.get(i).get(0) + " " + str_high_ratings.get(i).get(1) + "\n";
            txt_high_ratings.append(txt_to_add);
        }
    }

    private void init() {
        txt_low_ratings = findViewById(R.id.txt_low_ratings);
        txt_high_ratings = findViewById(R.id.txt_high_ratings);
        txt_low_ratings_title = findViewById(R.id.txt_low_ratings_title);
        txt_high_ratings_title = findViewById(R.id.txt_high_ratings_title);
    }
}
