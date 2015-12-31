package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.javajoke.Joke;
import com.udacity.gradle.builditbigger.jokeactivity.JokeActivity;

public class JokeAsync extends AsyncTask<String, Void, String> {

    private final Context context;

    public JokeAsync(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0)
            return null;

        return Joke.GetJoke(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        if (context != null && s != null) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

            Intent myIntent = new Intent(context, JokeActivity.class);
            myIntent.putExtra(JokeActivity.JOKE_TEXT_KEY, s);
            context.startActivity(myIntent);
        }
    }
}
