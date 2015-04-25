package http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

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
        setHeaders();
        return client;
    }

    public static void get(String uri, RequestParams params, ResponseHandler handler) {
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
