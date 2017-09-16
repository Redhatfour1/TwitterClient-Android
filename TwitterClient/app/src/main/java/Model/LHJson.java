package Model;

import android.content.Context;
import android.util.Log;
import java.io.InputStream;

/**
 * Created by louis on 9/16/17.
 */

public class LHJson {
    private static final String TAG ="LHJson";

    public static String getSampleJSONAsString(Context context) {
//        StringBuilder stringBuilder = new StringBuilder();

        String jsonString = null;
        try {
            InputStream stream = context.getAssets().open("Tweets.json");
            Integer jsonDataLength = stream.available();
            byte[] buffer = new byte[jsonDataLengthlength];
            stream.read(buffer);
            stream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (Exception exception) {
            Log.d(TAG, "getSampleJSONAsString: " + exception);
        }
        return jsonString;
    }
}
