package smart.liyinwang.jn.smarthome.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.loopj.android.http.RequestParams;

import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;
import smart.liyinwang.jn.smarthome.utils.Utils;

/**
 * Created by ajou on 2015-04-26.
 */
public class PushWeatherServiceImpl extends PushService {

    private SensorManager mSensorManager;

    private Sensor mTemperatureSensor;
    private Sensor mHumiditySensor;
    private Sensor mLightBrightnessSensor;

    private float mTemperatureValue;
    private float mHumidityValue;
    private float mLightBrightnessValue;

    public PushWeatherServiceImpl() {
        super("PushWeatherServiceImpl");
    }

    private void pushTemperature(float temperatureVal) {
        System.out.println("Push Temperature..." + temperatureVal);

        RequestParams params = new RequestParams();
        params.put(Utils.STRING_USER_NAME, mUserName);
        params.put(Utils.STRING_USER_WIFI_SSID, mUserWifiSsid);
        params.put(Utils.STRING_TEMPERATURE_CEL, temperatureVal);

        HttpClient.getClient().post(mUri, params, new ResponseHandler() {});
    }
    private void pushHumidity(float humidityVal) {
        System.out.println("Push Humidity..." + humidityVal);
    }
    private void pushLightBrightness(float lightBrightnessVal) {
        System.out.println("Push Light Brightness..." + lightBrightnessVal);

        RequestParams params = new RequestParams();
        params.put(Utils.STRING_USER_NAME, mUserName);
        params.put(Utils.STRING_USER_WIFI_SSID, mUserWifiSsid);
        params.put(Utils.STRING_LIGHT_BRIGHTNESS, lightBrightnessVal);

        HttpClient.getClient().post(mUri, params, new ResponseHandler() {});
    }
    private void pushPressure(float pressure) {
        System.out.println("Push Light Brightness..." + pressure);
    }

    public void pushWeatherInfo(String... weatherInfoTypes) {
        if(weatherInfoTypes.length == 0) {
            pushTemperature(mTemperatureValue);
            pushHumidity(mHumidityValue);
            pushLightBrightness(mLightBrightnessValue);
            pushPressure((float) 80.0);
        } else {
            for (int i = 0; i < weatherInfoTypes.length; i++) {
                if (weatherInfoTypes[i].equals(Utils.STRING_TEMPERATURE_CEL))
                    pushTemperature(mTemperatureValue);
                else if (weatherInfoTypes[i].equals(Utils.STRING_HUMIDITY))
                    pushHumidity(mHumidityValue);
                else if (weatherInfoTypes[i].equals(Utils.STRING_LIGHT_BRIGHTNESS))
                    pushLightBrightness(mLightBrightnessValue);
                else if (weatherInfoTypes[i].equals(Utils.STRING_PRESSURE))
                    pushPressure((float) 80.0);
            }
        }
    }

    private void initSensors() {
        Log.d("PushWeatherService log", "--> initSensors()");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mTemperatureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        mHumiditySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        mLightBrightnessSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    private void getSensorData() {
        Log.d("PushWeatherService log", "--> getSensorData()");
        if(mTemperatureSensor != null) {
            mSensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    mTemperatureValue = event.values[0];
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, mTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(mHumiditySensor != null) {
            mSensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    mHumidityValue = event.values[0];
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, mHumiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(mLightBrightnessSensor != null) {
            mSensorManager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    mLightBrightnessValue = event.values[0];
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, mLightBrightnessSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onCreate() {
        Log.d("PushWeatherService log", "--> onCreate()");
        super.onCreate();

        mPreferences = getSharedPreferences(Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        mUserName = mPreferences.getString(Utils.STRING_USER_NAME, "");
        mUserWifiSsid = mPreferences.getString(Utils.STRING_USER_WIFI_SSID, "");

        initSensors();

        getSensorData();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("PushWeatherService log", "--> onHandleIntent()");
        mUri = intent.getData().toString();
        while(true) {
            System.out.println("Push Service..." + mUri + " " + mUserName + " " + mUserWifiSsid);
            pushWeatherInfo(Utils.STRING_LIGHT_BRIGHTNESS);
            System.out.println(mLightBrightnessValue);
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
