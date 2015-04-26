package smart.liyinwang.jn.smarthome.service;

import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;
import smart.liyinwang.jn.smarthome.http.URIRepository;
import smart.liyinwang.jn.smarthome.model.Environment;
import smart.liyinwang.jn.smarthome.utils.Utils;

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
        params.put(Utils.STRING_DEVICE_SERIAL_NUM, deviceSerialNum);

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
                            environment.setTemperature((double) jsonObj.get("sensorDataValue"));
                        } else if(sensorType.equals("humidity")) {
                            if(jsonObj.get("sensorDataValue") instanceof Integer) {
                                environment.setHumidity((int)(jsonObj.get("sensorDataValue")));
                            } else if(jsonObj.get("sensorDataValue") instanceof Double) {
                                environment.setHumidity((double)(jsonObj.get("sensorDataValue")));
                            }
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
