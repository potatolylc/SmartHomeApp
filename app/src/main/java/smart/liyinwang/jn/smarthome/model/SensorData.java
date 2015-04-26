package smart.liyinwang.jn.smarthome.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ajou on 2015-04-25.
 */
public class SensorData {
    private int sensorDataSerialNum;
    private double sensorDataValue;
    private Date sensorDataTimestamp;

    public SensorData() {
    }

    public SensorData(int sensorDataSerialNum, double sensorDataValue, Date sensorDataTimestamp) {
        this.sensorDataSerialNum = sensorDataSerialNum;
        this.sensorDataValue = sensorDataValue;
        this.sensorDataTimestamp = sensorDataTimestamp;
    }

    public static SensorData build(JSONObject response) {
        SensorData sensorData = new SensorData();
        try {
            sensorData.setSensorDataSerialNum((int) response.get("sensorDataSerialNum"));
            if(response.get("sensorDataValue") instanceof Integer) {
                sensorData.setSensorDataValue((int) response.get("sensorDataValue"));
            } else if(response.get("sensorDataValue") instanceof Double) {
                sensorData.setSensorDataValue((double) response.get("sensorDataValue"));
            }
            sensorData.setSensorDataTimestamp((Date) new Date((String) response.get("sensorDataTimestamp")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sensorData;
    }

    public static List<SensorData> buildList(JSONArray response) {
        List<SensorData> sensorDataList = new ArrayList<SensorData>();
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject jsonObj = (JSONObject)response.get(i);
                SensorData sensorData = build(jsonObj);
                sensorDataList.add(sensorData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sensorDataList;
    }

    public int getSensorDataSerialNum() {
        return sensorDataSerialNum;
    }

    public double getSensorDataValue() {
        return sensorDataValue;
    }

    public Date getSensorDataTimestamp() {
        return sensorDataTimestamp;
    }

    public void setSensorDataSerialNum(int sensorDataSerialNum) {
        this.sensorDataSerialNum = sensorDataSerialNum;
    }

    public void setSensorDataValue(double sensorDataValue) {
        this.sensorDataValue = sensorDataValue;
    }

    public void setSensorDataTimestamp(Date sensorDataTimestamp) {
        this.sensorDataTimestamp = sensorDataTimestamp;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "sensorDataSerialNum=" + sensorDataSerialNum +
                ", sensorDataValue=" + sensorDataValue +
                ", sensorDataTimestamp=" + sensorDataTimestamp +
                '}';
    }
}
