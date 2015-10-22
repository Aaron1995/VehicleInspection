package studio.imedia.vehicleinspection;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import studio.imedia.vehicleinspection.fragments.OrderedFragment;
import studio.imedia.vehicleinspection.fragments.OrderingFragment;

public class MyOrderActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private Button btnOrdering;
    private Button btnOrdered;

    private OrderedFragment fragmentOrdered;
    private OrderingFragment fragmentOrdering;


    private int textColorSelected;
    private int textColorNormal;

    private static final int ORDERING = 0;
    private static final int ORDERED = 1;

    private Context mContext = MyOrderActivity.this;

    private static final String TAG = "my_order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        findView(); // 关联控件
        initColor(); // 初始化颜色
        initEvent(); // 初始化监听事件
        setSelected(ORDERING);
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        btnOrdered = (Button) findViewById(R.id.btn_ordered);
        btnOrdering = (Button) findViewById(R.id.btn_ordering);
    }

    /**
     * 初始化颜色
     */
    private void initColor() {
        textColorNormal = getResources().getColor(R.color.colorPrimary);
        textColorSelected = Color.WHITE;
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
        btnOrdered.setOnClickListener(this);
        btnOrdering.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, MainActivity.class));
                break;
            case R.id.btn_ordering:
                Log.d(TAG, "ordering clicked");
                // TODO 切换fragment至orderingFragment
                setSelected(ORDERING);
                btnOrdered.setBackgroundResource(R.drawable.bg_ordered_normal);
                btnOrdered.setTextColor(textColorNormal);
                btnOrdering.setBackgroundResource(R.drawable.bg_ordering_selected);
                btnOrdering.setTextColor(textColorSelected);
                break;
            case R.id.btn_ordered:
                Log.d(TAG, "ordered clicked");
                // TODO 切换fragment至orderedFragment
                setSelected(ORDERED);
                btnOrdered.setBackgroundResource(R.drawable.bg_ordered_selected);
                btnOrdered.setTextColor(textColorSelected);
                btnOrdering.setBackgroundResource(R.drawable.bg_ordering_normal);
                btnOrdering.setTextColor(textColorNormal);
                break;
        }
    }

    private void setSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragments(transaction); // 隐藏fragments
        switch (position) {
            case ORDERING:
                if (null == fragmentOrdering) {
                    fragmentOrdering = new OrderingFragment();
                    transaction.add(R.id.container, fragmentOrdering);
                } else {
                    transaction.show(fragmentOrdering);
                }
                break;
            case ORDERED:
                if (null == fragmentOrdered) {
                    fragmentOrdered = new OrderedFragment();
                    transaction.add(R.id.container, fragmentOrdered);
                } else {
                    transaction.show(fragmentOrdered);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentOrdered != null) {
            transaction.hide(fragmentOrdered);
        }
        if (fragmentOrdering != null) {
            transaction.hide(fragmentOrdering);
        }
    }
}
