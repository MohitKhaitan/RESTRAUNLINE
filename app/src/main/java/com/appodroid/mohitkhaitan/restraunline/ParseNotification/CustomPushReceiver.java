package com.appodroid.mohitkhaitan.restraunline.ParseNotification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.appodroid.mohitkhaitan.restraunline.OtherActivity.NotificationActivity;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MOHIT KHAITAN on 22-03-2016.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {

    private final String TAG = CustomPushReceiver.class.getSimpleName();

    private NotificationUtils notificationUtils;

    private Intent parseIntent;

    public CustomPushReceiver(){ super();}

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if(intent == null)
            return;

        try{
            JSONObject jsonObject = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            Log.e(TAG, "Push received: "+jsonObject);

            parseIntent = intent;

            parsePushJson(context, jsonObject);

        } catch (JSONException e) {
            e.getMessage();
        }
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) { super.onPushDismiss(context, intent);    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    private void parsePushJson(Context context, JSONObject jsonObject){
        try{
            boolean isBackground = jsonObject.getBoolean("is_background");
            JSONObject data = jsonObject.getJSONObject("data");
            String title = data.getString("title");
            String message = data.getString("message");

            if(!isBackground){
                Intent resultIntent = new Intent(context, NotificationActivity.class);
                showNotificationMessage(context, title, message, resultIntent);
            }

        } catch (JSONException e) {
            e.getMessage();
        }
    }

    private void showNotificationMessage(Context context, String title, String message, Intent resultIntent) {
        notificationUtils = new NotificationUtils(context);

        resultIntent.putExtras(parseIntent.getExtras());

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        notificationUtils.showNotificationMessage(title, message, resultIntent);

    }
}
