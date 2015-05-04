package smart.liyinwang.jn.smarthome.utils;

/**
 * Created by ajou on 2015-04-26.
 */
public interface Utils {
    // path variable / request parameter strings for REST API - user
    public final static String STRING_USER_SERIAL_NUM = "userSerialNum";
    public final static String STRING_USER_NAME = "userName";
    public final static String STRING_USER_WIFI_SSID = "userWifiSsid";
    public final static String STRING_USER_PASSWORD = "userPassword";

    // path variable / request parameter strings for REST API - device
    public final static String STRING_DEVICE_SERIAL_NUM = "deviceSerialNum";

    // path variable / request parameter strings for REST API - sensor
    public final static String STRING_SENSOR_SERIAL_NUM = "sensorSerialNum";

    // path variable / request parameter strings for REST API - weather
    public final static String STRING_TEMPERATURE_CEL = "temperatureCel";
    public final static String STRING_LIGHT_BRIGHTNESS = "lightBrightness";
    public final static String STRING_HUMIDITY = "humidity";
    public final static String STRING_PRESSURE = "pressure";

    // path variable / request parameter strings for REST API - location
    public final static String STRING_LONGITUDE = "longitude";
    public final static String STRING_LATITUDE = "latitude";

    // commons
    public final static String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public final static String SHARED_PREFERENCES_NAME = "SMART_HOME_SHARED_PREFERENCES";
}
