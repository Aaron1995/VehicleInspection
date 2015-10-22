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

public class FeedBackActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private EditText etSuggestion;
    private Button btnSend;

    private Context mContext = FeedBackActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        findView(); // 关联控件
        initWidget(); // 初始化控件状态
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        etSuggestion = (EditText) findViewById(R.id.et_suggestion);
        btnSend = (Button) findViewById(R.id.btn_send);
    }

    /**
     * 初始化控件状态
     */
    private void initWidget() {
        WidgetUtils.enableButtonByEditText(btnSend, etSuggestion);
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
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }
}
