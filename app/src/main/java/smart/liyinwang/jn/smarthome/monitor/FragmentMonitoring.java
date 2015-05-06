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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import smart.liyinwang.jn.smarthome.model.SensorData;
import smart.liyinwang.jn.smarthome.service.MonitoringService;
import smart.liyinwang.jn.smarthome.service.MonitoringServiceImpl;
import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.utils.DummyUtils;
import smart.liyinwang.jn.smarthome.utils.Utils;


/**
 * Monitoring Fragment:
 * Showing graph of temperature and brightness
 * of the past one hour.
 */
public class FragmentMonitoring extends Fragment {
    // column line chart view
    private ComboLineColumnChartView mCLChartView;
    private ComboLineColumnChartData mCLChartData;

    // line chart view
    private LineChartView mLineChartView;
    private LineChartData mLineChartData;

    // column line chart numbers
    private int mCLNumberOfLines = 1;
    private int mCLMaxNumberOfLines = 4;
    private int mCLNumberOfPoints = 0;

    // line chart numbers
    private int mLNumberOfLines = 1;
    private int mLMaxNumberOfLines = 4;
    private int mLNumberOfPoints = 0;

    // column line chart booleans
    private boolean mCLhasAxes = true;
    private boolean mCLhasAxesNames = true;
    private boolean mCLhasPoints = true;
    private boolean mCLhasLines = true;
    private boolean mCLhasSeperationLines = true;
    private boolean mCLisCubic = false;
    private boolean mCLhasLabels = false;
    private boolean mCLhasLabelForSelected = true;

    // line chart booleans
    private boolean mLhasAxes = true;
    private boolean mLhasAxesNames = true;
    private boolean mLhasPoints = true;
    private boolean mLhasLines = true;
    private boolean mLisFilled = false;
    private boolean mLisCubic = false;
    private boolean mLhasLabels = false;
    private boolean mLhasLabelForSelected = true;
    private ValueShape mLlineShape = ValueShape.CIRCLE;

    // main view
    private View mView;

    // service and list for loading data
    private MonitoringService mMonitoringService;
    List<SensorData> mTempSensorDataList;
    List<SensorData> mHumiditySensorDataList;
    List<SensorData> mLightSensorDataList;

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
        mLightSensorDataList = new ArrayList<SensorData>();

        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - 1500 * 3600);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.DATE_FORMAT);
        String startTimeStr = dateFormat.format(startTime);
        String endTimeStr = dateFormat.format(endTime);
        System.out.println(startTimeStr + " " + endTimeStr);

        mTempSensorDataList =
                mMonitoringService.getMonitoringDataList(
                        DummyUtils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_TEMPERATURE,
                        startTimeStr, endTimeStr);
        mHumiditySensorDataList =
                mMonitoringService.getMonitoringDataList(
                        DummyUtils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_HUMIDITY,
                        startTimeStr, endTimeStr);
        mLightSensorDataList = mMonitoringService.getMonitoringDataList(
                        DummyUtils.DUMMY_ENVIRONMENT_SENSOR_SERIAL_NUM_LIGHT_BRIGHTNESS,
                startTimeStr, endTimeStr);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreate()");
        super.onCreate(savedInstanceState);

        // load data from server
        loadData();
    }

    private void initCLChartView() {
        mCLChartView = (ComboLineColumnChartView) mView.findViewById(R.id.monitoring_temperature_chart);
        mCLChartView.setOnValueTouchListener(new ComboLineColumnChartOnValueSelectListener() {
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
    }

    private void initLineChartView() {
        mLineChartView = (LineChartView) mView.findViewById(R.id.monitoring_light_brightness_chart);
        mLineChartView.setOnValueTouchListener(new LineChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, int i2, PointValue pointValue) {

            }

            @Override
            public void onValueDeselected() {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MonitoringFragment log", "--> onCreateView()");
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_monitoring, container, false);

        initCLChartView();
        initLineChartView();

        return mView;
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
            System.out.println("Data list size --> "
                    + mTempSensorDataList.size() + " "
                    + mHumiditySensorDataList.size() + " "
                    + mLightSensorDataList.size());

            mCLNumberOfPoints = mTempSensorDataList.size();
            mLNumberOfPoints = mLightSensorDataList.size();

            generateGraph();
        }
    }

    private void generateGraph() {
        // column line chart generation
        mCLChartData = new ComboLineColumnChartData(generateColumnGraph(), generateCLLineGraph());
        if (mCLhasAxes) {
            Axis axisX = new Axis();
            Axis axisYLeft = new Axis().setHasLines(false);
            Axis axisYRight = new Axis().setHasLines(mCLhasLines);
            axisX.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisYLeft.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisYRight.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisYLeft.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisYRight.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextSize(15);
            axisYLeft.setTextSize(15);
            axisYRight.setTextSize(15);
            axisX.setHasSeparationLine(mCLhasSeperationLines);
            if (mCLhasAxesNames) {
                axisX.setName("Time");
                axisYLeft.setName("Humidity");
                axisYRight.setName("Temperature");
            }
            mCLChartData.setAxisXBottom(axisX);
            mCLChartData.setAxisYLeft(axisYLeft);
            mCLChartData.setAxisYRight(axisYRight);
        } else {
            mCLChartData.setAxisXBottom(null);
            mCLChartData.setAxisYLeft(null);
            mCLChartData.setAxisYRight(null);
        }
        mCLChartView.setComboLineColumnChartData(mCLChartData);

        // line chart generation
        mLineChartData = generateLLineGraph();
        if(mLhasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            axisX.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisY.setLineColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisY.setTextColor(getResources().getColor(R.color.light_grey_color));
            axisX.setTextSize(15);
            axisY.setTextSize(15);
            if (mLhasAxesNames) {
                axisX.setName("Time");
                axisY.setName("Brightness");
            }
            mLineChartData.setAxisXBottom(axisX);
            mLineChartData.setAxisYLeft(axisY);
        } else {
            mLineChartData.setAxisXBottom(null);
            mLineChartData.setAxisYLeft(null);
        }
        mLineChartData.setBaseValue(0);
        mLineChartView.setLineChartData(mLineChartData);
    }

    private LineChartData generateCLLineGraph() {
        List<Line> lines = new ArrayList<Line>();
        for(int i = 0; i < mCLNumberOfLines; i++) {
            List<PointValue> values = new ArrayList<PointValue>();
            for(int j = 0; j < mCLNumberOfPoints; j++) {
                double d = (double) mTempSensorDataList.get(j).getSensorDataValue();
                float f = (float) d;
                values.add(new PointValue(j, f));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_BLUE);
            line.setCubic(mCLisCubic);
            line.setHasLabels(mCLhasLabels);
            line.setHasLabelsOnlyForSelected(mCLhasLabelForSelected);
            line.setHasLines(mCLhasLines);
            line.setHasPoints(mCLhasPoints);
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
                double d = mHumiditySensorDataList.get(i).getSensorDataValue();
                float f = (float) d;
                values.add(new SubcolumnValue(
                        f,
                        getResources().getColor(R.color.light_blue_color)));
            //}

            Column column = new Column(values);
            column.setHasLabelsOnlyForSelected(mCLhasLabelForSelected);
            columns.add(column);
        }

        ColumnChartData columnChartData = new ColumnChartData(columns);
        return columnChartData;
    }

    private LineChartData generateLLineGraph() {
        List<Line> lines = new ArrayList<Line>();
        for(int i = 0; i < mLNumberOfLines; i++) {
            List<PointValue> values = new ArrayList<PointValue>();
            for(int j = 0; j < mLNumberOfPoints; j++) {
                values.add(new PointValue(j, (int) mLightSensorDataList.get(j).getSensorDataValue()));
            }

            Line line = new Line(values);
            line.setColor(getResources().getColor(R.color.sky_blue_color));
            line.setShape(mLlineShape);
            line.setCubic(mLisCubic);
            line.setFilled(mLisFilled);
            line.setHasLabels(mLhasLabels);
            line.setHasLines(mLhasLines);
            line.setHasPoints(mLhasPoints);
            line.setHasLabelsOnlyForSelected(mLhasLabelForSelected);
            lines.add(line);
        }

        LineChartData lineChartData = new LineChartData(lines);
        return lineChartData;
    }

    private void toggleLines() {
        mCLhasLines = !mCLhasLines;

        generateGraph();
    }

    private void toggleSeperationLines() {
        mCLhasSeperationLines = !mCLhasSeperationLines;

        generateGraph();
    }

    private void togglePoints() {
        mCLhasPoints = !mCLhasPoints;

        generateGraph();
    }

    private void toggleCubic() {
        mCLisCubic = !mCLisCubic;

        generateGraph();
    }

    private void toggleLabels() {
        mCLhasLabels = !mCLhasLabels;

        generateGraph();
    }

    private void toggleAxes() {
        mCLhasAxes = !mCLhasAxes;

        generateGraph();
    }

    private void toggleAxesNames() {
        mCLhasAxesNames = !mCLhasAxesNames;

        generateGraph();
    }

    private void addLineToData() {
        if (mCLChartData.getLineChartData().getLines().size() >= mCLMaxNumberOfLines) {
            Toast.makeText(getActivity(), "Samples app uses max 4 lines!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ++mCLNumberOfLines;
        }

        generateGraph();
    }


    private void prepareDataAnimation() {

        // Line animations
        for (Line line : mCLChartData.getLineChartData().getLines()) {
            for (PointValue value : line.getValues()) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.getX(), (float) Math.random() * 50 + 5);
            }
        }

        // Columns animations
        for (Column column : mCLChartData.getColumnChartData().getColumns()) {
            for (SubcolumnValue value : column.getValues()) {
                value.setTarget((float) Math.random() * 50 + 5);
            }
        }
    }
}
