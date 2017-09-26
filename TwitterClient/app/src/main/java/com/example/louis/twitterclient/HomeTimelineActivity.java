package com.example.louis.twitterclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.LHJson;
import Model.LHTweet;

public class HomeTimelineActivity extends AppCompatActivity {

    private static final String TAG = "HomeTimelineActivity";
    private ListView twitterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);

        setupListView();
    }

    private void setupListView() {

        twitterListView = (ListView) findViewById(R.id.twitter_list_view);
        ArrayList<LHTweet> allTweets = LHJson.getTweets(this, true);
        TweetListAdapter adapter = new TweetListAdapter(allTweets);
        twitterListView.setAdapter(adapter);
    }
    class TweetListAdapter extends BaseAdapter {
        private ArrayList<LHTweet> allTweets;

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
            LHTweet currentTweet = allTweets.get(i);
            view = getLayoutInflater().inflate(R.layout.tweet_list_item, null);
            TextView usernameView = (TextView) view.findViewById(R.id.textView_username);
            TextView tweetTextView = (TextView) view.findViewById(R.id.textView_tweet_text);
            TextView locationView = (TextView)  view.findViewById(R.id.textView_location);
            usernameView.setText(currentTweet.user.name);
            tweetTextView.setText(currentTweet.text);
            locationView.setText(currentTweet.user.location);

            return view;
        }
    }
}
