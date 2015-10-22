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
import studio.imedia.vehicleinspection.adapters.MyCarFileAdapter;
import studio.imedia.vehicleinspection.bean.CarFile;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFileFragment extends Fragment {

    private ListView lvCarFiles;
    private List<CarFile> carFileList;
    private MyCarFileAdapter myCarFileAdapter;

    public CarFileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_file, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findView(); // 关联控件
        initData(); // 初始化数据
        putIntoAdapter(); // 数据放入adapter中
    }

    /**
     * 关联控件
     */
    private void findView() {
        lvCarFiles = (ListView) getActivity().findViewById(R.id.lv_car_file);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (null == carFileList) {
            carFileList = new ArrayList<>();
        }
        for (int i = 0; i < 7; i++) {
            CarFile carFile = new CarFile();
            carFile.setDate("2015年10月" + (i+1) + "日");
            carFile.setStation("杭州交警支队第三监测站");
            if (i % 2 == 0) {
                carFile.setState(true);
            } else {
                carFile.setState(false);
            }
            carFileList.add(carFile);
        }
    }

    /**
     * 数据放入adapter中
     */
    private void putIntoAdapter() {
        if (null == myCarFileAdapter) {
            myCarFileAdapter = new MyCarFileAdapter(getActivity(), carFileList);
        }

        lvCarFiles.setAdapter(myCarFileAdapter);
    }
}


