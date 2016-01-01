package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.javajoke.Joke;

public class JokeAsync extends AsyncTask<Void, Void, String> {

    private IJokeAsyncListener listener;

    public JokeAsync(IJokeAsyncListener listener)
    {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        return Joke.GetJoke();
    }

    @Override
    protected void onPostExecute(String s) {
        if (listener != null) {
            listener.onComplete(s);
        }
    }

    public interface IJokeAsyncListener {
        void onComplete (String result);
    }
}
