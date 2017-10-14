package com.example.louis.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import java.util.ArrayList;
import java.util.List;
import Model.LHTweet;
import retrofit2.Call;

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
            setupListView();
        } else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }

    private void setupListView() {

        twitterListView = (ListView) findViewById(R.id.twitter_list_view);

        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        Call<List<Tweet>> homeTimelineCall = apiClient.getStatusesService().homeTimeline(null, null, null, null, null, null, null);
        homeTimelineCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                for (Tweet tweet: result.data){
                    Log.d(TAG, "success: Tweet Text - " + tweet.text);
                }

//                FixedTweetTimeline.Builder builder = new FixedTweetTimeline.Builder();
//                FixedTweetTimeline.Builder tweetTimelineBuilder.build();
//                FixedTweetTimeline timeline = tweetTimelineBuilder.build();
                FixedTweetTimeline timeline = new FixedTweetTimeline.Builder().setTweets(result.data).build();
                updateCurrentListViewWithLatestList(timeline);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "failure: " + exception.getMessage());

            }
        });

//        UserTimeline userTimeline = new UserTimeline.Builder().build();
//        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, userTimeline);

//        twitterListView.setAdapter(adapter);
    }
    private void updateCurrentListViewWithLatestList(Timeline<Tweet> tweets){
        final HomeTimelineActivity homeTimelineActivityContext = this;
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, tweets) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                final Tweet currentTweet = this.getItem(position);
                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        LHTweet.selectedTweet = currentTweet;

                        Intent goToTweetDetailIntent = new Intent(homeTimelineActivityContext, TweetDetailActivity.class);
                        startActivity(goToTweetDetailIntent);

                    }
                });

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
