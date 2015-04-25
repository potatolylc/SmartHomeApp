package model;

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
    private Object sensorDataValue;
    private Date sensorDataTimestamp;

    public SensorData() {
    }

    public SensorData(int sensorDataSerialNum, Object sensorDataValue, Date sensorDataTimestamp) {
        this.sensorDataSerialNum = sensorDataSerialNum;
        this.sensorDataValue = sensorDataValue;
        this.sensorDataTimestamp = sensorDataTimestamp;
    }

    public SensorData build(JSONObject response) {
        try {
            this.setSensorDataSerialNum((int) response.get("sensorDataSerialNum"));
            this.setSensorDataValue(response.get("sensorDataValue"));
            this.setSensorDataTimestamp((Date) new Date((String) response.get("sensorDataTimestamp")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<SensorData> buildList(JSONArray response) {
        List<SensorData> sensorDataList = new ArrayList<SensorData>();
        try {
            for(int i = 0; i < response.length(); i++) {
                JSONObject jsonObj = (JSONObject)response.get(i);
                this.setSensorDataSerialNum((int)jsonObj.get("sensorDataSerialNum"));
                this.setSensorDataValue(jsonObj.get("sensorDataValue"));
                this.setSensorDataTimestamp((Date)new Date((String)jsonObj.get("sensorDataTimestamp")));
                sensorDataList.add(this);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sensorDataList;
    }

    public int getSensorDataSerialNum() {
        return sensorDataSerialNum;
    }

    public Object getSensorDataValue() {
        return sensorDataValue;
    }

    public Date getSensorDataTimestamp() {
        return sensorDataTimestamp;
    }

    public void setSensorDataSerialNum(int sensorDataSerialNum) {
        this.sensorDataSerialNum = sensorDataSerialNum;
    }

    public void setSensorDataValue(Object sensorDataValue) {
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
