package com.jeanboy.component.location.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.jeanboy.component.location.LocationMaster;
import com.jeanboy.component.location.permission.PermissionHelper;

import java.util.List;

/**
 * @author caojianbo
 * @since 2019/12/6 10:39
 */
public class LocationHelper {

    public static boolean checkPermission(@NonNull Context context,
                                          String permission,
                                          LocationCallback callback) {
        if (!PermissionHelper.isGranted(context, permission)) {
            if (callback != null) {
                callback.onNeedPermission(permission);
            }
            return false;
        }
        return true;
    }

    public static boolean checkProvider(@NonNull String provider,
                                        @NonNull LocationManager locationManager,
                                        LocationCallback callback) {
        if (!locationManager.isProviderEnabled(provider)) {
            Log.e(LocationMaster.class.getSimpleName(), provider + "===定位不可用，需要打开设置===");
            // 定位不可用，需要打开设置
            if (callback != null) {
                callback.onNeedOpenSettings(provider);
            }
            return false;
        }
        return true;
    }


    @SuppressLint("MissingPermission")
    public static Location getBestLocation(@NonNull LocationManager locationManager) {
        List<String> allProviders = locationManager.getAllProviders();
        Location bestLocation = null;
        for (String provider : allProviders) {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null) {
                continue;
            }
            if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = location;
            }
        }
        return bestLocation;
    }
}
