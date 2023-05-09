package com.example.atif.pxintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.netcore.android.Smartech;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.hansel.actions.configs.HanselConfigs;
import io.hansel.hanselsdk.Hansel;
import io.hansel.ujmtracker.HanselTracker;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToAnotherActivity = findViewById(R.id.next);
        goToAnotherActivity.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), AnotherActivity.class);
            view.getContext().startActivity(intent);
        });

        Hansel.pairTestDevice(getIntent().getDataString());
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("name", "OnePlus 10");
        properties.put("quantity", 2);
        properties.put("price", 35000.00);

        boolean featureEnabled = HanselConfigs.getBoolean("quick_battle_enabled", false);
        String text = String.valueOf(featureEnabled);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();



     //   Smartech.getInstance(new WeakReference<>(getApplicationContext())).trackEvent("Add To Cart", properties);
        HanselTracker.logEvent("Add To Cart", "tbl", properties);
        new DeeplinkReceiver().onReceive(this, getIntent());
    }
}