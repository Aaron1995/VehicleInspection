package studio.imedia.vehicleinspection.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import studio.imedia.vehicleinspection.CarInspectionActivity;
import studio.imedia.vehicleinspection.CarInsuranceActivity;
import studio.imedia.vehicleinspection.IllegalQueryActivity;
import studio.imedia.vehicleinspection.InspectionStationActivity;
import studio.imedia.vehicleinspection.OrderInfoActivity;
import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.RepairStationActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {

    private LinearLayout illegalQuery;
    private LinearLayout carInsurance;
    private LinearLayout repairStation;
    private LinearLayout validateCar;
    private RelativeLayout layoutInspectionOneKey; // 一键车检
    private TextView tvCheckOrder; // 查看车检订单

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findView(); // 关联控件
        initEvent(); // 注册监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        illegalQuery = (LinearLayout) getActivity().findViewById(R.id.illegal_query);
        carInsurance = (LinearLayout) getActivity().findViewById(R.id.car_insurance);
        repairStation = (LinearLayout) getActivity().findViewById(R.id.repair_station_nearby);
        validateCar = (LinearLayout) getActivity().findViewById(R.id.validate_car);

        layoutInspectionOneKey = (RelativeLayout) getActivity().findViewById(R.id.layout_inspection_one_key);
        tvCheckOrder = (TextView) getActivity().findViewById(R.id.tv_check_order);
    }

    /**
     * 注册监听事件
     */
    private void initEvent() {
        illegalQuery.setOnClickListener(this);
        carInsurance.setOnClickListener(this);
        repairStation.setOnClickListener(this);
        validateCar.setOnClickListener(this);

        layoutInspectionOneKey.setOnClickListener(this);
        tvCheckOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.illegal_query:
                startActivity(new Intent(getActivity(), IllegalQueryActivity.class));
                break;
            case R.id.car_insurance:
                startActivity(new Intent(getActivity(), CarInsuranceActivity.class));
                break;
            case R.id.repair_station_nearby:
                startActivity(new Intent(getActivity(), RepairStationActivity.class));
                break;
            case R.id.validate_car:
                startActivity(new Intent(getActivity(), CarInspectionActivity.class));
                break;
            case R.id.layout_inspection_one_key:
                startActivity(new Intent(getActivity(), InspectionStationActivity.class));
                break;
            case R.id.tv_check_order:
                startActivity(new Intent(getActivity(), OrderInfoActivity.class));
                break;
        }
    }
}
