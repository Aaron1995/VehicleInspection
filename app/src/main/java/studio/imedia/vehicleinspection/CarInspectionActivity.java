package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.adapters.MyCarInspectionAdapter;
import studio.imedia.vehicleinspection.bean.CarInspection;

public class CarInspectionActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private ListView lvCarInspection;

    private List<CarInspection> carInspectionList;
    private MyCarInspectionAdapter myCarInspectionAdapter;
    private Context mContext = CarInspectionActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_inspection);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
        initData(); // 初始化数据
        putIntoAdapter(); // 放入adapter中
    }

    /**
     * 关联控件
     */
    private void findView() {
        lvCarInspection = (ListView) findViewById(R.id.lv_car_inspection);
        layoutBack = (LinearLayout) findViewById(R.id.back);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
    }

    /**
     * 初始哈数据
     */
    private void initData() {
        if (null == carInspectionList) {
            carInspectionList = new ArrayList<>();
        }
        for (int i = 0; i < 8; i++) {
            CarInspection carInspection = new CarInspection();
            carInspection.setInpectionType("时尚套餐");
            carInspection.setSoldCount(239);
            carInspection.setPriceOriginal(399);
            carInspection.setPriceDiscount(199);
            carInspectionList.add(carInspection);
        }
    }

    /**
     * 放入adapter中
     */
    private void putIntoAdapter() {
        if (null == myCarInspectionAdapter) {
            myCarInspectionAdapter = new MyCarInspectionAdapter(mContext, carInspectionList);
        }
        lvCarInspection.setAdapter(myCarInspectionAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }
}
