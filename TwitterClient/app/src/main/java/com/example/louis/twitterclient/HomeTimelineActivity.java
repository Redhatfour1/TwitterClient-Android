package com.example.louis.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

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

        Twitter.initialize(this);
        Log.d(TAG, "on Create: Twitter Instance - " + Twitter.getInstance());
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
//        TwitterCore.getInstance().getSessionManager().clearActiveSession();

        Log.d(TAG, "onCreate: Session " + session);
        
        if(session != null) {
//            setupListView();
            setupUserTimelinelist();
        } else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        setupListView();
        setupUserTimelinelist();
    }
    private void setupUserTimelinelist() {
        UserTimeline userTimeline = new UserTimeline.Builder().build();
        TweetTimelineListAdapter userTimelineAdapter = new TweetTimelineListAdapter(this, userTimeline);
        twitterListView = (ListView) findViewById(R.id.twitter_list_view);
        twitterListView.setAdapter(userTimelineAdapter);
    }

    private void setupListView() {

        twitterListView = (ListView) findViewById(R.id.twitter_list_view);
        final ArrayList<LHTweet> allFetchedTweets = LHJson.getTweets(this, true);
        TweetListAdapter adapter = new TweetListAdapter(allFetchedTweets) {

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LHTweet currentTweet = (LHTweet) getItem(i);

                view = getLayoutInflater().inflate(R.layout.tweet_list_item, null);
                TextView usernameView = (TextView) view.findViewById(R.id.textView_username);
                TextView tweetTextView = (TextView) view.findViewById(R.id.textView_tweet_text);
                TextView locationView = (TextView) view.findViewById(R.id.textView_location);
                usernameView.setText(currentTweet.user.name);
                tweetTextView.setText(currentTweet.text);
                locationView.setText(currentTweet.user.location);

                return view;
            }
        };
        twitterListView.setAdapter(adapter);
    }
    abstract class TweetListAdapter extends BaseAdapter {
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
    }
}
