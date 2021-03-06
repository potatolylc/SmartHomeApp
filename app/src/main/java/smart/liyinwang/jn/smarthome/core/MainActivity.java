package smart.liyinwang.jn.smarthome.core;

/**
 * Created by ajou on 2015-03-04.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.http.URIRepository;
import smart.liyinwang.jn.smarthome.monitor.FragmentEnvironment;
import smart.liyinwang.jn.smarthome.monitor.FragmentMonitoring;
import smart.liyinwang.jn.smarthome.room.FragmentBedroom;
import smart.liyinwang.jn.smarthome.room.FragmentKitchen;
import smart.liyinwang.jn.smarthome.room.FragmentLivingRoom;
import smart.liyinwang.jn.smarthome.service.PushGeoLocationServiceImpl;
import smart.liyinwang.jn.smarthome.service.PushWeatherServiceImpl;
import smart.liyinwang.jn.smarthome.stomp.ListenerSubscription;
import smart.liyinwang.jn.smarthome.stomp.ListenerWSNetwork;
import smart.liyinwang.jn.smarthome.stomp.Stomp;
import smart.liyinwang.jn.smarthome.stomp.Subscription;

public class MainActivity extends ActionBarActivity implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;

    private FragmentEnvironment mFragEnvir;
    private FragmentLivingRoom mFragLiving;
    private FragmentBedroom mFragBed;
    private FragmentKitchen mFragKitch;
    private FragmentMonitoring mFragMonit;

    // list of fragments
    private ArrayList<Fragment> mFragList;

    // list of fragment titles
    private ArrayList<String> mFragTitleList;

    // service intents
    Intent geoServiceIntent;
    Intent weatherServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init toolbar
        Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        // init tab host
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);

        // init fragments
        mFragEnvir = FragmentEnvironment.newInstance();
        mFragLiving = FragmentLivingRoom.newInstance();
        mFragKitch = FragmentKitchen.newInstance();
        mFragBed = FragmentBedroom.newInstance();
        mFragMonit = FragmentMonitoring.newInstance();

        // add all fragments into array list
        mFragList = new ArrayList<Fragment>();
        mFragList.add(mFragEnvir);
        mFragList.add(mFragLiving);
        mFragList.add(mFragBed);
        mFragList.add(mFragKitch);
        mFragList.add(mFragMonit);

        // add all fragment titles into array list
        mFragTitleList = new ArrayList<String>();
        mFragTitleList.add("Environment");
        mFragTitleList.add("Living Room");
        mFragTitleList.add("Bedroom");
        mFragTitleList.add("Kitchen");
        mFragTitleList.add("Monitoring");

        for(String name : mFragTitleList) {
            System.out.println(name);
        }

        // init view pager
        pager = (ViewPager) this.findViewById(R.id.pager);

        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

        // start geo location and weather push service
        // TODO: get options in shared preferences to enable or disable push service
        if(true) {
            startPushWeatherService();
        }
        if(true) {
            startPushGeoLocationService();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.this.startService(geoServiceIntent);
        MainActivity.this.startService(weatherServiceIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainActivity.this.stopService(geoServiceIntent);
        MainActivity.this.stopService(weatherServiceIntent);
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            return mFragList.get(num);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragTitleList.get(position);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private void startPushWeatherService() {
        weatherServiceIntent = new Intent(MainActivity.this, PushWeatherServiceImpl.class);
        weatherServiceIntent.setData(Uri.parse(URIRepository.PUSH_WEATHER_INFO));
        MainActivity.this.startService(weatherServiceIntent);
    }

    private void startPushGeoLocationService() {
        geoServiceIntent = new Intent(MainActivity.this, PushGeoLocationServiceImpl.class);
        geoServiceIntent.setData(Uri.parse(URIRepository.PUSH_GEO_INFO));
        MainActivity.this.startService(geoServiceIntent);
    }


}
