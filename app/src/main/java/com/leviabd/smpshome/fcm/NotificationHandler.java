package com.leviabd.smpshome.fcm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.leviabd.smpshome.R;
import com.leviabd.smpshome.activity.MainActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class NotificationHandler {
    Context context;
    RemoteMessage remoteMessage;
    public static int Notification_ID = 500;
    public static String channelId;
    public static final CharSequence channelName = "SMPS_HOME";
    String type="";


    public NotificationHandler(Context context, RemoteMessage remoteMessage) {
        this.context = context;
        this.remoteMessage = remoteMessage;
        channelId = context.getResources().getString(R.string.default_notification_channel_id);
        initOthers();
    }

    private void initOthers(){

        try {
            sendNotification(context, remoteMessage.getNotification());
        } catch (Exception e) {
            Log.e("Notification Error", ""+e.getMessage());
            e.printStackTrace();
        }

    }


    private void sendNotification(Context context, RemoteMessage.Notification notification) {
        Intent intent = null;

        intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        }
        else{
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        }

        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle(notification.getTitle())
                .setAutoCancel(true)
                .setShowWhen(true)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setColor(ContextCompat.getColor(context, R.color.purple_200))
                .setContentText(notification.getBody())
                .setSmallIcon(R.drawable.flat);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification custom_notificaion = notificationBuilder.setChannelId(channelId).build();
            notificationManager.createNotificationChannel(createChannel());
            notificationManager.notify(Notification_ID, custom_notificaion);
        }
        else{
            notificationManager.notify(Notification_ID, notificationBuilder.getNotification());
        }
    }


    public static NotificationChannel createChannel(){
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[] { 1000, 1000, 1000});
        }

        return notificationChannel;
    }


}
