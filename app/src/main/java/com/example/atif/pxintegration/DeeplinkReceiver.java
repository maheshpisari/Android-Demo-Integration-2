package com.example.atif.pxintegration;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import com.netcore.android.SMTBundleKeys;

public class DeeplinkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundleExtra = intent.getExtras();
        if (bundleExtra != null) {

            if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK)) {
                String deepLinkvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK);
                if (deepLinkvalue != null && !deepLinkvalue.equals("")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkvalue));
                    browserIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(browserIntent);
                }

                if (bundleExtra != null) {
                    if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_DEEPLINK)) {

                    } else {
                        Log.v("Activity", "Does not have deeplink path.")
                    }
                } else {
                    Log.v("Activity", "does not have deeplink path.");
                }

                if (bundleExtra.containsKey(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD)) {
                    String customPayloadvalue = bundleExtra.getString(SMTBundleKeys.SMT_BUNDLE_KEY_CLICK_CUSTOM_PAYLOAD);
                } else {
                    Log.v("Activity", "does not have custom payload.");
                }
            }
        }
    }
}

