package smart.liyinwang.jn.smarthome.core;

/**
 * Created by ajou on 2015-03-04.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.monitor.FragmentEnvironment;
import smart.liyinwang.jn.smarthome.monitor.FragmentMonitoring;
import smart.liyinwang.jn.smarthome.room.FragmentBedroom;
import smart.liyinwang.jn.smarthome.room.FragmentKitchen;
import smart.liyinwang.jn.smarthome.room.FragmentLivingRoom;

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
}
