/**
 *   HelloCharts
     Copyright 2014 Leszek Wach

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
 */
package smart.liyinwang.jn.smarthome.monitor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;
import model.SensorData;
import service.MonitoringService;
import service.MonitoringServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import utils.Utils;


/**
 * Monitoring Fragment:
 * Showing graph of temperature and brightness
 * of the past one hour.
 */
public class FragmentMonitoring extends Fragment {
    // chart view
    private ComboLineColumnChartView mChartView;
    private ComboLineColumnChartData mChartData;

    // chart numbers
    private int mNumberOfLines = 1;
    private int mMaxNumberOfLines = 4;
    private int mNumberOfPoints = 0;

    // chart booleans
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasPoints = true;
    private boolean hasLines = true;
    private boolean hasSeperationLines = true;
    private boolean isCubic = false;
    private boolean hasLabels = false;

    // main view
    private View mMainView;

    // service and list for loading data
    private MonitoringService mMonitoringService;
    List<SensorData> mTempSensorDataList;
    List<SensorData> mHumiditySensorDataList;

    public static FragmentMonitoring newInstance() {
        Log.d("MonitoringFragment log", "--> new Instance()");
        FragmentMonitoring fragment = new FragmentMonitoring();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMonitoring() {}

    public void loadData() {
        mMonitoringService = new MonitoringServiceImpl();

        mTempSensorDataList = new ArrayList<SensorData>();
        mHumiditySensorDataList = new ArrayList<SensorData>();

        mTempSensorDataList =
                mMonitoringService.getMonitoringDataList(
                        Utils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_TEMPERATURE,
                        Utils.DUMMY_START_TIME, Utils.DUMMY_END_TIME);
        mHumiditySensorDataList =
                mMonitoringService.getMonitoringDataList(
                        Utils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_HUMIDITY,
                        Utils.DUMMY_START_TIME, Utils.DUMMY_END_TIME);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreate()");
        super.onCreate(savedInstanceState);

        // load data from server
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreateView()");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monitoring, container, false);

        mChartView = (ComboLineColumnChartView) view.findViewById(R.id.monitoring_temperature_chart);
        mChartView.setOnValueTouchListener(new ComboLineColumnChartOnValueSelectListener() {
            @Override
            public void onColumnValueSelected(int i, int i2, SubcolumnValue subcolumnValue) {

            }

            @Override
            public void onPointValueSelected(int i, int i2, PointValue pointValue) {

            }

            @Override
            public void onValueDeselected() {

            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        Log.d("MonitoringFragment log", "--> onAttach()");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("MonitoringFragment log", "--> onDetach()");
        super.onDetach();
    }

    @Override
    public void onStart() {
        Log.d("MonitoringFragment log", "--> onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("MonitoringFragment log", "--> onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("MonitoringFragment log", "--> onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("MonitoringFragment log", "--> onStop()");
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("MonitoringFragment log", "--> setUserVisibleHint()");
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            // debug
            System.out.println("Data list size --> " + mTempSensorDataList.size() + " " + mHumiditySensorDataList.size());

            mNumberOfPoints = mTempSensorDataList.size();

            generateGraph();
        }
    }

    private void generateGraph() {
        mChartData = new ComboLineColumnChartData(generateColumnGraph(), generateLineGraph());

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            axisX.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisY.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisY.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextSize(15);
            axisY.setTextSize(15);
            axisX.setHasSeparationLine(hasSeperationLines);
            if (hasAxesNames) {
                axisX.setName("Time");
                axisY.setName("Value");
            }
            mChartData.setAxisXBottom(axisX);
            mChartData.setAxisYLeft(axisY);
        } else {
            mChartData.setAxisXBottom(null);
            mChartData.setAxisYLeft(null);
        }

        mChartView.setComboLineColumnChartData(mChartData);
    }

    private LineChartData generateLineGraph() {
        List<Line> lines = new ArrayList<Line>();
        for(int i = 0; i < mNumberOfLines; i++) {
            List<PointValue> values = new ArrayList<PointValue>();
            for(int j = 0; j < mNumberOfPoints; j++) {
                double d = (double) mTempSensorDataList.get(j).getSensorDataValue();
                float f = (float) d;
                values.add(new PointValue(j, f));
            }

            Line line = new Line(values);
            line.setColor(getResources().getColor(R.color.basic_color));
            line.setCubic(isCubic);
            line.setHasLabels(hasLabels);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            lines.add(line);
        }

        LineChartData lineChartData = new LineChartData(lines);
        return lineChartData;
    }

    private ColumnChartData generateColumnGraph() {
        int numberOfColumns = mHumiditySensorDataList.size();

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for(int i = 0; i < numberOfColumns; i++) {
            values = new ArrayList<SubcolumnValue>();
            //for(int j = 0; j < numberOfColumns; j++) {
                double d = (double) mHumiditySensorDataList.get(0).getSensorDataValue();
                float f = (float) d;
                values.add(new SubcolumnValue(
                        f,
                        getResources().getColor(R.color.light_blue_color)));
            //}
            columns.add(new Column(values));
        }

        ColumnChartData columnChartData = new ColumnChartData(columns);
        return columnChartData;
    }

    private void toggleLines() {
        hasLines = !hasLines;

        generateGraph();
    }

    private void toggleSeperationLines() {
        hasSeperationLines = !hasSeperationLines;

        generateGraph();
    }

    private void togglePoints() {
        hasPoints = !hasPoints;

        generateGraph();
    }

    private void toggleCubic() {
        isCubic = !isCubic;

        generateGraph();
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;

        generateGraph();
    }

    private void toggleAxes() {
        hasAxes = !hasAxes;

        generateGraph();
    }

    private void toggleAxesNames() {
        hasAxesNames = !hasAxesNames;

        generateGraph();
    }

    private void addLineToData() {
        if (mChartData.getLineChartData().getLines().size() >= mMaxNumberOfLines) {
            Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++mNumberOfLines;
        }

        generateGraph();
    }


    private void prepareDataAnimation() {

        // Line animations
        for (Line line : mChartData.getLineChartData().getLines()) {
            for (PointValue value : line.getValues()) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.getX(), (float) Math.random() * 50 + 5);
            }
        }

        // Columns animations
        for (Column column : mChartData.getColumnChartData().getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 50 + 5);
            }
        }
    }
}
