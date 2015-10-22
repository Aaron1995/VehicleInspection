package studio.imedia.vehicleinspection.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.SelectTimeWayActivity;
import studio.imedia.vehicleinspection.adapters.MyInspectionStationAdapter;
import studio.imedia.vehicleinspection.bean.InspectionStation;


/**
 * A simple {@link Fragment} subclass.
 */
public class InspectionStationListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvInspectionStation;
    private List<InspectionStation> inspectionStationList;
    private MyInspectionStationAdapter myInspectionStationAdapter;

    public InspectionStationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inspection_station_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findView(); // 关联控件
        initData(); // 初始化数据
        initEvent(); // 初始化监听事件
        putIntoAdapter(); // 放入adapter中
    }

    /**
     * 关联控件
     */
    private void findView() {
        lvInspectionStation = (ListView) getActivity().findViewById(R.id.lv_station);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (null == inspectionStationList) {
            inspectionStationList = new ArrayList<>();
        }
        for (int i = 0; i < 9; i++) {
            InspectionStation inspectionStation = new InspectionStation();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_station);
            inspectionStation.setStationPic(bitmap);

            inspectionStation.setStationName("杭州交警支队第三检测站");

            inspectionStation.setStartTimeMorningSummer("8:00");
            inspectionStation.setEndTimeMorningSummer("11:30");
            inspectionStation.setStartTimeAfternoonSummer("13:30");
            inspectionStation.setEndTimeAfternoonSummer("17:30");
            inspectionStation.setStartTimeMorningWinter("8:00");
            inspectionStation.setEndTimeMorningWinter("11:00");
            inspectionStation.setStartTimeAfternoonWinter("13:30");
            inspectionStation.setEndTimeAfternoonWinter("16:30");

            inspectionStation.setStarNum(5);
            inspectionStation.setDistance(500);

            inspectionStation.setDay1(23);
            inspectionStation.setDay2(24);
            inspectionStation.setDay3(25);
            inspectionStation.setDay4(26);
            inspectionStation.setDay5(27);

            inspectionStation.setDay1Value(44);
            inspectionStation.setDay2Value(14);
            inspectionStation.setDay3Value(45);
            inspectionStation.setDay4Value(32);
            inspectionStation.setDay5Value(24);

            inspectionStationList.add(inspectionStation);
        }
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        lvInspectionStation.setOnItemClickListener(this);
    }

    /**
     * 将数据放入adapter中
     */
    private void putIntoAdapter() {
        if (null == myInspectionStationAdapter) {
            myInspectionStationAdapter = new MyInspectionStationAdapter(getActivity(), inspectionStationList);
        }
        lvInspectionStation.setAdapter(myInspectionStationAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_station:
                switch (position) {
                    default:
                        startActivity(new Intent(getActivity(), SelectTimeWayActivity.class));
                        break;
                }
                break;
        }
    }
}
