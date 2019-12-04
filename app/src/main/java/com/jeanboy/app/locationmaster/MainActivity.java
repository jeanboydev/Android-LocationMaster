package com.jeanboy.app.locationmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeanboy.component.location.core.LocationAdapter;
import com.jeanboy.component.location.LocationMaster;
import com.jeanboy.component.location.core.LocationCallback;
import com.jeanboy.component.location.core.Strategy;
import com.jeanboy.component.location.dialog.DialogCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void toRequestLocation(View view) {
        LocationMaster.request(this, Strategy.NETWORK_PRIORITY, new LocationAdapter() {
            @Override
            public FragmentActivity getActivity() {
                return MainActivity.this;
            }

            @Override
            public void onPermissionGranted() {
                Log.e(MainActivity.class.getSimpleName(), "====onPermissionGranted=====");
            }

            @Override
            public void onPermissionDeny() {
                Log.e(MainActivity.class.getSimpleName(), "====onPermissionDeny=====");

            }

            @Override
            public void onSettingsClosed() {
                Log.e(MainActivity.class.getSimpleName(), "====onSettingsClosed=====");

            }

            @Override
            public void onUpdate(Location location, boolean isGPS) {
                Log.e(MainActivity.class.getSimpleName(), "====onUpdate=====");
                Log.e(MainActivity.class.getSimpleName(), location.toString());

            }
        });
    }
}
