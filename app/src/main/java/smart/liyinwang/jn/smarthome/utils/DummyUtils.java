package smart.liyinwang.jn.smarthome.utils;

/**
 * Created by ajou on 2015-04-25.
 */
public interface DummyUtils {
    // Dummy user serial number of 'wang'
    public static final int DUMMY_USER_SERIAL_NUM = 11;
    // Dummy user name of 'wang'
    public static final String DUMMY_USER_NAME = "wanghao"; // wang
    // Dummy User WiFi SSID of 'wang'
    public static final String DUMMY_USER_WIFI_SSID = "shanda"; // social

    // Dummy device serial number of 'wang-outer' (used for Environment Fragment) for test
    public static final String DUMMY_ENVIRONMENT_DEVICE_SERIAL_NUM = "554c6962dee34328c4a00d7c"; // 5538adf85ace012fc8c64f8a

    // Dummy sensor serial numbers of 'wang-outer' (used for Environment Fragment) for test
    public static final String DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_LIGHT_BRIGHTNESS = "554c6977dee34328c4a00d7d"; // 5538ae605ace012fc8c64f8b
    public static final String DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_TEMPERATURE = "554c6992dee34328c4a00d7f"; // 5538ae805ace012fc8c64f8c
    public static final String DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_HUMIDITY = "554c6984dee34328c4a00d7e"; // 5538ae965ace012fc8c64f8d

    // Dummy start time and end time for showing data graph (used for Monitoring Fragment) for test
    public static final String DUMMY_START_TIME = "2015/04/26 18:30:00";
    public static final String DUMMY_END_TIME = "2015/04/26 20:00:00";
    public static final String DUMMY_START_TIME_LIGHT = "2015/04/26 17:00:00";
    public static final String DUMMY_END_TIME_LIGHT = "2015/04/26 18:00:00";
}
