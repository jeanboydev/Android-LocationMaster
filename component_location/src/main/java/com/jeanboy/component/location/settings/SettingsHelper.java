package com.jeanboy.component.location.settings;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

/**
 * @author caojianbo
 * @since 2019/12/4 15:13
 */
public class SettingsHelper {

    public static void toOpenSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
