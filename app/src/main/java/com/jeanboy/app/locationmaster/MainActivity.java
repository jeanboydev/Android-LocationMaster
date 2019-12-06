package com.jeanboy.app.locationmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.component.location.core.LocationAdapter;
import com.jeanboy.component.location.LocationMaster;
import com.jeanboy.component.location.core.Strategy;

public class MainActivity extends AppCompatActivity {

    private TextView iv_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_location = findViewById(R.id.iv_location);

    }

    public void toRequestLocationOnce(View view) {
        toGetLocation(Strategy.ONLY_ONCE);
    }

    public void toRequestNetworkLocation(View view) {
        toGetLocation(Strategy.SUSTAINED_LOW);
    }

    public void toRequestGPSLocation(View view) {
        toGetLocation(Strategy.SUSTAINED_HIGH);
    }

    private void toGetLocation(final int strategy) {
        LocationMaster.request(this, strategy, new LocationAdapter() {
            @Override
            public FragmentActivity getActivity() {
                return MainActivity.this;
            }

            @Override
            public void onPermissionGranted() {
                Log.e(MainActivity.class.getSimpleName(), "====onPermissionGranted=====");
                iv_location.setText("已获得位置权限");
                toGetLocation(strategy);
            }

            @Override
            public void onPermissionDeny() {
                Log.e(MainActivity.class.getSimpleName(), "====onPermissionDeny=====");
                iv_location.setText("没有位置权限");
            }

            @Override
            public void onSettingsClosed() {
                Log.e(MainActivity.class.getSimpleName(), "====onSettingsClosed=====");
                iv_location.setText("位置服务已关闭");

            }

            @Override
            public void onLocationChange(Location location) {
                Log.e(MainActivity.class.getSimpleName(), "====onLocationChange=====");
                Log.e(MainActivity.class.getSimpleName(), location.toString());
                iv_location.setText(location.toString());
            }
        });
    }

    public void toJump(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

}
