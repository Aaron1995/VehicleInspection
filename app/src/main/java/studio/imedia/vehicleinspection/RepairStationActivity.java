package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.adapters.MyRepairingStationAdapter;
import studio.imedia.vehicleinspection.bean.RepairingStation;

public class RepairStationActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private ListView lvRepairingStation;

    private List<RepairingStation> repairingStationList;
    private MyRepairingStationAdapter myRepairingStationAdapter;

    private Context mContext = RepairStationActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_station);

        findView(); // 关联控件
        initData(); // 初始化数据
        putIntoAdapter(); // 将数据放入adapter中
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.back);
        lvRepairingStation = (ListView) findViewById(R.id.lv_repairing_station);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (null == repairingStationList) {
            repairingStationList = new ArrayList<>();
        }
        for (int i = 0; i < 7; i++) {
            RepairingStation station = new RepairingStation();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_station);
            station.setStationPic(bitmap);
            station.setStationName("车道汽车服务中心");
            station.setStationAddress("余杭第五大道181号");
            station.setStationDistance(5.6);

            repairingStationList.add(station);
        }
    }

    /**
     * 将数据放入adapter中
     */
    private void putIntoAdapter() {
        if (null == myRepairingStationAdapter) {
            myRepairingStationAdapter = new MyRepairingStationAdapter(mContext, repairingStationList);
        }
        lvRepairingStation.setAdapter(myRepairingStationAdapter);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
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
