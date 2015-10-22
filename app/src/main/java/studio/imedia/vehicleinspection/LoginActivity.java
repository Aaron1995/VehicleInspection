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

import studio.imedia.vehicleinspection.pojo.StaticValues;
import studio.imedia.vehicleinspection.utils.SharedPreferencesUtils;
import studio.imedia.vehicleinspection.utils.WidgetUtils;

public class LoginActivity extends Activity implements View.OnClickListener {

    private LinearLayout layoutBack;
    private Button btnLogin;
    private Button btnVerify;
    private EditText etPhoneNum;
    private EditText etPassword;

    private Context mContext = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView(); // 关联控件
        initWidget(); // 初始化控件状态
        initEvent(); // 初始化监听事件
    }

    /**
     * 关联控件
     */
    private void findView() {
        layoutBack = (LinearLayout) findViewById(R.id.back);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnVerify = (Button) findViewById(R.id.btn_verify);
        etPhoneNum = (EditText) findViewById(R.id.et_phone_num);
        etPassword = (EditText) findViewById(R.id.et_password);
    }

    /**
     * 初始化控件状态
     */
    private void initWidget() {
        WidgetUtils.enableButtonByEditText(btnVerify, etPhoneNum); // 初始化短信密码按钮使能状态
        WidgetUtils.enableButtonByEditText(btnLogin, etPhoneNum, etPassword); // 初始化登录按钮使能状态
    }

    /**
     * 初始化监听事件
     */
    private void initEvent() {
        layoutBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
            case R.id.btn_login:
                SharedPreferencesUtils.save(mContext, StaticValues.FILE_LOGIN,
                        StaticValues.KEY_LOGIN_STATE, true);
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }
}
