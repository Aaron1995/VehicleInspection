package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SubmitOrderActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;

    private Context mContext = SubmitOrderActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
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
            case R.id.layout_back:
                startActivity(new Intent(mContext, SelectMasterActivity.class));
                finish();
                break;
        }
    }
}
