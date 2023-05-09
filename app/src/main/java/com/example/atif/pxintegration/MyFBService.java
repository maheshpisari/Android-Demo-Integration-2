package com.example.atif.pxintegration;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.netcore.android.smartechpush.SmartPush;

import java.lang.ref.WeakReference;

public class MyFBService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        SmartPush.getInstance(new WeakReference<Context>(this)).setDevicePushToken(token);
        //<Your code>
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("SmartechNotification", "Received payload is: " + remoteMessage.getData().toString());
        boolean pushHandledBySmartech = SmartPush.getInstance(new WeakReference<>(getApplicationContext())).handlePushNotification(remoteMessage.getData().toString());
        if (!pushHandledBySmartech){
            // Handle notification from other sources.
        }
    }
}
