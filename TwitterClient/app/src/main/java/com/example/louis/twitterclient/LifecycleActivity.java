package com.example.louis.twitterclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycle Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG, "onCreate Called.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ontop Called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Called.");
    }
}
