package com.leviabd.smpshome.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.RemoteMessage;

public class CustomBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle dataBundle = intent.getExtras();
        if (dataBundle != null){
            RemoteMessage remoteMessage = new RemoteMessage(dataBundle);
            Log.e("Notification Data 2", remoteMessage.getNotification().getTitle().toString());
            new NotificationHandler(context, remoteMessage);
        }
    }
}

