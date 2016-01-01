package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.jokeactivity.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    String joke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        final TextView tv = (TextView) root.findViewById(R.id.instructions_text_view);

        final ProgressBar pb = (ProgressBar) root.findViewById(R.id.pb_loading);
        pb.setVisibility(View.GONE);

        final Button btTellJoke = (Button) root.findViewById(R.id.bt_tell_joke);
        btTellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                btTellJoke.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);

                new JokeAsync(new JokeAsync.IJokeAsyncListener() {
                    @Override
                    public void onComplete(String result) {

                        if (result != null) {
                            Intent myIntent = new Intent(getActivity(), JokeActivity.class);
                            myIntent.putExtra(JokeActivity.JOKE_TEXT_KEY, joke);
                            startActivity(myIntent);
                        }

                        pb.setVisibility(View.GONE);
                        btTellJoke.setVisibility(View.VISIBLE);
                        tv.setVisibility(View.VISIBLE);
                    }
                }).execute();
            }
        });

        return root;
    }
}
