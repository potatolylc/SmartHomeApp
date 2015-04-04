package smart.liyinwang.jn.smarthome;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentEnvironment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentEnvironment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEnvironment extends Fragment {
    private OnFragmentInteractionListener mListener;

    // image views
    private ImageView mTempImageView;
    private ImageView mBrightImageView;
    private ImageView mHumidityImageView;

    // edit texts
    private TextView mTempTextView;
    private TextView mBrightTextView;
    private TextView mHumidityTextView;

    private View mMainView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Environment.
     */
    public static FragmentEnvironment newInstance(String param1, String param2) {
        Log.d("EnvironmentFragment log", "--> new Instance()");
        FragmentEnvironment fragment = new FragmentEnvironment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentEnvironment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("EnvironmentFragment log", "--> onCreate()");

        // fragment inflation
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_environment, (ViewGroup)getActivity().findViewById(R.id.pager), false);

        // init ImageView
        mTempImageView = (ImageView)mMainView.findViewById(R.id.temperature_imageView);
        mTempImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked...", Toast.LENGTH_SHORT).show();
            }
        });
        mBrightImageView = (ImageView)mMainView.findViewById(R.id.brightness_imageView);
        mHumidityImageView = (ImageView)mMainView.findViewById(R.id.humidity_imageView);

        // init editText
        mTempTextView = (TextView)mMainView.findViewById(R.id.temperature_textView);
        mBrightTextView = (TextView)mMainView.findViewById(R.id.brightness_textView);
        mHumidityTextView = (TextView)mMainView.findViewById(R.id.humidity_textView);
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
