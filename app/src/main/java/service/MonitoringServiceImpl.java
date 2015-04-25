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
import model.SensorData;

/**
 * Created by ajou on 2015-04-25.
 */
public class MonitoringServiceImpl implements MonitoringService {
    private SensorData sensorData;

    public MonitoringServiceImpl() {
        sensorData = new SensorData();
    }

    @Override
    public List<SensorData> getMonitoringDataList(String sensorSerialNum, String startTime, String endTime) {
        final List<SensorData> sensorDataList = new ArrayList<SensorData>();
        String uri = String.format(URIRepository.SENSOR_DATA_LIST);
        System.out.println(uri);
        RequestParams params = new RequestParams();
        params.put("sensorSerialNum", sensorSerialNum);
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        HttpClient.getClient().get(uri, params, new ResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    for (int i = 0; i < response.length(); i++) {
                        sensorDataList.add(SensorData.build((JSONObject) response.get(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return sensorDataList;
    }
}
