package com.kyli.kui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;

import com.kyli.kui.view.KUILoadingRotateView;

public class KUiLoadingDialogUtils {

    public static Dialog create(Context context) {
        KUiZeroDimDialog kUiZeroDimDialog = new KUiZeroDimDialog(context);
        KUILoadingRotateView kuiLoadingRotateView = new KUILoadingRotateView(context);
        kuiLoadingRotateView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        kUiZeroDimDialog.setContentView(kuiLoadingRotateView);
        return kUiZeroDimDialog;
    }
}
