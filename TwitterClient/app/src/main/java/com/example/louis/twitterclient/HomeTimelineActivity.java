package com.example.louis.twitterclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
        for (LHTweet tweet : allTweets) {
            Log.d(TAG, "Tweet Text: " + tweet.text);
        }


        ListAdapter twitterAdapter = new ArrayAdapter<LHTweet>(this, android.R.layout.simple_expandable_list_item_1, allTweets);
        ListView twitterListView = (ListView) findViewById(R.id.twitterListView);
        twitterListView.setAdapter(twitterAdapter);
    }


    class TweetListAdapter extends BaseAdapter {
        private ArrayList<LHTweets> allTweets;

        public TweetListAdapter(ArrayList<LHTweet> allTweets){
            super();
            this.allTweets = allTweets;
        }

        @Override
        public int getCount() {
            return allTweets.size();
        }

        @Override
        public Object getItem(int i) {
            return allTweets.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LWTweet currentTweet = allTweets.get(i);
            view = getLayoutInflater().inflate(R.layout.tweet_list_item, null);
            TextView usernameView = (TextView) view.findViewById(R.id>textView_username);
            TextView tweetTextView = (TextView) view.findViewById(R.id>textView_tweet_text);
            usernameView.setText("Louis");
            tweetTextView.setText(currentTweet.text);

            return view;
        }
    }
}
