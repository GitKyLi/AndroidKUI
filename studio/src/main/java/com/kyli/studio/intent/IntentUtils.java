package com.kyli.studio.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class IntentUtils {
    public static final String URI_PACKAGE_HOST = "package:";

    public static Intent buildActionIntent(String action) {
        return new Intent(action);
    }

    /**
     * 用于打开指定包的详细设置
     * -----系统设置----
     * 该包必须存在(已经安装)
     *
     * @param activity    活动
     * @param packageName 包名
     */
    public static void goTargetPackageDetail(Activity activity, String packageName) {
        Intent detailIntent = buildActionIntent(Settings.ACTION_APPLICATION_SETTINGS);
        detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        detailIntent.setData(Uri.parse(URI_PACKAGE_HOST + packageName));
        activity.startActivity(detailIntent);
    }
}
