package smart.liyinwang.jn.smarthome.service;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.core.MainActivity;
import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;
import smart.liyinwang.jn.smarthome.http.URIRepository;
import smart.liyinwang.jn.smarthome.stomp.ListenerSubscription;
import smart.liyinwang.jn.smarthome.stomp.ListenerWSNetwork;
import smart.liyinwang.jn.smarthome.stomp.Stomp;
import smart.liyinwang.jn.smarthome.stomp.Subscription;
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

    // variables about Stomp
    Stomp mStomp;
    Map<String, String> mHeaders = new HashMap<String, String>();;

    public PushGeoLocationServiceImpl() {
        super("PushGeoLocationServiceImpl");
    }

    public void pushGeoInfo() throws InterruptedException {
        Log.d("PushGeoService log", "--> pushGeoInfo()");

        // push geo information via HTTP REST API
        RequestParams params = new RequestParams();
        params.put(Utils.STRING_USER_NAME, mUserName);
        params.put(Utils.STRING_USER_WIFI_SSID, mUserWifiSsid);
        params.put(Utils.STRING_LONGITUDE, mLongitude);
        params.put(Utils.STRING_LATITUDE, mLatitude);

        HttpClient.getClient().post(mUri, params, new ResponseHandler() {});

        WebSocketSendTask webSocketSendTask = new WebSocketSendTask();
        webSocketSendTask.execute(null);
    }

    public void requestForPushNotification() {
        // check geo location for push service
        WebSocketSubscribeTask webSocketSubscribeTask = new WebSocketSubscribeTask();
        webSocketSubscribeTask.execute(null);
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
        mUserSerialNum = mPreferences.getInt(Utils.STRING_USER_SERIAL_NUM, -1);
        mUserName = mPreferences.getString(Utils.STRING_USER_NAME, "");
        mUserWifiSsid = mPreferences.getString(Utils.STRING_USER_WIFI_SSID, "");

        getLocation();
        requestForPushNotification();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("PushGeoService log", "--> onHandleIntent()");
        mUri = intent.getData().toString();
        while(true) {
            System.out.println("Push Service..." + mUri + " " + mUserSerialNum + " " + mUserName + " " + mUserWifiSsid);

            try {
                pushGeoInfo();
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

    private void websocketSubscribe() {
        mStomp = new Stomp(URIRepository.WEBSOCKET_BASE_DEBUG, mHeaders, new ListenerWSNetwork() {
            @Override
            public void onState(int state) {
                System.out.println("Stomp listening..." + state);
            }
        });
        mStomp.connect();
        mStomp.subscribe(new Subscription(URIRepository.SUBSCRIPTION_DESTINATION_USER_IS_NEAR_HOME, new ListenerSubscription() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onMessage(Map<String, String> headers, String body) {
                if(body.contains("push")) {
                    NotificationCompat.Builder mBuilder = new NotificationCompat
                            .Builder(PushGeoLocationServiceImpl.this)
                                .setSmallIcon(R.drawable.intro)
                                .setContentTitle("Smart Home Notification")
                                .setContentText("You are near home now");
                    Intent resultIntent = new Intent(PushGeoLocationServiceImpl.this, MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(PushGeoLocationServiceImpl.this);
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(pendingIntent);
                    NotificationManager mNotificationManager = (NotificationManager) PushGeoLocationServiceImpl.this.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());
                }
            }
        }));

    }

    private class WebSocketSubscribeTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            websocketSubscribe();
            return null;
        }
    }

    private void websocketSend() {
        JSONObject geoJson = new JSONObject();
        JSONObject geoCoor = new JSONObject();
        try {
            geoCoor.put(Utils.STRING_LONGITUDE, String.valueOf(mLongitude));
            geoCoor.put(Utils.STRING_LATITUDE, String.valueOf(mLatitude));
            geoJson.put(Utils.STRING_GEO_COORDINATE, geoCoor);
            geoJson.put(Utils.STRING_USER_SERIAL_NUM, String.valueOf(mUserSerialNum));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mStomp.send(URIRepository.SEND_DESTINATION_GEO_COORDINATE, mHeaders, geoJson.toString());
    }

    private class WebSocketSendTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            websocketSend();
            return null;
        }
    }

}
