package service;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import http.HttpClient;
import http.ResponseHandler;
import http.URIRepository;
import model.Environment;

/**
 * Created by ajou on 2015-04-08.
 */
public class EnvironmentServiceImpl implements EnvironmentService {
    private Environment environment;

    @Override
    public Environment monitorEnvironment(String deviceSerialNum) {
        environment = new Environment();
        String uri = URIRepository.DUMMY_MONITOR_ENVIRONMENT + deviceSerialNum;
        System.out.println(uri);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");

        HttpClient.get(uri, headers, null, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString() + "sssssssssssssssssssssssss");
                try {
                    environment.setTemperature((String) response.get("temperatureCel"));
                    environment.setLightBrightness((String) response.get("lightBrightness"));
                    environment.setHumidity((String) response.get("humidity"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(environment + "eeeeeeeeeeeeeeeeeeee");
        return environment;
    }
}
