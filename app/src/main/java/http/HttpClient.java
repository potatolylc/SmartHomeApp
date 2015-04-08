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
    private static AsyncHttpClient getClient() {
        if(client == null) {
            client = new AsyncHttpClient();
        }
        return client;
    }

    public static void get(String uri, Map<String, String> headers, RequestParams params, AsyncHttpResponseHandler handler) {
        setHeaders(headers);
        client.get(uri, params, handler);
    }

    public static void post(String uri, Map<String, String> headers, RequestParams params, AsyncHttpResponseHandler handler) {
        setHeaders(headers);
        client.post(uri, params, handler);
    }

    public static void put(String uri, Map<String, String> headers, RequestParams params, AsyncHttpResponseHandler handler) {
        setHeaders(headers);
        client.put(uri, params, handler);
    }

    public static void delete(String uri, Map<String, String> headers, RequestParams params, AsyncHttpResponseHandler handler) {
        setHeaders(headers);
        client.delete(null, uri, null, params, handler);
    }

    private static void setHeaders(Map<String, String> headers) {
        if(headers == null){
            return;
        }

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            getClient().addHeader(entry.getKey(), entry.getValue());
        }
    }

}
