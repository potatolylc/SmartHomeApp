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
 * LivingRoom Fragment:
 * Control TV and air conditioner
 * and send event log data to server.
 */
public class FragmentLivingRoom extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_living_room, container, false);
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

}
