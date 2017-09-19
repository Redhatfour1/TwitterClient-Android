package Model;

import android.util.Log;
import org.json.JSONObject;

/**
 * Created by louis on 9/16/17.
 */

public class LHTweet {

    private static final String TAG = "LHTweet";
    public String text;
    public String id;
    public LHUser user;
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
