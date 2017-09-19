package Model;

import android.util.Log;
import org.json.JSONObject;

/**
 * Created by louis on 9/16/17.
 */

public class LHUser {
    private static final String TAG = "LHUser";

    public String name;
    public String profileImageURL;
    public String location;
    public LHUser (JSONObject userObject) {
        try{

            this.name = userObject.getString("name");
            this.profileImageURL = userObject.getString("profile_image_url_https");
            this.location = userObject.getString("location");

        } catch (Exception exception) {
            Log.d(TAG, "LHUser: User Creation Exception - " + exception);
        }
    }
}
