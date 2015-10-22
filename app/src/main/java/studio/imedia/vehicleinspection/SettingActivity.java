package studio.imedia.vehicleinspection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import studio.imedia.vehicleinspection.pojo.StaticValues;
import studio.imedia.vehicleinspection.utils.SharedPreferencesUtils;

public class SettingActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private RelativeLayout layoutModifyPwd;
    private Button btnLogout;

    private Context mContext = SettingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findView(); // 关联控件
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.layout_back);
        layoutModifyPwd = (RelativeLayout) findViewById(R.id.layout_modify_pwd);
        btnLogout = (Button) findViewById(R.id.btn_logout);
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
        layoutModifyPwd.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_back:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
            case R.id.layout_modify_pwd:
                startActivity(new Intent(mContext, ModifyActivity.class));
                break;
            case R.id.btn_logout:
                SharedPreferencesUtils.save(mContext, StaticValues.FILE_LOGIN, StaticValues.KEY_LOGIN_STATE, false);
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }
}
