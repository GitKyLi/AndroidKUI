package com.cd.jyf.studio;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

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
        ImageView imageView = findViewById(R.id.img);


        ColorMatrix  colorMatrix=new ColorMatrix();
        colorMatrix.setSaturation(0f);
        ColorMatrixColorFilter  colorMatrixColorFilter=new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorMatrixColorFilter);

//
//        getPackageManager().getArtManager();



    }

    private void init() {
        dialog = KUILoadingDialog.buildRotateLoading(this, null).build();

    }
}
