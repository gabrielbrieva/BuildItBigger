package com.udacity.gradle.builditbigger.jokegce;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private String jokeData;

    public String getData() {
        return jokeData;
    }

    public void setData(String data) {
        jokeData = data;
    }
}