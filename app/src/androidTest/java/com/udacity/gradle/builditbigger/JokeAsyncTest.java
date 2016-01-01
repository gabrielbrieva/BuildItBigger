package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

public class JokeAsyncTest extends AndroidTestCase {

    public void TestJokeAsyncResult() {
        new JokeAsync(new JokeAsync.IJokeAsyncListener() {
            @Override
            public void onComplete(String result) {
                assertNotNull(result);
            }
        }).execute();
    }
}
