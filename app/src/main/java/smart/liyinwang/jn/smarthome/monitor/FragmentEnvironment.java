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


/**
 * Environment Fragment:
 * Showing temperature, brightness and humidity values
 * which are retrieved from database.
 */
public class FragmentEnvironment extends Fragment {
    private OnFragmentInteractionListener mListener;
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
        super.onCreate(savedInstanceState);
        Log.d("EnvironmentFragment log", "--> onCreate()");

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
        // Inflate the layout for this fragment
        Log.d("EnvironmentFragment log", "--> onCreateView()");
        ViewGroup vp = (ViewGroup)mMainView.getParent();
        if(vp != null) {
            vp.removeAllViewsInLayout();
            Log.d("EnvironmentFragment log", "--> All views removed");
        }

        mEnvironmentService = new EnvironmentServiceImpl();
        mEnvironment = mEnvironmentService.monitorEnvironment("1");
        System.out.println("------------->" + mEnvironment);
        if(mEnvironment != null) {
            mTempTextView.setText(mEnvironment.getTemperature());
            mBrightTextView.setText(mEnvironment.getLightBrightness());
            mHumidityTextView.setText(mEnvironment.getHumidity());
        }

        return inflater.inflate(R.layout.fragment_environment, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("EnvironmentFragment log", "--> onAttach()");
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("EnvironmentFragment log", "--> onDetach()");
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("EnvironmentFragment log", "--> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("EnvironmentFragment log", "--> onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("EnvironmentFragment log", "--> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("EnvironmentFragment log", "--> onStop()");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
