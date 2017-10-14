package com.example.louis.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.twitter.sdk.android.core.models.Tweet;
import Model.LHTweet;

public class TweetDetailActivity extends AppCompatActivity {
    private Tweet mSelectedTweet;
    private ImageView mImageView;
    private TextView mTweetTextView;
    private TextView mUsernameView;
    private TextView mLocation;
    private Button mViewMoreButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        mSelectedTweet = LHTweet.selectedTweet;

    }
    private void configureLayout() {
        mImageView = (ImageView) findViewById(R.id.imageView_profile_image);
        mUsernameView = (TextView) findViewById(R.id.textView_username);
        mTweetTextView = (TextView) findViewById(R.id.textView_tweet_text);
        mLocation = (TextView) findViewById(R.id.textView_location);
        mViewMoreButton = (Button) findViewById(R.id.button_view_more_tweets);
        mUsernameView.setText(mSelectedTweet.user.screenName);
        mTweetTextView.setText(mSelectedTweet.text);
        mLocation.setText(mSelectedTweet.user.location);

                final TweetDetailActivity tweetDetailActivityContext = this;
        mViewMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goTimelineIntent = new Intent(tweetDetailActivityContext, HomeTimelineActivity.class);
                startActivity(goTimelineIntent);
            }
        });
    }
}
