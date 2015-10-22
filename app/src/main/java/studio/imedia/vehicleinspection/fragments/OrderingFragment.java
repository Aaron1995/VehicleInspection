package studio.imedia.vehicleinspection.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.OrderInfoActivity;
import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.adapters.MyOrderAdapter;
import studio.imedia.vehicleinspection.bean.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderingFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvOrdering;
    private List<Order> orderList;

    private MyOrderAdapter myOrderAdapter;

    public OrderingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ordering, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
        initData(); // 初始化
        putIntoAdapter(); // 将数据放入adapter中
    }

    /**
     * 关联控件
     */
    private void findView() {
        lvOrdering = (ListView) getActivity().findViewById(R.id.lv_ordering);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        lvOrdering.setOnItemClickListener(this);
    }

    /**
     *
     */
    private void initData() {
        if (null == orderList) {
            orderList = new ArrayList<>();
        }
        for (int i = 0; i < 5; i++) {
            Order order = new Order();
            order.setStation("杭州交警支队第三检测站");
            order.setYear(2015);
            order.setMonth(10);
            order.setDay(i+20);
            order.setPrice(160);
            order.setIsOrdered(false);
            order.setIsRated(false);
            orderList.add(order);
        }
    }

    /**
     * 将数据放入adapter中
     */
    private void putIntoAdapter() {
        if (null == myOrderAdapter) {
            myOrderAdapter = new MyOrderAdapter(getActivity(), orderList);
        }
        lvOrdering.setAdapter(myOrderAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_ordering:
                switch (position) {
                    default:
                        startActivity(new Intent(getActivity(), OrderInfoActivity.class));
                        break;
                }
                break;
        }
    }
}
