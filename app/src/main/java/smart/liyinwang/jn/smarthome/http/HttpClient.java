package smart.liyinwang.jn.smarthome.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Created by ajou on 2015-04-08.
 */
public class HttpClient {
    private static AsyncHttpClient client;
    private HttpClient() {
    }
    public static AsyncHttpClient getClient() {
        if(client == null) {
            client = new AsyncHttpClient();
        }
        return client;
    }

    public static void get(String uri, RequestParams params, ResponseHandler handler) {
        setHeaders();
        client.get(uri, params, handler);
    }

    public static void post(String uri, RequestParams params, ResponseHandler handler) {
        client.post(uri, params, handler);
    }

    public static void put(String uri, RequestParams params, ResponseHandler handler) {
        client.put(uri, params, handler);
    }

    public static void delete(String uri, RequestParams params, ResponseHandler handler) {
        client.delete(null, uri, null, params, handler);
    }

    private static void setHeaders() {
        if(client == null){
            return;
        }
        client.addHeader("Accept", "application/json");
        client.addHeader("Content-type", "application/json");
    }

}
