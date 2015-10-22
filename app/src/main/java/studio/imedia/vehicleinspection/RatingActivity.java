package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import studio.imedia.vehicleinspection.utils.WidgetUtils;

public class RatingActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private EditText etLeaveMsg;
    private Button btnConfirm;

    private Context mContext = RatingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        findView(); // 关联控件
        initWidget(); // 初始化控件状态
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        etLeaveMsg = (EditText) findViewById(R.id.et_leave_msg);
    }

    /**
     * 初始化控件状态
     */
    private void initWidget() {
        WidgetUtils.enableButtonByEditText(btnConfirm, etLeaveMsg); // 初始化确认按钮使能状态
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
                startActivity(new Intent(mContext, MyOrderActivity.class));
                finish();
                break;
        }
    }
}
