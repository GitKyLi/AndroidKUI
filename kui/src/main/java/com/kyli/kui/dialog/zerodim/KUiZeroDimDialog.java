package com.kyli.kui.dialog.zerodim;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.kyli.kui.R;

/**
 * @author Kyli  2022年11月10日16:10:28
 * 封装全透明dialog  :
 * {@link Window#setDimAmount(float)}   0f:全透明
 * 或者  style中设置 <p><item name="android:backgroundDimAmount">0</item></p>
 * and <p> <item name="android:windowIsFloating">true</item></p>
 * and <p><item name="android:backgroundDimEnabled">true</item></p>
 */
public class KUiZeroDimDialog extends Dialog {

    public KUiZeroDimDialog(@NonNull Context context) {
        super(context, R.style.kuiDialogStyle);
        setDefaultConfig();
    }

    public KUiZeroDimDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setDefaultConfig();
    }


    private void setDefaultConfig() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowConfig();
    }


    /**
     * 重新设置window 的 宽度
     * 并且设备背景调光  为0  默认不是      window.setDimAmount(0f);
     */
    private void setWindowConfig() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(attributes);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setDimAmount(0f);
        }
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
    }
}
