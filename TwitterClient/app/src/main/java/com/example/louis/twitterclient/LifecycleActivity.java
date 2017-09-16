package com.example.louis.twitterclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycle Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        log.d(TAG, "onCreate Called.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.d(TAG, "onStart Called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.d(TAG, "onResume Called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log.d(TAG, "onPause Called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log.d(TAG, "ontop Called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log.d(TAG, "onRestart Called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.d(TAG, "onDestroy Called.");
    }
}
