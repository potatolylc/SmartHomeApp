package smart.liyinwang.jn.smarthome.room;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smart.liyinwang.jn.smarthome.R;


/**
 * BedRoom Fragment:
 * Control light, humidifier and air conditioner
 * and send event log data to server.
 */
public class FragmentBedroom extends Fragment {

    public static FragmentBedroom newInstance() {
        Log.d("BedroomFragment log", "--> new Instance()");
        FragmentBedroom fragment = new FragmentBedroom();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBedroom() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("BedroomFragment log", "--> new Instance()");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("BedroomFragment log", "--> new Instance()");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bedroom, container, false);
    }
    @Override
    public void onAttach(Activity activity) {
        Log.d("BedroomFragment log", "--> onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("BedroomFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("BedroomFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("BedroomFragment log", "--> onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("BedroomFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("BedroomFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("BedroomFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            System.out.println("Data list size --> ");
        }
    }

}
