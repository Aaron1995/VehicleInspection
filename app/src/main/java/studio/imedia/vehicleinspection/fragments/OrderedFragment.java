package studio.imedia.vehicleinspection.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.adapters.MyOrderAdapter;
import studio.imedia.vehicleinspection.bean.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderedFragment extends Fragment {

    private ListView lvOrdered;
    private List<Order> orderList;

    private MyOrderAdapter myOrderAdapter;

    public OrderedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ordered, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findView(); // 关联控件
        initData(); // 初始化数据
        putIntoAdapter(); // 将数据存入adapter中
    }

    /**
     * 关联控件
     */
    private void findView() {
        lvOrdered = (ListView) getActivity().findViewById(R.id.lv_ordered);
    }

    /**
     * 初始化数据
     */
    private void  initData() {
        if (null == orderList) {
            orderList = new ArrayList<>();
        }
        for (int i = 0; i < 6; i++) {
            Order order = new Order();
            order.setStation("杭州交警支队第三监测站");
            order.setYear(2015);
            order.setMonth(10);
            order.setDay(15 + i);
            order.setPrice(160);
            order.setIsOrdered(true);
            if (i % 2 == 0) {
                order.setIsRated(true);
            } else {
                order.setIsRated(false);
            }
            orderList.add(order);
        }
    }

    /**
     * 将数据存入adapter中
     */
    private void putIntoAdapter() {
        if (null == myOrderAdapter) {
            myOrderAdapter = new MyOrderAdapter(getActivity(), orderList);
        }
        lvOrdered.setAdapter(myOrderAdapter);
    }

}
