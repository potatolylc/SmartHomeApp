package smart.liyinwang.jn.smarthome.room;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import smart.liyinwang.jn.smarthome.R;


/**
 * LivingRoom Fragment:
 * Control TV and air conditioner
 * and send event log data to server.
 */
public class FragmentLivingRoom extends Fragment {
    // components of air conditioner
    private ToggleButton mAirConToggleButton;
    private TextView mAirConTextView;
    private SeekBar mAirConSeekBar;

    // components of television
    private ToggleButton mTVToggleButton;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("LivingRoomFragment log", "--> new Instance()");
        mView = inflater.inflate(R.layout.fragment_living_room, container, false);

        // init components of air conditioner
        mAirConToggleButton = (ToggleButton)mView.findViewById(R.id.living_room_air_conditioner_toggle_button);
        mAirConToggleButton.setTextOff("ON");
        mAirConToggleButton.setTextOn("OFF");
        mAirConTextView = (TextView) mView.findViewById(R.id.living_room_temperature_text_view);
        mAirConSeekBar = (SeekBar) mView.findViewById(R.id.living_room_temperature_control_bar);

        // init component of television
        mTVToggleButton = (ToggleButton) mView.findViewById(R.id.play_pause_button);
        mTVToggleButton.setTextOff(null);
        mTVToggleButton.setTextOn(null);
        mTVChannelUpButton = (ImageButton) mView.findViewById(R.id.channel_plus_button);
        mTVChannelDownButton = (ImageButton) mView.findViewById(R.id.channel_minus_button);
        mTVVolumeUpButton = (ImageButton) mView.findViewById(R.id.volume_plus_button);
        mTVVolumeDownButton = (ImageButton) mView.findViewById(R.id.volume_minus_button);

        // event trigger
        mAirConToggleButton.setOnCheckedChangeListener(new ToggleButtonClickListener());
        mTVToggleButton.setOnCheckedChangeListener(new ToggleButtonClickListener());
        mAirConSeekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
        mTVChannelUpButton.setOnClickListener(new ImageButtonClickListener());
        mTVChannelDownButton.setOnClickListener(new ImageButtonClickListener());
        mTVVolumeUpButton.setOnClickListener(new ImageButtonClickListener());
        mTVVolumeDownButton.setOnClickListener(new ImageButtonClickListener());

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

    private class ToggleButtonClickListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.living_room_air_conditioner_toggle_button:
                    Toast.makeText(FragmentLivingRoom.this.getActivity(),
                            (isChecked ? "Air conditioner is on" : "Air conditioner is off"),
                            Toast.LENGTH_SHORT).show();
                    System.out.println(isChecked ? "Air conditioner is on" : "Air conditioner is off");
                    break;
                case R.id.play_pause_button:
                    Toast.makeText(FragmentLivingRoom.this.getActivity(),
                            (isChecked ? "TV is on" : "TV is off"),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private class ImageButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.channel_plus_button:
                    System.out.println("channel plus!");
                    break;
                default:
                    break;
            }
        }
    }

    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

}
