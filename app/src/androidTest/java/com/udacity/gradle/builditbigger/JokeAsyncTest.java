package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

public class JokeAsyncTest extends AndroidTestCase {

    public void testVerifyJokeAsyncResult() throws InterruptedException {

        final CountDownLatch signal = new CountDownLatch(1);

        new JokeAsync(new JokeAsync.IJokeAsyncListener() {
            @Override
            public void onComplete(String result) {
                assertNotNull(result);

                signal.countDown();
            }
        }).execute();

        signal.await();

    }
}
