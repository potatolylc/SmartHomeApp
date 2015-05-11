package smart.liyinwang.jn.smarthome.room;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.apache.http.Header;

import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.http.HttpClient;
import smart.liyinwang.jn.smarthome.http.ResponseHandler;


/**
 * LivingRoom Fragment:
 * Control TV and air conditioner
 * and send event log data to server.
 */
public class FragmentLivingRoom extends Fragment {
    private static final int LOWEST_TEMPERATURE = 18;

    // components of air conditioner
    private ToggleButton mAirConToggleButton;
    private Button mAirConStudyButton;
    private ImageButton mAirConTempDownButton;
    private ImageButton mAirConTempUpButton;
    private TextView mAirConTextView;

    // components of television
    private ToggleButton mTVToggleButton;
    private Button mTVStudyButton;
    private ImageButton mTVChannelUpButton;
    private ImageButton mTVChannelDownButton;
    private ImageButton mTVVolumeUpButton;
    private ImageButton mTVVolumeDownButton;

    // main view
    private View mView;

    public static FragmentLivingRoom newInstance() {
        Log.d("LivingRoomFragment log", "--> new Instance()");
        FragmentLivingRoom fragment = new FragmentLivingRoom();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentLivingRoom() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("LivingRoomFragment log", "--> new Instance()");
        super.onActivityCreated(savedInstanceState);
        /*listView = (AsymmetricGridView)getView().findViewById(R.id.listView);*/

    }

    private void initAirConViews() {
        // init components of air conditioner
        mAirConToggleButton = (ToggleButton)mView.findViewById(R.id.living_room_air_conditioner_toggle_button);
        mAirConToggleButton.setTextOff("ON");
        mAirConToggleButton.setTextOn("OFF");
        mAirConStudyButton = (Button) mView.findViewById(R.id.living_room_air_con_study_button);
        mAirConTempDownButton = (ImageButton) mView.findViewById(R.id.living_room_temperature_down_button);
        mAirConTempUpButton = (ImageButton) mView.findViewById(R.id.living_room_temperature_up_button);
        mAirConTextView = (TextView) mView.findViewById(R.id.living_room_temperature_text_view);
    }

    private void initTVViews() {
        // init component of television
        mTVToggleButton = (ToggleButton) mView.findViewById(R.id.play_pause_button);
        mTVToggleButton.setTextOff(null);
        mTVToggleButton.setTextOn(null);
        mTVStudyButton = (Button) mView.findViewById(R.id.living_room_tv_study_button);
        mTVChannelUpButton = (ImageButton) mView.findViewById(R.id.channel_plus_button);
        mTVChannelDownButton = (ImageButton) mView.findViewById(R.id.channel_minus_button);
        mTVVolumeUpButton = (ImageButton) mView.findViewById(R.id.volume_plus_button);
        mTVVolumeDownButton = (ImageButton) mView.findViewById(R.id.volume_minus_button);
    }

    private void setEventTriggers() {
        // event trigger - air conditioner
        mAirConToggleButton.setOnCheckedChangeListener(new ToggleButtonClickListener());
        mAirConStudyButton.setOnClickListener(new ButtonClickListener());
        mAirConTempDownButton.setOnClickListener(new ImageButtonClickListener());
        mAirConTempUpButton.setOnClickListener(new ImageButtonClickListener());

        // event trigger - television
        mTVToggleButton.setOnCheckedChangeListener(new ToggleButtonClickListener());
        mTVStudyButton.setOnClickListener(new ButtonClickListener());
        mTVChannelUpButton.setOnClickListener(new ImageButtonClickListener());
        mTVChannelDownButton.setOnClickListener(new ImageButtonClickListener());
        mTVVolumeUpButton.setOnClickListener(new ImageButtonClickListener());
        mTVVolumeDownButton.setOnClickListener(new ImageButtonClickListener());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LivingRoomFragment log", "--> new Instance()");
        mView = inflater.inflate(R.layout.fragment_living_room, container, false);

        // init views
        initAirConViews();
        initTVViews();

        // event triggers
        setEventTriggers();

        // Inflate the layout for this fragment
        return mView;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("LivingRoomFragment log", "--> onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("LivingRoomFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("LivingRoomFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("LivingRoomFragment log", "--> onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LivingRoomFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LivingRoomFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("LivingRoomFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            System.out.println("Data list size --> ");
        }
    }

    private void showToastMessage(String msg) {
        Toast.makeText(FragmentLivingRoom.this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.living_room_air_con_study_button:
                    showToastMessage("Air conditioner studying...");
                    break;
                case R.id.living_room_tv_study_button:
                    showToastMessage("TV studying...");
                    break;
                default:
                    break;
            }
        }
    }

    private class ToggleButtonClickListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.living_room_air_conditioner_toggle_button:
                    if(isChecked) {
                        HttpClient.getClient().get("http://192.168.1.16?/led=1", null, new ResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                super.onSuccess(statusCode, headers, responseString);
                                System.out.println("turn OFF~~~");
                            }
                        });
                    } else {
                        HttpClient.getClient().get("http://192.168.1.16/?led=1", null, new ResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                super.onSuccess(statusCode, headers, responseString);
                                System.out.println("turn ON~~~");
                            }
                        });
                    }
                    showToastMessage(isChecked ? "Air conditioner is on" : "Air conditioner is off");
                    break;
                case R.id.play_pause_button:
                    if(isChecked) {
                        HttpClient.getClient().get("http://192.168.1.16?/led=2", null, new ResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                super.onSuccess(statusCode, headers, responseString);
                                System.out.println("turn OFF~~~");
                            }
                        });
                    } else {
                        HttpClient.getClient().get("http://192.168.1.16/?led=2", null, new ResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                super.onSuccess(statusCode, headers, responseString);
                                System.out.println("turn ON~~~");
                            }
                        });
                    }
                    showToastMessage(isChecked ? "TV is on" : "TV is off");
                    break;
            }
        }
    }

    private class ImageButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.living_room_temperature_down_button:
                    showToastMessage("Air conditioner temperature down");
                    break;
                case R.id.living_room_temperature_up_button:
                    showToastMessage("Air conditioner temperature up");
                    break;
                case R.id.channel_plus_button:
                    showToastMessage("next TV channel");
                    break;
                case R.id.channel_minus_button:
                    showToastMessage("previous TV channel");
                    break;
                case R.id.volume_plus_button:
                    showToastMessage("TV volume up");
                    break;
                case R.id.volume_minus_button:
                    showToastMessage("TV volume down");
                    break;
                default:
                    break;
            }
        }
    }



}
