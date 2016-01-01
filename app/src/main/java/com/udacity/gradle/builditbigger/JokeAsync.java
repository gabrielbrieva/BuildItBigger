package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.javajoke.Joke;

public class JokeAsync extends AsyncTask<String, Void, String> {

    private IJokeAsyncListener listener;

    public JokeAsync(IJokeAsyncListener listener)
    {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        if (params.length == 0)
            return null;

        return Joke.GetJoke(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        if (listener != null) {
            listener.onComplete(s);
        }
    }

    public interface IJokeAsyncListener {
        public void onComplete (String result);
    }
}
