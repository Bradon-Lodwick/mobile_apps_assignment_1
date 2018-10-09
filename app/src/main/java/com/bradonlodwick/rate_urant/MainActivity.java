package com.bradonlodwick.rate_urant;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Elements on the main activity
    Button btn_rate;
    TextView txt_instructions;
    ProgressBar pb_progress;
    Button btn_view_ratings;

    // Request numbers for intents
    public static final int REQUEST_RATING = 1;

    // Strings for intent extras
    public static final String EXTRA_NAME = "R_NAME";
    public static final String EXTRA_INDEX = "R_INDEX";
    public static final String EXTRA_RATING = "RATING";
    public static final String EXTRA_RATINGS_GIVEN = "RATINGS";
    public static final String EXTRA_RESTAURANT_NAMES = "R_NAMES";


    // The list of restaurants to be rated
    String[] str_restaurants;
    int int_next_restaurant;
    int[] int_ratings_given;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the view elements
        init();

        // Load the array of restaurant names
        str_restaurants = getResources().getStringArray(R.array.restaurants);
        // Set the maximum of the progress bar to the string array's length
        pb_progress.setMax(str_restaurants.length);
        // Sets the restaurant to rate to 1
        int_next_restaurant = 0;
        int_ratings_given = new int[str_restaurants.length];
    }

    /**
     * Used to setup the elements that will be used in the activity.
     */
    private void init() {
        btn_rate = findViewById(R.id.btn_rate);
        txt_instructions = findViewById(R.id.txt_instructions);
        pb_progress = findViewById(R.id.pb_progress);
        btn_view_ratings = findViewById(R.id.btn_view_ratings);
    }

    /**
     * Function to be run when the rate button is pressed
     */
    public void rateClick(View v) {
        // Create intent to rate the other restaurant
        Intent intent_rate = new Intent(this, RateRestaurant.class);
        // Add the restaurant name and it's number in the string array to the intent extras
        intent_rate.putExtra(EXTRA_INDEX, int_next_restaurant);
        intent_rate.putExtra(EXTRA_NAME, str_restaurants[int_next_restaurant]);
        // Start the activity for a result to be given (the rating)
        startActivityForResult(intent_rate, REQUEST_RATING);
    }

    /**
     * Runs the intent for viewing ratings.
     */
    public void viewRatingsClick(View v) {
        // Create intent to show the ratings
        Intent intent_view_ratings = new Intent(this, ViewRatings.class);
        // Add the extras that are used in the next intent
        intent_view_ratings.putExtra(EXTRA_RESTAURANT_NAMES, str_restaurants);
        intent_view_ratings.putExtra(EXTRA_RATINGS_GIVEN, int_ratings_given);
        // Start the activity
        startActivity(intent_view_ratings);
    }

    /**
     * Receives intent from the RateRestaurant activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Make sure the intent request code is the proper code
        if (requestCode == REQUEST_RATING && resultCode == RESULT_OK) {
            // Get the extra data passed in the intent
            int int_given_index = data.getIntExtra(EXTRA_INDEX, -1);
            int int_given_rating = data.getIntExtra(EXTRA_RATING, -1);
            // Stores the rating in the rating array
            int_ratings_given[int_given_index] = int_given_rating;

            // Output the results to the console to show that the intent was received
            System.out.println(int_ratings_given);

            // Increase the rating index
            int_next_restaurant++;

            // Check if the ratings are completed
            if (int_next_restaurant < str_restaurants.length) {
                // Increase the progress bar
                pb_progress.incrementProgressBy(1);
            }
            else {
                // Hide the btn_rate and show the btn_view_ratings
                btn_rate.setVisibility(View.GONE);
                btn_view_ratings.setVisibility(View.VISIBLE);
                // TODO add a button that allows viewing the ratings
            }
        }
    }
}
