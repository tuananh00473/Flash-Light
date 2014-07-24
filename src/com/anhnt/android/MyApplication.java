package com.anhnt.android;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.hardware.Camera;
import android.widget.RemoteViews;

/**
 * User: nguyentuananh
 * Date: 7/24/14
 * Time: 11:30
 */
public class MyApplication extends Application
{

    private static Context context;
    private static Camera camera;
    private static RemoteViews remoteViews;
    private static ComponentName watchWidget;

    public void onCreate()
    {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        MyApplication.camera = Camera.open();
        MyApplication.remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_main);
        MyApplication.watchWidget = new ComponentName(context, FlashLightWidgetProvider.class);
    }

    public static Context getAppContext()
    {
        return MyApplication.context;
    }

    public static Camera getCamera()
    {
        return MyApplication.camera;
    }

    public static RemoteViews getRemoteViews()
    {
        return MyApplication.remoteViews;
    }

    public static ComponentName getWatchWidget()
    {
        return MyApplication.watchWidget;
    }
}
