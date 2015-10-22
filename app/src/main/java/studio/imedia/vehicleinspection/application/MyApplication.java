package studio.imedia.vehicleinspection.application;

import android.app.Application;
import android.content.SharedPreferences;

import studio.imedia.vehicleinspection.pojo.StaticValues;
import studio.imedia.vehicleinspection.utils.SharedPreferencesUtils;

/**
 * Created by eric on 15/10/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferencesUtils.save(getApplicationContext(), StaticValues.FILE_LOGIN,
                StaticValues.KEY_LOGIN_STATE, false);
    }
}
