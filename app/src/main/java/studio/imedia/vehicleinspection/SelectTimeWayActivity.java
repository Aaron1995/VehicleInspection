package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Calendar;
import java.util.LinkedList;

import studio.imedia.vehicleinspection.bean.InspectionStation;
import studio.imedia.vehicleinspection.views.MyDatePickerDialog;
import studio.imedia.vehicleinspection.views.MyTimePickerDialog;

public class SelectTimeWayActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private Button btnCommit;
    private FrameLayout layoutChart;

    private RelativeLayout layoutSelectDate;
    private RelativeLayout layoutSelectTime;

    private TextView tvDate;
    private TextView tvTime;

    private Context mContext = SelectTimeWayActivity.this;

    private XYMultipleSeriesDataset mDataset;
    private XYSeries series;
    private XYMultipleSeriesRenderer mSeriesRenderer = null;
    private GraphicalView mGraphicalView;

    private LinkedList<Integer> mDataList = new LinkedList<>();

    private static final String TITLE = "title";
    private static final int AMOUNT_VALUE = 6;
    private static final int MAX_X = 7;
    private static final int MIN_X = 0;
    private static final int MAX_Y = 40;
    private static final int MIN_Y = 10;

    private static final int LABELS_X = 7;
    private static final int LABELS_Y = 5;

    private static final int TYPE_SHOW_DAILY_DATA = 0;
    private static final int TYPE_SHOW_TIMELY_DATA = 1;

    //    private static final int[] valuesY = {15, 18, 26, 33, 28, 35};
    private static final int[][] valuesY = {
            {15, 18, 26, 33, 28, 35},
            {34, 24, 21, 17, 25, 24},
            {17, 38, 26, 19, 24, 21},
            {27, 21, 19, 17, 17, 23}
    };
    private static final String[] xLabelDate = {"24日", "25日", "26日", "27日", "28日", "29日"};
    private static final String[] xLabelTime = {"8:00~10:00", "10:00~11:30", "13:00~14:00", "14:00~15:00", "15:00~16:00", "16:00~17:30"};

    private int year = 1991;
    private int month = 1;
    private int day = 1;
    private int hour = 0;
    private int minute = 0;

    private static final int TYPE_DATE = 0;
    private static final int TYPE_TIME = 1;

    private int indexOfvalueY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time_way);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件

        initGraphData(); // 初始化图表数据
        initGraph(); // 初始化柱状图
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        btnCommit = (Button) findViewById(R.id.btn_commit);
        layoutChart = (FrameLayout) findViewById(R.id.chart);
        layoutSelectDate = (RelativeLayout) findViewById(R.id.layout_select_date);
        layoutSelectTime = (RelativeLayout) findViewById(R.id.layout_select_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        layoutSelectDate.setOnClickListener(this);
        layoutSelectTime.setOnClickListener(this);
    }

    /**
     * 初始化图表数据
     */
    private void initGraphData() {
        if (null == mDataList) {
            mDataList = new LinkedList<>();
        }
        for (int i = 0; i < AMOUNT_VALUE; i++) {
            mDataList.add(valuesY[0][i]);
        }
    }

    /**
     * 初始化柱状图
     */
    private void initGraph() {
        // 初始化呼吸心率曲线
        mDataset = new XYMultipleSeriesDataset();
        series = new XYSeries(TITLE);

        // 绘制折线
        int i = 1;
        for (Integer data : mDataList) {
            series.add(i++, data);
        }

        mDataset.addSeries(series);
        mGraphicalView = initChart(mDataset);
    }

    /**
     * 刷新图标数据
     *
     * @param valueY
     */
    private void refreshGraphData(int[] valueY, int type) {
        if (null == mDataList) {
            mDataList = new LinkedList<>();
        }
        if (null == mDataset) {
            mDataset = new XYMultipleSeriesDataset();
        }
        if (null == series) {
            series = new XYSeries(TITLE);
        }

        mDataset.removeSeries(series);
        series.clear();
        mDataList.clear();
        for (int i = 0; i < valueY.length; i++) {
            mDataList.add(valueY[i]);
        }

        int i = 1;
        for (Integer data : mDataList) {
            series.add(i++, data);
        }
        mDataset.addSeries(series);

        switch (type) {
            case TYPE_SHOW_DAILY_DATA:
                Log.d("test", "daily");
                refreshXTextLabel(xLabelDate);
                break;
            case TYPE_SHOW_TIMELY_DATA:
                refreshXTextLabel(xLabelTime);
                break;
        }

        mGraphicalView.repaint();
        layoutBack.postInvalidate();
    }

    /**
     * 修改x轴刻度标签
     *
     * @param labels
     */
    private void refreshXTextLabel(String[] labels) {
        for (int i = MIN_X; i <= MAX_X; i++) {
            if (i > MIN_X && i < MAX_X) {
                mSeriesRenderer.addXTextLabel(i, labels[i - 1]);
            }
        }
    }

    private GraphicalView initChart(XYMultipleSeriesDataset dataset) {
        mSeriesRenderer = getRenderer();

        GraphicalView view = ChartFactory.getBarChartView(mContext,
                dataset, mSeriesRenderer, BarChart.Type.DEFAULT);
        view.setBackgroundColor(Color.WHITE);

        layoutChart.addView(view);
        return view;
    }

    /**
     * 获取renderer
     *
     * @return
     */
    private XYMultipleSeriesRenderer getRenderer() {
        XYMultipleSeriesRenderer multipleSeriesRenderer = new XYMultipleSeriesRenderer();
        multipleSeriesRenderer.setZoomEnabled(false);
        multipleSeriesRenderer.setZoomButtonsVisible(false);
        multipleSeriesRenderer.setBarSpacing(0.7);

        XYSeriesRenderer seriesRenderer = new XYSeriesRenderer();
        seriesRenderer.setColor(getResources().getColor(R.color.color_chart_bar_graph));
//        seriesRenderer.setPointStyle(PointStyle.CIRCLE);
//        seriesRenderer.setFillPoints(true);

        multipleSeriesRenderer.addSeriesRenderer(seriesRenderer);
        setSettings(multipleSeriesRenderer);
        return multipleSeriesRenderer;

    }

    /**
     * 图标设置
     */
    private void setSettings(XYMultipleSeriesRenderer r) {
        r.setAxisTitleTextSize(20);
        r.setShowLegend(false);  // 是否显示图例文字
        r.setLabelsTextSize(24); // 设置轴标签文本大小
//        r.setPointSize(5f); // 设置点的大小
        r.setAxesColor(getResources().getColor(R.color.color_divider)); // 坐标轴颜色
        r.setLabelsColor(getResources().getColor(R.color.color_text_default));
        r.setDisplayValues(true); // 是否显示数值
        r.setZoomEnabled(false, false); // xy轴放大缩小设置
        r.setPanEnabled(false, false); // xy轴拖动设置
        r.setMarginsColor(Color.WHITE);
        r.setBackgroundColor(Color.WHITE);
        r.setApplyBackgroundColor(true);
        r.setFitLegend(true); // 设置是否调整合适的位置

        r.setXAxisMin(MIN_X); // 设置X轴最小值是MIN_X
        r.setXAxisMax(MAX_X); // 设置X轴最大值是MAX_X
        r.setXLabelsColor(getResources().getColor(R.color.color_text_default));
        r.setXLabels(0);
        // X轴刻度文字
        for (int i = MIN_X; i <= MAX_X; i++) {
            if (i > MIN_X && i < MAX_X) {
                r.addXTextLabel(i, xLabelDate[i - 1]);
            }
        }

        r.setYAxisMin(MIN_Y);
        r.setYAxisMax(MAX_Y);
        r.setYLabels(LABELS_Y);
        r.setYLabelsColor(0, getResources().getColor(R.color.color_divider));
        r.setYLabelsAlign(Paint.Align.CENTER.RIGHT);
        r.setGridColor(getResources().getColor(R.color.color_divider));
        r.setShowGrid(true);
//        r.setShowCustomTextGrid(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, InspectionStationActivity.class));
                finish();
                break;
            case R.id.btn_commit:
                startActivity(new Intent(mContext, SelectMasterActivity.class));
                break;
            case R.id.layout_select_date:
                String dateContent = tvDate.getText().toString();
                if (dateContent.isEmpty()) {
                    getCurDate(); // 获取当前日期
                } else {
                    parseDateTimeFromTextView(tvDate, TYPE_DATE);
                }
                showDatePicker();
                tvTime.setText("");
                break;
            case R.id.layout_select_time:
                String timeContent = tvTime.getText().toString();
                if (timeContent.isEmpty()) {
                    getCurTime(); // 获取当前时间
                } else {
                    parseDateTimeFromTextView(tvTime, TYPE_TIME);
                }
                showTimePicker();
                break;
        }
    }

    /**
     * 显示日期选择器弹出框
     */
    private void showDatePicker() {
        new MyDatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String yearSelected = formatNum(year);
                String monthSelected = formatNum(monthOfYear);
                String daySelected = formatNum(dayOfMonth);
                tvDate.setText(monthSelected + "月" + daySelected + "日");

                switchGraph(TYPE_SHOW_DAILY_DATA);
            }
        }, year, month, day).show();
    }

    /**
     * 显示时间选择器弹出框
     */
    private void showTimePicker() {
        new MyTimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hourSelected = formatNum(hourOfDay);
                String minuteSelected = formatNum(minute);
                tvTime.setText(hourOfDay + ":" + minuteSelected);

                switchGraph(TYPE_SHOW_TIMELY_DATA); // 切换柱状图数值
            }
        }, hour, minute, true).show();
    }

    /**
     * 获取当前日期
     */
    private void getCurDate() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前时间
     */
    private void getCurTime() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    /**
     * 规范数字，如果传入的数小于10，则返回两位字符串
     * 如传入7，则返回“07”
     *
     * @param num
     * @return
     */
    private String formatNum(int num) {
        if (num < 10)
            return "0" + num;
        else
            return "" + num;
    }

    /**
     * 从EditText中选择日期、时间
     *
     * @param textView
     * @param type
     */
    private void parseDateTimeFromTextView(TextView textView, int type) {
        String content = textView.getText().toString();
        switch (type) {
            case TYPE_DATE:
                month = Integer.parseInt(content.substring(0, content.indexOf("月")));
                day = Integer.parseInt(content.substring(content.indexOf("月") + 1, content.indexOf("日")));
                break;
            case TYPE_TIME:
                hour = Integer.parseInt(content.substring(0, content.indexOf(":")));
                minute = Integer.parseInt(content.substring(content.indexOf(":") + 1));
                break;
        }
    }

    private int getIndexOfvalueY(int type) {
        int sum = 0;
        switch (type) {
            case TYPE_SHOW_DAILY_DATA:
                sum = month + day;
                break;
            case TYPE_SHOW_TIMELY_DATA:
                sum = hour + minute;
                break;
        }
        return sum % 4;
    }

    private void switchGraph(int type) {
        indexOfvalueY = getIndexOfvalueY(type);
        refreshGraphData(valuesY[indexOfvalueY], type);
    }
}
