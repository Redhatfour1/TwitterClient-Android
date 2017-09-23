package Model;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by louis on 9/16/17.
 */

public class LHJson {
    private static final String TAG = "LHJson";

    public static String getSampleJSONAsString(Context context) {
//        StringBuilder stringBuilder = new StringBuilder();

        String jsonString = null;
        try {
            InputStream stream = context.getAssets().open("Tweets.json");
            Integer jsonDataLength = stream.available();
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (Exception exception) {
            Log.d(TAG, "getSampleJSONAsString: " + exception);
        }
        return jsonString;
    }

    public static ArrayList<LHTweet> getTweets(Context context, Boolean useSampleJSON) {
        ArrayList<LHTweet> tweets = new ArrayList<LHTweet>();
        if (useSampleJSON == true) {
            String tweetsJSONString = getSampleJSONAsString(context);

            try {
                JSONArray tweetsJson = new JSONArray(tweetsJSONString);
                for (int i = 0;
                     i < tweetsJson.length(); i++) {
                    JSONObject singleTweetObject = tweetsJson.getJSONObject(i);
                    tweets.add(new LHTweet(singleTweetObject));
                }
            } catch (Exception exception) {
                Log.d(TAG, "getTweets: Exception Parsing Tweets Array - " + exception);
            }

        }
        return tweets;
    }
}


