package com.anhnt.android;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

/**
 * User: nguyentuananh
 * Date: 7/24/14
 * Time: 11:30
 */
public class FlashLightWidgetProvider extends AppWidgetProvider
{

    private static final String SWITCHES = "com.anhnt.android.switches";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
        Log.d("ANHNT", "receive");
        if (intent.getAction().equals(SWITCHES))
        {
            if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            {
                Toast.makeText(context.getApplicationContext(), "Your device doesn't have camera!", Toast.LENGTH_SHORT).show();
                return;
            }

            Camera cam = MyApplication.getCamera();
            Camera.Parameters params = cam.getParameters();
            if (null == params.getFlashMode() || params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_OFF)){
                Log.d("ANHNT", "camera on");
                MyApplication.getRemoteViews().setInt(R.id.btnSwitches, "setBackgroundResource", R.drawable.power_on);
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(params);
            }else if (params.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH)){
                Log.d("ANHNT", "camera off");
                MyApplication.getRemoteViews().setInt(R.id.btnSwitches, "setBackgroundResource", R.drawable.power_off);
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                cam.setParameters(params);
            }

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(MyApplication.getWatchWidget(), MyApplication.getRemoteViews());
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Intent intent = new Intent(SWITCHES);
        MyApplication.getRemoteViews().setOnClickPendingIntent(R.id.btnSwitches, PendingIntent.getBroadcast(context, 0, intent, 0));

        appWidgetManager.updateAppWidget(MyApplication.getWatchWidget(), MyApplication.getRemoteViews());
    }
}
