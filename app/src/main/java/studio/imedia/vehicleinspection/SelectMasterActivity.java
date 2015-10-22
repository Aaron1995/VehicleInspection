package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.adapters.MyMasterEXLVAdapter;
import studio.imedia.vehicleinspection.bean.Master;

public class SelectMasterActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private ExpandableListView exlvMasters;
    private List<Master> masterList;
    private MyMasterEXLVAdapter myMasterEXLVAdapter;

    private Context mContext = SelectMasterActivity.this;

    private static final String[] names = {"张师傅", "李师傅", "王师傅"};
    private static final int[] serviceAmount = {123, 221, 231};
    private static final int[] prices = {60, 80, 70};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_master);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
        initData(); // 初始化数据
        putIntoAdapter(); // 放入适配器中
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        exlvMasters = (ExpandableListView) findViewById(R.id.exlv_masters);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
    }

    private void initData() {
        if (null == masterList) {
            masterList = new ArrayList<>();
        }
        for (int i = 0; i < names.length; i++) {
            Master master = new Master();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_head);
            master.setAvatar(bitmap);
            master.setName(names[i]);
            master.setStartNum(i+3);
            master.setServiceAmount(serviceAmount[i]);
            master.setPrice(prices[i]);

            masterList.add(master);
        }
    }

    /**
     * 放入适配器中
     */
    private void putIntoAdapter() {
        if (null == myMasterEXLVAdapter) {
            myMasterEXLVAdapter = new MyMasterEXLVAdapter(mContext, masterList);
        }
        exlvMasters.setGroupIndicator(null); // 将控件默认的左边箭头去掉
        exlvMasters.setAdapter(myMasterEXLVAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, SelectTimeWayActivity.class));
                finish();
                break;
        }
    }
}
