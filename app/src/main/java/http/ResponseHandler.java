package http;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by ajou on 2015-04-09.
 */
public class ResponseHandler extends JsonHttpResponseHandler {
    private Context context;
    public ResponseHandler() {}
    public ResponseHandler(Context context) {
        this.context = context;
    }
}
