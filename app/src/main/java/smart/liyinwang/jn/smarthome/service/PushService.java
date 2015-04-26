package smart.liyinwang.jn.smarthome.service;

import android.app.IntentService;
import android.content.SharedPreferences;

/**
 * Created by ajou on 2015-04-26.
 */
public abstract class PushService extends IntentService {
    SharedPreferences mPreferences;
    String mUserName;
    String mUserWifiSsid;
    String mUri;

    public PushService(String name) {
        super("PushService");
    }

}
