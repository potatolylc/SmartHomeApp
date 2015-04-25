package http;

/**
 * Created by ajou on 2015-04-08.
 */
public interface URIRepository {
    // Host URI for test
    public static final String URL_BASE_DEBUG = "http://192.168.0.11:8888/MavenIoEData";

    public static final String LATEST_SENSOR_DATA_SET = URL_BASE_DEBUG + "/sensorData/all/latest";
    public static final String SENSOR_DATA_LIST = URL_BASE_DEBUG + "/sensorData";
}
