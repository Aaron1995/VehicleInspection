package studio.imedia.vehicleinspection.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by eric on 15/10/15.
 */
public class WidgetUtils {

    /**
     * 根据EditText文本内容是否为空判断是否disable按钮
     * 如果有一个EditText文本内容为空，则disable按钮
     *
     *@parambutton
     *@parameditText
     */
    public static void enableButtonByEditText(final Button button,final EditText... editTexts) {
        int length = editTexts.length;// 表示传入的EditText的个数
        final boolean[] flag =new boolean[length];// 表示各个edittext文本内容是否为空，false表示空，true表示非空

        button.setEnabled(false);
        for(int i =0; i < length; i++) {
            final int finalI = i;
            editTexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s,int start,int count,int after) {}

                @Override
                public void onTextChanged(CharSequence s,int start, int before, int count) {
                    if(editTexts[finalI].getText().toString().length() ==0) {
                        flag[finalI] =false;
                    }else{
                        flag[finalI] =true;
                    }

                    if(allNotEmpty(flag)) {
                        button.setEnabled(true);
                    }else{
                        button.setEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}

            });
        }
    }

    /**
     * 判断是不是所有的标记表示的都是非空
     * 如果有一个表示的为空，则返回false
     * 否则，返回true
     *
     *@paramflag
     *@return
     */
    private static boolean allNotEmpty(boolean[] flag) {
        for(int i =0; i < flag.length; i++) {
            if(false== flag[i])
                return false;
        }
        return true;
    }
}
