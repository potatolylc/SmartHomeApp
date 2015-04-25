/*
package smart.liyinwang.jn.smarthome.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.SensorData;
import service.MonitoringService;
import service.MonitoringServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import utils.Utils;


*/
/**
 * Monitoring Fragment:
 * Showing graph of temperature and brightness
 * of the past one hour.
 *//*

public class FragmentMonitoringBackup extends Fragment {
    private MonitoringService mMonitoringService;
    List<SensorData> sensorDataList;

    private View mMainView;

    public static FragmentMonitoringBackup newInstance() {
        Log.d("MonitoringFragment log", "--> new Instance()");

        FragmentMonitoringBackup fragment = new FragmentMonitoringBackup();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMonitoringBackup() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreate()");

        super.onCreate(savedInstanceState);
        if(sensorDataList != null) {
            System.out.println("1~!!!!!!!!!!!! " + sensorDataList.size());
            for(SensorData data : sensorDataList) {
                System.out.println(data + "&&&&&&&&&&&&");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreateView()");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monitoring, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        Log.d("MonitoringFragment log", "--> onAttach()");

        super.onAttach(activity);

        mMonitoringService = new MonitoringServiceImpl();
        sensorDataList =
                mMonitoringService.getMonitoringDataList(
                        Utils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_TEMPERATURE,
                        Utils.DUMMY_START_TIME, Utils.DUMMY_END_TIME);
    }

    @Override
    public void onDetach() {
        Log.d("MonitoringFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("MonitoringFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("MonitoringFragment log", "--> onResume()");
        super.onResume();
        System.out.println("2~!!!!!!!!!!!! " + sensorDataList.size());
    }

    @Override
    public void onPause() {
        Log.d("MonitoringFragment log", "--> onPause()");
        super.onPause();
        System.out.println("3~!!!!!!!!!!!! " + sensorDataList.size());
    }

    @Override
    public void onStop() {
        Log.d("MonitoringFragment log", "--> onStop()");
        super.onStop();
    }
}
*/
