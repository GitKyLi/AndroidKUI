package com.kyli.kui.dialog.zerodim;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.kyli.kui.dialog.zerodim.KUiZeroDimDialog;
import com.kyli.kui.view.loading.KUILoadingRotateView;

public class KuiLoadingDialog extends KUiZeroDimDialog {
    private KUILoadingRotateView kuiLoadingRotateView;

    public KuiLoadingDialog(@NonNull Context context) {
        super(context);
    }

    public KuiLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLoadingView());

    }

    @Override
    public void show() {
        super.show();
        kuiLoadingRotateView.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        kuiLoadingRotateView.stop();
    }


    private View getLoadingView() {
        kuiLoadingRotateView = new KUILoadingRotateView(getContext());

        kuiLoadingRotateView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return kuiLoadingRotateView;
    }


}
