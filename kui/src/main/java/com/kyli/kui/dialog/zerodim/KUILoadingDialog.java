package com.kyli.kui.dialog.zerodim;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.kyli.kui.R;
import com.kyli.kui.view.loading.KUILoadingRotateView;

public class KUILoadingDialog extends KUiZeroDimDialog {

    public KUILoadingDialog(@NonNull Context context) {
        super(context);
    }

    public KUILoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kui_dialog_framelayout);
    }

    public void addView(View view) {
        FrameLayout frameLayout = findViewById(R.id.container);
        frameLayout.addView(view);
    }


    public static class Builder {
        private Context context;

        private View view;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder addView(View view) {
            this.view = view;
            return this;
        }

        public KUILoadingDialog build() {
            return new KUILoadingDialog(context);
        }
    }

    public static Builder buildRotateLoading(Context context, ViewGroup.LayoutParams layoutParams) {
        KUILoadingRotateView kuiLoadingRotateView = new KUILoadingRotateView(context);
        kuiLoadingRotateView.setLayoutParams(layoutParams != null ? layoutParams : new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Builder builder = new Builder(context);
        builder.addView(kuiLoadingRotateView);
        return builder;
    }
}
