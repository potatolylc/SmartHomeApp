package service;

import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import http.HttpClient;
import http.ResponseHandler;
import http.URIRepository;
import model.Environment;
import model.SensorData;

/**
 * Created by ajou on 2015-04-08.
 */
public class EnvironmentServiceImpl implements EnvironmentService {

    public EnvironmentServiceImpl() {}

    @Override
    public Environment getEnvironmentData(String deviceSerialNum) {
        final Environment environment = new Environment();

        String uri = URIRepository.LATEST_SENSOR_DATA_SET;
        System.out.println(uri);

        RequestParams params = new RequestParams();
        params.put("deviceSerialNum", deviceSerialNum);

        HttpClient.getClient().get(uri, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObj = (JSONObject) response.get(i);
                        String sensorType = (String)jsonObj.get("sensorType");
                        if(sensorType.equals("lightBrightness")) {
                            environment.setLightBrightness((int)jsonObj.get("sensorDataValue"));
                        } else if(sensorType.equals("temperatureCel")) {
                            environment.setTemperature((double)jsonObj.get("sensorDataValue"));
                        } else if(sensorType.equals("humidity")) {
                            environment.setHumidity((double)(jsonObj.get("sensorDataValue")));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return environment;
    }
}
