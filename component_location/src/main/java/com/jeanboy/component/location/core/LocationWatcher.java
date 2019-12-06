package com.jeanboy.component.location.core;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @author caojianbo
 * @since 2019/12/4 10:49
 */
public class LocationWatcher implements LocationListener {

    private final boolean isOnlyOnce;
    private final LocationManager locationManager;
    private final LocationCallback callback;

    public LocationWatcher(boolean isOnlyOnce, LocationManager locationManager,
                           LocationCallback callback) {
        this.isOnlyOnce = isOnlyOnce;
        this.locationManager = locationManager;
        this.callback = callback;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (isOnlyOnce && locationManager != null) {
            locationManager.removeUpdates(this);
        }
        if (callback != null) {
            callback.onLocationChange(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
        if (callback != null) {
            callback.onNeedOpenSettings(provider);
        }
    }
}
