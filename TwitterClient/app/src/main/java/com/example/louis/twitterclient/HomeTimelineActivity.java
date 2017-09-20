package com.example.louis.twitterclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.twitter.sdk.android.core.Twitter;

import java.util.ArrayList;

import Model.LHJson;
import Model.LHTweet;

public class HomeTimelineActivity extends AppCompatActivity {

    private static final String TAG = "HomeTimelineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);

        ArrayList<LHTweet> allTweets = LHJson.getTweets(this, true);
        for (LHTweet tweet: allTweets) {
            Log.d(TAG, "Tweet Text: " + tweet.text);
        }


        ListAdapter twitterAdapter = new ArrayAdapter<LHTweet>(this, android.R.layout.simple_expandable_list_item_1, allTweets);
        ListView twitterListView = (ListView) findViewById(R.id.twitterListView);
        twitterListView.setAdapter(twitterAdapter);


    }
}
