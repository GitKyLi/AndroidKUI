package com.kyli.kui.dialog.zerodim;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

/**
 * 设置dialog 显示的最小时间 和最小延迟时间   保障UI的流畅性
 * <p>
 * 当我们发起显示{@link #show()}  需要移除等待队列中的关闭方法   防止两次交叉出现闪烁
 * 需要发起一次延迟显示      如果说网络状态良好 那么需要不用开启{@link #show()} 又立刻调用{@link #dismiss()}
 * <p>
 * 当我们发起消失是{@link #dismiss()}  需要移除等待队列中的显示方法   防止两次交叉出现闪烁
 * 需要发起一次延迟关闭    如果说成功发起过一次显示 需要检测是否显示足够的时间{@link #MIN_SHOW_TIME}
 */
public class KUIContentLoadingDialog extends KUILoadingDialog {
    /**
     * 最小显示时间
     */
    private static final int MIN_SHOW_TIME = 500;

    /**
     * 最小延迟时间
     */
    private static final int MIN_DELAY_TIME = 500;


    /**
     * 记录启动的时间
     */
    private long startedTime = -1;

    /**
     * 延迟miss  是否发起   当delay 一段时间后  收到run()  需要被标记为false;
     */
    private boolean postedDismiss = false;
    /**
     * 延迟显示  是否被发起   当delay 一段时间后   收到run()  需要被标记为false
     */
    private boolean postedShow = false;

    /**
     * 表示窗口是否立即被发出指令关闭
     */

    private boolean dismissed = false;


    private final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 延迟处理
     */
    private final Runnable delayedDismiss = () -> {
        postedDismiss = false;
        startedTime = -1;
        super.dismiss();
    };

    private final Runnable delayedShow = () -> {
        postedShow = false;
        if (!dismissed) {
            startedTime = System.currentTimeMillis();
            super.show();
        }

    };

    public KUIContentLoadingDialog(@NonNull Context context) {
        super(context);
    }

    public KUIContentLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public synchronized void show() {
        startedTime = -1;
        dismissed = false;
        handler.removeCallbacks(delayedDismiss);
        postedDismiss = false;
        if (!postedShow) {//是否发起过post
            handler.postDelayed(delayedShow, MIN_DELAY_TIME);
            postedShow = true;//标记为发起了
        }

    }


    @Override
    public synchronized void dismiss() {
        dismissed = true;
        handler.removeCallbacks(delayedShow);
        postedShow = false;
        long diff = System.currentTimeMillis() - startedTime;
        if (diff >= MIN_SHOW_TIME || startedTime == -1) {
            super.dismiss();
        } else {
            if (!postedDismiss) {
                handler.postDelayed(delayedDismiss, MIN_DELAY_TIME);
                postedDismiss = true;
            }
        }

    }

    @Override
    public void cancel() {
        dismiss();
    }

    /**
     * 移除所有的run
     */
    private void removeCallBacks() {
        handler.removeCallbacks(delayedDismiss);
        handler.removeCallbacks(delayedShow);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        removeCallBacks();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallBacks();
    }
}
