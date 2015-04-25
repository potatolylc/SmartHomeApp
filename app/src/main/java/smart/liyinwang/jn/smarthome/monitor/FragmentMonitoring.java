package smart.liyinwang.jn.smarthome.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.SensorData;
import service.MonitoringService;
import service.MonitoringServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import utils.Utils;


/**
 * Monitoring Fragment:
 * Showing graph of temperature and brightness
 * of the past one hour.
 */
public class FragmentMonitoring extends Fragment {
    private MonitoringService mMonitoringService;
    List<SensorData> sensorDataList;

    private View mMainView;

    public static FragmentMonitoring newInstance() {
        Log.d("MonitoringFragment log", "--> new Instance()");
        FragmentMonitoring fragment = new FragmentMonitoring();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMonitoring() {}

    public void loadData() {
        mMonitoringService = new MonitoringServiceImpl();
        sensorDataList = new ArrayList<SensorData>();
        sensorDataList =
        mMonitoringService.getMonitoringDataList(
                        Utils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_TEMPERATURE,
                        Utils.DUMMY_START_TIME, Utils.DUMMY_END_TIME);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreate()");
        super.onCreate(savedInstanceState);

        // load data from server
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreateView()");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        Log.d("MonitoringFragment log", "--> onAttach()");
        super.onAttach(activity);
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
    }

    @Override
    public void onPause() {
        Log.d("MonitoringFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("MonitoringFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("MonitoringFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            System.out.println("Data list size --> " + sensorDataList.size());
            for(SensorData data : sensorDataList) {
                System.out.println(data);
            }
        }
    }
}
