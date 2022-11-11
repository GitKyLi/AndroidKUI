package com.kyli.kui.view.loading;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.kyli.kui.R;
import com.kyli.kui.utils.log.LogUtils;

/**
 * @author Kyli   2022年11月10日16:13:06
 * 绘制转圈的加载 视图   依赖{@link android.animation.ValueAnimator} 实现
 */
public class KUILoadingRotateView extends View implements IKUILading {

    private ValueAnimator valueAnimator;


    private int wheelNum = 12;


    private int bgColor = Color.BLACK;


    private int wheelColor = Color.WHITE;


    private int wheelWidth = 10;


    private int centerCircleRadius = 20;


    private int padding = 20;

    private Paint paint;

    /**
     * 旋转弧度
     */
    private int rotateRadians = 10;


    private int animatorWheelIndex = 0;


    public KUILoadingRotateView(Context context) {
        super(context);
        init();
    }

    public KUILoadingRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KUILoadingRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(wheelColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(wheelWidth);
        paint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(height, width));
    }

    /**
     * @param padding loadingWheel   padding rect    distance
     *                we  support  system padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setCenterCircleRadius(int centerCircleRadius) {
        this.centerCircleRadius = centerCircleRadius;
    }

    public void setWheelNum(int wheelNum) {
        this.wheelNum = wheelNum;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setWheelColor(int wheelColor) {
        this.wheelColor = wheelColor;
    }

    private void startAnimator() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, wheelNum);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.setDuration(800);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();
                    if (animatedValue != animatorWheelIndex) {
                        animatorWheelIndex = animatedValue;
                        postInvalidate();
                    }
                }
            });
            valueAnimator.start();
        } else {
            if (!valueAnimator.isStarted()) {
                valueAnimator.start();
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bgColor);
        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint);
        drawLoading(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    private void drawLoading(Canvas canvas) {
        rotateRadians = rotateRadians % 360;
        rotateRadians += 10;
        //绘制旋转进度
        canvas.rotate(rotateRadians, getWidth() / 2f, getHeight() / 2f);

        for (int wheelIndex = 0; wheelIndex < wheelNum; wheelIndex++) {

            canvas.drawLine(getWidth() / 2f, padding, getWidth() / 2f, getHeight() / 2f - centerCircleRadius, paint);

            canvas.rotate(360f / wheelNum, getWidth() / 2f, getHeight() / 2f);
        }


    }

    @Override
    public void start() {
        startAnimator();
    }


    @Override
    public void stop() {
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }
}
