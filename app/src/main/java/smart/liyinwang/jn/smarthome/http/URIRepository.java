package smart.liyinwang.jn.smarthome.http;

/**
 * Created by ajou on 2015-04-08.
 */
public interface URIRepository {
    // Host URI for test
    public static final String URL_BASE_DEBUG = "http://192.168.0.11:8888/MavenIoEData";

    // user API URI
    public static final String USER_LOGIN_AUTHENTICATION = URL_BASE_DEBUG + "/user/auth";

    // static data API URI
    public static final String LATEST_SENSOR_DATA_SET = URL_BASE_DEBUG + "/sensorData/all/latest";
    public static final String SENSOR_DATA_LIST = URL_BASE_DEBUG + "/sensorData";

    // dynamic data API URI
    public static final String PUSH_WEATHER_INFO = URL_BASE_DEBUG + "/weather";
    public static final String PUSH_GEO_INFO = URL_BASE_DEBUG + "/location";
}
