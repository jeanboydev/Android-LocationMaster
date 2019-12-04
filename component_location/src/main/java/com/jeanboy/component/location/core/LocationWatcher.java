package com.jeanboy.component.location.core;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @author caojianbo
 * @since 2019/12/4 10:49
 */
public class LocationWatcher implements LocationListener {

    private final LocationCallback callback;

    private String currentProvider;

    public LocationWatcher(LocationCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (callback != null) {
            callback.onUpdate(location, LocationManager.GPS_PROVIDER.equals(currentProvider));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        currentProvider = provider;
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (callback != null) {
            callback.onNeedOpenSettings(LocationManager.GPS_PROVIDER.equals(provider));
        }
    }
}
