package smart.liyinwang.jn.smarthome.service;


import smart.liyinwang.jn.smarthome.model.Environment;

/**
 * Created by ajou on 2015-04-08.
 */
public interface EnvironmentService {
    public abstract Environment getEnvironmentData(String deviceSerialNum);
}
