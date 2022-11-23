package com.leviabd.smpshome.fcm;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.legacy.content.WakefulBroadcastReceiver;

import com.google.firebase.messaging.RemoteMessage;

/**
 * FCMBroadCastReceiver receives the Broadcast Intent
 */

public class FCMBroadCastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle dataBundle = intent.getExtras();
        if (dataBundle != null){
            RemoteMessage remoteMessage = new RemoteMessage(dataBundle);
            Log.e("Notification Data 3", remoteMessage.getNotification().getTitle().toString());
            new NotificationHandler(context, remoteMessage);
        }
    }
}

