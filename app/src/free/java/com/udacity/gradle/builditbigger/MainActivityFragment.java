package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.jokeactivity.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    InterstitialAd mInterstitialAd;
    String joke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                showJoke(joke);
            }
        });

        requestNewInterstitial();

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

                        joke = result;
                        if (joke != null) {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                showJoke(joke);
                            }
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void showJoke(String joke) {
        Intent myIntent = new Intent(getActivity(), JokeActivity.class);
        myIntent.putExtra(JokeActivity.JOKE_TEXT_KEY, joke);
        startActivity(myIntent);
    }
}
