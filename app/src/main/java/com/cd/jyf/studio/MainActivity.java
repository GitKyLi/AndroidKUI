package com.cd.jyf.studio;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kyli.kui.dialog.zerodim.KUILoadingDialog;
import com.kyli.kui.view.loading.IKUILading;
import com.kyli.kui.view.loading.KUILoadingRotateView;

public class MainActivity extends AppCompatActivity {
    private KUILoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.b1)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        init();


    }

    private void init() {
        dialog = KUILoadingDialog.buildRotateLoading(this,null).build();

    }
}
