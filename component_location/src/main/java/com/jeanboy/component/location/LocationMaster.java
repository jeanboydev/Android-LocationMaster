package com.jeanboy.component.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.ArrayMap;

import com.jeanboy.component.location.core.Constant;
import com.jeanboy.component.location.core.LocationCallback;
import com.jeanboy.component.location.core.LocationWatcher;
import com.jeanboy.component.location.core.Strategy;
import com.jeanboy.component.location.permission.PermissionHelper;

/**
 * @author caojianbo
 * @since 2019/12/3 14:39
 */
public class LocationMaster {

    private static int strategy = Strategy.NETWORK_PRIORITY;

    private static LocationManager locationManager;

    private static ArrayMap<String, LocationWatcher> watcherMap = new ArrayMap<>();


    @SuppressLint("MissingPermission")
    public static void request(Activity activity, int strategy, LocationCallback callback) {
        if (Strategy.GPS_PRIORITY == strategy) {
            requestGPSPriority(activity, 1000, 1, callback);
        } else {
            requestNetworkPriority(activity, 3000, 5, callback);
        }
    }

    @SuppressLint("MissingPermission")
    private static void requestNetworkPriority(Activity activity, long minTime, float minMeters,
                                               LocationCallback callback) {
        String permission = Manifest.permission.ACCESS_COARSE_LOCATION;
        if (!PermissionHelper.isGranted(activity, permission)) {
            if (callback != null) {
                callback.onNeedPermission(permission);
            }
            return;
        }

        locationManager = getLocationManager(activity.getApplicationContext());
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // 网络定位不可用，需要打开设置
            if (callback != null) {
                callback.onNeedOpenSettings(false);
            }
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null && callback != null) { // 缓存可用
            callback.onUpdate(location, false);
        }

        // 获取网络实时位置
        String networkKey = Constant.PREFIX_NETWORK + activity.getClass().getCanonicalName();
        LocationWatcher locationWatcher = getLocationWatcher(networkKey, callback);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime,
                minMeters,
                locationWatcher);
    }

    @SuppressLint("MissingPermission")
    private static void requestGPSPriority(Activity activity, long minTime, float minMeters,
                                           LocationCallback callback) {
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (!PermissionHelper.isGranted(activity, permission)) {
            if (callback != null) {
                callback.onNeedPermission(permission);
            }
            return;
        }

        locationManager = getLocationManager(activity.getApplicationContext());
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS 定位不可用，需要打开设置
            if (callback != null) {
                callback.onNeedOpenSettings(true);
            }
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) { // GPS 缓存可用
            if (callback != null) {
                callback.onUpdate(location, true);
            }
        } else { // GPS 缓存不可用
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) { // 网络定位可用
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) { // 网络缓存可用
                    if (callback != null) {
                        callback.onUpdate(location, false);
                    }
                }
                // 获取网络实时位置
                String networkKey =
                        Constant.PREFIX_NETWORK + activity.getClass().getCanonicalName();
                LocationWatcher locationWatcher = getLocationWatcher(networkKey, callback);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime,
                        minMeters,
                        locationWatcher);
            }
        }
        // 获取 GPS 实时位置
        String gpsKey = Constant.PREFIX_GPS + activity.getClass().getCanonicalName();
        LocationWatcher locationWatcher = getLocationWatcher(gpsKey, callback);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minMeters,
                locationWatcher);
    }

    private static LocationWatcher getLocationWatcher(String key, LocationCallback callback) {
        LocationWatcher locationWatcher = watcherMap.get(key);
        if (locationWatcher != null) {
            locationManager.removeUpdates(locationWatcher);
        }
        locationWatcher = new LocationWatcher(callback);
        watcherMap.put(key, locationWatcher);
        return locationWatcher;
    }

    private static LocationManager getLocationManager(Context context) {
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        return locationManager;
    }
}
