package com.bradonlodwick.rate_urant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class RateRestaurant extends AppCompatActivity {
    // Elements on the RateRestaurant activity
    Button btn_back;
    ImageButton btn_1;
    ImageButton btn_2;
    ImageButton btn_3;
    ImageButton btn_4;
    ImageButton btn_5;
    TextView txt_rate_prompt;
    SeekBar seek_bar;

    // The rating that is given, based off the buttons that are selected
    int int_rating;

    // Values to be gotten from the intent
    int int_index;
    String str_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_restaurant);

        // Initialize the elements on the activity
        init();

        // Set up the values from the intent
        int_index = this.getIntent().getIntExtra(MainActivity.EXTRA_INDEX, -1);
        str_name = this.getIntent().getStringExtra(MainActivity.EXTRA_NAME);

        // Display the proper string in the prompt TextView
        txt_rate_prompt.setText(getString(R.string.rate_prompt, str_name));

        // Set event listener for the seek bar
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seek_bar, int progress, boolean from_user) {
                // Update the stars
                update_stars(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seek_bar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seek_bar) {

            }
        });
    }

    private void init() {
        btn_back = findViewById(R.id.btn_rate);
        btn_1 = findViewById(R.id.btn_rate_1);
        btn_2 = findViewById(R.id.btn_rate_2);
        btn_3 = findViewById(R.id.btn_rate_3);
        btn_4 = findViewById(R.id.btn_rate_4);
        btn_5 = findViewById(R.id.btn_rate_5);
        txt_rate_prompt = findViewById(R.id.txt_rate_prompt);
        seek_bar = findViewById(R.id.seek_bar);
    }

    public void update_stars(int rating) {
        // Switch to fill/un-fill the stars, and set which value is currently selected
        switch(rating) {
            case 5:
                int_rating = 5;
                btn_5.setImageResource(R.drawable.star_filled);
                btn_4.setImageResource(R.drawable.star_filled);
                btn_3.setImageResource(R.drawable.star_filled);
                btn_2.setImageResource(R.drawable.star_filled);
                btn_1.setImageResource(R.drawable.star_filled);
                break;
            case 4:
                int_rating = 4;
                btn_5.setImageResource(R.drawable.star_unfilled);
                btn_4.setImageResource(R.drawable.star_filled);
                btn_3.setImageResource(R.drawable.star_filled);
                btn_2.setImageResource(R.drawable.star_filled);
                btn_1.setImageResource(R.drawable.star_filled);
                break;
            case 3:
                int_rating = 3;
                btn_5.setImageResource(R.drawable.star_unfilled);
                btn_4.setImageResource(R.drawable.star_unfilled);
                btn_3.setImageResource(R.drawable.star_filled);
                btn_2.setImageResource(R.drawable.star_filled);
                btn_1.setImageResource(R.drawable.star_filled);
                break;
            case 2:
                int_rating = 2;
                btn_5.setImageResource(R.drawable.star_unfilled);
                btn_4.setImageResource(R.drawable.star_unfilled);
                btn_3.setImageResource(R.drawable.star_unfilled);
                btn_2.setImageResource(R.drawable.star_filled);
                btn_1.setImageResource(R.drawable.star_filled);
                break;
            case 1:
                int_rating = 1;
                btn_5.setImageResource(R.drawable.star_unfilled);
                btn_4.setImageResource(R.drawable.star_unfilled);
                btn_3.setImageResource(R.drawable.star_unfilled);
                btn_2.setImageResource(R.drawable.star_unfilled);
                btn_1.setImageResource(R.drawable.star_filled);
                break;
            case 0:
                int_rating = 0;
                btn_5.setImageResource(R.drawable.star_unfilled);
                btn_4.setImageResource(R.drawable.star_unfilled);
                btn_3.setImageResource(R.drawable.star_unfilled);
                btn_2.setImageResource(R.drawable.star_unfilled);
                btn_1.setImageResource(R.drawable.star_unfilled);
                break;
        }
    }

    /**
     * Listener for rating buttons.
     */
    public void rate(View v) {
        // Switch to fill/un-fill the stars, and set which value is currently selected
        switch(v.getId()) {
            case R.id.btn_rate_5:
                update_stars(5);
                seek_bar.setProgress(5);
                break;
            case R.id.btn_rate_4:
                update_stars(4);
                seek_bar.setProgress(4);
                break;
            case R.id.btn_rate_3:
                update_stars(3);
                seek_bar.setProgress(3);
                break;
            case R.id.btn_rate_2:
                update_stars(2);
                seek_bar.setProgress(2);
                break;
            case R.id.btn_rate_1:
                update_stars(1);
                seek_bar.setProgress(1);
                break;
        }
    }

    /**
     * Returns to the previous view after a rating is given.
     */
    public void back(View v) {
        // First check to see if a rating has been given
        if (int_rating == 0) {
            System.out.println("no rating given");
            return;
        }
        // Create the intent
        Intent return_intent = new Intent();
        return_intent.putExtra(MainActivity.EXTRA_RATING, int_rating);
        return_intent.putExtra(MainActivity.EXTRA_INDEX, int_index);
        return_intent.putExtra(MainActivity.EXTRA_NAME, str_name);
        setResult(RESULT_OK, return_intent);
        finish();
    }
}
