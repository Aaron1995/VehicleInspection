package studio.imedia.vehicleinspection;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import studio.imedia.vehicleinspection.fragments.InspectionStationListFragment;
import studio.imedia.vehicleinspection.fragments.InspectionStationMapFragment;

public class InspectionStationActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private ImageView imgSwitch;

    private InspectionStationListFragment fragmentStationList;
    private InspectionStationMapFragment fragmentStationMap;

    private static final int STATION_LIST = 0;
    private static final int STATION_MAP = 1;

    private int curFragment = STATION_LIST;

    private Context mContext = InspectionStationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_station);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
        setSelect(STATION_LIST); // 设置选择为列表显示
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        imgSwitch = (ImageView) findViewById(R.id.img_switch);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
        imgSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
            case R.id.img_switch:
                switch (curFragment) {
                    case STATION_LIST:
                        curFragment = STATION_MAP;
                        setSelect(STATION_MAP);
                        imgSwitch.setImageResource(R.drawable.icon_list_show);
                        break;
                    case STATION_MAP:
                        curFragment = STATION_LIST;
                        setSelect(STATION_LIST);
                        imgSwitch.setImageResource(R.drawable.icon_map_show);
                        break;
                }
//                startActivity(new Intent(mContext, MapActivity.class));
                break;
        }
    }

    /**
     * 设置选择
     * @param position
     */
    private void setSelect(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragments(transaction); // 隐藏fragments
        switch (position) {
            case STATION_LIST:
                if (null == fragmentStationList) {
                    fragmentStationList = new InspectionStationListFragment();
                    transaction.add(R.id.container, fragmentStationList);
                } else {
                    transaction.show(fragmentStationList);
                }
                break;
            case STATION_MAP:
                if (null == fragmentStationMap) {
                    fragmentStationMap = new InspectionStationMapFragment();
                    transaction.add(R.id.container, fragmentStationMap);
                } else {
                    transaction.show(fragmentStationMap);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏fragments
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != fragmentStationList) {
            transaction.hide(fragmentStationList);
        }
        if (null != fragmentStationMap) {
            transaction.hide(fragmentStationMap);
        }
    }
}
