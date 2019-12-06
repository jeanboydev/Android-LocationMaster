package com.jeanboy.component.location;

import android.app.Activity;

import com.jeanboy.component.location.core.LocationCallback;
import com.jeanboy.component.location.core.LocationLifeManager;

/**
 * @author caojianbo
 * @since 2019/12/3 14:39
 */
public class LocationMaster {


    public static void request(Activity activity, int strategy, final LocationCallback callback) {
        LocationLifeManager lifeManager = new LocationLifeManager(strategy, callback);
        lifeManager.bind(activity);
    }


}
