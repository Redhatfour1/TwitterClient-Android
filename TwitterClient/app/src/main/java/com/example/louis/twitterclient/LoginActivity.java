package com.example.louis.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Twitter.initialize(this);
        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "Success loggin into Twitter: " + result);
                finish();

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "Exception Raised - Logging into Twitter Failed: " + exception);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG, "onActivityResult: RequestCode " + requestCode);
//        Log.d(TAG, "onActivityResult: resultCode " + resultCode);
//        Log.d(TAG, "onActivityResult: Data " + data.getDataString());

        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
