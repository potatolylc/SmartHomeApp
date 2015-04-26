package smart.liyinwang.jn.smarthome.stomp;

import java.util.Map;
/**
 * Created by ajou on 2015-04-26.
 */
public interface ListenerSubscription {
    public void onMessage(Map<String, String> headers, String body);
}