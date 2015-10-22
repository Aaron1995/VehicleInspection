package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import studio.imedia.vehicleinspection.adapters.MyCouponAdapter;
import studio.imedia.vehicleinspection.bean.Coupon;

public class MyCouponActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private ListView lvCoupon;
    private List<Coupon> couponList;

    private MyCouponAdapter myCouponAdapter;

    private Context mContext = MyCouponActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);

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
        lvCoupon = (ListView) findViewById(R.id.lv_coupon);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (null == couponList) {
            couponList = new ArrayList<>();
        }
        for (int i = 0; i < 5; i++) {
            Coupon coupon = new Coupon();
            coupon.setPrice(30);
            coupon.setYear(2015);
            coupon.setMonth(10);
            coupon.setDay(23 + i);
            couponList.add(coupon);
        }
    }

    /**
     * 数据放入适配器中
     */
    private void putIntoAdapter() {
        if (null == myCouponAdapter) {
            myCouponAdapter = new MyCouponAdapter(mContext, couponList);
        }
        lvCoupon.setAdapter(myCouponAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }
}
