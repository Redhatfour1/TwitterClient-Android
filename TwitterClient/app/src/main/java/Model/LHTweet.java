package Model;

import android.util.Log;

import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONObject;

/**
 * Created by louis on 9/16/17.
 */

public class LHTweet {

    public static Tweet selectedTweet;
    private static final String TAG = "LHTweet";
    public String text;
    public String id;
    public LHUser user;

    @Override
    public String toString() {
        return text;
    }
    public LHTweet(JSONObject tweetObject) {
        try {

            this.text = tweetObject.getString("text");
            this.id = tweetObject.getString("id_str");
            this.user = new LHUser(tweetObject.getJSONObject("user"));

        } catch (Exception exception) {
            Log.d(TAG, "LHTweet: Tweet Creation Exception - " + exception);
        }

    }

}
