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
 * Kitchen Fragment:
 *
 */
public class FragmentKitchen extends Fragment {

    public static FragmentKitchen newInstance() {
        Log.d("KitchenFragment log", "--> new Instance()");
        FragmentKitchen fragment = new FragmentKitchen();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentKitchen() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("KitchenFragment log", "--> new Instance()");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("KitchenFragment log", "--> new Instance()");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kitchen, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("KitchenFragment log", "--> onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("KitchenFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("KitchenFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("KitchenFragment log", "--> onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("KitchenFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("KitchenFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("KitchenFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            System.out.println("Data list size --> ");
        }
    }
}
