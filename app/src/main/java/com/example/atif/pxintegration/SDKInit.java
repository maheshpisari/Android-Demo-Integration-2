package com.example.atif.pxintegration;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import org.json.simple.JSONObject;
import android.net.Uri;
import android.util.Log;

import com.netcore.android.SMTBundleKeys;
import com.netcore.android.Smartech;
import com.netcore.android.logger.SMTDebugLevel;
import com.netcore.android.smartechpush.SmartPush;
import com.netcore.android.smartechpush.notification.SMTNotificationOptions;
import com.netcore.android.smartechpush.notification.channel.SMTNotificationChannel;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.core.logger.HSLLogLevel;
import io.hansel.hanselsdk.Hansel;
import io.hansel.ujmtracker.HanselEventDataListener;
import io.hansel.ujmtracker.HanselInternalEventsListener;
import io.hansel.ujmtracker.HanselTracker;


public class SDKInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Smartech smartech = Smartech.getInstance(new WeakReference<>(getApplicationContext()));
        HSLLogLevel.all.setEnabled(true);
        HSLLogLevel.mid.setEnabled(true);
        HSLLogLevel.debug.setEnabled(true);
        smartech.initializeSdk(this);

        Hansel.getUser().putAttribute("Country Code", "IN");

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("Game Id", 12);
        HanselTracker.logEvent("Game View Quick Battles", "ctp", payload);
        smartech.trackAppInstallUpdateBySmartech();
        smartech.setDebugLevel(SMTDebugLevel.Level.VERBOSE);
        try {
            SmartPush smartPush = SmartPush.getInstance(new WeakReference<>(getApplicationContext()));
            smartPush.fetchAlreadyGeneratedTokenFromFCM();
        } catch (Exception e) {
            Log.e(TAG, "Fetching FCM token failed.");
        }

        DeeplinkReceiver deeplinkReceiver = new DeeplinkReceiver();
        IntentFilter filter = new IntentFilter("com.smartech.EVENT_PN_INBOX_CLICK");
        getApplicationContext().registerReceiver(deeplinkReceiver, filter);

        HanselInternalEventsListener hanselInternalEventsListener = new HanselInternalEventsListener() {
            @Override
            public void onEvent(String eventName, HashMap dataFromHansel) {
                if ("hansel_nudge_show_event".equals(eventName)) {
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("hansel_nudge_show_event", dataFromHansel);
                }
                if ("hansel_nudge_event".equals(eventName)) {
                    Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("hansel_nudge_event", dataFromHansel);
                }
            }
        };




        SMTNotificationOptions options = new SMTNotificationOptions(this);
        options.setBrandLogo("logo"); //e.g.logo is sample name for brand logo
        options.setLargeIcon("icon_nofification");//e.g.ic_notification is sample name for large icon
        options.setSmallIcon("ic_action_play"); //e.g.ic_action_play is sample name for icon
        options.setSmallIconTransparent("ic_action_play"); //e.g.ic_action_play is sample name for transparent small icon
        options.setTransparentIconBgColor("#FF0000");
        options.setPlaceHolderIcon("ic_notification");//e.g.ic_notification is sample name for placeholder icon
        SmartPush.getInstance(new WeakReference(getApplicationContext())).setNotificationOptions(options);
    }
}
