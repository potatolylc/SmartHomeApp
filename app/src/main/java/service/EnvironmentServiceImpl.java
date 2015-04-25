package service;

import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import http.HttpClient;
import http.ResponseHandler;
import http.URIRepository;
import model.Environment;
import utils.Utils;

/**
 * Created by ajou on 2015-04-08.
 */
public class EnvironmentServiceImpl implements EnvironmentService {
    private Environment mEnvironment;

    public EnvironmentServiceImpl() {
        mEnvironment = new Environment();
    }

    @Override
    public Environment getEnvironmentData(String deviceSerialNum) {
        String uri = URIRepository.LATEST_SENSOR_DATA_SET;
        System.out.println(uri);
        RequestParams params = new RequestParams();
        params.put("deviceSerialNum", deviceSerialNum);

        HttpClient.getClient().get(uri, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString() + "sssssssssssssssssssssssss");
                try {
                    mEnvironment.setTemperature((String) response.get("temperatureCel"));
                    mEnvironment.setLightBrightness((String) response.get("lightBrightness"));
                    mEnvironment.setHumidity((String) response.get("humidity"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(mEnvironment + "eeeeeeeeeeeeeeeeeeee");
        return mEnvironment;
    }
}
