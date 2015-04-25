package smart.liyinwang.jn.smarthome.monitor;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import model.Environment;
import service.EnvironmentService;
import service.EnvironmentServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import utils.Utils;


/**
 * Environment Fragment:
 * Showing temperature, brightness and humidity values
 * which are retrieved from database.
 */
public class FragmentEnvironment extends Fragment {
    private EnvironmentService mEnvironmentService;
    private Environment mEnvironment;

    // image views
    private ImageView mTempImageView;
    private ImageView mBrightImageView;
    private ImageView mHumidityImageView;

    // text views
    private TextView mTempTextView;
    private TextView mBrightTextView;
    private TextView mHumidityTextView;

    private View mMainView;

    public static FragmentEnvironment newInstance() {
        Log.d("EnvironmentFragment log", "--> new Instance()");

        FragmentEnvironment fragment = new FragmentEnvironment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentEnvironment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("EnvironmentFragment log", "--> onCreate()");

        super.onCreate(savedInstanceState);

        // fragment inflation
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_environment, (ViewGroup)getActivity().findViewById(R.id.pager), false);

        // init ImageView
        mTempImageView = (ImageView)mMainView.findViewById(R.id.environment_temperature_imageView);
        mBrightImageView = (ImageView)mMainView.findViewById(R.id.environment_brightness_imageView);
        mHumidityImageView = (ImageView)mMainView.findViewById(R.id.environment_humidity_imageView);

        // init textView
        mTempTextView = (TextView)mMainView.findViewById(R.id.environment_temperature_textView);
        mBrightTextView = (TextView)mMainView.findViewById(R.id.environment_brightness_textView);
        mHumidityTextView = (TextView)mMainView.findViewById(R.id.environment_humidity_textView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("EnvironmentFragment log", "--> onCreateView()");

        // Inflate the layout for this fragment
        ViewGroup vp = (ViewGroup)mMainView.getParent();
        if(vp != null) {
            vp.removeAllViewsInLayout();
            Log.d("EnvironmentFragment log", "--> All views removed");
        }
        return inflater.inflate(R.layout.fragment_environment, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("EnvironmentFragment log", "--> onAttach()");

        super.onAttach(activity);

        mEnvironmentService = new EnvironmentServiceImpl();
        mEnvironment = mEnvironmentService.getEnvironmentData(Utils.DUMMY_ENVIRONMENT_DEVICE_SERIAL_NUM);
        System.out.println("------------->" + mEnvironment);
        /*if(mEnvironment != null) {
            mTempTextView.setText(mEnvironment.getTemperature());
            mBrightTextView.setText(mEnvironment.getLightBrightness());
            mHumidityTextView.setText(mEnvironment.getHumidity());
        }*/
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
}
