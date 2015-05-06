package smart.liyinwang.jn.smarthome.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import smart.liyinwang.jn.smarthome.model.Environment;
import smart.liyinwang.jn.smarthome.service.EnvironmentService;
import smart.liyinwang.jn.smarthome.service.EnvironmentServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.utils.DummyUtils;


/**
 * Environment Fragment:
 * Showing temperature, brightness and humidity values
 * which are retrieved from database.
 */
public class FragmentEnvironment extends Fragment {

    // image views
    private ImageView mTempImageView;
    private ImageView mBrightImageView;
    private ImageView mHumidityImageView;

    // text views
    private TextView mTempTextView;
    private TextView mBrightTextView;
    private TextView mHumidityTextView;

    // main view
    private View mView;

    // service and model object for loading data
    private EnvironmentService mEnvironmentService;
    private Environment mEnvironment;

    public FragmentEnvironment() {
    }

    public static FragmentEnvironment newInstance() {
        Log.d("EnvironmentFragment log", "--> new Instance()");
        FragmentEnvironment fragment = new FragmentEnvironment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void loadData() {
        mEnvironmentService = new EnvironmentServiceImpl();
        mEnvironment = new Environment();
        mEnvironment = mEnvironmentService.getEnvironmentData(DummyUtils.DUMMY_ENVIRONMENT_DEVICE_SERIAL_NUM);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("EnvironmentFragment log", "--> onCreate()");

        super.onCreate(savedInstanceState);

        // load data from server
        loadData();
    }

    public void initImageView() {
        // init ImageView
        mTempImageView = (ImageView) mView.findViewById(R.id.environment_temperature_imageView);
        mBrightImageView = (ImageView) mView.findViewById(R.id.environment_brightness_imageView);
        mHumidityImageView = (ImageView) mView.findViewById(R.id.environment_humidity_imageView);
    }

    public void initTextView() {
        // init textView
        mTempTextView = (TextView) mView.findViewById(R.id.environment_temperature_textView);
        mBrightTextView = (TextView) mView.findViewById(R.id.environment_brightness_textView);
        mHumidityTextView = (TextView) mView.findViewById(R.id.environment_humidity_textView);
    }

    public void setEnvironmentTextView() {
        if(mTempTextView == null || mBrightTextView == null || mHumidityTextView == null)
            return;
        if(mEnvironment != null) {

            String tempText = Double.toString(mEnvironment.getTemperature());
            mTempTextView.setText(tempText);

            String lightText = null;
            double brightness = mEnvironment.getLightBrightness();
            if(brightness >= 0 && brightness < 300) {
                lightText = "Dim";
            } else if(brightness >300 && brightness <= 700) {
                lightText = "Soft";
            } else if(brightness > 700 && brightness <= 1000) {
                lightText = "Bright";
            }
            mBrightTextView.setText(lightText);

            String humiText = Double.toString(mEnvironment.getHumidity());
            mHumidityTextView.setText(humiText);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("EnvironmentFragment log", "--> onCreateView()");

        // init main view
        mView = inflater.inflate(R.layout.fragment_environment, container, false);

        // init view
        initImageView();
        initTextView();

        // Inflate the layout for this fragment
        ViewGroup vp = (ViewGroup) mView.getParent();
        if (vp != null) {
            vp.removeAllViewsInLayout();
            Log.d("EnvironmentFragment log", "--> All views removed");
        }

        return mView;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("EnvironmentFragment log", "--> onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("EnvironmentFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("EnvironmentFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("EnvironmentFragment log", "--> onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("EnvironmentFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("EnvironmentFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("EnvironmentFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            System.out.println("Data --> " + mEnvironment);
            setEnvironmentTextView();
        }
    }
}
