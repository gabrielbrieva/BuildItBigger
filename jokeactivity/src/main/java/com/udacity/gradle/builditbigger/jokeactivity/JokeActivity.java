package com.udacity.gradle.builditbigger.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_TEXT_KEY = "JOKE_TEXT_KEY";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if (getIntent().hasExtra(JOKE_TEXT_KEY)) {
            String joke = getIntent().getStringExtra(JOKE_TEXT_KEY);

            if (joke != null) {
                TextView tvJoke = (TextView) findViewById(R.id.tvJoke);
                tvJoke.setText(joke);
            }
        }
    }
}
