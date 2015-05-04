package smart.liyinwang.jn.smarthome.service;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.RequestParams;

import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;
import smart.liyinwang.jn.smarthome.utils.Utils;

/**
 * Created by ajou on 2015-04-26.
 */
public class PushGeoLocationServiceImpl extends PushService {
    private LocationManager mLocationManager;
    private Criteria mCriteria;
    private Location mLocation;

    private double mLongitude;
    private double mLatitude;

    public PushGeoLocationServiceImpl() {
        super("PushGeoLocationServiceImpl");
    }

    public void pushGeoInfo() {
        Log.d("PushGeoService log", "--> pushGeoInfo()");

        RequestParams params = new RequestParams();
        params.put(Utils.STRING_USER_NAME, mUserName);
        params.put(Utils.STRING_USER_WIFI_SSID, mUserWifiSsid);
        params.put(Utils.STRING_LONGITUDE, mLongitude);
        params.put(Utils.STRING_LATITUDE, mLatitude);

        HttpClient.getClient().post(mUri, params, new ResponseHandler() {});
    }

    private void getLocation() {
        Log.d("PushGeoService log", "--> getLocation()");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mCriteria = new Criteria();
        mCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        mCriteria.setCostAllowed(false);

        boolean isGPSProviderEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkProviderEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(!isGPSProviderEnabled && !isNetworkProviderEnabled) {
            Log.e("PushGeoService log", "No providers available.");
        } else {
            if(isNetworkProviderEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 100, new MyLocationListener());
                if(mLocationManager != null) {
                    mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(mLocation != null) {
                        mLongitude = mLocation.getLongitude();
                        mLatitude = mLocation.getLatitude();
                    }
                }
            }

            if(isGPSProviderEnabled) {
                if(mLocation == null) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 100, new MyLocationListener());
                    if(mLocationManager != null) {
                        mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(mLocation != null) {
                            mLongitude = mLocation.getLongitude();
                            mLatitude = mLocation.getLatitude();
                        }
                    }
                }
            }
        }

        System.out.println(mLongitude + " & " + mLatitude);


    }


    @Override
    public void onCreate() {
        Log.d("PushGeoService log", "--> onCreate()");
        super.onCreate();

        mPreferences = getSharedPreferences(Utils.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        mUserName = mPreferences.getString(Utils.STRING_USER_NAME, "");
        mUserWifiSsid = mPreferences.getString(Utils.STRING_USER_WIFI_SSID, "");

        getLocation();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("PushGeoService log", "--> onHandleIntent()");
        mUri = intent.getData().toString();
        while(true) {
            System.out.println("Push Service..." + mUri + " " + mUserName + " " + mUserWifiSsid);
            pushGeoInfo();
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if(location != null) {
                mLongitude = location.getLongitude();
                mLatitude = location.getLatitude();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
