package smart.liyinwang.jn.smarthome.http;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

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
