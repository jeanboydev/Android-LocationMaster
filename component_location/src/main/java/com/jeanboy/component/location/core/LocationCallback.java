package com.jeanboy.component.location.core;

import android.location.Location;

/**
 * @author caojianbo
 * @since 2019/12/3 16:41
 */
public interface LocationCallback {

    void onNeedPermission(String permission);

    void onNeedOpenSettings(boolean isGPS);

    void onUpdate(Location location, boolean isGPS);
}
