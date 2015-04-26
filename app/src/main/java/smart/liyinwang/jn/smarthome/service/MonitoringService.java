package smart.liyinwang.jn.smarthome.service;

import java.util.List;

import smart.liyinwang.jn.smarthome.model.SensorData;


/**
 * Created by ajou on 2015-04-25.
 */
public interface MonitoringService {
    public abstract List<SensorData> getMonitoringDataList(String sensorSerialNum, String startTime, String endTime);
}
