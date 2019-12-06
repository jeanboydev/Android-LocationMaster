package com.jeanboy.component.location.core;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jeanboy.component.location.LocationMaster;
import com.jeanboy.component.location.lifecycle.LifeCycleManager;
import com.jeanboy.component.location.lifecycle.Tag;
import com.jeanboy.component.location.utils.LogUtil;

/**
 * @author caojianbo
 * @since 2019/12/4 18:51
 */
public class LocationLifeManager extends LifeCycleManager {

    private LocationManager locationManager;
    private ArrayMap<String, LocationWatcher> watcherMap = new ArrayMap<>();
    private final int strategy;
    private final LocationCallback callback;
    private long minTime = 1000;
    private long minMeters = 1;

    public LocationLifeManager(int strategy, LocationCallback callback) {
        this.strategy = strategy;
        this.callback = callback;
    }

    public void setParams(long minTime, long minMeters) {
        this.minTime = minTime;
        this.minMeters = minMeters;
    }

    @Override
    protected String getTag() {
        return Tag.LOCATION_LIFE_MANAGER;
    }

    @Override
    public void onStart() {
        if (context == null) return;
        releaseResource();
        switch (strategy) {
            case Strategy.ONLY_ONCE:
                requestOnce(context, callback);
                break;
            case Strategy.SUSTAINED_LOW:
                requestLow(context, callback);
                break;
            case Strategy.SUSTAINED_HIGH:
                requestHigh(context, callback);
                break;
        }
    }

    @Override
    public void onStop() {
        releaseResource();
    }

    private void releaseResource() {
        if (locationManager == null || watcherMap.isEmpty()) return;
        for (String key : watcherMap.keySet()) {
            LocationWatcher locationWatcher = watcherMap.get(key);
            if (locationWatcher != null) {
                locationManager.removeUpdates(locationWatcher);
            }
        }
        watcherMap.clear();
        locationManager = null;
    }


    private void requestOnce(@NonNull Context context, LocationCallback callback) {
        if (!LocationHelper.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION,
                callback)) {
            return;
        }

        locationManager = getLocationManager(context);
        // 先获取缓存位置
        Location bestLocation = LocationHelper.getBestLocation(locationManager);
        if (bestLocation != null) {
            // 缓存位置可用
            if (callback != null) {
                callback.onLocationChange(bestLocation);
            }
        } else {
            // 缓存不可用，请求一次网络定位
            String provider = LocationManager.NETWORK_PROVIDER;
            if (!LocationHelper.checkProvider(provider, locationManager, callback)) {
                return;
            }

            LocationWatcher locationWatcher = watcherMap.get(Type.NETWORK);
            if (locationWatcher != null) {
                locationManager.removeUpdates(locationWatcher);
            }
            locationWatcher = new LocationWatcher(true, locationManager, callback);
            watcherMap.put(Type.NETWORK, locationWatcher);
            locationManager.requestLocationUpdates(provider, minTime, minMeters, locationWatcher);
        }
    }


    private void requestLow(@NonNull Context context, LocationCallback callback) {
        if (!LocationHelper.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION,
                callback)) {
            return;
        }

        locationManager = getLocationManager(context);
        // 先获取缓存位置
        Location bestLocation = LocationHelper.getBestLocation(locationManager);
        if (bestLocation != null) {
            // 缓存位置可用
            if (callback != null) {
                callback.onLocationChange(bestLocation);
            }
        }

        // 然后持续获取网络位置
        String provider = LocationManager.NETWORK_PROVIDER;
        if (!LocationHelper.checkProvider(provider, locationManager, callback)) {
            // 网络位置开关未打开
            return;
        }

        LocationWatcher locationWatcher = watcherMap.get(Type.NETWORK);
        if (locationWatcher != null) {
            locationManager.removeUpdates(locationWatcher);
        }
        locationWatcher = new LocationWatcher(false, locationManager, callback);
        watcherMap.put(Type.NETWORK, locationWatcher);
        locationManager.requestLocationUpdates(provider, minTime, minMeters, locationWatcher);
    }

    private void requestHigh(@NonNull Context context, LocationCallback callback) {
        if (!LocationHelper.checkPermission(context, Manifest.permission.ACCESS_FINE_LOCATION,
                callback)) {
            return;
        }

        locationManager = getLocationManager(context);
        // 先获取缓存位置
        Location bestLocation = LocationHelper.getBestLocation(locationManager);
        if (bestLocation != null) {
            // 缓存位置可用
            if (callback != null) {
                callback.onLocationChange(bestLocation);
            }
        } else {
            // 缓存不可用，优先获取网络位置
            String networkProvider = LocationManager.NETWORK_PROVIDER;
            if (LocationHelper.checkProvider(networkProvider, locationManager, callback)) {
                // 网络位置开关已打开
                LocationWatcher locationWatcher = watcherMap.get(Type.NETWORK);
                if (locationWatcher != null) {
                    locationManager.removeUpdates(locationWatcher);
                }
                locationWatcher = new LocationWatcher(true, locationManager, callback);
                watcherMap.put(Type.NETWORK, locationWatcher);
                locationManager.requestLocationUpdates(networkProvider, minTime, minMeters,
                        locationWatcher);
            }
        }

        // 最后持续获取 GPS 位置
        String gpsProvider = LocationManager.GPS_PROVIDER;
        if (!LocationHelper.checkProvider(gpsProvider, locationManager, callback)) {
            // GPS 位置开关未打开
            return;
        }

        LocationWatcher locationWatcher = watcherMap.get(Type.GPS);
        if (locationWatcher != null) {
            locationManager.removeUpdates(locationWatcher);
        }
        locationWatcher = new LocationWatcher(false, locationManager, callback);
        watcherMap.put(Type.GPS, locationWatcher);
        locationManager.requestLocationUpdates(gpsProvider, minTime, minMeters, locationWatcher);
    }

    private LocationManager getLocationManager(@NonNull Context context) {
        if (locationManager == null) {
            locationManager = (LocationManager) context.getApplicationContext()
                    .getSystemService(Context.LOCATION_SERVICE);
        }
        return locationManager;
    }
}
